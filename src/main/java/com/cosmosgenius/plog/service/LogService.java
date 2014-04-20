package com.cosmosgenius.plog.service;

import java.util.List;

import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;

public interface LogService {
    @GET("")
    List getLogs();

    @POST("")
    Object log();

    @DELETE("")
    boolean deleteLog();
}
