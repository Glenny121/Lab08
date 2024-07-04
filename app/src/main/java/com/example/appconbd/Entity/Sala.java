package com.example.appconbd.Entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sala")
public class Sala {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String descripcion;
}
