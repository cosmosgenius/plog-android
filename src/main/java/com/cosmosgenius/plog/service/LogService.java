package com.cosmosgenius.plog.service;

import com.cosmosgenius.plog.bean.Log;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface LogService {
    @GET("/")
    List<Log> Log();

    @GET("/{id}")
    Log Log(@Path("id") String id);

    @POST("/")
    Log log(@Body Log log);

    @DELETE("/{id}")
    boolean deleteLog(@Path("id") String id);
}
