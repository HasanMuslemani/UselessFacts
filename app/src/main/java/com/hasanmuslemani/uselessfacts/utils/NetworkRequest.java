package com.hasanmuslemani.uselessfacts.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetworkRequest {

    private static NetworkRequest instance;
    private RequestQueue requestQueue;
    private static Context context;

    private NetworkRequest(Context context) {
        this.context = context;
    }

    public static NetworkRequest getInstance(Context context) {
        if(instance == null) {
            instance = new NetworkRequest(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
}
