package com.example.appconbd.Room;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appconbd.Entity.Sala;

import java.util.List;

@Dao
public interface SalaDao {
    @Insert
    long insert(Sala sala);

    @Query("SELECT * FROM sala")
    List<Sala> getAllSalas();
}
