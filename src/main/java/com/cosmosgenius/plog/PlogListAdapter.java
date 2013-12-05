package com.cosmosgenius.plog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlogListAdapter extends BaseAdapter implements EmitterInterface<ArrayList<PlogBean>>{
    private ArrayList<PlogBean> m_plogs;
    Context context;
    URL url = null;

    private static class ViewHolder {
        public final TextView plogTextView;
        public final ImageButton plogListBtn;

        public ViewHolder(TextView logView, ImageButton plogListBtn) {
            this.plogTextView = logView;
            this.plogListBtn = plogListBtn;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView plogText;
        ImageButton plogListBtn;
        PlogBean plog = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.plog_item, parent, false);
            plogText = (TextView)convertView.findViewById(R.id.plogText);
            plogListBtn = (ImageButton)convertView.findViewById(R.id.btn_plog_item_del);
            convertView.setTag(new ViewHolder(plogText,plogListBtn));
        }else{
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            plogText = viewHolder.plogTextView;
            plogListBtn = viewHolder.plogListBtn;
        }
        plogListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        plogText.setText(plog.getPlog());
        return convertView;
    }

    @Override
    public void done(ArrayList<PlogBean> plogs) {
        this.setSrc(plogs);
        notifyDataSetChanged();
    }

    public void add(String new_plog){
        RestTask rest = new RestTask(this,url,RestTask.POST);
        PlogBean plog = new PlogBean();
        plog.setPlog(new_plog);
        this.m_plogs.add(plog);
        rest.execute(plog);
        notifyDataSetChanged();
    }

    public PlogListAdapter(Context context, ArrayList<PlogBean> plogs) {
        this.m_plogs = plogs;
        this.context = context;
    }

    public PlogListAdapter(Context context) {
        this.m_plogs = new ArrayList<PlogBean>();
        this.context = context;
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