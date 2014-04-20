package com.cosmosgenius.plog.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cosmosgenius.plog.bean.Log;

import java.util.List;

public class LogListAdapter extends BaseAdapter {
    private List<Log> m_logs;

    @Override
    public int getCount() {
        return m_logs.size();
    }

    @Override
    public Log getItem(int i) {
        return m_logs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log log = getItem(i);
        return null;
    }
}
