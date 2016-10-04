package com.imagesearcher.api;

import android.util.Log;

import com.imagesearcher.api.exceptions.NetworkException;
import com.imagesearcher.api.exceptions.NonSuccessfulResponseCodeException;
import com.imagesearcher.database.pojo.Items;
import com.imagesearcher.util.Constants;
import com.imagesearcher.util.JsonUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author equa1s.
 */

public class HttpManager {

    private static final String TAG = HttpManager.class.getSimpleName();

    public HttpManager() {
    }

    public Items getItems(String param) throws Exception {
        String responseText = makeRequest(param, "GET", null);
        return JsonUtil.fromJson(responseText, Items.class);
    }

    private String makeRequest(String searchParam, String method, String body) throws Exception {
        Response response = getConnection(searchParam, method, body);

        int    responseCode;
        String responseMessage;
        String responseBody;

        try {
            responseCode    = response.code();
            responseMessage = response.message();
            responseBody    = response.body().string();
        } catch (IOException ioe) {
            throw new Exception(ioe);
        }

        if (responseCode != 200) {
            throw new NonSuccessfulResponseCodeException("Bad response: " + responseCode + ". Message: " + responseMessage);
        }

        return responseBody;
    }

    private Response getConnection(String searchParam, String method, String body) throws NetworkException {

        try {

            HttpUrl url = new HttpUrl.Builder()
                    .scheme("https")
                    .host("www.googleapis.com")
                    .addPathSegment("customsearch")
                    .addPathSegment("v1")
                    .addQueryParameter("q", searchParam)
                    .addQueryParameter("cx", Constants.ENGINE_ID)
                    .addQueryParameter("key", Constants.GOOGLE_API_KEY)
                    .build();

            Log.w(TAG, "Opening URL: " + url.toString());

            OkHttpClient okHttpClient = new OkHttpClient();

            Request.Builder request = new Request.Builder();
                request.url(url);

            if (body != null) {
                request.method(method, RequestBody.create(MediaType.parse("application/json"), body));
            } else {
                request.method(method, null);
            }

            return okHttpClient.newCall(request.build()).execute();

        } catch (IOException e) {
            throw new NetworkException(e.getMessage());
        }
    }
}
