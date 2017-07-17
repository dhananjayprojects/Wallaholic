package com.imagineappdev.wallaholic;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.EwHolder>{

    private String[] mDataset;

    public static class EwHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;
        public EwHolder(View v){
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);

        }

    }

    public SampleAdapter(String[] myDataset){
        mDataset = myDataset;
    }

    @Override
    public SampleAdapter.EwHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        EwHolder vh = new EwHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(EwHolder holder, int position){
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() { return mDataset.length; }
}
