package com.example.welcome.expensemanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CreditDebitActivity extends drawerMainActivity {
    Button b;
    String todayDate;
    SharedPreferences sp;
    String display;
    protected DrawerLayout mDrawerLayout;
    int numID;
    float x1,x2;
    float y1,y2;
    public  static  final  String TAG="ACTI";
    static int pre,curr,tot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_credit_debit  , null, false);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerLayout.addView(contentView, 0);
        //setContentView(R.layout.activity_credit_debit);
        b=(Button)findViewById(R.id.updateID);
        final EditText credit=(EditText)findViewById(R.id.creditID);
        final EditText debit=(EditText)findViewById(R.id.debitID);
        //final TextView tv=(TextView) findViewById(R.id.totalID);
        sp=getSharedPreferences("CRDR",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        /*editor.putInt("TOTAL",0);//delete this after execution
        editor.putInt("BEFORE",0);//
        editor.putInt("ID",0);//*/
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            sp=getSharedPreferences("CRDR",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();

                if(credit.getText().toString().length()!=0)
                editor.putInt("CREDIT",Integer.parseInt(credit.getText().toString()));
                else
                    editor.putInt("CREDIT",0);
                if(debit.getText().toString().length()!=0)
                editor.putInt("DEBIT",Integer.parseInt(debit.getText().toString()));
                else
                    editor.putInt("DEBIT",0);

                if(credit.getText().toString().length()==0 &&debit.getText().toString().length()==0)
                {
                    Toast.makeText(CreditDebitActivity.this,"YOUR DID NOT ENTER AMOUNT",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(credit.getText().toString().length()!=0 &&debit.getText().toString().length()==0)
                {
                    editor.putString("CHECK","Cr");
                }
                else
                {
                    editor.putString("CHECK","Dr");
                }
                editor.commit();
                if(sp.getString("CHECK"," ").matches("Dr"))
                {
                    int num=sp.getInt("TOTAL",9)-Integer.parseInt(debit.getText().toString());
                    if(num<0)
                    {
                        TextView enough=(TextView)findViewById(R.id.noEnoughBalID);
                        //enough.setError("Not Enough Balance to Debit");
                        enough.setText("Not Enough Balance to Debit");
                        //enough.setVisibility(View.VISIBLE);
                        return;
                    }
                }
                final Dialog dialog = new Dialog(CreditDebitActivity.this);
                dialog.setContentView(R.layout.activity_dialog);
                dialog.setTitle("Choose One Option");
                Button cancelButton=(Button)dialog.findViewById(R.id.PINconfirmID);
                Button noDetailButton=(Button)dialog.findViewById(R.id.noDetailID);
                Button yesDetailButton=(Button)dialog.findViewById(R.id.yesDetailId);
                TextView tv1=(TextView)dialog.findViewById(R.id.showID);
                //TextView tota=(TextView)findViewById(R.id.totalID);

                if(sp.getString("CHECK"," ").matches("Cr")){
                    tv1.setText("+"+sp.getInt("CREDIT",100));
                }
                else{
                    tv1.setText("-"+sp.getInt("DEBIT",200));
                }

                noDetailButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         todayDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                        SQLiteDatabase db=openOrCreateDatabase("expDataB",MODE_PRIVATE,null);
                        //db.execSQL("DROP TABLE IF EXISTS expenseTable");
                        db.execSQL("CREATE TABLE IF NOT EXISTS expenseTable(id INTEGER PRIMARY KEY,date VARCHAR,crORdr VARCHAR,recent INTEGER,preAmt INTEGER,fortDetail VARCHAR,enterDetail VARCHAR, newAmt INTEGER );");
                        credit.getText().clear();
                        debit.getText().clear();
                        SharedPreferences.Editor editor=sp.edit();
                        //TextView textShow=(TextView)findViewById(R.id.totalID);
                        editor.putInt("BEFORE",sp.getInt("TOTAL",10));
                        //Log.i(TAG,"beforehola"+String.valueOf(numID));
                        if(sp.getString("CHECK"," ").matches("Cr")){
                            numID=sp.getInt("ID",23);
                            numID=numID+1;
                           // Log.i(TAG,"HOLA"+String.valueOf(numID));
                            editor.putInt("ID",numID);
                            tot=sp.getInt("CREDIT",20)+sp.getInt("TOTAL",9);
                            editor.putInt("TOTAL",tot);
                            editor.commit();
                             db.execSQL("INSERT INTO expenseTable(id,date,crORdr,preAmt,recent,fortDetail,enterDetail,newAmt) VALUES('"+sp.getInt("ID",23)+"','"+todayDate+"','"+sp.getString("CHECK"," ")+"','"+sp.getInt("BEFORE",55)+"','"+sp.getInt("CREDIT",60)+"','"+" "+"','"+" "+"','"+sp.getInt("TOTAL",66)+"');");
                            dialog.dismiss();
                        }
                        else{
                            numID=sp.getInt("ID",99);
                            numID=numID+1;
                            tot=sp.getInt("TOTAL",9)-sp.getInt("DEBIT",20);
                            editor.putInt("ID",numID);
                            editor.putInt("TOTAL",tot);
                            editor.commit();
                           // textShow.setText(String.valueOf(sp.getInt("TOTAL",50)));
                            db.execSQL("INSERT INTO expenseTable(id,date,crORdr,preAmt,recent,fortDetail,enterDetail,newAmt) VALUES('"+sp.getInt("ID",23)+"','"+todayDate+"','"+sp.getString("CHECK"," ")+"','"+sp.getInt("BEFORE",55)+"','"+sp.getInt("DEBIT",66)+"','"+" "+"','"+" "+"','"+sp.getInt("TOTAL",66)+"');");
                            dialog.dismiss();
                        }
                        Intent i=new Intent(CreditDebitActivity.this,DataShow.class);
                        startActivity(i);
                    }
                });
                yesDetailButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        todayDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                        SQLiteDatabase db=openOrCreateDatabase("expDataB",MODE_PRIVATE,null);
                        credit.getText().clear();
                        debit.getText().clear();
                        SharedPreferences.Editor editor=sp.edit();
                        //TextView textShow=(TextView)findViewById(R.id.totalID);
                        editor.putInt("BEFORE",sp.getInt("TOTAL",10));
                        if(sp.getString("CHECK"," ").matches("Cr")){
                            numID=sp.getInt("ID",23);
                            numID=numID+1;
                            editor.putInt("ID",numID);
                            tot=sp.getInt("CREDIT",20)+sp.getInt("TOTAL",9);
                            editor.putInt("TOTAL",tot);
                            editor.commit();
                            //textShow.setText(String.valueOf(sp.getInt("TOTAL",50)));
                            dialog.dismiss();
                        }
                        else{
                            numID=sp.getInt("ID",23);
                            numID=numID+1;
                            tot=sp.getInt("TOTAL",9)-sp.getInt("DEBIT",20);
                            editor.putInt("ID",numID);
                            editor.putInt("TOTAL",tot);
                            editor.commit();
                            //textShow.setText(String.valueOf(sp.getInt("TOTAL",50)));
                            dialog.dismiss();
                        }
                        Intent i=new Intent(CreditDebitActivity.this,FillDetailActivity.class);
                        startActivity(i);
                    }
                });
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        credit.getText().clear();
                        debit.getText().clear();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}
