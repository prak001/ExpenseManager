package com.example.welcome.expensemanager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by welcome on 23-07-2016.
 */
public class AboutDialogActivity extends AppCompatActivity {
    String Tag="hu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //Log.i("TAG","hola");
        final Dialog dialog = new Dialog(AboutDialogActivity.this);
        dialog.setContentView(R.layout.activity_alboutdialog);
        dialog.setTitle("EXPENSE MANAGER");
        TextView abt = (TextView) dialog.findViewById(R.id.aboutID);
        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AboutDialogActivity.this,CreditDebitActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
