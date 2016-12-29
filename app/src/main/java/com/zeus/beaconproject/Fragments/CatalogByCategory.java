package com.zeus.beaconproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zeus.beaconproject.MainActivity;
import com.zeus.beaconproject.Models.PaginatedItemResult;
import com.zeus.beaconproject.Models.WalmartItem;
import com.zeus.beaconproject.ProductAdapter;
import com.zeus.beaconproject.R;

import java.util.ArrayList;

/**
 * Created by Zeus on 29-Dec-16.
 */

public class CatalogByCategory extends Fragment implements AdapterView.OnItemClickListener{

    PaginatedItemResult paginatedItemResult;
    ArrayList<WalmartItem> curItemList;
    Bundle recBundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.catalog_by_category_fragment, container, false);
        recBundle=MainActivity.catalogByCategoryBundle;
        if(recBundle!=null){
            paginatedItemResult= (PaginatedItemResult) recBundle.get("impData");
            curItemList=paginatedItemResult.items;
            ListView listViewCatalog=(ListView) v.findViewById(R.id.ListViewCatalog);
            listViewCatalog.setAdapter(new ProductAdapter(curItemList,inflater,false,getContext()));
            //Toast.makeText(getActivity(), curItemList.get(0).name, Toast.LENGTH_SHORT).show();
            listViewCatalog.setOnItemClickListener(this);
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Catalog By Category");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
        Fragment frag=new ProductDetailFragment();
        Bundle b=new Bundle();
        b.putSerializable("chosenProduct",curItemList.get(i));
        frag.setArguments(b);
        MainActivity.stats=7;
        ft.replace(R.id.content_frame,frag);
        ft.commit();
    }
}
