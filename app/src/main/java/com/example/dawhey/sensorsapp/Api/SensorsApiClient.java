package com.example.dawhey.sensorsapp.Api;

import com.example.dawhey.sensorsapp.Models.Entries;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dawhey on 20.09.16.
 */
public interface SensorsApiClient {

    @GET("entries")
    Call<Entries> getEntries();
}
