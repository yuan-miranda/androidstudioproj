package com.example.slaveimpact;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroSelectPopup {
    static void startPopup(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.hero_select_popup, null);

        TextView chName = view.findViewById(R.id.hsChName);
        ImageView chAvatar = view.findViewById(R.id.hsChAvatar);
        TextView chLv = view.findViewById(R.id.hsChLv);
        Button upgradeBtn = view.findViewById(R.id.hsUpgradeBtn);
        Button sellBtn = view.findViewById(R.id.hsSellBtn);

        chName.setText(String.valueOf(GlobalValues.name));
        chAvatar.setImageResource(view.getResources().getIdentifier(String.valueOf(GlobalValues.avatar), "drawable", view.getContext().getPackageName()));
        chLv.setText(String.valueOf(GlobalValues.level));

        upgradeBtn.setOnClickListener(v -> {
            UpgradePopup.startPopup(context);
        });

        sellBtn.setOnClickListener(v -> {
            SellPopup.startPopup(context);
        });

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
