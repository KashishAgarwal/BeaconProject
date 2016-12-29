package com.zeus.beaconproject.Networking;

import com.zeus.beaconproject.Models.PaginatedItemResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Zeus on 29-Dec-16.
 */

public interface ApiInterface {
    @GET("v1/paginated/items")
    Call<PaginatedItemResult> getItemsListByCategory(@Query("category") String category, @Query("apiKey") String apiKey, @Query("format") String format);
}
