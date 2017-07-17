package com.imagineappdev.wallaholic.mData;

import java.util.ArrayList;

/**
 * Created by DHANA on 09-Mar-17.
 *
 * keytool -exportcert -list -v -alias androiddebugkey -keystore %USERPROFILE%\.android\debug.keystore
 *
 */

public class ImageLists {

    public static ArrayList<ImagesGetSet> imagesGetSets(){
        ArrayList<ImagesGetSet> images = new ArrayList<>();
        ImagesGetSet img = new ImagesGetSet();

        img.setName("Abstract");
        img.setUrl("Abstract/pexels-photo-211157");
        images.add(img);

        img = new ImagesGetSet();
        img.setName("Adventure");
        img.setUrl("Adventure/utah-mountain-biking-bike-biking-71104");
        images.add(img);

        img = new ImagesGetSet();
        img.setName("Animal");
        img.setUrl("Animal/pexels-photo-171227");
        images.add(img);

        img = new ImagesGetSet();
        img.setName("Architecture");
        img.setUrl("Architecture/pexels-photo-169647");
        images.add(img);

        return images;
    }




}
