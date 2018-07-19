package aksar.inji.outthecloset;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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

public class newClothesFragment extends Fragment {

    private static final String ARG_BRAND_ID = "brandId";
    private static final int REQUEST_PHOTO = 2;

    private Clothes mClothes;

    private EditText mClothingTitle;
    private EditText mClothingSize;
    private EditText mClothingCost;
    private EditText mClothingColor;
    private EditText mClothingNotes;

    private Button mSaveButton;
    private Button mCancelButton;

    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private File mPhotoFile;
    private UUID mBrandId;

    public static newClothesFragment newClothesInstance(UUID brandId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BRAND_ID, brandId);

        newClothesFragment fragment = new newClothesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBrandId = (UUID) getArguments().getSerializable(ARG_BRAND_ID);
        mClothes = new Clothes();
        mPhotoFile = ClothesLab.get(getActivity()).getPhotoFile(mClothes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clothing, container, false);

        PackageManager packageManager = getActivity().getPackageManager();

        mClothingTitle = (EditText) view.findViewById(R.id.clothing_title);
        mClothingSize = (EditText) view.findViewById(R.id.clothing_size);
        mClothingCost = (EditText) view.findViewById(R.id.clothing_cost);
        mClothingColor = (EditText) view.findViewById(R.id.clothing_color);
        mClothingNotes = (EditText) view.findViewById(R.id.clothing_notes);

        mSaveButton = (Button) view.findViewById(R.id.save_button);
        mCancelButton = (Button) view.findViewById(R.id.cancel_button);

        // Setting up PhotoButton
        //
        mPhotoButton = (ImageButton) view.findViewById(R.id.clothes_camnera);
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

        mPhotoView = (ImageView) view.findViewById(R.id.clothes_pic);
        updatePhotoView();

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClothes.setmName(mClothingTitle.getText().toString());
                mClothes.setmSize(mClothingSize.getText().toString());
                BigDecimal cost = new BigDecimal(mClothingCost.getText().toString());
                mClothes.setmCostDec(cost.doubleValue());
                mClothes.setmCost(cost.toString());
                mClothes.setmColor(mClothingColor.getText().toString());
                mClothes.setmNotes(mClothingNotes.getText().toString());
                mClothes.setmBrandId(mBrandId);
                Brands brand = BrandLab.get(getActivity()).getBrand(mBrandId);
                brand.setmBrandWorthBigDec(cost);
                int count = brand.getmBrandCount();
                brand.setmBrandCount(count+1);
                BrandLab.get(getActivity()).updateBrand(brand);
                ClothesLab.get(getActivity()).addClothes(mClothes);
                getActivity().finish();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

    // Load the Bitmap into the ImageView
    //
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
        if (resultCode == REQUEST_PHOTO) {
            Uri uri = FileProvider.getUriForFile(getActivity(), "com.aksar.inji.outthecloset.fileprovider", mPhotoFile);

            getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updatePhotoView();
        }
    }
}
