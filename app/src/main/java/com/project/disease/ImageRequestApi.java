package com.project.disease;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

interface ImageRequestApi {

    @Multipart
    @POST("CallRecog")
    Call<ImageListRequestResponse> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);
}
