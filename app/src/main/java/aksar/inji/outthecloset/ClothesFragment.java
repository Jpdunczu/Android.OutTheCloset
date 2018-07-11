package aksar.inji.outthecloset;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.UUID;

/**
 * Created by Josh on 6/13/2018.
 */

public class ClothesFragment extends Fragment {

    private static final String ARG_CLOTHES_ID = "clothes_id";
    private static final String FULL_SIZE_IMAGE = "FullSizeImage";

    private Clothes mClothes;

    private EditText mClothingTitle;
    private EditText mClothingSize;
    private EditText mClothingCost;
    private EditText mClothingColor;
    private EditText mClothingNotes;

    private Button mSaveButton;
    private Button mCancelButton;
    private Button mDIYButton;

    private ImageView mPhotoView;

    private static int position;

    public static ClothesFragment newInstance(UUID clothesId, int pos) {
        position = pos;
        Bundle args = new Bundle();
        args.putSerializable(ARG_CLOTHES_ID, clothesId);

        ClothesFragment fragment = new ClothesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID clothesId = (UUID) getArguments().getSerializable(ARG_CLOTHES_ID);
        mClothes = ClothesLab.get(getActivity()).getClothe(clothesId);

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

        mPhotoView = (ImageView) v.findViewById(R.id.clothes_pic);

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

                mClothes.setmName(mClothingTitle.getText().toString());
                mClothes.setmSize(mClothingSize.getText().toString());
                mClothes.setmCost(mClothingCost.getText().toString());
                mClothes.setmColor(mClothingColor.getText().toString());
                mClothes.setmNotes(mClothingNotes.getText().toString());
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

}
