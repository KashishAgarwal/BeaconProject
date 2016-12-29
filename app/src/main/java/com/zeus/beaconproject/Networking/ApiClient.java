package com.zeus.beaconproject.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zeus on 28-Dec-16.
 */

public class ApiClient {
    private static ApiInterface mService;
    public static ApiInterface getApiInterface(){
        if(mService==null){
            Retrofit r=new Retrofit.Builder()
                    .baseUrl("http://api.walmartlabs.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mService=r.create(ApiInterface.class);
        }
        return mService;
    }
}
