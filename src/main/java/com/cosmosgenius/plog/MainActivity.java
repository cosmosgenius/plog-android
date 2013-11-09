package com.cosmosgenius.plog;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends Activity {
    ImageButton btn_log;            // send Button resource
    EditText log_text;              // text input box
    LogListAdapter logListAdapter;  // The adapter attached to the listview

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // setting the objects
        btn_log = (ImageButton) findViewById(R.id.btn_log);
        log_text = (EditText) findViewById(R.id.input_log);
        ListView log_list = (ListView) findViewById(R.id.log_list);

        // By default disabling the Send button
        // TODO : Create a custom button with custom enable and disable option
        enableBtn_log(false);

        //attaching the Adapter to the list view
        logListAdapter = new LogListAdapter(getApplicationContext());
        log_list.setAdapter(logListAdapter);
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
}