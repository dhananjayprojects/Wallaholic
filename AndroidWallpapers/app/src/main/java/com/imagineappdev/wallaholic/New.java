package com.imagineappdev.wallaholic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.imagineappdev.wallaholic.mRecycler.MyAdapter;
import com.imagineappdev.wallaholic.mRecycler.NewRecyclerAdapter;
import com.kc.unsplash.Unsplash;
import com.kc.unsplash.api.Order;
import com.kc.unsplash.models.Photo;

import java.util.LinkedList;
import java.util.List;

public class New extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private final String CLIENT_ID = "f91421e2b94897e0ad44e9f87c641493d15452a801c0eaca16e4c26a798b708d";
    private Unsplash unsplash;
    int pageNumber = 1;
    public New() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new,container,false);
        unsplash = new Unsplash(CLIENT_ID);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_new);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        unsplash.getPhotos(1, 30, Order.LATEST, new Unsplash.OnPhotosLoadedListener() {
            @Override
            public void onComplete(List<Photo> photos) {
                Log.d("Photos", "Photos Fetched " + photos.size());
                NewRecyclerAdapter adapter = new NewRecyclerAdapter(photos, getActivity());
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {

            }
        });

        mRecyclerView.addOnScrollListener(createInfiniteScrollListener());

        return view;
    }

    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(30, mLinearLayoutManager) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
                // load your items here
                // logic of loading items will be different depending on your specific use case

                // when new items are loaded, combine old and new items, pass them to your adapter
                // and call refreshView(...) method from InfiniteScrollListener class to refresh RecyclerView

                pageNumber += 1;
                unsplash.getPhotos(pageNumber, 30, Order.LATEST, new Unsplash.OnPhotosLoadedListener() {
                    @Override
                    public void onComplete(List<Photo> photos) {
                        Log.d("Photos", "Photos Fetched " + photos.size());
                        NewRecyclerAdapter newAdapter = createNewRecyclerAdapter(photos);
                        refreshView(mRecyclerView, newAdapter, firstVisibleItemPosition);
                    }

                    @Override
                    public void onError(String error) {

                    }
                });

            }
        };
    }

    private NewRecyclerAdapter createNewRecyclerAdapter(List<Photo> newPhotos){
        final NewRecyclerAdapter adapter = (NewRecyclerAdapter)mRecyclerView.getAdapter();
        final List<Photo> oldPhotos = adapter.getPhotos();
        final List<Photo> PhotoList = new LinkedList<>();
        PhotoList.addAll(oldPhotos);
        PhotoList.addAll(newPhotos);
        return new NewRecyclerAdapter(PhotoList,getActivity());
    }
}
