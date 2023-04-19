package com.test.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button gpa_button = findViewById(R.id.gpa_button);
        Button grade_button = findViewById(R.id.grade_list_button);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        gpa_button.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(intent);
        });

        grade_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,GradeListActivity.class);
                startActivity(intent);
            }
        });

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // Handle navigation view item clicks here
            int id = menuItem.getItemId();

            if (toggle.onOptionsItemSelected(menuItem)) {
                return true;
            }

            if (id == R.id.nav_calculate_gpa) {
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_grade_list) {
                Intent intent = new Intent(HomeActivity.this,GradeListActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_share) {
                // Handle the share action
            } else if (id == R.id.nav_rate_us) {
                // Handle the rate us action
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}