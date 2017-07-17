package com.imagineappdev.wallaholic.mRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.imagineappdev.wallaholic.Abstract;
import com.imagineappdev.wallaholic.R;
import com.imagineappdev.wallaholic.mCloud.CloudinaryClient;
import com.imagineappdev.wallaholic.mData.ImagesGetSet;
import com.imagineappdev.wallaholic.mPicasso.PicassoClient;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsHolder>{

    Context c;
    ArrayList<ImagesGetSet> images;
    public DetailsAdapter(Context c, ArrayList<ImagesGetSet> images) {
        this.c = c;
        this.images = images;
    }

    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.details_grid_view,parent,false);
        DetailsHolder holder=new DetailsHolder(v,c);
        return holder;
    }

    @Override
    public void onBindViewHolder(DetailsHolder holder, int position) {

        //IMAGE
        PicassoClient.downloadImage(c, CloudinaryClient.getImages(images.get(position).getUrl()),holder.img);
    }

    public class DetailsHolder extends RecyclerView.ViewHolder {

        ImageView img;
        RelativeLayout picDesc;
        public DetailsHolder(View itemView, final Context context) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.gridImage);
            picDesc = (RelativeLayout)itemView.findViewById(R.id.details_pic_desc);

            picDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Abstract) context).userItemClick(getAdapterPosition(),v);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

}
