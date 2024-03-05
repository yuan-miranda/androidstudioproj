package com.example.slaveimpact;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class SellPopup {
    static void startPopup(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sell_popup, null);

        builder.setTitle("Sell Hero").setMessage("This would sell " + GlobalValues.name)
                .setPositiveButton("Continue", (dialog, which) -> {

                })
                .setNegativeButton("Cancel", (dialog, which) -> {

                });

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
