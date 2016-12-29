package com.zeus.beaconproject;


import android.app.Notification;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.activeandroid.query.Select;
import com.zeus.beaconproject.Fragments.CatalogByCategory;
import com.zeus.beaconproject.Fragments.ChooseCategory;
import com.zeus.beaconproject.Fragments.TakemeTo;
import com.zeus.beaconproject.Models.ChosenProduct;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import br.com.goncalves.pugnotification.notification.PugNotification;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Region region;
    BeaconManager beaconManager;
    public static String nearestCategoryCurrently;

    public static void updateShoppingList(){
        myshoppinglist=new Select()
                .from(ChosenProduct.class)
                .execute();
    }

    public static List<ChosenProduct> myshoppinglist;
    public static final Map<String,String> beaconToCat=new HashMap<String, String>(){{
        put("42133:54277","3944");
        put("1001:1001","4125");
        put("1002:1002","3891");
        put("1020:1020","4104");
    }};

    ProgressDialog progressDialog;
    static public int stats=0;
    static public Bundle catalogByCategoryBundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateShoppingList();
        beaconManager =new BeaconManager(this);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region1, List<Beacon> list) {
                if(region==region1 && !list.isEmpty()){
                    Beacon b= list.get(0);
                    String Id=String.format("%d:%d",b.getMajor(),b.getMinor());
                    String cat=beaconToCat.get(Id);
                    nearestCategoryCurrently=ChooseCategory.categoryName.get(cat);
                    for(int i=0;i<myshoppinglist.size();i++)
                    {
                        String missedCategory=ChooseCategory.categoryName.get(myshoppinglist.get(i).category);
                        if(myshoppinglist.get(i).category.equals(cat)){
                            ChosenProduct toRemindProduct=myshoppinglist.get(i);
                            myshoppinglist.remove(i);
                            remind(toRemindProduct);
                        }

                    }

                }
            }
        });

        region = new Region("ranged region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);


        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.explore_catalog);
    }

    public void remind(ChosenProduct toRemindProduct)
    {
        Toast.makeText(this,"You missed "+toRemindProduct.name+" in "+ChooseCategory.categoryName.get(toRemindProduct.category),Toast.LENGTH_LONG).show();

        PugNotification.with(getApplicationContext())
                .load()
                .title("Reminder")
                .message("Buy "+toRemindProduct.name+" from "+ChooseCategory.categoryName.get(toRemindProduct.category)+" section")
                .bigTextStyle("Hurry")
                .smallIcon(R.drawable.pugnotification_ic_launcher)
                .largeIcon(R.drawable.pugnotification_ic_launcher)
                .flags(Notification.DEFAULT_ALL)
                .color(android.R.color.background_dark)
                .simple()
                .build();
    }

    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if(stats==5){
            Fragment frag=new ChooseCategory();
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,frag);
            stats=3;
            ft.commit();
        }else if(stats==7){
            Fragment frag=new CatalogByCategory();
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,frag);
            stats=5;
            ft.commit();
        }else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void displaySelectedScreen(int optionId){
        Fragment fragment=null;
        switch(optionId){
            case R.id.explore_catalog:
                fragment=new ChooseCategory();
                break;
            case R.id.takemeTo:
                fragment= new TakemeTo();
                break;
            default:
                Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_LONG).show();
        }
        if(fragment!=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            stats=3;
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
