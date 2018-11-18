package com.project.disease;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ImageListRequestResponse {

    public List<ImageInfo> getImageInfos() {
        return imageInfos;
    }

    @SerializedName("images")
    List<ImageInfo> imageInfos;


    class ImageInfo{
        public String getUrl() {
            return url;
        }

        @SerializedName("url")
        String url;

        public int getIndex() {
            return index;
        }

        @SerializedName("index")
        int index;

        @SerializedName("id")
        String id;

        public String getId() {
            return id;
        }
    }
}
