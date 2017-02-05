package com.example.welcome.expensemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class passwordActivity extends AppCompatActivity {
    Switch swit;
    Button but;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        swit=(Switch) findViewById(R.id.switch1);
        TextView tv9=(TextView)findViewById(R.id.textView9);
        TextView tv10=(TextView)findViewById(R.id.textView10);
        EditText et1=(EditText)findViewById(R.id.enterPinID);
        EditText et2=(EditText)findViewById(R.id.againPinID);
         but=(Button)findViewById(R.id.upButID);
        Button goBack=(Button)findViewById(R.id.goBackID);
        //if(sp.getString("PASSWORD"," ").length()==4)
        if(sp.getInt("statePass",0)==1)
        {
            swit.setChecked(true);
            tv9.setVisibility(View.VISIBLE);
            tv10.setVisibility(View.VISIBLE);
            et1.setVisibility(View.VISIBLE);
            et2.setVisibility(View.VISIBLE);
            but.setVisibility(View.VISIBLE);
            goBack.setVisibility(View.INVISIBLE);
        }
        else
        {
            swit.setChecked(false);
        }
        //swit.setChecked(true);
        swit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                TextView tv9=(TextView)findViewById(R.id.textView9);
                TextView tv10=(TextView)findViewById(R.id.textView10);
                EditText et1=(EditText)findViewById(R.id.enterPinID);
                EditText et2=(EditText)findViewById(R.id.againPinID);
                Button but=(Button)findViewById(R.id.upButID);
                Button goBack=(Button)findViewById(R.id.goBackID);
                if(isChecked)
                {
                    editor.putInt("statePass",1);
                    editor.commit();
                    tv9.setVisibility(View.VISIBLE);
                    tv10.setVisibility(View.VISIBLE);
                    et1.setVisibility(View.VISIBLE);
                    et2.setVisibility(View.VISIBLE);
                    but.setVisibility(View.VISIBLE);
                    goBack.setVisibility(View.INVISIBLE);
                }
                else
                {
                    editor.putInt("statePass",0);
                    editor.commit();
                    tv9.setVisibility(View.INVISIBLE);
                    tv10.setVisibility(View.INVISIBLE);
                    et1.setVisibility(View.INVISIBLE);
                    et2.setVisibility(View.INVISIBLE);
                    but.setVisibility(View.INVISIBLE);
                    goBack.setVisibility(View.VISIBLE);
                }
            }
        });
        //Button goBack=(Button)findViewById(R.id.goBackID);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(passwordActivity.this,CreditDebitActivity.class);
                startActivity(i);
            }
        });

        Button but=(Button)findViewById(R.id.upButID);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                EditText et1=(EditText)findViewById(R.id.enterPinID);
                EditText et2=(EditText)findViewById(R.id.againPinID);
                String s1=et1.getText().toString();
                String s2=et2.getText().toString();
                if(s1.length()==0 && s2.length()==0)
                {
                    Toast.makeText(passwordActivity.this,"ENTER YOUR PASSWORD",Toast.LENGTH_SHORT).show();
                }
                else if(s1.matches(s2) && s1.length()==4)
                {
                    Toast.makeText(passwordActivity.this,"PASSWORD SET SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                    editor.putString("PASSWORD",s1);
                    editor.commit();
                    Intent i=new Intent(passwordActivity.this,CreditDebitActivity.class);
                    startActivity(i);
                }
                else if(s1.matches(s2) && s1.length()!=4)
                {
                    Toast.makeText(passwordActivity.this,"ENTER 4 digits",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(passwordActivity.this,"Password Do Not Match",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
