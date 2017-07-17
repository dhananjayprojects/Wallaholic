package com.imagineappdev.wallaholic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imagineappdev.wallaholic.mData.ImageLists;
import com.imagineappdev.wallaholic.mRecycler.MyAdapter;

public class Categories extends Fragment{

    RecyclerView recyclerView;
    MyAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    public Categories() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        adapter = new MyAdapter(getActivity(), ImageLists.imagesGetSets());
        recyclerView.setAdapter(adapter);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.SetOnItemClickListener(new MyAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v , int position) {
                Intent i = new Intent(v.getContext(), Abstract.class);
                i.putExtra("name", ImageLists.imagesGetSets().get(position).name);
                v.getContext().startActivity(i);
            }
        });
        return rootView;
    }

}
