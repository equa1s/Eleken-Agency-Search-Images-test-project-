package com.imagesearcher.api;

import com.imagesearcher.database.pojo.Items;
import com.imagesearcher.util.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @author equa1s.
 */

public interface GoogleSearchService {
    @GET(Constants.CUSTOM_SEARCH + Constants.V1)
    Call<Items> getSearchResults(@QueryMap Map<String, String> options);
}
