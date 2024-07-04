package com.example.appconbd;
import android.app.Application;
import androidx.room.Room;

public class MyApp extends Application {
    private AppDatabase database;
    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "smart_gallery").build();
    }

    public static MyApp getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}

