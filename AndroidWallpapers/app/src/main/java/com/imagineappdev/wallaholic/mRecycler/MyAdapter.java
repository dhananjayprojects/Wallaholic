package com.imagineappdev.wallaholic.mRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imagineappdev.wallaholic.Categories;
import com.imagineappdev.wallaholic.MainActivity;
import com.imagineappdev.wallaholic.R;
import com.imagineappdev.wallaholic.mCloud.CloudinaryClient;
import com.imagineappdev.wallaholic.mData.ImagesGetSet;
import com.imagineappdev.wallaholic.mPicasso.PicassoClient;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context c;
    OnItemClickListener mItemClickListener;
    ArrayList<ImagesGetSet> images;
    public MyAdapter(Context c, ArrayList<ImagesGetSet> images) {
        this.c = c;
        this.images = images;
    }

    @Override
    public MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.nameTxt.setText(images.get(position).getName());
        //IMAGE
        PicassoClient.downloadImage(c,CloudinaryClient.getImages(images.get(position).getUrl()),holder.img);
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img;
        TextView nameTxt;
        RelativeLayout picDesc;
        public MyHolder(View itemView) {
            super(itemView);
            nameTxt = (TextView)itemView.findViewById(R.id.txt);
            img= (ImageView) itemView.findViewById(R.id.grid);
            picDesc = (RelativeLayout)itemView.findViewById(R.id.pic_desc);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
