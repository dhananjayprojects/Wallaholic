package com.imagineappdev.wallaholic.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.imagineappdev.wallaholic.R;
import com.imagineappdev.wallaholic.WallpaperActivity;
import com.imagineappdev.wallaholic.mData.DetailsLists;
import com.kc.unsplash.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by DHANA on 15-07-2017.
 */

public class NewRecyclerAdapter extends RecyclerView.Adapter<NewRecyclerAdapter.ViewHolder> {
    private final List<Photo> photoList;
    private Context mContext;

    public NewRecyclerAdapter(List<Photo> photos, Context context) {
        photoList = photos;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_new, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Photo photo = photoList.get(position);

        Picasso.with(mContext).load(photo.getUrls().getRegular()).into(holder.image);
        holder.newPicDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, WallpaperActivity.class);
                i.putExtra("url", photo.getUrls().getRegular());
                i.putExtra("name",photo.getId());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout newPicDesc;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.grid_new);
            newPicDesc = (RelativeLayout)itemView.findViewById(R.id.new_pic_desc);
        }
    }
    public List<Photo> getPhotos() {
        return Collections.unmodifiableList(photoList);
    }
}
