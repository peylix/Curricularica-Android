package com.example.curricularica;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ChatBotApiClient {

    private static final String API_URL = "https://api.openai.com/v1/engines/gpt-3.5-turbo/completions";
    private static final String API_KEY = "";
    private RequestQueue requestQueue;

    public ChatBotApiClient(Context context) {
        // Initialize the RequestQueue with the provided context
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void sendMessage(String message, Callback callback) {
        // Construct the request body
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("prompt", message);
            requestBody.put("max_tokens", 50);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API_URL, requestBody,
                callback::onResponse,
                callback::onError) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + API_KEY);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    public interface Callback {
        void onResponse(JSONObject response);
        void onError(VolleyError error);
    }
}
