package com.imagineappdev.wallaholic;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.imagineappdev.wallaholic.mData.DetailsLists;
import com.imagineappdev.wallaholic.mRecycler.DetailsAdapter;

public class Abstract extends AppCompatActivity implements DetailsMediatorInterface{

    RecyclerView rv;
    DetailsAdapter adapter;
    AlertDialog myDialog;
    String[] items = {"List","2 Grids","3 Grids"};
    String selectedItem = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv = (RecyclerView)findViewById(R.id.recycler_view2);
        final String title = getIntent().getStringExtra("name");
        setTitle(title);
/*
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose layout");
            builder.setIcon(R.drawable.ic_view_quilt_black_36dp);
            selectedItem = items[0];
            builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedItem = items[which];
                }
            });

            builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    *//*Toast toast = Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT);
                    toast.show();*//*
                    if (selectedItem.equals("List")){
                        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new DetailsAdapter(Abstract.this, DetailsLists.imagesGetSets(title));
                        rv.setAdapter(adapter);
                    }
                    else if (selectedItem.equals("2 Grids") ){
                        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                        adapter = new DetailsAdapter(Abstract.this, DetailsLists.imagesGetSets(title));
                        rv.setAdapter(adapter);
                    }
                    else{
                        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                        adapter = new DetailsAdapter(Abstract.this, DetailsLists.imagesGetSets(title));
                        rv.setAdapter(adapter);
                    }
                }
            });

            builder.setCancelable(false);
            myDialog = builder.create();


        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {



                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();*/

        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));




        /*String title = getIntent().getStringExtra("name");*/
        setTitle(title);

        adapter = new DetailsAdapter(this, DetailsLists.imagesGetSets(title));
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.abstract_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            /*case R.id.change:
                myDialog.show();

                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void userItemClick(int pos, View view) {

        String title = getIntent().getStringExtra("name");
        Intent i = new Intent(view.getContext(), WallpaperActivity.class);
        i.putExtra("url", DetailsLists.imagesGetSets(title).get(pos).url);
        i.putExtra("name",title);

        view.getContext().startActivity(i);
    }

}
