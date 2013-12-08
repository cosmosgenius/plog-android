package com.cosmosgenius.plog;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RestTask extends AsyncTask<PlogBean, Void ,ArrayList<PlogBean>>{
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
    protected ArrayList<PlogBean> doInBackground(PlogBean... plogs) {
        String JSONBody = "";
        try{
            if(method.equals(GET)){
                JSONBody = get(url);
            }else if(method.equals(POST)){
                JSONBody = post(url,plogs[0].toJSON().getBytes());
                JSONBody = get(url);
            }else if(method.equals(DELETE)){
                delete(url);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return PlogBean.fromJSON(JSONBody);
    }

    @Override
    protected void onPostExecute(ArrayList<PlogBean>plogs){
        if(method.equals(DELETE))
            return;
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

    String post(URL url,byte[] body) throws IOException {
        HttpURLConnection connection = client.open(url);
        OutputStream out = null;
        InputStream in = null;
        try {
            // Write the request.
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod(POST);
            out = connection.getOutputStream();
            out.write(body);
            out.close();

            // Read the response.
            if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new IOException("Unexpected HTTP response: "
                        + connection.getResponseCode() + " " + connection.getResponseMessage());
            }
            in = connection.getInputStream();
            byte[] response = readFully(in);
            return new String(response, "UTF-8");
        } finally {
            // Clean up.
            if (out != null) out.close();
            if (in != null) in.close();
        }
    }

    void delete(URL url) throws IOException {
        HttpURLConnection connection = client.open(url);
        connection.setRequestMethod(DELETE);
        int res = connection.getResponseCode();
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
