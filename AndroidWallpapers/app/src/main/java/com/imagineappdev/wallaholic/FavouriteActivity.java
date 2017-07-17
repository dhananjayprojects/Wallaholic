package com.imagineappdev.wallaholic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.imagineappdev.wallaholic.mData.favLists;
import com.imagineappdev.wallaholic.mRecycler.favAdapter;

public class FavouriteActivity extends AppCompatActivity implements DetailsMediatorInterface{

    RecyclerView rv;
    favAdapter adapter;
    public static Context contextOfApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        Toolbar toolbar3 = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contextOfApplication = getApplicationContext();

        rv = (RecyclerView)findViewById(R.id.recycler_view3);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        setTitle("Favourites");

        adapter = new favAdapter(this, favLists.imagesGetSets());
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void userItemClick(int pos, View view) {

        Intent i = new Intent(view.getContext(), WallpaperActivity.class);
        i.putExtra("url", favLists.imagesGetSets().get(pos).url);
        view.getContext().startActivity(i);
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        adapter = new favAdapter(this, favLists.imagesGetSets());
        rv.setAdapter(adapter);
        //Refresh your stuff here
    }

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }
}
