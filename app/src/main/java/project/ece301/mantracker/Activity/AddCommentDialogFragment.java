package project.ece301.mantracker.Activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.widget.EditText;

import project.ece301.mantracker.CareProviderHome.CareProviderHomeActivity;
import project.ece301.mantracker.R;

public class AddCommentDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private EditText editQuantity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        editQuantity = new EditText(getActivity());
        editQuantity.setInputType(InputType.TYPE_CLASS_TEXT);
        editQuantity.setHint(R.string.commentInputHint);

        return new AlertDialog.Builder(getActivity()).setTitle(R.string.add_comment)
                .setMessage(R.string.enter_your_comment)
                .setPositiveButton("ADD", this)
                .setNegativeButton("CANCEL", null)
                .setView(editQuantity).create();

    }

    @Override
    public void onClick(DialogInterface dialog, int position) {
        String comment = editQuantity.getText().toString();
        RecordListActivity callingActivity = (RecordListActivity) getActivity();
        callingActivity.addComment(comment);
        dialog.dismiss();
    }
}