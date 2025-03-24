package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Site;

import java.util.ArrayList;
import java.util.List;

public class SiteAdapter extends BaseAdapter {
    ArrayList<Site> sites;
    Context context;
    public SiteAdapter(Context context, ArrayList<Site> sites){
        this.context=context;
        this.sites = (sites != null) ? sites : new ArrayList<>();
    }

    @Override
    public int getCount() {
        return sites.size();
    }

    @Override
    public Object getItem(int position) {
        return sites.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void setFilteredList(ArrayList<Site> filteredList){
        sites = filteredList;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Site s = sites.get(position);
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.list_item, null);
        }
        ImageView siteImage = view.findViewById(R.id.site_img);
        TextView siteTitle = view.findViewById(R.id.site_title);
        TextView siteSummary = view.findViewById(R.id.site_summary);
        siteImage.setImageResource(s.getImageRes());
        siteTitle.setText(s.getNameRes());
        siteSummary.setText(s.getSummaryRes());
        return view;
    }

}
