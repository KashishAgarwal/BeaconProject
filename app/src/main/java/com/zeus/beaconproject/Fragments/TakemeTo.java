package com.zeus.beaconproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.zeus.beaconproject.MainActivity;
import com.zeus.beaconproject.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.zeus.beaconproject.Fragments.ChooseCategory.categoryId;

/**
 * Created by lenovo on 29-12-2016.
 */

public class TakemeTo extends Fragment implements View.OnClickListener {
    Button goToDeptButton;

    Map<Pair<String,String>,String> direction= new HashMap<>();
    Pair<String,String> p= new Pair<>("Electronics","Toys");
    String received;
    Region region;
    BeaconManager beaconManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.takemeto, container, false);


        direction.put(p,"Right");
        p=new Pair<>("Electronics","Jewelery");
        direction.put(p,"Straight");
        p=new Pair<>("Electronics","Office");
        direction.put(p,"Straight");
        p=new Pair<>("Electronics","Music");
        direction.put(p,"Straight");
        p=new Pair<>("Electronics","Movies & TV");
        direction.put(p,"Straight");

        p=new Pair<>("Toys","Jewelery");
        direction.put(p,"Straight");
        p=new Pair<>("Toys","Electronics");
        direction.put(p,"Left");
        p=new Pair<>("Toys","Office");
        direction.put(p,"Straight");
        p=new Pair<>("Toys","Movies & TV");
        direction.put(p,"Straight");
        p=new Pair<>("Toys","Music");
        direction.put(p,"Straight");

        p=new Pair<>("Office","Jewelery");
        direction.put(p,"Left");
        p=new Pair<>("Office","Electronics");
        direction.put(p,"Back");
        p=new Pair<>("Office","Toys");
        direction.put(p,"Back");
        p=new Pair<>("Office","Movies & TV");
        direction.put(p,"Straight");
        p=new Pair<>("Office","Music");
        direction.put(p,"Straight");


        goToDeptButton=(Button) v.findViewById(R.id.deptToGoSelectButton);
        goToDeptButton.setOnClickListener(this);
        return v;


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Depts");
    }


    String destDeptName;
    @Override
    public void onClick(View view) {
        new MaterialDialog.Builder(getContext())
                .title("Categories")
                .items(categoryId.keySet())
                .backgroundColor(getResources().getColor(R.color.background))
                .itemsColor(getResources().getColor(R.color.text))
                .contentColor(getResources().getColor(R.color.text))
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {

                        Log.d("g","selected an item");
                        destDeptName=text.toString();
                        String nearestCategory=MainActivity.nearestCategoryCurrently;
                        Pair<String,String> currp= new Pair<String, String>(nearestCategory,destDeptName);
                        String moveTo=direction.get(currp);
                        Log.d("g","direction fetched");

                        Log.d("FRAG","Reaching image");
                        FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
                        Fragment frag=new Image();
                        Bundle myBundle=new Bundle();
                        myBundle.putString("moveTo",moveTo);
                        Log.d("Directed","Directed ");
                        ft.replace(R.id.content_frame,frag);
                        ft.commit();

                    }
                })
                .show();
    }
}
