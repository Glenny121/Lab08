package com.example.appconbd;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.appconbd.Entity.Autor;
import com.example.appconbd.Entity.Pintura;
import com.example.appconbd.Entity.Sala;
import com.example.appconbd.Room.AutorDao;
import com.example.appconbd.Room.PinturaDao;
import com.example.appconbd.Room.SalaDao;

@Database(entities = {Autor.class, Sala.class, Pintura.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AutorDao autorDao();
    public abstract SalaDao salaDao();
    public abstract PinturaDao pinturaDao();
}
