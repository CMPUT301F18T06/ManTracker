package project.ece301.mantracker.CareProviderHome;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.text.InputType;

import project.ece301.mantracker.R;

public class AddPatientDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private EditText editQuantity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        editQuantity = new EditText(getActivity());
        editQuantity.setInputType(InputType.TYPE_CLASS_TEXT);
        editQuantity.setHint(R.string.user);

        return new AlertDialog.Builder(getActivity()).setTitle(R.string.add_patient_title)
                .setMessage(R.string.add_patient_prompt)
                .setPositiveButton("ADD", this)
                .setNegativeButton("CANCEL", null)
                .setView(editQuantity).create();

    }

    @Override
    public void onClick(DialogInterface dialog, int position) {
        String patient = editQuantity.getText().toString();
        CareProviderHomeActivity callingActivity = (CareProviderHomeActivity) getActivity();
        callingActivity.addPatient(patient);
        dialog.dismiss();
    }
}