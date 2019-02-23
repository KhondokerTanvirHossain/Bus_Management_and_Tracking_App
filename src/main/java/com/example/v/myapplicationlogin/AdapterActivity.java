package com.example.v.myapplicationlogin;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterActivity extends ArrayAdapter<RouteOption> {
    public AdapterActivity(@NonNull Context context, int resource, ArrayList<RouteOption> options) {
        super(context, resource,options);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view,parent,false);
        RouteOption current = getItem(position);
        TextView textStart = (TextView)listItemView.findViewById(R.id.start);
        textStart.setText(current.getStart());
        TextView textStop = (TextView)listItemView.findViewById(R.id.end);
        textStop.setText(current.getStop());
        TextView textTime = (TextView)listItemView.findViewById(R.id.time);
        textTime.setText(current.getmTime());
        TextView textGender = (TextView)listItemView.findViewById(R.id.gender);
        textGender.setText(current.getGender());
        return listItemView;
    }
}
