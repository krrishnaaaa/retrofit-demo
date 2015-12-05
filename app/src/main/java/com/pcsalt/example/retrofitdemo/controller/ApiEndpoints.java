package com.pcsalt.example.retrofitdemo.controller;

import com.pcsalt.example.retrofitdemo.model.Geocode;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by krrishnaaaa on Dec 06, 2015
 */
public interface ApiEndpoints {

    @GET("/maps/api/geocode/json")
    Call<Geocode> getGeocodeByLatLng(@QueryMap Map<String, String> params);

}
