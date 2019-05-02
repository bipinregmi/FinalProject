package com.example.spotlight_movies.models;

import com.google.gson.annotations.SerializedName;
/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */

public class BackdropImage {

    @SerializedName("backdrop_path")
    private String bannerImageLink;

    public String getBannerImageLink() {
        return bannerImageLink;
    }

    public void setBannerImageLink(String bannerImageLink) {
        this.bannerImageLink = bannerImageLink;
    }
}
