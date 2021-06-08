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
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Offers extends AppCompatActivity {
    private DatabaseReference ndatabaseref;
    private RecyclerView Offerfoodresult;
    FirebaseRecyclerAdapter<Offeritem, foodviewofferholder> firebaseRecyclerAdapter;

    private android.support.v4.widget.DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        initNavDrawer();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Offers");
        Offerfoodresult = (RecyclerView) findViewById(R.id.offerfoodlist);
        Offerfoodresult.setHasFixedSize(true);
        Offerfoodresult.setLayoutManager(new LinearLayoutManager(this));
        ndatabaseref = FirebaseDatabase.getInstance().getReference().child("Offer_post");
        viewdata();
    }

    private void viewdata() {
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Offeritem, foodviewofferholder>(
                Offeritem.class,
                R.layout.offerfooditemview,
                foodviewofferholder.class,
                ndatabaseref
        ) {
            @Override
            protected void populateViewHolder(foodviewofferholder viewHolder, Offeritem model, int position) {
                viewHolder.setOffername(model.getOffername());
                viewHolder.setOfferquantity(model.getOfferquantity());
                viewHolder.setOfferprice(model.getOfferprice());
                viewHolder.setOfferestaurantname(model.getOfferrestaurantname());
                viewHolder.setOfferfoodid(model.getOfferfoodid());
                viewHolder.setOfferimage(getApplicationContext(), model.getOfferimage());
            }
        };
        Offerfoodresult.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    public static class foodviewofferholder extends RecyclerView.ViewHolder {
        View nView;

        public foodviewofferholder(View itemView) {
            super(itemView);
            nView = itemView;
        }

        public void setOffername(String offername) {
            TextView uname = nView.findViewById(R.id.offerfname);
            uname.setText(offername);
        }

        public void setOfferquantity(String offerquantity) {
            TextView uname = nView.findViewById(R.id.offerfquantity);
            uname.setText(offerquantity);
        }

        public void setOfferprice(String offerprice) {
            TextView uname = nView.findViewById(R.id.offerfprice);
            uname.setText(offerprice);
        }

        public void setOfferestaurantname(String offerestaurantname) {
            TextView uname = nView.findViewById(R.id.offerfres);
            uname.setText(offerestaurantname);
        }

        public void setOfferfoodid(String offerfoodid) {
            TextView uname = nView.findViewById(R.id.offerfid);
            uname.setText(offerfoodid);
        }

        public void setOfferimage(Context ctx, String offerimage) {
            ImageView u = nView.findViewById(R.id.offerfimg);
            Picasso.with(ctx).load(offerimage).into(u);
        }
    }
    private void initNavDrawer() {
        DrawerLayout = findViewById(R.id.navidrawer4);
        navigationView = findViewById(R.id.nv4);
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
