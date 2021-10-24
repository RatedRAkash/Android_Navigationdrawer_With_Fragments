package com.example.navigationdrawer_with_fragments;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //As we Removed DEFAULT ACTION_BAR... tai amader TOOLBAR(jeita activity_main.xml ee ache sheita) ke SET korbo DEFAULT ACTION_BAR hisave
        Toolbar toolbar = findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        //Menu(3 dot) button in Top Left Corner of the Screen
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);

        toggle.syncState(); //Navigation Drawer khular time ee 3 dot Rotate Animation dekha jabe
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
}