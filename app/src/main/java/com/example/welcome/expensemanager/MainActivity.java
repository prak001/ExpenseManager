package com.example.welcome.expensemanager;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    public  static  final  String TAG="ACTI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout RL=(RelativeLayout)findViewById(R.id.rLayoutID);
        TextView eID=(TextView)findViewById(R.id.expenseID);
        TextView mID=(TextView)findViewById(R.id.managerID);
        eID.setTextColor(Color.parseColor("#8E24AA"));
        mID.setTextColor(Color.parseColor("#8E24AA"));
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        int k1=sp.getInt("statePass",90);
        if(k1==90)
        {
            editor.putInt("statePass",0);
            editor.putString("NAME","");
        }
        RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
                String logi=sp.getString("NAME","");
                String pass=sp.getString("PASSWORD"," ");
                int k=sp.getInt("statePass",0);
            //    Toast.makeText(MainActivity.this,String.valueOf(k),Toast.LENGTH_LONG).show();
                //Log.i("TAG","BEFORE");
                //if(pass.length()==0)//change !to =
                if(sp.getInt("statePass",0)==0)
                    {
                    Log.i("TAG","IF");
                    if (logi.length() == 0) {//CHECK FOR THIS
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        Intent iii = new Intent(MainActivity.this, CreditDebitActivity.class);
                        startActivity(iii);
                    }
                }
                else
                {
                    Log.i("TAG","ELSE");
                    final Dialog dialog = new Dialog(MainActivity.this);
                    //Log.i("TAG","ELS1");
                    dialog.setContentView(R.layout.activity_passworddialog);
                    //Log.i("TAG","ELS2");
                    dialog.setTitle("   ENTER YOUR PIN");
                    dialog.show();
                    //Log.i("TAG","ELSE3");
                    Button confirmbutton=(Button)dialog.findViewById(R.id.PINconfirmID);
                    Log.i("TAG","ELSE4");
                    //EditText pinEnter=(EditText)dialog.findViewById(R.id.PINenterID);
                    confirmbutton.setOnClickListener(new View.OnClickListener() {
                        //Log.i("TAG","ELSE5");
                        @Override
                        public void onClick(View view) {
                            //Log.i("TAG","passConfrim");
                            sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
                            EditText pinEnter=(EditText)dialog.findViewById(R.id.PINenterID);
                            String pinString=pinEnter.getText().toString();
                            if(pinString.matches(sp.getString("PASSWORD","")))
                            {
                                Intent i=new Intent(MainActivity.this,CreditDebitActivity.class);
                                startActivity(i);
                                dialog.dismiss();
                            }
                            else{
                                pinEnter.setError("WRONG PASSWORD");
                            }
                        }
                    });
                }
                /*Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);*/
            }
        });

    }
}
