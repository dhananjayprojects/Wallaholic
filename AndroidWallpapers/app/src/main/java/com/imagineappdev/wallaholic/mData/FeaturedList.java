package com.imagineappdev.wallaholic.mData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FeaturedList {
    private static FirebaseDatabase myDatabase;
    private static DatabaseReference myRef;
    private static ChildEventListener mChildEventListener;
    public static ArrayList<ImagesGetSet> imagesGetSets() {
        final ArrayList<ImagesGetSet> images = new ArrayList<>();
        final ImagesGetSet[] img = new ImagesGetSet[1];
        myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference().child("featured");
        img[0].setName("Abstract");
        img[0].setUrl("Abstract/pexels-photo-211157");
        myRef.push().setValue(img);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                img[0] = dataSnapshot.getValue(ImagesGetSet.class);
                images.add(img[0]);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener(mChildEventListener);


        return images;
    }

}
