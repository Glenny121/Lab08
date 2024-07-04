package com.example.appconbd.Room;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appconbd.Entity.Pintura;
import com.example.appconbd.PinturaInfo;
import com.example.appconbd.PinturaMapaInfo;

import java.util.List;

@Dao
public interface PinturaDao {
    @Insert
    void insert(Pintura pintura);

    @Query("SELECT titulo, nombre || ' ' || apellido AS autor, tecnica, categoria, descripcion, ano, enlace " +
            "FROM pintura " +
            "INNER JOIN autor ON pintura.autorId = autor.id")
    List<PinturaInfo> getPinturaDetails();

    @Query("SELECT sala.nombre AS sala, pintura.titulo, autor.nombre || ' ' || autor.apellido AS autor, pintura.enlace " +
            "FROM pintura " +
            "INNER JOIN autor ON pintura.autorId = autor.id " +
            "INNER JOIN sala ON pintura.salaId = sala.id")
    List<PinturaMapaInfo> getPinturaForMap();
}
