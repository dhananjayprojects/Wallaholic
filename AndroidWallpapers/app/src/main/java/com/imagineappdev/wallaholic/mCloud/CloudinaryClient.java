package com.imagineappdev.wallaholic.mCloud;

import com.cloudinary.Cloudinary;

/**
 * Created by DHANA on 09-Mar-17.
 */

public class CloudinaryClient {

    public static String getImages(String imageName){
        Cloudinary cloud = new Cloudinary(MyConfiguration.getMyConfigs());

        String url = cloud.url().generate(imageName);
        return url;
    }
}
