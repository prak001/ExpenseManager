package com.example.welcome.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changeDetailActivity extends AppCompatActivity {

    SharedPreferences sLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_detail);
        final EditText nChange=(EditText)findViewById(R.id.nameChangeID);
        final EditText mChange=(EditText)findViewById(R.id.mobileChangeID);
         sLogin=getSharedPreferences("LOGIN",MODE_PRIVATE);
        SharedPreferences.Editor editor=sLogin.edit();
        String a=sLogin.getString("NAME"," ");
        String b=sLogin.getString("MOBILE"," ");
        //Toast.makeText(changeDetailActivity.this,b,Toast.LENGTH_LONG).show();
        nChange.setText(a);
        mChange.setText(b);
        Button b1=(Button)findViewById(R.id.upID);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                EditText nChange = (EditText) findViewById(R.id.nameChangeID);
                EditText mChange = (EditText) findViewById(R.id.mobileChangeID);
                String na = nChange.getText().toString();
                String num = mChange.getText().toString();
                editor.putString("NAME",na);
                editor.putString("MOBILE",num);
                editor.commit();
                if (na.length() == 0 && num.length() == 0) {
                    Intent i = new Intent(changeDetailActivity.this, CreditDebitActivity.class);
                    startActivity(i);
                } else if (na.length() != 0 && num.length() == 0) {
                    editor.putString("NAME", na);
                } else if (na.length() != 0 && num.length() == 10) {
                    editor.putString("NAME", na);
                    editor.putString("MOBILE", num);
                } else if (na.length() != 0 && num.length() != 10) {
                    mChange.setError("ENTER CORRECT NUMBER1");
                    Toast.makeText(changeDetailActivity.this,na+" "+sp.getString("MOBILE"," "),Toast.LENGTH_LONG).show();
                    return;
                } else if (na.length() == 0 && num.length() == 10) {
                    editor.putString("MOBILE", num);
                } else if (na.length() == 0 && num.length() != 10) {
                    mChange.setError("ENTER CORRECT NUMBER2");
                    return;
                }
                editor.commit();
                //Toast.makeText(changeDetailActivity.this, sp.getString("NAME", " "), Toast.LENGTH_LONG).show();
                Intent i = new Intent(changeDetailActivity.this, CreditDebitActivity.class);
                startActivity(i);
            }

        });
    }
}