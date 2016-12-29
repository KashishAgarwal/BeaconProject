package com.zeus.beaconproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zeus.beaconproject.R;

import static com.zeus.beaconproject.Fragments.ChooseCategory.categoryId;

/**
 * Created by lenovo on 29-12-2016.
 */

public class TakemeTo extends Fragment implements View.OnClickListener {
    Button goToDeptButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.takemeto, container, false);
        goToDeptButton=(Button) v.findViewById(R.id.deptToGoSelectButton);
        goToDeptButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Depts");
    }


    String deptName;
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

                        deptName=text.toString();



                    }
                })
                .show();
    }
}
