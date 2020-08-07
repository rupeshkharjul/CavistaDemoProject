package com.example.cavistademoproject.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cavistademoproject.dao.ProductDao;
import com.example.cavistademoproject.model.ImageData;
import com.example.cavistademoproject.model.ProductData;

@Database(entities = {ImageData.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract ProductDao productDao();
}
