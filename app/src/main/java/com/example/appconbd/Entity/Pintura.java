package com.example.appconbd.Entity;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "pintura",
        foreignKeys = {
                @ForeignKey(entity = Autor.class, parentColumns = "id", childColumns = "autorId"),
                @ForeignKey(entity = Sala.class, parentColumns = "id", childColumns = "salaId")
        }
)
public class Pintura {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public int autorId;
    public int salaId;
    public String tecnica;
    public String categoria;
    public String descripcion;
    public int ano;
    public String enlace;
}
