package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationsFragment extends Fragment {
    ArrayList<Site> sites;
    SiteAdapter adapter;
    SearchView searchView;
    public LocationsFragment() {

    }

    public static LocationsFragment newInstance(String param1, String param2) {
        LocationsFragment fragment = new LocationsFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sites = new ArrayList<>();
        sites.add(new Site(R.string.site1_title, R.string.site1_summary, R.string.site1_description, R.drawable.castle_of_skalitz, "https://www.gnu.org/", "1234567890"));
        sites.add(new Site(R.string.site2_title, R.string.site2_summary, R.string.site2_description, R.drawable.henrys_house, "", ""));
        sites.add(new Site(R.string.site3_title, R.string.site3_summary, R.string.site3_description, R.drawable.deutschs_house, "", ""));
        sites.add(new Site(R.string.site4_title, R.string.site4_summary, R.string.site4_description, R.drawable.castle_of_skalitz, "", ""));
        sites.add(new Site(R.string.site5_title, R.string.site5_summary, R.string.site5_description, R.drawable.castle_of_skalitz, "", ""));
        sites.add(new Site(R.string.site6_title, R.string.site6_summary, R.string.site6_description, R.drawable.castle_of_skalitz, "", ""));
        sites.add(new Site(R.string.site7_title, R.string.site7_summary, R.string.site7_description, R.drawable.castle_of_skalitz, "", ""));
        sites.add(new Site(R.string.site8_title, R.string.site8_summary, R.string.site8_description, R.drawable.castle_of_skalitz, "", ""));
        sites.add(new Site(R.string.site9_title, R.string.site9_summary, R.string.site9_description, R.drawable.castle_of_skalitz, "", ""));
        sites.add(new Site(R.string.site10_title, R.string.site10_summary, R.string.site10_description, R.drawable.castle_of_skalitz, "", ""));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_locations, container, false);
        ListView listView = rootview.findViewById(R.id.site_list);
        View footerView = inflater.inflate(R.layout.footer_layout, listView, false);
        listView.addFooterView(footerView);
        return rootview;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        EdgeToEdge.enable(requireActivity());


        View mainContainer = view.findViewById(R.id.main);
        if (mainContainer != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainContainer, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
        searchView = view.findViewById(R.id.search_view);
        searchView.clearFocus();
        ListView lv = view.findViewById(R.id.site_list);
        adapter = new SiteAdapter(getActivity(), sites);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemView, int position, long id) {
                Site site = adapter.sites.get(position);
                Intent intent = new Intent(getActivity(), SiteActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("name", site.getNameRes());
                intent.putExtra("description", site.getDescriptionRes());
                intent.putExtra("phone", site.getPhone());
                intent.putExtra("website", site.getWebsite());
                intent.putExtra("image", site.getImageRes());
                startActivity(intent);

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

    }

    private void filterList(String text) {
        ArrayList<Site> filteredList = new ArrayList<Site>();
        for(Site site : sites){
            if(getString(site.getNameRes()).toLowerCase().contains(text.toLowerCase())){
                filteredList.add(site);
            }
        }
        if(filteredList.isEmpty())
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        else{
            adapter.setFilteredList(filteredList);
        }
    }
}
