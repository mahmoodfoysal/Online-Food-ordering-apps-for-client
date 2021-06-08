package com.example.dreamland.foodloverclient;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Order_Food extends AppCompatActivity {

    EditText cliname,cliphn,cliaddress,cliorder,cliqun;
    Button sentbtn;
    DatabaseReference userref;
    StorageReference userStorageRef=null;

    private android.support.v4.widget.DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_food);

        initNavDrawer();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        cliname=(EditText)findViewById(R.id.cliname);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Order Food");
        cliphn=(EditText)findViewById(R.id.cliphn);
        cliaddress=(EditText)findViewById(R.id.cliaddress);
        cliorder=(EditText)findViewById(R.id.foodidorder);
        cliqun=(EditText)findViewById(R.id.clquan);


        sentbtn=(Button)findViewById(R.id.sentbtn);
        userStorageRef= FirebaseStorage.getInstance().getReference("Order");
        userref= FirebaseDatabase.getInstance().getReference("Order");
        sentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na=cliname.getText().toString().trim();
                String add=cliaddress.getText().toString().trim();
                String or=cliorder.getText().toString().trim();
                String qn=cliqun.getText().toString().trim();
                String pn=cliphn.getText().toString().trim();

                //Creating cild
                if(!TextUtils.isEmpty(na)&&!TextUtils.isEmpty(add)&&!TextUtils.isEmpty(or)&&!TextUtils.isEmpty(or)&&!TextUtils.isEmpty(qn)&&!TextUtils.isEmpty(pn)){
                    final DatabaseReference order=userref.push();
                    //Uploading data in database
                    order.child("clientname").setValue(na);
                    order.child("clientaddress").setValue(add);
                    order.child("clientorder").setValue(or);
                    order.child("clientquantity").setValue(qn);
                    order.child("clientphone").setValue(pn);
                    Toast.makeText(getApplicationContext(),"Sent Order",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void initNavDrawer() {
        DrawerLayout = findViewById(R.id.navidrawer5);
        navigationView = findViewById(R.id.nv5);
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
