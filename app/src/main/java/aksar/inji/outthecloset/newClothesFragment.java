package aksar.inji.outthecloset;


import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

public class newClothesFragment extends Fragment {

    private Clothes mClothes;

    private EditText mClothingTitle;
    private EditText mClothingSize;
    private EditText mClothingCost;
    private EditText mClothingColor;
    private EditText mClothingNotes;

    private Button mSaveButton;
    private Button mCancelButton;

    public static newClothesFragment newClothesInstance() {
        return new newClothesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mClothes = new Clothes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clothing, container, false);

        mClothingTitle = (EditText) view.findViewById(R.id.clothing_title);
        mClothingSize = (EditText) view.findViewById(R.id.clothing_size);
        mClothingCost = (EditText) view.findViewById(R.id.clothing_cost);
        mClothingColor = (EditText) view.findViewById(R.id.clothing_color);
        mClothingNotes = (EditText) view.findViewById(R.id.clothing_notes);

        mSaveButton = (Button) view.findViewById(R.id.save_button);
        mCancelButton = (Button) view.findViewById(R.id.cancel_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClothes.setmName(mClothingTitle.getText().toString());
                mClothes.setmSize(mClothingSize.getText().toString());
                mClothes.setmCost(mClothingCost.getText().toString());
                mClothes.setmColor(mClothingColor.getText().toString());
                mClothes.setmNotes(mClothingNotes.getText().toString());
                mClothes.setmBrandId(UUID.randomUUID());
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
}
