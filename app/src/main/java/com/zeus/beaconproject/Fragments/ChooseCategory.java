package com.zeus.beaconproject.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zeus.beaconproject.MainActivity;
import com.zeus.beaconproject.Contract;
import com.zeus.beaconproject.Models.PaginatedItemResult;
import com.zeus.beaconproject.Networking.ApiClient;
import com.zeus.beaconproject.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zeus on 29-Dec-16.
 */

public class ChooseCategory extends Fragment implements View.OnClickListener{

    Button chooseCat;

    public static final HashMap<String,String> categoryId=new HashMap<String,String>(){{
        put("Electronics","3944");
        put("Toys","4125");
        put("Jewelry","3891");
        put("Office","1229749");
        put("Music","4104");
        put("Movies & TV","4096");
    }};

    public static final HashMap<String,String> categoryName=new HashMap<String,String>(){{
        put("3944","Electronics");
        put("4125","Toys");
        put("3891","Jewelry");
        put("1229749","Office");
        put("4104","Music");
        put("4096","Movies & TV");
    }};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.choose_category_fragment, container, false);
        chooseCat=(Button) v.findViewById(R.id.categorySelectionButton);
        chooseCat.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Product Categories");
    }

    String cat;
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
                        //Toast.makeText(getContext(), "text= "+text.toString(), Toast.LENGTH_SHORT).show();
                        cat=text.toString();

                        final Call<PaginatedItemResult> getElec= ApiClient.getApiInterface().getItemsListByCategory
                                (categoryId.get(cat), Contract.WALMART_API_KEY,Contract.WALMART_API_RETURN_FORMAT);
                        final ProgressDialog progressDialog = new ProgressDialog(getContext());
                        progressDialog.setTitle("Fetching Data");
                        progressDialog.setMessage("In progress");
                        progressDialog.show();
                        getElec.enqueue(new Callback<PaginatedItemResult>() {
                            @Override
                            public void onResponse(Call<PaginatedItemResult> call, Response<PaginatedItemResult> response) {
                                progressDialog.dismiss();
                                if (response.isSuccessful()){
                                    PaginatedItemResult hh=response.body();
                                    Log.i("help",response.toString()+"14525");
                                    Toast.makeText(getContext(), "Welcome to "+cat+" section!", Toast.LENGTH_SHORT).show();
                                    FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
                                    Fragment frag=new CatalogByCategory();
                                    Bundle b=new Bundle();
                                    MainActivity.catalogByCategoryBundle=b;
                                    MainActivity.stats=5;
                                    b.putSerializable("impData",hh);
                                    ft.replace(R.id.content_frame,frag);
                                    ft.commit();

                                }else{
                                    Log.i("fetch data","error occurred");
                                }
                            }

                            @Override
                            public void onFailure(Call<PaginatedItemResult> call, Throwable t) {
                                Log.i("fetch data","failure occurred");
                            }
                        });

                    }
                })
                .show();
    }
}
