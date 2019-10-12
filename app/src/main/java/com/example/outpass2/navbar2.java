package com.example.outpass2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class navbar2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private DrawerLayout drawer;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navbar);


        auth = FirebaseAuth.getInstance();


        toolbar = findViewById(R.id.vtoolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.NavFragmentContainer, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        if (id == R.id.nav_home) {
           getSupportFragmentManager().beginTransaction().replace(R.id.NavFragmentContainer, new HomeFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
        }

        else if (id == R.id.nav_editprofile) {

        }

        else if (id == R.id.nav_history) {
            getSupportFragmentManager().beginTransaction().replace(R.id.NavFragmentContainer, new history()).commit();
            drawer.closeDrawer(GravityCompat.START);        }

        else if (id == R.id.nav_feedback) {
            getSupportFragmentManager().beginTransaction().replace(R.id.NavFragmentContainer, new feed()).commit();
            drawer.closeDrawer(GravityCompat.START);          }

        else if (id == R.id.nav_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String sharebody= "Play store link will be updated soon";
            String sharesub =" Your subject here";
            i.putExtra(Intent.EXTRA_SUBJECT,sharesub);
            i.putExtra(Intent.EXTRA_TEXT,sharebody);
            startActivity(Intent.createChooser(i,"Share using "));
        }

        else if (id == R.id.nav_logout){
            signOut();
            startActivity(new Intent(navbar2.this,LoginActivity.class));
            finish();
        }

        return true;
    }

    public void signOut() {
        auth.signOut();
    }

}
