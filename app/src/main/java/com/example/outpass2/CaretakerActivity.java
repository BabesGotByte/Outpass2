package com.example.outpass2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class CaretakerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caretaker);
        auth = FirebaseAuth.getInstance();



        toolbar = findViewById(R.id.ctoolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.caretaker_drawer_layout);

        NavigationView navigationView = findViewById(R.id.caretaker_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.NavCaretakerFragmentContainer, new RequestFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_request);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment fragment;
        if (id == R.id.nav_request) {
            getSupportFragmentManager().beginTransaction().replace(R.id.NavCaretakerFragmentContainer, new RequestFragment()).commit();
            drawer.closeDrawer(GravityCompat.START);
        }

//        else if (id == R.id.nav_history) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.NavCaretakerFragmentContainer, new history()).commit();
//            drawer.closeDrawer(GravityCompat.START);        }

        else if (id == R.id.nav_defaulters) {
            getSupportFragmentManager().beginTransaction().replace(R.id.NavCaretakerFragmentContainer, new DefaultersFragment()).commit();
            drawer.closeDrawer(GravityCompat.START); }

        else if (id == R.id.nav_feedback) {
            getSupportFragmentManager().beginTransaction().replace(R.id.NavCaretakerFragmentContainer, new feed()).commit();
            drawer.closeDrawer(GravityCompat.START); }

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
            finish();
            auth.signOut();
            startActivity(new Intent(CaretakerActivity.this,LoginActivity.class));
        }

        return true;
    }
}
