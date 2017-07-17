package com.imagineappdev.wallaholic.mData;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DHANA on 14-06-2017.
 */

public class FavModel {
    public String url;
    public String username;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public FavModel(){}

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public Map<String, Boolean> getStars() {
        return stars;
    }

    public void setStars(Map<String, Boolean> stars) {
        this.stars = stars;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FavModel(String url, String username) {
        this.url = url;
        this.username = username;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("url", url);
        result.put("username", username);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }
}
