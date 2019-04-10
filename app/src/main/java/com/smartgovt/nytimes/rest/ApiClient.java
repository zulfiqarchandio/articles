package com.smartgovt.nytimes.rest;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


public class ApiClient {

    // URL
    public static final String BASE_URL = "http://api.nytimes.com";
    public static final String PATH_URL = "/svc/mostpopular/v2/mostviewed/";


    //Methods
    public static final String Sections = "all-sections";

    private static final Integer MY_SOCKET_TIMEOUT_MS = 30000;

    public static ApiClient instance;


    public static ApiClient getInstance(Context context) {
        if (instance != null)
            return instance;
        else {
            return new ApiClient(context);
        }
    }

    private ApiClient(Context context) {
        instance = this;
        MyVolley.init(context);

    }

    public void getUrlRequest(final String url, final GetCallback callback) {

        RequestQueue queue = MyVolley.getRequestQueue();
        Log.v("apirequest", url);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        callback.onResponseReceived(true, response);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                callback.onResponseReceived(false, null);
            }
        });


        request.setRetryPolicy(new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MyVolley.getRequestQueue().add(request);


    }


    ///callbacks
    public abstract class GetCallback {
        public abstract void onResponseReceived(boolean success, JSONObject response);

    }


}
