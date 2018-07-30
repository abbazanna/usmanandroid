package com.project.disease;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImageListRequestResponse {

    public ArrayList<ImageInfo> getImageInfos() {
        return imageInfos;
    }

    @SerializedName("images")
    ArrayList<ImageInfo> imageInfos;


    class ImageInfo{
        @SerializedName("url")
        String url;
        @SerializedName("id")
        String id;
    }
}
