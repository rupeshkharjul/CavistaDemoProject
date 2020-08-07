package com.example.cavistademoproject.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;


public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx.getApplicationContext(),AppDatabase.class,"Product").fallbackToDestructiveMigration().build();
      //  appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "Product").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
