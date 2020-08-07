package com.example.cavistademoproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class ImageData implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @SerializedName(value = "Id", alternate = {"id"})
    @NonNull
    String id;

    @ColumnInfo(name = "link")
    @SerializedName(value = "Link", alternate = {"link"})
    String link;

    @ColumnInfo(name = "comment")
    String comment;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
