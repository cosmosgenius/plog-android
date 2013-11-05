package com.cosmosgenius.plog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LogListAdapter extends BaseAdapter{
    private ArrayList<String> m_logs;
    Context context;

    private static class ViewHolder {
        public final TextView logView;

        public ViewHolder(TextView logView) {
            this.logView = logView;
        }
    }

    @Override
    public int getCount() {
        return m_logs.size();
    }

    @Override
    public String getItem(int position) {
        return m_logs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        TextView logText;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.log_item, parent, false);
            logText = (TextView)convertView.findViewById(R.id.logText);
            convertView.setTag(new ViewHolder(logText));
        }else{
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            logText = viewHolder.logView;
        }
        logText.setText(getItem(i));
        return convertView;
    }

    public void add(String new_log){
        this.m_logs.add(new_log);
        notifyDataSetChanged();
    }

    public LogListAdapter(Context context,ArrayList<String> logs) {
        this.m_logs = logs;
        this.context = context;
    }

    public LogListAdapter(Context context) {
        this.m_logs = new ArrayList<String>();
        this.context = context;
    }
}