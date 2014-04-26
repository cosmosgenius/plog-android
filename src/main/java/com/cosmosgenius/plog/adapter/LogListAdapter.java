package com.cosmosgenius.plog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cosmosgenius.plog.R;
import com.cosmosgenius.plog.bean.Log;

import java.util.ArrayList;
import java.util.List;

public class LogListAdapter extends BaseAdapter {
    private List<Log> m_logs;
    private Context m_context;

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
    public View getView(int i, View convertView, ViewGroup parent) {
        View rowView = convertView;
        Log log = getItem(i);
        TextView logText;
        ImageButton deleteButton;

        if(rowView == null){
            LayoutInflater inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.log_item,parent,false);
            logText = (TextView) rowView.findViewById(R.id.logText);
            deleteButton = (ImageButton) rowView.findViewById(R.id.btn_log_item_del);
            rowView.setTag(new ViewHolder(logText,deleteButton));
        } else {
            ViewHolder viewHolder = (ViewHolder) rowView.getTag();
            logText = viewHolder.m_textView;
            deleteButton = viewHolder.m_imageButton;
        }

        logText.setText(log.getPlog());

        deleteButton.setTag(Integer.valueOf(i));

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(m_context,String.valueOf(view.getTag()),Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return rowView;
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

    public LogListAdapter(Context context) {
        this(context,new ArrayList<Log>());
    }

    public LogListAdapter(Context context,List<Log> m_logs) {
        this.m_context = context;
        this.m_logs = m_logs;
    }

    public void updateList(List<Log> m_logs){
        this.m_logs = m_logs;
        notifyDataSetChanged();
    }
}

class ViewHolder{
    public final TextView m_textView;
    public final ImageButton m_imageButton;

    ViewHolder(TextView textView,ImageButton imageButton){
        this.m_textView = textView;
        this.m_imageButton = imageButton;
    }
}