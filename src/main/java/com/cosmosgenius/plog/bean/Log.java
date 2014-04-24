package com.cosmosgenius.plog.bean;

public class Log {
    private String plog;
    private String _id;

    public String getPlog() {
        return plog;
    }

    public void setPlog(String plog) {
        this.plog = plog;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Log() {
        this("");
    }

    public Log(String log) {
        this(log,"");
    }

    public Log(String log, String _id) {
        this.plog = log;
        this._id = _id;
    }
}
