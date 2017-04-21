package com.project.fuyou.battle2048;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fuyou on 2017/4/17.
 */

public class HistoryAdapter extends ArrayAdapter<History> {
    private int rescouceId;
    public HistoryAdapter(Context context, int textViewResourceId, List<History> objects) {
        super(context, textViewResourceId, objects);
        this.rescouceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        History history = getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(rescouceId,parent,false);
        TextView t1,t2,t3,t4;
        t1=(TextView) view.findViewById(R.id.textView9);
        t2=(TextView) view.findViewById(R.id.textView10);
        t3=(TextView) view.findViewById(R.id.textView7);
        t4=(TextView) view.findViewById(R.id.textView12);
        t1.setText(history.getUserName());
        t2.setText(history.getScore());
        t3.setText(history.getStep());
        t4.setText(history.getCreateTime());
        return view;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
