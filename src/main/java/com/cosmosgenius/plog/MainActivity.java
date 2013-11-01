package com.cosmosgenius.plog;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView logList = (ListView) findViewById(R.id.loglist);
    }

}