package com.example.slaveimpact;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroSelectPopup {
    static void startPopup(Context context, Object[][] chData, int index, Runnable callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.hero_select_popup, null);

        TextView chName = view.findViewById(R.id.hsChName);
        ImageView chAvatar = view.findViewById(R.id.hsChAvatar);
        TextView chLv = view.findViewById(R.id.hsChLv);
        Button upgradeBtn = view.findViewById(R.id.hsUpgradeBtn);
        Button sellBtn = view.findViewById(R.id.hsSellBtn);

        chName.setText(String.valueOf(chData[index][0]));
        chAvatar.setImageResource(view.getResources().getIdentifier(String.valueOf(chData[index][4]), "drawable", view.getContext().getPackageName()));
        chLv.setText(String.valueOf(chData[index][1]));

        AlertDialog dialog = builder.setView(view).create();

        upgradeBtn.setOnClickListener(v -> {
            UpgradePopup.startPopup(context, chData[index], index, () -> {
                chLv.setText(String.valueOf(chData[index][1]));
                callback.run();
            });
        });

        sellBtn.setOnClickListener(v -> {
            SellPopup.startPopup(context, chData[index], index, () -> {
                callback.run();
                dialog.dismiss();
            });
        });

        dialog.setOnDismissListener(dialogInterface -> {
            callback.run();
        });

        dialog.show();
    }
}
