package org.linphone.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import org.linphone.R;

public class AccessSettingsDialogFragment extends DialogFragment {
    public AccessSettingsDialogListener accessSettingsDialogListener;
    private EditText editTextPassword;

    public interface AccessSettingsDialogListener {
        void setPassword(String password);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            accessSettingsDialogListener = (AccessSettingsDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement AccessSettingsDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_access_settings, null);
        builder.setView(view)
                .setPositiveButton(R.string.access_settings_dialog_button_description, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String password = editTextPassword.getText().toString();
                            accessSettingsDialogListener.setPassword(password);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AccessSettingsDialogFragment.this.getDialog().cancel();
                    }
                });

        editTextPassword = (EditText) view.findViewById(R.id.editText_password);
        return builder.create();
    }
}
