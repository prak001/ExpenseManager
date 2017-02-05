package com.example.welcome.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by welcome on 21-07-2016.
 */
public class cDrawer extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ListView listView;
    public String[] drawerItems;
    public ActionBarDrawerToggle drawerToggle;
    private ActionBarDrawerToggle mDrawerToggle;
    public DrawerLayout drawerlayout;
    ListView drawerList;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        drawerlayout.setDrawerListener(mDrawerToggle);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.customdrawer, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.mainContent);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerItems = getResources().getStringArray(R.array.drawerItems);
        ListAdapter myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drawerItems);
        ListView listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    ListView listView = (ListView) findViewById(R.id.drawerList);
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String str=String.valueOf(parent.getItemAtPosition(position));
                        //Toast.makeText(cDrawer.this,drawerItems[position]+"selected", Toast.LENGTH_LONG).show();
                        selectItem(position);
                        switch (position)
                        {
                            case 0:
                                Intent i=new Intent(cDrawer.this,changeDetailActivity.class);
                                startActivity(i);
                                break;
                            case 1:
                                Intent i1=new Intent(cDrawer.this,passwordActivity.class);
                                startActivity(i1);
                                break;

                        }
                    }
                    private void selectItem(int position)
                    {
                        listView.setItemChecked(position,true);
                    }
                    public  void setTitle(String title)
                    {
                        getSupportActionBar().setTitle(title);
                    }
                }
        );

        //listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, drawerItems));
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Do what you want when an item from the Navigation Drawer is clicked
                //Toast.makeText(this,drawerItems[position]+"was selected",Toast.LENGTH_LONG).show();
                selectItem(position);
            }
            private void selectItem(int position)
            {
                listView.setItemChecked(position,true);
            }
            public  void setTitle(String title)
            {
                getSupportActionBar().setTitle(title);
            }
        });*/
    }
    /*@Override
    public  void onItemClick(AdapterView<?> parent,View view,int position,long id)
    {
        Toast.makeText(this,drawerItems[position]+"was selected",Toast.LENGTH_LONG).show();
        selectItem(position);
        switch (position)
        {
            case 0:
                Intent i=new Intent(cDrawer.this,changeDetailActivity.class);
                startActivity(i);
                break;
        }

    }
    private void selectItem(int position)
    {
        listView.setItemChecked(position,true);
    }
    public  void setTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }*/
}
