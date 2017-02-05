package com.example.welcome.expensemanager;

//import android.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

//import android.app.Fragment;

public class drawerMainActivity extends AppCompatActivity {
    private static String TAG = drawerMainActivity.class.getSimpleName();
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    TextView tv;
    SharedPreferences sLogin,sp;
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawermain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNavItems.add(new NavItem("Details", "Change Name/Phone No.", R.mipmap.ic_profile));
        mNavItems.add(new NavItem("PIN", "Change/Set your PIN", R.mipmap.ic_pinf));
        mNavItems.add(new NavItem("Transactions", "Show previous Transactions", R.mipmap.ic_details));
        //mNavItems.add(new NavItem("Notify Me!", " ", R.mipmap.ic_launcher));
        mNavItems.add(new NavItem("About ", " ", R.mipmap.ic_about));
        mNavItems.add(new NavItem("Logout ", " ", R.mipmap.ic_logout));
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        sLogin=getSharedPreferences("LOGIN",MODE_PRIVATE);
        sp=getSharedPreferences("CRDR",MODE_PRIVATE);
        SharedPreferences.Editor editor=sLogin.edit();
        SharedPreferences.Editor editor1=sp.edit();
        TextView tv=(TextView)findViewById(R.id.userName);
        TextView tv2=(TextView)findViewById(R.id.mobileDrawerId);
        TextView tv3=(TextView)findViewById(R.id.desc);
        tv.setText(sLogin.getString("NAME"," "));
        tv2.setText(sLogin.getString("MOBILE"," "));
        tv3.setText("Available Amount"+"\n"+"     Rs "+String.valueOf(sp.getInt("TOTAL",78)));

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(getBaseContext(), mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //selectItemFromDrawer(position);
                switch (position)
                {
                    case 0:
                        Intent i=new Intent(drawerMainActivity.this,changeDetailActivity.class);
                        startActivity(i);
                        break;
                    case 1:
                        Intent i1=new Intent(drawerMainActivity.this,passwordActivity.class);
                        startActivity(i1);
                        break;
                    case 2:
                        Intent i2=new Intent(drawerMainActivity.this,LViewActivity.class);
                        startActivity(i2);
                        break;
                    /*case 3:
                        Intent i3=new Intent(drawerMainActivity.this,NotificationActivity.class);
                        startActivity(i3);
                        break;*/
                    case 3:
                        Intent i3=new Intent(drawerMainActivity.this,AboutDialogActivity.class);
                        startActivity(i3);
                        break;
                    case 4:
                        Intent i4=new Intent(drawerMainActivity.this,LogoutActivity.class);
                        startActivity(i4);
                        break;

                }
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
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
    private void selectItemFromDrawer(int position) {
        Fragment fragment = new preferencesfragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();
        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }
    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    }
    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText( mNavItems.get(position).mTitle );
            subtitleView.setText( mNavItems.get(position).mSubtitle );
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }
}
