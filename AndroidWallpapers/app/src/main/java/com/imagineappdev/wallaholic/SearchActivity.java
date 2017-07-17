package com.imagineappdev.wallaholic;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.imagineappdev.wallaholic.mRecycler.NewRecyclerAdapter;
import com.kc.unsplash.Unsplash;
import com.kc.unsplash.models.Photo;
import com.kc.unsplash.models.SearchResults;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private final String CLIENT_ID = "f91421e2b94897e0ad44e9f87c641493d15452a801c0eaca16e4c26a798b708d";
    private Unsplash unsplash;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolba);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        unsplash = new Unsplash(CLIENT_ID);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        handleIntent(getIntent());
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            setTitle(query);
            //use the query to search your data somehow
            unsplash.searchPhotos(query,1,50, new Unsplash.OnSearchCompleteListener() {
                @Override
                public void onComplete(SearchResults results) {
                    Log.d("Photos", "Total Results Found " + results.getTotal());
                    List<Photo> photos = results.getResults();
                    NewRecyclerAdapter adapter = new NewRecyclerAdapter(photos,SearchActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                }

                @Override
                public void onError(String error) {
                    Log.d("Unsplash", error);
                }
            });
        }
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
}
