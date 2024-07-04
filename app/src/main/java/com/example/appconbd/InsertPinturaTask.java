package com.example.appconbd;
import android.os.AsyncTask;

import com.example.appconbd.Entity.Pintura;

public class InsertPinturaTask extends AsyncTask<Pintura, Void, Void> {
    private AppDatabase db;

    public InsertPinturaTask(AppDatabase db) {
        this.db = db;
    }

    @Override
    protected Void doInBackground(Pintura... pinturas) {
        db.pinturaDao().insert(pinturas[0]);
        return null;
    }
}
