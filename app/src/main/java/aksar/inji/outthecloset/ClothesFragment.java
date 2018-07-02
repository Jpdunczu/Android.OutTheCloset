package aksar.inji.outthecloset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Josh on 6/13/2018.
 */

public class ClothesFragment extends Fragment {

    private static final String ARG_CLOTHES_ID = "clothes_id";

    private Clothes mClothes;

    private EditText mClothingTitle;
    private EditText mClothingSize;
    private EditText mClothingCost;
    private EditText mClothingColor;
    private EditText mClothingBrand;
    private EditText mClothingNotes;

    private Button mSaveButton;

    public static ClothesFragment newInstance(UUID clothesId) {
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

        ClothesLab.get(getActivity())
                .updateClothes(mClothes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clothing, container, false);

        mClothingTitle = (EditText) v.findViewById(R.id.clothing_title);
        mClothingSize = (EditText) v.findViewById(R.id.clothing_size);
        mClothingCost = (EditText) v.findViewById(R.id.clothing_cost);
        mClothingColor = (EditText) v.findViewById(R.id.clothing_color);
        mClothingNotes = (EditText) v.findViewById(R.id.clothing_notes);
        mSaveButton = (Button) v.findViewById(R.id.save_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClothes.setmName(mClothingTitle.getText().toString());
                mClothes.setmSize(mClothingSize.getText().toString());
                mClothes.setmCost(mClothingCost.getText().toString());
                mClothes.setmColor(mClothingColor.getText().toString());
                mClothes.setmNotes(mClothingNotes.getText().toString());
            }
        });

        return v;
    }

}
