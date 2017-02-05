package com.example.welcome.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DataShow extends AppCompatActivity {
    SharedPreferences sp;
    ImageView star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        sp=getSharedPreferences("CRDR",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        EditText e1=(EditText)findViewById(R.id.editText);
        EditText e2=(EditText)findViewById(R.id.editText2);
        EditText e3=(EditText)findViewById(R.id.editText3);
        e1.setText(String.valueOf(sp.getInt("BEFORE",20)));
        if(sp.getString("CHECK"," ").matches("Cr")){
            e2.setText("+"+String.valueOf(sp.getInt("CREDIT",100)));
        }
        else{
            e2.setText("-"+String.valueOf(sp.getInt("DEBIT",200)));
        }
        e3.setText(String.valueOf(sp.getInt("TOTAL",94)));
        //star=(ImageView)findViewById(R.id.starID);
        Button b=(Button)findViewById(R.id.mainButtonID);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DataShow.this,CreditDebitActivity.class);
                startActivity(i);
            }
        });

    }
}
