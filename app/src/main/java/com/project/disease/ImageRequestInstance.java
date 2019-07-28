package com.project.disease;

import android.app.Activity;
import android.content.Context;


public class ImageRequestInstance {

    static ImageRequestApi imageRequestApi;

    public static ImageRequestApi getInstance(Activity activity){
        if(imageRequestApi != null){
            return imageRequestApi;
        }

        imageRequestApi= RetrofitInstance.getRetrofit(activity.
                getSharedPreferences(SettingsFragment.PREFS_NAME, Context.MODE_PRIVATE).
                getString(SettingsFragment.URL_KEY,null),
                activity.getSharedPreferences(SettingsFragment.PREFS_NAME, Context.MODE_PRIVATE).
                        getString(SettingsFragment.PORT_NUMBER_KEY,null)
        ).create(ImageRequestApi.class);
        return imageRequestApi;
    };
}
