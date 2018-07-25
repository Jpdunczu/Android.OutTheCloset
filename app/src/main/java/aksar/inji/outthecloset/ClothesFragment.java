package aksar.inji.outthecloset;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Josh on 6/13/2018.
 */

public class ClothesFragment extends Fragment {

    private static final String ARG_CLOTHES_ID = "clothes_id";
    private static final String ARG_CLOTHES_POS = "clothes_pos";
    private static final String FULL_SIZE_IMAGE = "FullSizeImage";
    private static final int REQUEST_PHOTO = 2;

    private Clothes mClothes;

    private EditText mClothingTitle;
    private EditText mClothingSize;
    private EditText mClothingCost;
    private EditText mClothingColor;
    private EditText mClothingNotes;

    private Button mSaveButton;
    private Button mCancelButton;
    private Button mDIYButton;
    private ImageButton mPhotoButton;

    private ImageView mPhotoView;
    private File mPhotoFile;

    public static ClothesFragment newInstance(UUID clothesId, int pos) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CLOTHES_ID, clothesId);
        args.putSerializable(ARG_CLOTHES_POS, pos);
        ClothesFragment fragment = new ClothesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID clothesId = (UUID) getArguments().getSerializable(ARG_CLOTHES_ID);
        int position = (int) getArguments().getSerializable(ARG_CLOTHES_POS);
        mClothes = ClothesLab.get(getActivity()).getClothe(clothesId);
        mPhotoFile = ClothesLab.get(getActivity()).getPhotoFile(mClothes);
    }

    @Override
    public void onPause() {
        super.onPause();
        //ClothesLab.get(getActivity()).updateClothes(mClothes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clothing, container, false);

        PackageManager packageManager = getActivity().getPackageManager();

        // Setting up PhotoButton
        //
        mPhotoButton = (ImageButton) v.findViewById(R.id.clothes_camnera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = FileProvider.getUriForFile(getActivity(), "com.aksar.inji.outthecloset.fileprovider", mPhotoFile);

                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                List<ResolveInfo> cameraActivities = getActivity().getPackageManager().queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo activity : cameraActivities) {
                    getActivity().grantUriPermission(activity.activityInfo.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.clothes_pic);
        updatePhotoView();

        mClothingTitle = (EditText) v.findViewById(R.id.clothing_title);
        mClothingTitle.setText(mClothes.getmName());

        mClothingSize = (EditText) v.findViewById(R.id.clothing_size);
        mClothingSize.setText(mClothes.getmSize());

        mClothingCost = (EditText) v.findViewById(R.id.clothing_cost);
        mClothingCost.setText(mClothes.getmCost());

        mClothingColor = (EditText) v.findViewById(R.id.clothing_color);
        mClothingColor.setText(mClothes.getmColor());

        mClothingNotes = (EditText) v.findViewById(R.id.clothing_notes);
        mClothingNotes.setText(mClothes.getmNotes());

        mSaveButton = (Button) v.findViewById(R.id.save_button);
        mCancelButton = (Button) v.findViewById(R.id.cancel_button);
        mDIYButton = (Button) v.findViewById(R.id.diy_button);
        if (mClothes.getmDIY()) {
            mDIYButton.setVisibility(View.VISIBLE);
        }

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Brands brand = BrandLab.get(getActivity()).getBrand(mClothes.getmBrandId());
                mClothes.setmName(mClothingTitle.getText().toString());
                mClothes.setmSize(mClothingSize.getText().toString());
                String mCost = mClothingCost.getText().toString();
                if (!mCost.equals("") && !mCost.equals(mClothes.getmCost())) {
                    BigDecimal cost = new BigDecimal(mCost);
                    BigDecimal clothesCost = new BigDecimal(mClothes.getmCost());
                    BigDecimal difference = cost.subtract(clothesCost);
                    BigDecimal worth = new BigDecimal(brand.getmBrandWorth());
                    BigDecimal result = worth.add(difference);
                    mClothes.setmCostDec(cost.doubleValue());
                    mClothes.setmCost(cost.toString());
                    brand.setmBrandWorth(result.toString());
                }
                mClothes.setmColor(mClothingColor.getText().toString());
                mClothes.setmNotes(mClothingNotes.getText().toString());

                BrandLab.get(getActivity()).updateBrand(brand);
                ClothesLab.get(getActivity()).updateClothes(mClothes);
                getActivity().finish();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mDIYButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getDIYReport());
                startActivity(intent);
            }
        });

        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FullSizeImageFragment dialog = FullSizeImageFragment.newInstance(mClothes);
                dialog.show(fragmentManager, FULL_SIZE_IMAGE);
            }
        });

        return v;
    }

    private String getDIYReport() {
        String brandName = mClothes.getBrandName();
        String clothesName = mClothes.getmName();

        return brandName + " " + clothesName + " DIY fixes and modifications";
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PHOTO) {
            Uri uri = FileProvider.getUriForFile(getActivity(), "com.aksar.inji.outthecloset.fileprovider", mPhotoFile);

            getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updatePhotoView();
        }
    }
}
