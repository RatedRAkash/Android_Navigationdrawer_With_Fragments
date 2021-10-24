package com.example.navigationdrawer_with_fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //As we Removed DEFAULT ACTION_BAR... tai amader TOOLBAR(jeita activity_main.xml ee ache sheita) ke SET korbo DEFAULT ACTION_BAR hisave
        Toolbar toolbar = findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_viewId);
        navigationView.setNavigationItemSelectedListener(this); //Main Activity LISTENER ke implement koreche NICHE

        //Menu(3 dot) button in Top Left Corner of the Screen
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState(); //Navigation Drawer khular time ee 3 dot Rotate Animation dekha jabe

        //At first APP open hole amra just MessageFragment ta display koraite chacchi
        LoadInitialFragment(savedInstanceState);//"savedInstanceState" pass korachi jate APP jokon Rotate hobe tokon amra jeita SELECT kore raksi sheita jeno Select kore rakte pare

    }

    private void LoadInitialFragment(Bundle savedInstanceState) {
        //savedInstanceState == null mane App kebol amra prothom bar START korsi... Rotate kori hui nai
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerId,
                    new MessageFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_message_id);
        }
    }


    //Back Button close korle jano INSTEAD of Activity Destroy nah hoye NAVIGATION_DRAWER ta vitor ee dukhe jai shei code
    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }

        else{
            super.onBackPressed();
        }

    }


    //onNavigationClickListener er OVERRIDE method
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_message_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerId,
                        new MessageFragment()).commit();
                break;

            case R.id.nav_chat_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerId,
                        new ChatFragment()).commit();
                break;

            case R.id.nav_profile_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerId,
                        new ProfileFragment()).commit();
                break;

            case R.id.nav_share_id:
                Toast.makeText(this, "Share Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_send_id:
                Toast.makeText(this, "Send Clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        //Item Select korar por DRAWER CLOSE korte hobe
        drawer.closeDrawer(GravityCompat.START);

        return true;// True mane kono item select kora hoyeche
    }


}