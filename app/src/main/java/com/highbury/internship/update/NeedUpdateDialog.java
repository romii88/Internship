package com.highbury.internship.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.highbury.internship.R;
import com.highbury.internship.util.SafeDialogOperator;

/**
 * Created by han on 2016/12/29.
 */

public class NeedUpdateDialog {
    public Dialog create(final Update update, final Activity activity){
        if(activity==null||activity.isFinishing()){
            return null;
        }
        String updateContent = activity.getText(R.string.update_version_name)
                + ": " + update.getVersionName() + "\n\n\n"
                + update.getUpdateContent();
        AlertDialog.Builder builder =  new AlertDialog.Builder(activity)
                .setMessage(updateContent)
                .setTitle(R.string.update_title)
                .setPositiveButton(R.string.update_immediate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(activity,UpdateService.class);
                        activity.startService(intent);
                        SafeDialogOperator.safeDismissDialog((Dialog) dialog);
                    }
                });

        if (!update.isForced()) {
            builder.setNegativeButton(R.string.update_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SafeDialogOperator.safeDismissDialog((Dialog) dialog);
                }
            });
        }
        builder.setCancelable(false);
        return builder.create();
    }
}
