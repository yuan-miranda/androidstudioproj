package com.example.test;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PopupDialogue {
    public static void showPopup(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.popup_layout, null);

        // get the id's using view
        TextView textView = view.findViewById(R.id.btnState);
        Button btn = view.findViewById(R.id.popup_button);

        btn.setOnClickListener(v -> {
            textView.setText("btn is clicked");
        });

        // set the popup's content
        builder.setTitle(title).setMessage(message)
                .setPositiveButton("OK",
                        (dialog, which) -> {
                            // if "OK" is selected
                        })
                .setNegativeButton("Cancel",
                        (dialog, which) -> {
                            // if "CANCEL" is selected
                        });


        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
