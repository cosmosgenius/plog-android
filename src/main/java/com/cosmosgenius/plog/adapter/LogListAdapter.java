package com.cosmosgenius.plog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cosmosgenius.plog.R;
import com.cosmosgenius.plog.bean.Log;
import com.cosmosgenius.plog.service.LogService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LogListAdapter extends BaseAdapter {
    private List<Log> m_logs;
    private Context m_context;
    private final LogService logService;

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
                delete((Integer) view.getTag());
            }
        });

        return rowView;
    }

    public void add(String value){
        Log log = new Log(value);
        try{
            logService.log(log,new Callback<Log>() {
                @Override
                public void success(Log log, Response response) {
                    m_logs.add(log);
                    notifyDataSetChanged();
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void delete(int position){
        Log log = getItem(position);
        m_logs.remove(position);
        logService.deleteLog(log.get_id(),new Callback<Boolean>() {
            @Override
            public void success(Boolean aBoolean, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        notifyDataSetChanged();
    }

    public LogListAdapter(Context context) {
        this(context,new ArrayList<Log>());
    }

    public LogListAdapter(Context context,List<Log> m_logs) {
        this.m_context = context;
        this.m_logs = m_logs;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(context.getString(R.string.url))
                .setErrorHandler(new MyErrorHandler())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        logService = restAdapter.create(LogService.class);
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

class MyErrorHandler implements ErrorHandler {
    @Override public Throwable handleError(RetrofitError cause) {
        Response r = cause.getResponse();
        if (r != null && r.getStatus() == 401) {
            cause.printStackTrace();
        }
        return cause;
    }
}
