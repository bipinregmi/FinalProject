package com.example.spotlight_movies.Network;

import com.example.spotlight_movies.models.BackdropImage;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by 5-Star Production
 * Bipin , Kyle, Arnie, Anthony & Roborto.
 */
public class ImageResponse {
    @SerializedName("backdrops")
    private ArrayList<BackdropImage> bannerImageLinks;

    public ArrayList<BackdropImage> getBannerImageLinks() {
        return bannerImageLinks;
    }

    public void setBannerImageLinks(ArrayList<BackdropImage> bannerImageLinks) {
        this.bannerImageLinks = bannerImageLinks;
    }
}
