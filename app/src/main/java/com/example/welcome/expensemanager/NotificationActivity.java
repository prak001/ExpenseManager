package com.example.welcome.expensemanager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotificationActivity extends AppCompatActivity {
    NotificationCompat.Builder notification;
    private  static  final int UniqueID=123456;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifyme);
        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        EditText et=(EditText)findViewById(R.id.belowAmtID);
        Button but=(Button)findViewById(R.id.notifyID);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et=(EditText)findViewById(R.id.belowAmtID);
                Button but=(Button)findViewById(R.id.notifyID);
                sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("BELOW",Integer.parseInt(et.getText().toString()));
                editor.commit();
            }
        });
        editor.putInt("BELOW",Integer.parseInt(et.getText().toString()));
        editor.commit();
    }
    public  void buttonIsClicked(View View)
    {
        //build notification
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("ExpenseManager");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("ALERT! LESS AMOUNT");
        notification.setContentText("You have less balance");
        notification.setColor(5);

       Intent intent=new Intent(this,NotificationActivity.class);
       PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(UniqueID,notification.build());
    }
}
