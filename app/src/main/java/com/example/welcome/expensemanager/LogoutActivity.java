package com.example.welcome.expensemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by welcome on 24-07-2016.
 */
public class LogoutActivity extends AppCompatActivity {
    String Tag="hu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder b=new AlertDialog.Builder(LogoutActivity.this);
        b.setIcon(R.mipmap.ic_logoutcross);
        b.setTitle("LOGOUT");
        b.setMessage("ALL previous data will be deleted!!!"+"\n"+"THIS ACTION CANNOT BE UNDONE");
        b.setNegativeButton("CONFIRM",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                SharedPreferences sLogin=getSharedPreferences("LOGIN",MODE_PRIVATE);
                SharedPreferences.Editor editor1=sLogin.edit();
                editor1.putString("NAME","");
                editor1.putString("PASSWORD","");
                editor1.putString("MOBILE","");
                editor1.putInt("statePass",0);
                editor1.commit();
                SharedPreferences sp=getSharedPreferences("CRDR",MODE_PRIVATE);
                SharedPreferences.Editor editor2=sp.edit();
                editor2.putInt("TOTAL",0);
                editor2.putInt("BEFORE",0);
                editor2.putInt("ID",0);
                editor2.putString("CHECK"," ");
                editor2.putInt("CREDIT",0);
                editor2.putInt("DEBIT",0);
                editor2.commit();
                SQLiteDatabase dbl=openOrCreateDatabase("expDataB",MODE_PRIVATE,null);
                dbl.execSQL("DROP TABLE IF EXISTS expenseTable");
                Intent i=new Intent(LogoutActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        b.setPositiveButton("CANCEL",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Intent i=new Intent(LogoutActivity.this,CreditDebitActivity.class);
                startActivity(i);
            }
        });
        AlertDialog d=b.create();
        d.show();
    }
}
