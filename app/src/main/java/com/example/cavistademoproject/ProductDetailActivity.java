package com.example.cavistademoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cavistademoproject.databinding.ActivityProductDetailActivityBinding;
import com.example.cavistademoproject.db.DatabaseClient;
import com.example.cavistademoproject.model.ImageData;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailActivityBinding binding;

    ImageData imageData = new ImageData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_product_detail_activity);
        setData();
    }

    private void setData() {
        Bundle bundle = getIntent().getExtras();

        imageData = (ImageData) bundle.getSerializable("Product");

        getSupportActionBar().setTitle(bundle.getString("Title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Picasso.get()
                .load(imageData.getLink())
                .into(binding.ivProduct);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitComment();
            }
        });

    }

    private void submitComment() {
        if(binding.etComment.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter comment in comment box.", Toast.LENGTH_LONG).show();
            return;
        }
        class SaveComment extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

               imageData.setComment(binding.etComment.getText().toString());

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .productDao()
                        .insert(imageData);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                finish();
            }
        }

        SaveComment st = new SaveComment();
        st.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
