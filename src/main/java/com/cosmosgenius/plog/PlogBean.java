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

    public void setPlog(String plog) {
        this.plog = plog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static ArrayList<PlogBean> fromJSON(String JSONBody){
        return new Gson().fromJson(JSONBody, new TypeToken<ArrayList<PlogBean>>() {
        }.getType());
    }
}
