package com.example.dunzo.api;

import com.example.dunzo.responsemodel.FlickrRootResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("services/rest")
    Call<FlickrRootResponseModel> getPhotos(@Query("method") String method, @Query("api_key") String apiKey,
                                            @Query("text") String text, @Query("page") int page,
                                            @Query("per_page") int perPage, @Query("format") String format,
                                            @Query("nojsoncallback") int noJsonCallback);
}
