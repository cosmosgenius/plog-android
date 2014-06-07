package com.cosmosgenius.plog.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.cosmosgenius.plog.R;
import com.cosmosgenius.plog.adapter.LogListAdapter;
import com.cosmosgenius.plog.button.LogButton;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LogListActivity extends ListActivity {

    private LogListAdapter logListAdapter;
    @InjectView(R.id.add_log) LogButton add_log;
    @InjectView(R.id.input_log) EditText input_log;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_list_activity);

        ButterKnife.inject(this);

        logListAdapter  = new LogListAdapter(this);

        this.setListAdapter(logListAdapter);

        add_log.enable(false);
    }

    @OnClick(R.id.add_log)
    void OnAddLogClick(LogButton view){
        //getting the text for edit text and added to the list adapter
        if((input_log.getText() != null) && (input_log.getText().length() > 0)){
            logListAdapter.add(input_log.getText().toString());
        }
        //clearing the text after the text has been added to the list adapter
        input_log.setText("");
    }

    @OnTextChanged(value = R.id.input_log , callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void OnInputLogAfterTextChange(Editable editable){
        String text = editable.toString();
        add_log.enable(text.length() > 0);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
