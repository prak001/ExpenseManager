package com.example.welcome.expensemanager;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by welcome on 23-07-2016.
 */
public class LViewActivity extends Activity {
    //SQLiteHelper SQLITEHELPER;
    public  static  final  String TAG="ACTI";
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter1 ;

    ArrayList<String> ID = new ArrayList<String>();
    ArrayList<String> DATEarray = new ArrayList<String>();
    ArrayList<String> CRDRarray = new ArrayList<String>();
    ArrayList<String> RECENTarray = new ArrayList<String>();
    ArrayList<String> TRANSarray = new ArrayList<String>();
    ArrayList<String> NEWarray = new ArrayList<String>();
    ArrayList<String> FROTarray = new ArrayList<String>();
    ArrayList<String> FULLDarray = new ArrayList<String>();
    ListView LISTVIEW;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        //SQLITEHELPER = new SQLiteHelper(this);
    }
    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }
    private void ShowSQLiteDBdata() {

        //SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();
        SQLiteDatabase dbl=openOrCreateDatabase("expDataB",MODE_PRIVATE,null);
        //cursor = SQLITEDATABASE.rawQuery("SELECT * FROM demoTable", null);
        dbl.execSQL("CREATE TABLE IF NOT EXISTS expenseTable(id INTEGER PRIMARY KEY,date VARCHAR,fortDetail VARCHAR,crORdr VARCHAR,recent INTEGER,preAmt INTEGER,enterDetail VARCHAR, newAmt INTEGER );");
        Cursor cursor= dbl.rawQuery("select * from expenseTable",null);
        ID.clear();
        DATEarray.clear();
        CRDRarray.clear();
        RECENTarray.clear();
        TRANSarray.clear();
        NEWarray.clear();
        FROTarray.clear();
        FULLDarray.clear();
        if(cursor.getCount()==0)
        {
            Toast.makeText(LViewActivity.this,"No Result To Show",Toast.LENGTH_LONG).show();
            return;
        }
        if (cursor.moveToFirst()) {
            do {
                //ID.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)));
                ID.add(cursor.getString(cursor.getColumnIndex("id")));
                DATEarray.add(cursor.getString(cursor.getColumnIndex("date")));
                CRDRarray.add(cursor.getString(cursor.getColumnIndex("crORdr")));
                RECENTarray.add(cursor.getString(cursor.getColumnIndex("preAmt")));
                TRANSarray.add(cursor.getString(cursor.getColumnIndex("recent")));
                NEWarray.add(cursor.getString(cursor.getColumnIndex("newAmt")));
                FROTarray.add(cursor.getString(cursor.getColumnIndex("fortDetail")));
                FULLDarray.add(cursor.getString(cursor.getColumnIndex("enterDetail")));

            } while (cursor.moveToNext());
        }
        ListAdapter1 = new SQLiteListAdapter(LViewActivity.this ,ID,DATEarray, CRDRarray, RECENTarray,TRANSarray,NEWarray,FROTarray,FULLDarray);
        LISTVIEW.setAdapter(ListAdapter1);

        cursor.close();
    }
}
