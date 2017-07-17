package com.imagineappdev.wallaholic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.imagineappdev.wallaholic.mCloud.CloudinaryClient;
import com.imagineappdev.wallaholic.mData.FeaturedGetSet;
import com.imagineappdev.wallaholic.mData.FeaturedList;
import com.imagineappdev.wallaholic.mData.ImageLists;
import com.imagineappdev.wallaholic.mPicasso.PicassoClient;
import com.imagineappdev.wallaholic.mRecycler.MyAdapter;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class Featured extends Fragment {
    /*RecyclerView recyclerView;
    FeaturedAdapter adapter;
    LinearLayoutManager linearLayoutManager;*/

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    public static final String ANONYMOUS = "anonymous";
    private String mUsername;

    private FirebaseRemoteConfig firebaseRemoteConfig;
    private String Featured_Wallpaper_url = "featured_wallpaper_url";
    private String UrlOfWallpaper = "Abstract/pexels-photo-211157";
    String WallpaperUrl;

    ImageView imageView;

    public Featured() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_featured, container, false);

        imageView = (ImageView)view.findViewById(R.id.img);
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        firebaseRemoteConfig.setConfigSettings(configSettings);

        Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put(Featured_Wallpaper_url,UrlOfWallpaper);

        firebaseRemoteConfig.setDefaults(defaultConfigMap);
        fetchConfig();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WallpaperActivity.class);
                intent.putExtra("url",WallpaperUrl);
                startActivity(intent);
            }
        });

        return view;
    }

    public void fetchConfig() {
        long cacheExpiration = 3600;

        if (firebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()){
            cacheExpiration = 0;
        }

        firebaseRemoteConfig.fetch(cacheExpiration)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        firebaseRemoteConfig.activateFetched();
                        showWallpaper();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Featured Fragment","Error Fetching Config",e);
                        showWallpaper();
                    }
                });


    }

    private void showWallpaper() {
        WallpaperUrl = firebaseRemoteConfig.getString(Featured_Wallpaper_url);
        PicassoClient.downloadImage(getActivity(),CloudinaryClient.getImages(WallpaperUrl),imageView);
    }

}
