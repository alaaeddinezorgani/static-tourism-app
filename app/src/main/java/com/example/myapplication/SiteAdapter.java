package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Site;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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


        Button shareButton = view.findViewById(R.id.site_fav_btn);
        shareButton.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            String name = context.getString(s.getNameRes());
            String website = s.getWebsite();
            String shareText;

            if (Locale.getDefault().getLanguage().equals("ar")) {
                shareText = "اكتشف : " + name + " على " + website;
            } else {
                shareText = "Check out this amazing location: " + name + " on " + website;
            }

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            Intent chooser = Intent.createChooser(shareIntent, "Share via");

            if (shareIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(chooser);
            }
        });

        return view;
    }

}
