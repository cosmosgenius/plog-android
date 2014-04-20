package com.cosmosgenius.plog.button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class LogButton extends ImageButton{

    public LogButton(Context context) {
        super(context);
    }

    public LogButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void enable(boolean value){
        this.setEnabled(value);
        if(value){
            this.setAlpha(1.0f);
        }else{
            this.setAlpha(0.5f);
        }
    }
}
