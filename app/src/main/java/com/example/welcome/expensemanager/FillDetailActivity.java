package com.example.welcome.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class FillDetailActivity extends AppCompatActivity {
    String todayDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_detail);
        TextView tv1=(TextView)findViewById(R.id.textCDID);
        TextView tvdate=(TextView)findViewById(R.id.dateID);
        SharedPreferences sp=getSharedPreferences("CRDR",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        TextView frto=(TextView)findViewById(R.id.fromToID);
        TextView fullentry=(TextView)findViewById(R.id.textView6);
        if(sp.getString("CHECK"," ").matches("Cr")){
            tv1.setText("CREDIT DETAILS");
            frto.setText("RECEIVED FROM");
            fullentry.setText("Enter Detail");
        }
        else{
            tv1.setText("DEBIT DETAILS");
            frto.setText("PAID TO");
            fullentry.setText("Enter Detail");
        }
        TextView fOK=(TextView)findViewById(R.id.finalOK);
        fOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todayDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                SharedPreferences sp=getSharedPreferences("CRDR",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                SQLiteDatabase db=openOrCreateDatabase("expDataB",MODE_PRIVATE,null);
                db.execSQL("CREATE TABLE IF NOT EXISTS expenseTable(id INTEGER PRIMARY KEY,date VARCHAR,crORdr VARCHAR,recent INTEGER,preAmt INTEGER,fortDetail VARCHAR,enterDetail VARCHAR, newAmt INTEGER );");
                EditText et1=(EditText)findViewById(R.id.editText4);
                EditText et2=(EditText)findViewById(R.id.editText5);
                String et1str=et1.getText().toString();
                String et2str=et2.getText().toString();
                if(sp.getString("CHECK"," ").matches("Cr")){
                    db.execSQL("INSERT INTO expenseTable(id,date,crORdr,preAmt,recent,fortDetail,enterDetail,newAmt) VALUES('"+sp.getInt("ID",23)+"','"+todayDate+"','"+sp.getString("CHECK"," ")+"','"+sp.getInt("BEFORE",55)+"','"+sp.getInt("CREDIT",66)+"','"+et1str+"','"+et2str+"','"+sp.getInt("TOTAL",66)+"');");
                }
                else{
                    db.execSQL("INSERT INTO expenseTable(id,date,crORdr,preAmt,recent,fortDetail,enterDetail,newAmt) VALUES('"+sp.getInt("ID",23)+"','"+todayDate+"','"+sp.getString("CHECK"," ")+"','"+sp.getInt("BEFORE",55)+"','"+sp.getInt("DEBIT",66)+"','"+et1str+"','"+et2str+"','"+sp.getInt("TOTAL",66)+"');");
                }
                Intent i=new Intent(FillDetailActivity.this,DataShow.class);
                startActivity(i);
            }
        });

    }
}
