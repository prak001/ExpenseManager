package com.example.welcome.expensemanager;

/**
 * Created by welcome on 23-07-2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SQLiteListAdapter extends BaseAdapter {

    Context context;
    //ArrayList<String> userID;
    ArrayList<String> ID;
   // ArrayList<String> UserName;
   ArrayList<String> date;
    //ArrayList<String> User_PhoneNumber;
    ArrayList<String> credit_debit;
    //ArrayList<String> UserSubject ;
    ArrayList<String> recentAmt;
    ArrayList<String> transactionAmt;
    ArrayList<String> newAmt;
    ArrayList<String> for_to;
    ArrayList<String> detailfull;

    SharedPreferences sp;
    public SQLiteListAdapter(Context context2, ArrayList<String> id, ArrayList<String> date, ArrayList<String> cd, ArrayList<String> rA,ArrayList<String> tA,ArrayList<String> nA,ArrayList<String> f_t,ArrayList<String> detF)
    {

        this.context = context2;
        this.ID = id;
        this.date = date;
        this.credit_debit = cd;
        this.recentAmt = rA ;
        this.transactionAmt = tA;
        this.newAmt = nA;
        this.for_to = f_t;
        this.detailfull =detF ;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewdate = (TextView) child.findViewById(R.id.textViewDATE);
            holder.textviewcd = (TextView) child.findViewById(R.id.textViewCREDIT_DEBIT);
            holder.textviewprev = (TextView) child.findViewById(R.id.textViewPREVIOUS);
            holder.textviewtran = (TextView) child.findViewById(R.id.textViewTRANSACTION);
            holder.textviewnew = (TextView) child.findViewById(R.id.textViewNEW);
            holder.textviewfrot = (TextView) child.findViewById(R.id.textViewFROM_TO);
            holder.textviewfdetail = (TextView) child.findViewById(R.id.textViewFULLdet);
            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(ID.get(position));
        holder.textviewdate.setText(date.get(position));
        if(credit_debit.get(position).matches("Cr")) {
            //holder.textviewcd.setText(credit_debit.get(position));
            holder.textviewcd.setText("Credit =");
        }
        else
        {
            holder.textviewcd.setText("Debit =");
        }
        holder.textviewprev.setText(recentAmt.get(position));
        holder.textviewtran.setText(transactionAmt.get(position));
        holder.textviewnew.setText(newAmt.get(position));
        holder.textviewfrot.setText(for_to.get(position));
        holder.textviewfdetail.setText(detailfull.get(position));

        return child;
    }
    public class Holder {

        TextView textviewid;
        TextView textviewdate;
        TextView textviewcd;
        TextView textviewprev;
        TextView textviewtran;
        TextView textviewnew;
        TextView textviewfrot;
        TextView textviewfdetail;
    }

}
