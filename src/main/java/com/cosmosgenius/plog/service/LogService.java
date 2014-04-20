package com.cosmosgenius.plog.service;

import com.cosmosgenius.plog.bean.Log;

import java.util.List;

import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface LogService {
    @GET("/")
    List<Log> getLogs();

    @GET("/{id}")
    Log getLog(@Path("id") String id);

    @POST("/")
    Log log();

    @DELETE("/")
    boolean deleteLog();
}
