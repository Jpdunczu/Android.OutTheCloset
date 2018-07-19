package aksar.inji.outthecloset;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class NoBrandsFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.no_brands_dialog)
                .setMessage(R.string.no_clothes_diag_message)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }
}
