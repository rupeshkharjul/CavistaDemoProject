package com.example.cavistademoproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductData {

    @SerializedName(value = "Id", alternate = {"id"})
    String id;

    @SerializedName(value = "Title", alternate = {"title"})
    String title;

    @SerializedName(value = "Images", alternate = {"images"})
    ArrayList<ImageData> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ImageData> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageData> images) {
        this.images = images;
    }
}
