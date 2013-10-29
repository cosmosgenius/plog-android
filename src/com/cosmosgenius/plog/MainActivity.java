package com.cosmosgenius.plog;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final ListView listview = (ListView) findViewById(R.id.loglist);
        String[] values = new String [] { "Sharat" , "Praseetha", "PLog", "imagineer", "arun", "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };
        final ArrayList<String> list = new ArrayList<String>();
        
        for (int i = 0; i < values.length ; ++i) {
        	list.add(values[i]);
        }
        
        final ArrayAdapter<String> listada = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        
        listview.setAdapter(listada);
        
    }
}
