package com.cosmosgenius.plog;

import android.content.Context;
import android.widget.ArrayAdapter;

public class LogListAdapter extends ArrayAdapter<String>{

    public LogListAdapter(Context context) {
        super(context,R.layout.log_item);
    }
}
