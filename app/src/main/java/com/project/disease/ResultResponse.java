package com.project.disease;


import android.app.Activity;
import android.content.Context;

import com.google.gson.annotations.SerializedName;

public class ResultResponse {
    static ImageRequestApi imageRequestApi;
    public String getResult() {
        return result;
    }

    public String getOrignalImageUrl() {
        return orignalImageUrl;
    }

    public String getSegmentUrl() {
        return segmentUrl;
    }

    @SerializedName("disease")
    String result;
    @SerializedName("orignal")
    String orignalImageUrl;

    @SerializedName("segment")
    String segmentUrl;

}
