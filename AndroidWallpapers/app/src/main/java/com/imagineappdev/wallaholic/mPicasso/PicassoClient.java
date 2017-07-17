package com.imagineappdev.wallaholic.mPicasso;

import android.content.Context;
import android.widget.ImageView;

import com.imagineappdev.wallaholic.R;
import com.squareup.picasso.Picasso;

/**
 * Created by DHANA on 09-Mar-17.
 */

public class PicassoClient {

    public static void downloadImage(Context c, String url, ImageView img){
        if(url != null &&url.length()>0){
            Picasso.with(c).load(url).fit().centerCrop()
                    .placeholder(R.drawable.placeholder_300x300)
                    .error(R.drawable.blur).into(img);
        }
    }

}
