package com.example.pasman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ArrayList<PasManModal> pasManModals;
    private DBHandler dbhandler;
    private ItemRVAdaptor itemRVAdaptor;
    private RecyclerView itemsRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        // initializing our all variable
        pasManModals = new ArrayList<>();
        dbhandler = new DBHandler(MainActivity.this);

        // getting our course array
        // list from db handler class.
        pasManModals = dbhandler.readPassTable();

        // on below line passing our array lost to our adapter class.
        itemRVAdaptor = new ItemRVAdaptor(pasManModals, this);
        itemsRV = findViewById(R.id.idRVItem);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        itemsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        itemsRV.setAdapter(itemRVAdaptor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.toString()){
            case "Add New":Bundle dataBundle = new Bundle();
              dataBundle.putInt("id", 0);
              Intent intent = new Intent(getApplicationContext(),EntitiForm.class);
              intent.putExtras(dataBundle);
              startActivity(intent);
              return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}