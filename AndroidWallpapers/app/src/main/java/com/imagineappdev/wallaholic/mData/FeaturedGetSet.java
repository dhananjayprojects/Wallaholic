package com.imagineappdev.wallaholic.mData;

/**
 * Created by DHANA on 11-06-2017.
 */

public class FeaturedGetSet {
    public String url;
    public String username;
    public FeaturedGetSet(){
    }
    public FeaturedGetSet(String url,String username){
        this.url = url;
        this.username = username;
    }


    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
