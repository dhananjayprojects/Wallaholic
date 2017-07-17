package com.imagineappdev.wallaholic;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imagineappdev.wallaholic.mRecycler.NewRecyclerAdapter;
import com.kc.unsplash.Unsplash;
import com.kc.unsplash.api.Order;
import com.kc.unsplash.models.Photo;

import java.util.List;


public class Popular extends Fragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private final String CLIENT_ID = "f91421e2b94897e0ad44e9f87c641493d15452a801c0eaca16e4c26a798b708d";
    private Unsplash unsplash;
    public Popular() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular,container,false);
        unsplash = new Unsplash(CLIENT_ID);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_popular);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        unsplash.getPhotos(1, 30, Order.POPULAR, new Unsplash.OnPhotosLoadedListener() {
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
        return view;
    }
}
