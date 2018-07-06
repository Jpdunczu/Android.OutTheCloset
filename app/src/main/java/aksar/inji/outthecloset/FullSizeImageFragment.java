package aksar.inji.outthecloset;

import android.app.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;


public class FullSizeImageFragment extends DialogFragment {

    private static final String CLOTHES_ID = "clothesId";

    private static Clothes mClothes;

    private ImageView mPhotoView;
    private File mPhotoFile;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.full_size_image_dialog, null);

        mPhotoView = (ImageView) view.findViewById(R.id.full_size_pic);

        mPhotoFile = ClothesLab.get(getActivity()).getPhotoFile(mClothes);
        Uri mPhotoUri = FileProvider.getUriForFile(getActivity(), "com.aksar.inji.outthecloset.fileprovider", mPhotoFile);

        mPhotoView.setImageURI(mPhotoUri);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //getActivity().finish();
                    }
                })
                .create();
    }

    public static FullSizeImageFragment newInstance(Clothes clothes) {
        mClothes = clothes;
        FullSizeImageFragment fragment = new FullSizeImageFragment();
        return fragment;
    }
}
