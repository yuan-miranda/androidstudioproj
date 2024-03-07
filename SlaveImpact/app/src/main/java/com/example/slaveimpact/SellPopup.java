package com.example.slaveimpact;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class SellPopup {
    static void startPopup(Context context, Object[] chData, int index, Runnable callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sell_popup, null);

        builder.setTitle("Sell Hero").setMessage("This would sell " + chData[0])
                .setPositiveButton("Continue", (dialog, which) -> {
                    chData[0] = "";
                    chData[1] = 0;
                    chData[2] = 0.0;
                    chData[3] = 0;
                    chData[4] = "blank";
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
