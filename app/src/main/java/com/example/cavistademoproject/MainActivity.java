package com.example.cavistademoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cavistademoproject.adapter.ProductListAdapter;
import com.example.cavistademoproject.databinding.ActivityMainBinding;
import com.example.cavistademoproject.model.ProductData;
import com.example.cavistademoproject.viewmodel.ProductListViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ProductListViewModel viewModel;
    ProductListAdapter adapter;
    protected ProgressDialog progressDialog = null;
    private ArrayList<ProductData> productData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        setViews();
        setLiveCompenent();
    }

    private void setLiveCompenent() {
        viewModel.showProgress.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldShow) {
                if(this != null) {
                    if (shouldShow) {
                       showProgressDialog();
                    } else {
                        dismissProgressDialog();
                    }
                }
            }
        });

        viewModel.productList.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String data) {
                if(data != null){
                    Type type = new TypeToken<List<ProductData>>() {
                    }.getType();
                    productData = new Gson().fromJson(data, type);
                    setAdapter();
                }
            }
        });
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    private void setViews() {
        productData = new ArrayList<>();
        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    if(!binding.etSearch.getText().toString().isEmpty()) {
                        viewModel.getProductList(binding.etSearch.getText().toString());
                    }else{
                        Toast.makeText(MainActivity.this, "Please enter the text in search field.", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void setAdapter() {
        adapter = new ProductListAdapter( getApplicationContext(),productData);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        binding.recyclerView.setAdapter(adapter);
    }

}
