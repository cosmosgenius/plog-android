package com.cosmosgenius.plog.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.cosmosgenius.plog.R;
import com.cosmosgenius.plog.adapter.LogListAdapter;
import com.cosmosgenius.plog.button.LogButton;

public class LogListActivity extends Activity{

    private LogListAdapter logListAdapter;
    private LogButton add_log;
    private EditText input_log;
    private ListView log_list;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_list_activity);

        add_log         = (LogButton) findViewById(R.id.add_log);
        input_log       = (EditText) findViewById(R.id.input_log);
        log_list        = (ListView) findViewById(R.id.log_list);
        logListAdapter  = new LogListAdapter();

        log_list.setAdapter(logListAdapter);

        add_log.enable(false);

        add_log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //getting the text for edit text and added to the list adapter
                if((input_log.getText() != null) && (input_log.getText().length() > 0)){
                    logListAdapter.add(input_log.getText().toString());
                }
                //clearing the text after the text has been added to the list adapter
                input_log.setText("");
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();

        input_log.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                add_log.enable(text.length() > 0);
            }
        });
    }
}
