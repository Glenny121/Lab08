package com.example.appconbd.Room;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appconbd.Entity.Autor;

import java.util.List;

@Dao
public interface AutorDao {
    @Insert
    long insert(Autor autor);

    @Query("SELECT * FROM autor")
    List<Autor> getAllAutores();
}
