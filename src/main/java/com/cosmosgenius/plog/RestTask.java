package com.cosmosgenius.plog;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RestTask extends AsyncTask<URL, Void ,ArrayList<PlogBean>>{
    public static String GET = "GET";
    public static String POST = "POST";
    public static String DELETE = "DELETE";

    private OkHttpClient client = new OkHttpClient();
    private EmitterInterface<ArrayList<PlogBean>> activity;
    private String method;
    private URL url;

    RestTask(EmitterInterface<ArrayList<PlogBean>> activity ,URL url,String method){
        this.activity = activity;
        this.url = url;
        this.method = method;
    }

    @Override
    protected ArrayList<PlogBean> doInBackground(URL... urls) {
        String JSONBody = "";
        try{
            JSONBody = get(url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return PlogBean.fromJSON(JSONBody);
    }

    @Override
    protected void onPostExecute(ArrayList<PlogBean>plogs){
        activity.done(plogs);
    }

    String get(URL url) throws IOException {
        HttpURLConnection connection = client.open(url);
        InputStream in = null;
        try {
            // Read the response.
            in = connection.getInputStream();
            byte[] response = readFully(in);
            return new String(response, "UTF-8");
        } finally {
            if (in != null) in.close();
        }
    }

    byte[] readFully(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int count; (count = in.read(buffer)) != -1; ) {
            out.write(buffer, 0, count);
        }
        return out.toByteArray();
    }
}
