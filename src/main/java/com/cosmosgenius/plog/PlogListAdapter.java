package com.cosmosgenius.plog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlogListAdapter extends BaseAdapter implements EmitterInterface<ArrayList<PlogBean>>{
    private ArrayList<PlogBean> m_plogs;
    Context context;

    private static class ViewHolder {
        public final TextView plogView;

        public ViewHolder(TextView logView) {
            this.plogView = logView;
        }
    }

    @Override
    public int getCount() {
        return m_plogs.size();
    }

    @Override
    public PlogBean getItem(int position) {
        return m_plogs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        TextView plogText;
        PlogBean plog = getItem(i);
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.plog_item, parent, false);
            plogText = (TextView)convertView.findViewById(R.id.plogText);
            convertView.setTag(new ViewHolder(plogText));
        }else{
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            plogText = viewHolder.plogView;
        }
        plogText.setText(plog.getPlog());
        return convertView;
    }

    @Override
    public void done(ArrayList<PlogBean> plogs) {
        this.setSrc(plogs);
        notifyDataSetChanged();
    }

    public void add(String new_plog){
        PlogBean plog = new PlogBean();
        plog.setPlog(new_plog);
        this.m_plogs.add(plog);
        notifyDataSetChanged();
    }

    public PlogListAdapter(Context context, ArrayList<PlogBean> plogs) {
        this.m_plogs = plogs;
        this.context = context;
    }

    public PlogListAdapter(Context context) {
        this.m_plogs = new ArrayList<PlogBean>();
        this.context = context;
        URL url = null;
        try {
            url = new URL(context.getString(R.string.url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        RestTask rest = new RestTask(this,url,RestTask.GET);
        rest.execute();
    }

    public void setSrc(ArrayList<PlogBean> plogs){
        this.m_plogs = plogs;
        notifyDataSetChanged();
    }
}