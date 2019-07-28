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

    public String getAccuracy() {
        return accuracy;
    }

    public String getDiseaseDefinition() {
        return diseaseDefinition;
    }

    public String getTreatmentSolution() {
        return treatmentSolution;
    }

    public String getServerError() {
        return serverError;
    }

    @SerializedName(ServerContract.SERVER_DISEASE)
    String result;

    @SerializedName(ServerContract.SERVER_ACCURACY)
    String accuracy;
    @SerializedName(ServerContract.SERVER_ORIGINAL)
    String orignalImageUrl;

    @SerializedName(ServerContract.SERVER_SEGMENT)
    String segmentUrl;

    @SerializedName(ServerContract.SERVER_DEFINITION)
    String diseaseDefinition;

    @SerializedName(ServerContract.SERVER_SOLUTION)
    String treatmentSolution;

    public String getMean() {
        return mean;
    }

    public String getCorrelation() {
        return Correlation;
    }

    public String getEntropy() {
        return entropy;
    }

    public String getKurtosis() {
        return kurtosis;
    }

    public String getContrast() {
        return contrast;
    }

    public String getSmoothness() {
        return smoothness;
    }

    public String getVariance() {
        return variance;
    }

    public String getArea() {
        return area;
    }

    public String getIDM() {
        return IDM;
    }

    public String getEnergy() {
        return energy;
    }

    public String getSTD() {
        return STD;
    }

    public String getSkweness() {
        return skweness;
    }

    public String getHomogeneity() {
        return homogeneity;
    }

    public String getRMS() {
        return RMS;
    }

    @SerializedName(ServerContract.SERVER_MEAN)
    String mean;

    @SerializedName(ServerContract.SERVER_CORRELATION)
    String Correlation;

    @SerializedName(ServerContract.SERVER_ENTROPY)
    String entropy;


    @SerializedName(ServerContract.SERVER_KURTOSIS)
    String kurtosis;

    @SerializedName(ServerContract.SERVER_CONTRAST)
    String contrast;

    @SerializedName(ServerContract.SERVER_SMOOTHNESS)
    String smoothness;

    @SerializedName(ServerContract.SERVER_VARIANCE)
    String variance;

    @SerializedName(ServerContract.SERVER_AREA)
    String area;

    @SerializedName(ServerContract.SERVER_IDM)
    String IDM;
    @SerializedName(ServerContract.SERVER_ENERGY)
    String energy;
    @SerializedName(ServerContract.SERVER_STD)
    String STD;
    @SerializedName(ServerContract.SERVER_SKEWNESS)
    String skweness;
    @SerializedName(ServerContract.SERVER_HOMOGENEITY)
    String homogeneity;
    @SerializedName(ServerContract.SERVER_RMS)
    String RMS;


    @SerializedName("error")
    String serverError;

}
