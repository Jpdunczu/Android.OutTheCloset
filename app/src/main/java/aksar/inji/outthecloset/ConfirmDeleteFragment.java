package aksar.inji.outthecloset;



import android.app.Activity;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
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
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
