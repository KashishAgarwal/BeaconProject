package com.zeus.beaconproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zeus.beaconproject.R;

/**
 * Created by lenovo on 29-12-2016.
 */

public class Image extends Fragment {


    String mystring;
    Bundle recBundle;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.image, container, false);
        recBundle=this.getArguments();
        if(recBundle!=null){
            mystring=recBundle.getString("moveTo");
        }
        if(mystring==null)
            mystring="here";
        ImageView i = (ImageView) v.findViewById(R.id.imageNavigate);

        switch (mystring) {
            case "Left":
                Picasso.with(getContext()).load(R.drawable.left).into(i);
                break;
            case "Right":
                Picasso.with(getContext()).load(R.drawable.right).into(i);
                break;
            case "Straight":
                Picasso.with(getContext()).load(R.drawable.straight).into(i);
                break;
            case "Back":
                Picasso.with(getContext()).load(R.drawable.goback).into(i);
                break;
            default:
                Picasso.with(getContext()).load(R.drawable.uarehere).into(i);
                break;
        }
        //Log.i("Image nn",mystring+" 555");
        return v;
    }

}
