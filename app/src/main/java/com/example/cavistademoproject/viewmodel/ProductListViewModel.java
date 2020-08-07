package com.example.cavistademoproject.viewmodel;

import android.app.ProgressDialog;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cavistademoproject.AppController;
import com.example.cavistademoproject.model.ProductData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListViewModel extends ViewModel {

    public ArrayList<ProductData> productDataArrayList;
    public MutableLiveData<Boolean> showProgress = new MutableLiveData<>();
    public MutableLiveData<String> productList = new MutableLiveData<>();

    public ProductListViewModel() {
        productDataArrayList = new ArrayList<>();
    }

    public void getProductList(String searchQuery){
        showProgress.setValue(true);
        productDataArrayList.clear();
        String url = "https://api.imgur.com/3/gallery/search/1?q="+searchQuery;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            showProgress.setValue(false);
                            JSONArray jsonArray = response.getJSONArray("data");
                            Type type = new TypeToken<List<ProductData>>() {
                            }.getType();
                            productList.setValue(jsonArray.toString());
                            //productDataArrayList = new Gson().fromJson(jsonArray.toString(), type);

                            Log.d("TAG", response.toString());
                        }catch (Exception e){
                            showProgress.setValue(false);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                showProgress.setValue(false);
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Client-ID 137cda6b5008a7c");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, "ProDuct list");

    }
}
