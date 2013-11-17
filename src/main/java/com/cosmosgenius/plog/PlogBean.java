package com.cosmosgenius.plog;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class PlogBean {
    private String plog;
    private String id;

    public String getPlog() {
        return plog;
    }

    public PlogBean setPlog(String plog) {
        this.plog = plog;
        return this;
    }

    public String getId() {
        return id;
    }

    public PlogBean setId(String id) {
        this.id = id;
        return this;
    }

    public static ArrayList<PlogBean> fromJSON(String JSONBody){
        return new Gson().fromJson(JSONBody, new TypeToken<ArrayList<PlogBean>>() {
        }.getType());
    }

    public String toJSON(){
        return new Gson().toJson(this);
    }
}
