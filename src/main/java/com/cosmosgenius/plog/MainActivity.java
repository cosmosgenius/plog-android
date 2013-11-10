package com.cosmosgenius.plog;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity {
    ImageButton btn_log;            // send Button resource
    EditText log_text;              // text input box
    LogListAdapter logListAdapter;  // The adapter attached to the listview
    ListView log_list;              // The List view

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // setting the objects
        btn_log  = (ImageButton) findViewById(R.id.btn_log);
        log_text = (EditText) findViewById(R.id.input_log);
        log_list = (ListView) findViewById(R.id.log_list);

        // By default disabling the Send button
        // TODO : Create a custom button with custom enable and disable option
        enableBtn_log(false);
        logListAdapter = new LogListAdapter(this);
        try {
            new restgetlist().execute(new URL("http://log.cosmosgenius.webfactional.com"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //attaching a text change listener to the edit text to find whether to
        //enable the send button or not
        log_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                enableBtn_log(text.length() > 0);
            }
        });
    }

    //send button click event handler
    public void btn_log_click(View view){
        //getting the text for edit text and added to the list adapter
        if((log_text.getText() != null) && (log_text.getText().length() > 0)){
            logListAdapter.add(log_text.getText().toString());
        }
        //clearing the text after the text has been added to the list adapter
        log_text.setText("");
    }

    //Function to whether enable or disable the function
    void enableBtn_log(boolean value){
        btn_log.setEnabled(value);
        if(value){
            btn_log.setAlpha(1.0f);
        }else{
            btn_log.setAlpha(0.5f);
        }
    }

    private class restgetlist extends AsyncTask< URL, Void ,ArrayList<String>>{
        OkHttpClient client = new OkHttpClient();

        @Override
        protected ArrayList<String> doInBackground(URL... urls) {
            ArrayList<String> logs = new ArrayList<String>();
            String log;
            try{
                log = get(urls[0]);
                logs.add(log);
            }catch (Exception e){
                e.printStackTrace();
            }
            return logs;
        }

        @Override
        protected void onPostExecute(ArrayList<String> logs){
            logListAdapter.setSrc(logs);
            log_list.setAdapter(logListAdapter);
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
}