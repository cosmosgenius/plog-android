package com.cosmosgenius.plog;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends Activity{
    ImageButton btn_plog;            // send Button resource
    EditText plog_text;              // text input box
    PlogListAdapter plogListAdapter;  // The adapter attached to the listview
    ListView plog_list;              // The List view

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // setting the objects
        btn_plog  = (ImageButton) findViewById(R.id.btn_plog);
        plog_text = (EditText) findViewById(R.id.input_plog);
        plog_list = (ListView) findViewById(R.id.plog_list);

        // By default disabling the Send button
        // TODO : Create a custom button with custom enable and disable option
        enableBtn_plog(false);
        plogListAdapter = new PlogListAdapter(this);
        plog_list.setAdapter(plogListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        //attaching a text change listener to the edit text to find whether to
        //enable the send button or not
        plog_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                enableBtn_plog(text.length() > 0);
            }
        });
    }

    //send button click event handler
    public void btn_plog_click(View view){
        //getting the text for edit text and added to the list adapter
        if((plog_text.getText() != null) && (plog_text.getText().length() > 0)){
            plogListAdapter.add(plog_text.getText().toString());
        }
        //clearing the text after the text has been added to the list adapter
        plog_text.setText("");
    }

    //Function to whether enable or disable the function
    void enableBtn_plog(boolean value){
        btn_plog.setEnabled(value);
        if(value){
            btn_plog.setAlpha(1.0f);
        }else{
            btn_plog.setAlpha(0.5f);
        }
    }
}