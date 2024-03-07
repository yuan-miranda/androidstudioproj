package com.example.slaveimpact;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class UpgradePopup {
    static void startPopup(Context context, Object[] chData, int index, Runnable callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.upgrade_popup, null);

        builder.setTitle("Upgrade Hero").setMessage("This would upgrade " + chData[0] + " to " + ((int) chData[1] + 1))
                .setPositiveButton("Continue", (dialog, which) -> {
                    chData[1] = (int) chData[1] + 1;
                    GlobalValues.getMainScreenInstance().updateCharacterData(chData, index);
                    callback.run();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {

                });

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
