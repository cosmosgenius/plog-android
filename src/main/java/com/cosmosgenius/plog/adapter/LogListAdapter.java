package com.cosmosgenius.plog.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cosmosgenius.plog.bean.Log;

import java.util.ArrayList;
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

    public void add(String value){
        Log log = new Log(value);
        m_logs.add(log);
        notifyDataSetChanged();
    }

    public void delete(int position){
        m_logs.remove(position);
        notifyDataSetChanged();
    }

    public LogListAdapter() {
        this(new ArrayList<Log>());
    }

    public LogListAdapter(List<Log> m_logs) {
        this.m_logs = m_logs;
    }

    public void updateList(List<Log> m_logs){
        this.m_logs = m_logs;
        notifyDataSetChanged();
    }
}
