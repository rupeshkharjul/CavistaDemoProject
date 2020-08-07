package com.example.cavistademoproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cavistademoproject.model.ImageData;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM imagedata")
    List<ImageData> getAll();

    @Insert
    void insert(ImageData data);

    @Delete
    void delete(ImageData data);

    @Update
    void update(ImageData data);
}
