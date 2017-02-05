package com.example.welcome.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    int num=0;
    String namefromlogin,mobfromlogin;
    EditText tv;
    SharedPreferences sLogin;
    private static final String TAG = "ABC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText name=(EditText)findViewById(R.id.nameID);
        final EditText mob=(EditText)findViewById(R.id.mobileID);
        //mobfromlogin=mob.getText().toString();
        Button b=(Button)findViewById(R.id.continueID);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namefromlogin = name.getText().toString();
                 sLogin=getSharedPreferences("LOGIN",MODE_PRIVATE);
                SharedPreferences.Editor editor=sLogin.edit();
                mobfromlogin=mob.getText().toString();
                editor.putString("NAME",namefromlogin);
                editor.putString("MOBILE",mobfromlogin);
                editor.commit();
                SharedPreferences sp=getSharedPreferences("CRDR",MODE_PRIVATE);
                SharedPreferences.Editor editor1=sp.edit();
                editor1.putInt("TOTAL",0);
                editor1.putInt("BEFORE",0);
                editor1.putInt("ID",0);
                editor1.commit();
                //Log.i(TAG,"HOhvhLA"+String.valueOf(sp.getInt("ID",90)));
                if((namefromlogin.length()==0 && mobfromlogin.length()==0)||(namefromlogin.length()==0 && mobfromlogin.length()!=0)) {
                    name.setError("ENTER YOUR NAME");
                    return;
                }
                else if((namefromlogin.length()!=0 && mobfromlogin.length()==0)||(namefromlogin.length()!=0 && mobfromlogin.length()==10))
                {
                    Intent i = new Intent(LoginActivity.this, CreditDebitActivity.class);
                    startActivity(i);
                }
                else if(namefromlogin.length()!=0 && mobfromlogin.length()!=10)
                {
                    Toast.makeText(LoginActivity.this,"ENTER CORRECT NUMBER",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
