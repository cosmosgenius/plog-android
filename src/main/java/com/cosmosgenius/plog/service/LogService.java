package com.cosmosgenius.plog.service;

import com.cosmosgenius.plog.bean.Log;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface LogService {
    @GET("/")
    void Log(Callback<List<Log>> cb);

    @GET("/{id}")
    void Log(@Path("id") String id, Callback<Log> cb);

    @POST("/")
    void log(@Body Log log, Callback<Log> cb);

    @DELETE("/{id}")
    void deleteLog(@Path("id") String id, Callback<Boolean> cb);
}
