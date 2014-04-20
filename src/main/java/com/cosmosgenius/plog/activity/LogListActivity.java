package com.cosmosgenius.plog.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.cosmosgenius.plog.R;

public class LogListActivity extends Activity{

    private ImageButton add_log;
    private EditText input_log;
    private ListView log_list;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_list_activity);

        add_log     = (ImageButton) findViewById(R.id.add_log);
        input_log   = (EditText) findViewById(R.id.input_log);
        log_list    = (ListView) findViewById(R.id.log_list);

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
