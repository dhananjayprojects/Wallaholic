package com.imagineappdev.wallaholic.mRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.imagineappdev.wallaholic.FavouriteActivity;
import com.imagineappdev.wallaholic.R;
import com.imagineappdev.wallaholic.mCloud.CloudinaryClient;
import com.imagineappdev.wallaholic.mData.ImagesGetSet;
import com.imagineappdev.wallaholic.mPicasso.PicassoClient;

import java.util.ArrayList;

public class favAdapter extends RecyclerView.Adapter<favAdapter.favHolder>{


    Context c;
    ArrayList<ImagesGetSet> images;
    public favAdapter(Context c, ArrayList<ImagesGetSet> images) {
        this.c = c;
        this.images = images;
    }

    @Override
    public favHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.details_grid_view,parent,false);
        favHolder holder=new favHolder(v,c);
        return holder;
    }

    @Override
    public void onBindViewHolder(favHolder holder, int position) {

        //IMAGE
        PicassoClient.downloadImage(c, CloudinaryClient.getImages(images.get(position).getUrl()),holder.img);
    }

    public class favHolder extends RecyclerView.ViewHolder {

        ImageView img;
        RelativeLayout picDesc;
        public favHolder(View itemView, final Context context) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.gridImage);
            picDesc = (RelativeLayout)itemView.findViewById(R.id.details_pic_desc);

            picDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((FavouriteActivity) context).userItemClick(getAdapterPosition(),v);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

}
