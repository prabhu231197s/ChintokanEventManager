package madmaxstudios.com.chintokaneventmanager.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import madmaxstudios.com.chintokaneventmanager.Adapters.ViewPagerAdapter;
import madmaxstudios.com.chintokaneventmanager.Entities.GetBalanceEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.LoginEntity;
import madmaxstudios.com.chintokaneventmanager.Entities.LogoutEntity;
import madmaxstudios.com.chintokaneventmanager.Fragments.AboutUsFragment;
import madmaxstudios.com.chintokaneventmanager.Fragments.AddMoneyFragment;
import madmaxstudios.com.chintokaneventmanager.Fragments.FeedBackFragment;
import madmaxstudios.com.chintokaneventmanager.Fragments.RegisterEventFragment;
import madmaxstudios.com.chintokaneventmanager.R;
import madmaxstudios.com.chintokaneventmanager.RestClient.RestClientImplementation;

public class Homescreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView wallet;
    ViewPager pager;
    String email;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager)findViewById(R.id.vp) ;
        wallet = (TextView)findViewById(R.id.tvwallet);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        pager.setAdapter(adapter);
        preferences = getSharedPreferences("userDetails",MODE_PRIVATE);
         email = preferences.getString("mail","prabhu231197@gmail.com");
        RestClientImplementation.getBalance(preferences.getInt("id", 0), new GetBalanceEntity(), new GetBalanceEntity.RestClientInterface() {
            @Override
            public void onInitialize(int statusCode, String message, Long balance, VolleyError error) {
                if(statusCode==200||statusCode==304){
                    editor = preferences.edit();
                    editor.putLong("cash",balance);
                    editor.commit();
                    Long cash = preferences.getLong("cash",0);
                    String cas = "Rs: "+String.valueOf(cash)+".00";
                    wallet.setText(cas);
                }
                else{
                    Toast.makeText(Homescreen.this,"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        },Homescreen.this);
        Long cash = preferences.getLong("cash",0);
        String cas = "Rs: "+String.valueOf(cash)+".00";
        wallet.setText(cas);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homescreen, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.addMoney) {
            toolbar.setVisibility(View.VISIBLE);
            AddMoneyFragment addMoneyFragment = new AddMoneyFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.replaceThis,addMoneyFragment).addToBackStack(null).commit();
            //Toast.makeText(Homescreen.this,"Swipe for navigation drwaer",Toast.LENGTH_LONG).show();
        } else if (id == R.id.regEvent) {
            toolbar.setVisibility(View.VISIBLE);
            RegisterEventFragment registerEventFragment = new RegisterEventFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.replaceThis,registerEventFragment).addToBackStack(null).commit();

        } else if (id == R.id.about_us) {
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            toolbar.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.replaceThis,aboutUsFragment).addToBackStack(null).commit();
        } else if (id == R.id.feedbacks) {
            FeedBackFragment fragment = new FeedBackFragment();
            toolbar.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.replaceThis,fragment).addToBackStack(null).commit();

        } else if (id == R.id.logout) {

            LogoutEntity logoutEntity= new LogoutEntity();
            RestClientImplementation.Logout(email,logoutEntity,new LogoutEntity.ChintokanRestClientInterface(){
                @Override
                public void onInitialize(LogoutEntity entity, VolleyError error) {
                    if(entity.getStatusCode()==200||entity.getStatusCode()==304){
                        int x = Integer.parseInt(preferences.getString("status","1"));
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("status","0");
                        editor.commit();
                        Toast.makeText(Homescreen.this,entity.getMessage(),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Homescreen.this,LoginActivity.class));
                        finish();
                    }
                    else{
                        Toast.makeText(Homescreen.this,entity.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            },Homescreen.this);

        }
        else if(id == R.id.homescreen){
            finishAffinity();
            startActivity(new Intent(Homescreen.this,Homescreen.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
