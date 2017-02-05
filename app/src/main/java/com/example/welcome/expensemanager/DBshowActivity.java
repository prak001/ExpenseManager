package com.example.welcome.expensemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class DBshowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbshow);
        TextView tv=(TextView)findViewById(R.id.DBID);
        SQLiteDatabase dbl=openOrCreateDatabase("expDataB",MODE_PRIVATE,null);
        dbl.execSQL("CREATE TABLE IF NOT EXISTS expenseTable(id INTEGER PRIMARY KEY,date VARCHAR,fortDetail VARCHAR,crORdr VARCHAR,recent INTEGER,preAmt INTEGER,enterDetail VARCHAR, newAmt INTEGER );");
        EditText et1=(EditText)findViewById(R.id.editText4);
        Cursor cursor= dbl.rawQuery("select * from expenseTable",null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
              String data=cursor.getString(cursor.getColumnIndex("id"))+" "+cursor.getString(cursor.getColumnIndex("date"))+" "+cursor.getString(cursor.getColumnIndex("crORdr"))+" "+cursor.getString(cursor.getColumnIndex("preAmt"))+" "+cursor.getString(cursor.getColumnIndex("recent"))+" "+cursor.getString(cursor.getColumnIndex("fortDetail"))+" "+cursor.getString(cursor.getColumnIndex("enterDetail"))+" "+cursor.getString(cursor.getColumnIndex("newAmt"))+"\n";
              tv.append(data);
        }
        //dbl.delete(expenseTable,null,null);

    }
}
