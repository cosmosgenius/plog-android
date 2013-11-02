package com.cosmosgenius.plog;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
    ImageButton btn_log;
    EditText log_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        btn_log = (ImageButton) findViewById(R.id.btn_log);
        log_text = (EditText) findViewById(R.id.input_log);
        enableBtn_log(false);

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
                if(text.length() > 0){
                    enableBtn_log(true);
                }else{
                    enableBtn_log(false);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void btn_log_click(View view){
        log_text = (EditText) findViewById(R.id.input_log);
        log_text.setText("");
        enableBtn_log(false);
    }

    void enableBtn_log(boolean value){
        btn_log.setEnabled(value);
        if(value){
            btn_log.setAlpha(1.0f);
        }else{
            btn_log.setAlpha(0.5f);
        }
    }
}