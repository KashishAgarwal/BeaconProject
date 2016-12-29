package com.zeus.beaconproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zeus.beaconproject.Models.WalmartItem;
import com.zeus.beaconproject.R;

/**
 * Created by Zeus on 29-Dec-16.
 */

public class ProductDetailFragment extends Fragment {
    WalmartItem curItem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.product_detail_fragment, container, false);
        Bundle recBundle=this.getArguments();
        if(recBundle!=null){
            curItem= (WalmartItem) recBundle.get("chosenProduct");
            Toast.makeText(getActivity(), curItem.name, Toast.LENGTH_SHORT).show();
        }
        return v;
    }
}
