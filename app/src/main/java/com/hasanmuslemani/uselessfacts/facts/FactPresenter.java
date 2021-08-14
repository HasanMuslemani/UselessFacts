package com.hasanmuslemani.uselessfacts.facts;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hasanmuslemani.uselessfacts.MyApp;
import com.hasanmuslemani.uselessfacts.models.Fact;
import com.hasanmuslemani.uselessfacts.utils.NetworkRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class FactPresenter implements FactContract.Presenter {

    private final FactContract.View view;
    private final String url;

    public FactPresenter(FactContract.View view) {
        this.view = view;
        this.url = "https://uselessfacts.jsph.pl/random.json?language=en";
    }

    @Override
    public void loadFact() {
        view.showLoading();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Fact fact;
                    String id = response.getString("id");
                    String content = response.getString("text");
                    fact = new Fact(id, content);
                    if(content.length() > 225) {
                        loadFact();
                        return;
                    }
                    view.showResult(fact.getText());
                    view.hideLoading();
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.showError("An unexpected error has occurred.");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError || error instanceof AuthFailureError || error instanceof TimeoutError) {
                    view.showError("No internet connection.");
                } else if(error.networkResponse.statusCode == 429) {
                    view.showError("Too many attempts. Please try again later.");
                } else {
                    view.showError("An unexpected error has occurred.");
                }
                view.hideLoading();
            }
        });
        NetworkRequest.getInstance(MyApp.getContext()).getRequestQueue().add(jsonObjectRequest);
    }
}
