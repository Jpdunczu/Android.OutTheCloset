package aksar.inji.outthecloset;



import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.UUID;

public class ConfirmDeleteFragment extends DialogFragment {

    public static final String DELETE_CONFIRM =
            "com.aksar.inji.outthecloset.delete";

    private static final String ARG_DELETE = "delete";

    public static ConfirmDeleteFragment newInstance(UUID clothesId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DELETE, clothesId);

        ConfirmDeleteFragment fragment = new ConfirmDeleteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.confirm_delete_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

}
