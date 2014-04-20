package com.cosmosgenius.plog.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cosmosgenius.plog.R;

public class LogListActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.id.log_list_activity);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
