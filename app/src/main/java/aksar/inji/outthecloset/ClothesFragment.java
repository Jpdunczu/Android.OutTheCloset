package aksar.inji.outthecloset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clothing, container, false);

        mClothingTitle = (EditText) v.findViewById(R.id.clothing_title);
        mClothingSize = (EditText) v.findViewById(R.id.clothing_size);
        mClothingCost = (EditText) v.findViewById(R.id.clothing_cost);
        mClothingColor = (EditText) v.findViewById(R.id.clothing_color);
        mClothingBrand = (EditText) v.findViewById(R.id.brand_name);
        mClothingNotes = (EditText) v.findViewById(R.id.clothing_notes);

        mClothingTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClothes.setmName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mClothingSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClothes.setmSize(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mClothingCost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClothes.setmCost(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mClothingColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClothes.setmColor(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mClothingBrand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClothes.setmBrand(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mClothingNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClothes.setmNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }
}
