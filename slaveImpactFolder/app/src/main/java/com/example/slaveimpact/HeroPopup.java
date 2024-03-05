package com.example.slaveimpact;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroPopup {
    public static void showHeroPopup(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.upgrade_popup, null);

        TextView name = view.findViewById(R.id.upsChName);
        ImageView characterImg = view.findViewById(R.id.upsChImg);
        Button upsUpgradeBtn = view.findViewById(R.id.upsUpgradeBtn);
        TextView cost = view.findViewById(R.id.upsUpgradeCost);

        name.setText(String.valueOf(GlobalValue.name));

        builder.setTitle("Upgrade hero").setMessage("This would upgrade " + GlobalValue.name)
                .setPositiveButton("Continue", (dialog, which) -> {

                })
                .setNegativeButton("Cancel", (dialog, which) -> {

                });

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
