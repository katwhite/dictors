package com.example.azureretrofit;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class DictorsAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<Dictor> dictors;

    public DictorsAdapter(Context ctx, ArrayList<Dictor> dictors) {
        this.ctx = ctx;
        this.dictors = dictors;
    }

    @Override
    public int getCount() {
        return dictors.size();
    }

    @Override
    public Object getItem(int position) {
        return dictors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dictor u = dictors.get(position);
        convertView = LayoutInflater.from(ctx).
                inflate(R.layout.item, parent, false);
        TextView tvText = convertView.findViewById(R.id.text);
        tvText.setText(u.toString());
        Log.d("mytag", u.toString());
        return convertView;
    }
}
