package com.example.dreamland.foodloverclient;

import android.content.Context;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;

import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
public class Item extends AppCompatActivity {
    private DatabaseReference mdatabaseref;
    private RecyclerView Foodresult;
    FirebaseRecyclerAdapter<FoodItem, foodviewhoolder> firebaseRecyclerAdapter;

    private android.support.v4.widget.DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        initNavDrawer();



        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Food Item");
        Foodresult = (RecyclerView) findViewById(R.id.foodList);
        Foodresult.setHasFixedSize(true);
        Foodresult.setLayoutManager(new LinearLayoutManager(this));
        mdatabaseref = FirebaseDatabase.getInstance().getReference().child("Post_Item");
        Viewdata();
    }

    private void Viewdata() {
        // Query
        firebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<FoodItem, foodviewhoolder>(
                FoodItem.class,
                R.layout.singlemenuitem,
                foodviewhoolder.class,
                mdatabaseref
        ) {
            @Override
            protected void populateViewHolder(foodviewhoolder viewHolder, FoodItem model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setFood_description(model.getDescription());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setRestaurant_name(model.getRestaurantname());
                viewHolder.setFood_id(model.getFoodid());
                viewHolder.setFoodimage(getApplicationContext(),model.getFoodimage());

            }
        };
        Foodresult.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }
    public static class foodviewhoolder extends RecyclerView.ViewHolder {
        View mView;
        public foodviewhoolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setName(String name) {
            TextView vname = mView.findViewById(R.id.fname);
            vname.setText(name);
        }
        public void setFood_description(String description) {
            TextView vname = mView.findViewById(R.id.fdes);
            vname.setText(description);
        }
        public void setPrice(String price) {
            TextView vname = mView.findViewById(R.id.fprice);
            vname.setText(price);
        }
        public void setRestaurant_name(String restaurantname) {
            TextView vname = mView.findViewById(R.id.fres);
            vname.setText(restaurantname);
        }
        public void setFood_id(String foodid) {
            TextView vname = mView.findViewById(R.id.fid);
            vname.setText(foodid);
        }

        public void setFoodimage(Context ctx,String foodimage) {
            ImageView v = mView.findViewById(R.id.imgview);
            Picasso.with(ctx).load(foodimage).into(v);
        }

    }



    private void initNavDrawer() {
        DrawerLayout = findViewById(R.id.navidrawer2);
        navigationView = findViewById(R.id.nv2);
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

