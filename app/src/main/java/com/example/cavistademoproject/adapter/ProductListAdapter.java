package com.example.cavistademoproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cavistademoproject.MainActivity;
import com.example.cavistademoproject.ProductDetailActivity;
import com.example.cavistademoproject.R;
import com.example.cavistademoproject.model.ProductData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListItemHolder> {

    private Context mContext;
    private ArrayList<ProductData> mDataset;


    public ProductListAdapter(Context mContext, ArrayList<ProductData> data) {
        this.mContext = mContext;
        this.mDataset = data;
    }

    @NonNull
    @Override
    public ProductListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = (LayoutInflater) parent.getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = mInflater
                .inflate(R.layout.item_product_list, parent, false);
        return new ProductListItemHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListItemHolder holder, final int position) {
        ProductData productData = mDataset.get(position);
        if(productData.getImages().size()>0) {
            Picasso.get()
                    .load(productData.getImages().get(0).getLink())
                    .into(holder.imageviewName);
        }

        holder.imageviewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                intent.putExtra("Product", mDataset.get(position).getImages().get(0));
                intent.putExtra("Title", mDataset.get(position).getTitle());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ProductListItemHolder extends RecyclerView.ViewHolder {

        public ImageView imageviewName;
        Context context;

        public ProductListItemHolder(Context context, View view) {
            super(view);
            this.context = context;
            imageviewName = (ImageView) view.findViewById(R.id.iv_product);
        }

    }

}
