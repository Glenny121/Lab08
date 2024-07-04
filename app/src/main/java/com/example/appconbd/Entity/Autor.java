package com.example.appconbd.Entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "autor")
public class Autor {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String apellido;
}
