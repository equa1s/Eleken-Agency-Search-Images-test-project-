package com.imagesearcher.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imagesearcher.api.GoogleSearchService;
import com.imagesearcher.controllers.callbacks.GoogleSearchCallback;
import com.imagesearcher.database.pojo.Items;
import com.imagesearcher.util.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author equa1s.
 */

public class GoogleSearchController {

    private GoogleSearchCallback mGoogleSearchCallback;
    private GoogleSearchService mGoogleSearchService;

    public GoogleSearchController(GoogleSearchCallback mGoogleSearchCallback) {

        this.mGoogleSearchCallback = mGoogleSearchCallback;

        ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.mGoogleSearchService = retrofit.create(GoogleSearchService.class);

    }

    public void getGoogleSearchResults(Map<String, String> options) {
        Call<Items> searchResult = mGoogleSearchService.getSearchResults(options);

            searchResult.enqueue(new Callback<Items>() {

                @Override
                public void onResponse(Call<Items> call, Response<Items> response) {
                    if (response.isSuccessful()) {
                        mGoogleSearchCallback.onSuccess(response.body());
                    }
                }

                @Override
                public void onFailure(Call<Items> call, Throwable t) {
                    mGoogleSearchCallback.onFailure(t);
                }

            });

        mGoogleSearchCallback.onFinish();

    }

}
