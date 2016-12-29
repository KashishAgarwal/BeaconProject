package com.zeus.beaconproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.zeus.beaconproject.Models.ChosenProduct;
import com.zeus.beaconproject.Models.WalmartItem;
import com.zeus.beaconproject.ProductAdapter;
import com.zeus.beaconproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeus on 29-Dec-16.
 */

public class ShoppingListFragment extends Fragment {

    private List<ChosenProduct> mCartList;
    private ProductAdapter mProductAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.shopping_list_fragment_layout, container, false);
        mCartList=new Select()
                .from(ChosenProduct.class)
                .execute();
        ListView listViewCatalog = (ListView) v.findViewById(R.id.ListViewCatalog);
        ArrayList<WalmartItem> dispList=new ArrayList<>();
        if(mCartList!=null)
            for(int i=0;i<mCartList.size();++i)
                dispList.add(mCartList.get(i).getWalmartItem());
        listViewCatalog.setAdapter(new ProductAdapter(dispList, inflater, true,getContext()));

        return v;
    }
}
