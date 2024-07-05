// PinturaDao.java
package com.example.appconbd.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.appconbd.Entity.Pintura;
import com.example.appconbd.PinturaInfo;

import java.util.List;

@Dao
public interface PinturaDao {
    @Insert
    long insert(Pintura pintura);

    @Query("SELECT pintura.titulo, autor.nombre || ' ' || autor.apellido AS autor, pintura.tecnica, pintura.categoria, pintura.descripcion, pintura.ano, pintura.enlace " +
            "FROM pintura " +
            "INNER JOIN autor ON pintura.autorId = autor.id")
    List<PinturaInfo> getPinturaDetails();
}
