package com.imagineappdev.wallaholic.mData;

import java.util.ArrayList;

public class DetailsLists {

    public static ArrayList<ImagesGetSet> imagesGetSets(String title) {
        ArrayList<ImagesGetSet> images = new ArrayList<>();
        ImagesGetSet img;

        switch (title){
            case "Abstract" :
                img = new ImagesGetSet();
                img.setUrl("Abstract/pexels-photo-211157");
                images.add(img);
                img = new ImagesGetSet();
                img.setUrl("Abstract/pexels-photo-211157");
                images.add(img);
                img = new ImagesGetSet();
                img.setUrl("Abstract/pexels-photo-211157");
                images.add(img);
                img = new ImagesGetSet();
                img.setUrl("Abstract/pexels-photo-211157");
                images.add(img);
                img = new ImagesGetSet();
                img.setUrl("Abstract/pexels-photo-211157");
                images.add(img);
                img = new ImagesGetSet();
                img.setUrl("Abstract/pexels-photo-211157");
                images.add(img);
                img = new ImagesGetSet();
                img.setUrl("Abstract/pexels-photo-211157");
                images.add(img);
                img = new ImagesGetSet();
                img.setUrl("Abstract/pexels-photo-211157");
                images.add(img);
                img = new ImagesGetSet();
                img.setUrl("Abstract/pexels-photo-211157");
                images.add(img);


                break;
            case "Adventure":
                img = new ImagesGetSet();
                img.setUrl("Adventure/utah-mountain-biking-bike-biking-71104");
                images.add(img);
                break;
            case "Animal":
                img = new ImagesGetSet();
                img.setUrl("Animal/pexels-photo-171227");
                images.add(img);
                break;
            case "Architecture":
                img = new ImagesGetSet();
                img.setUrl("Architecture/pexels-photo-169647");
                images.add(img);
                break;
            default:
                break;
        }

        return images;
    }
}
