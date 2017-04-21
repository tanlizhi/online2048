package com.project.fuyou.battle2048.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by fuyou on 2017/4/12.
 */

public class HistoryItemAdapter extends ArrayAdapter<HistoryItem> {

    public HistoryItemAdapter(Context context, int textViewResouceId, List<HistoryItem> objects){
        super(context,textViewResouceId,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
