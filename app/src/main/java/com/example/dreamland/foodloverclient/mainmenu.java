package com.example.dreamland.foodloverclient;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class mainmenu extends AppCompatActivity {
   Button categorybtn, offersbtn, massagebtn,btnorder;

    private android.support.v4.widget.DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initNavDrawer();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Menu");
        categorybtn=(Button)findViewById(R.id.category);
        offersbtn=(Button)findViewById(R.id.offersbtn);
        massagebtn=(Button)findViewById(R.id.btnmsg);
        btnorder=(Button)findViewById(R.id.orderid);
        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a =new Intent(mainmenu.this,Item.class);
                startActivity(a);
            }
        });

        offersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(mainmenu.this,Offers.class);
                startActivity(b);
            }
        });
        massagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c= new Intent(mainmenu.this,Inbox.class);
                startActivity(c);

            }
        });
        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d=new Intent(mainmenu.this,Order_Food.class);
                startActivity(d);
            }
        });

    }
    private void initNavDrawer() {
        DrawerLayout = findViewById(R.id.navidrawer1);
        navigationView = findViewById(R.id.nv1);
        Toggle = new ActionBarDrawerToggle(this, DrawerLayout, R.string.opennev, R.string.closenev);
        DrawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.itemnev:
                        Intent a = new Intent(getApplicationContext(), Item.class);
                        startActivity(a);
                        break;
                    case R.id.offernev:
                        Intent b = new Intent(getApplicationContext(), Offers.class);
                        startActivity(b);
                        break;
                    case R.id.massagenev:
                        Intent c = new Intent(getApplicationContext(), Inbox.class);
                        startActivity(c);
                        break;
                    case R.id.ordernev:
                        Intent d = new Intent(getApplicationContext(), Order_Food.class);
                        startActivity(d);
                        break;

                    default:
                        break;
                }

                return true;
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (Toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    }
