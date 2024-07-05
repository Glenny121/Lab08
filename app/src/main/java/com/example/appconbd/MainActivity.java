// MainActivity.java
package com.example.appconbd;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appconbd.Entity.Autor;
import com.example.appconbd.Entity.Pintura;
import com.example.appconbd.Entity.Sala;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private TextView tvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvDatos = findViewById(R.id.tvDatos);
        Button btnMostrarDatos = findViewById(R.id.btnMostrarDatos);

        db = MyApp.getInstance().getDatabase();

        // Insertar datos de prueba
        insertarDatosDePrueba();

        // Configurar el botón para mostrar los datos
        btnMostrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatosInsertados();
            }
        });
    }

    private void insertarDatosDePrueba() {
        Autor autor = new Autor();
        autor.nombre = "Chalo";
        autor.apellido = "P";

        Sala sala = new Sala();
        sala.nombre = "Sala 1";
        sala.descripcion = "Sala principal";

        Pintura pintura = new Pintura();
        pintura.titulo = "Glennys";
        pintura.tecnica = "Olea";
        pintura.categoria = "Fotografía";
        pintura.descripcion = "Una fotografía del bosque";
        pintura.ano = 2024;
        pintura.enlace = "https://example.com/guernica.jpg";

        new InsertDataTask(db).execute(autor, sala, pintura);
    }

    private void mostrarDatosInsertados() {
        new RetrieveDataTask(db, new OnDataRetrievedListener() {
            @Override
            public void onDataRetrieved(List<PinturaInfo> pinturaInfos) {
                StringBuilder datos = new StringBuilder();
                for (PinturaInfo pinturaInfo : pinturaInfos) {
                    datos.append("Titulo: ").append(pinturaInfo.titulo)
                            .append(", Autor: ").append(pinturaInfo.autor)
                            .append(", Técnica: ").append(pinturaInfo.tecnica)
                            .append(", Categoría: ").append(pinturaInfo.categoria)
                            .append(", Descripción: ").append(pinturaInfo.descripcion)
                            .append(", Año: ").append(pinturaInfo.ano)
                            .append(", Enlace: ").append(pinturaInfo.enlace)
                            .append("\n\n");
                }
                tvDatos.setText(datos.toString());
            }
        }).execute();
    }

    private static class InsertDataTask extends AsyncTask<Object, Void, Void> {
        private AppDatabase db;

        InsertDataTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(Object... params) {
            Autor autor = (Autor) params[0];
            Sala sala = (Sala) params[1];
            Pintura pintura = (Pintura) params[2];
            long autorId = db.autorDao().insert(autor);
            long salaId = db.salaDao().insert(sala);
            pintura.autorId = (int) autorId;
            pintura.salaId = (int) salaId;
            db.pinturaDao().insert(pintura);
            return null;
        }
    }

    private static class RetrieveDataTask extends AsyncTask<Void, Void, List<PinturaInfo>> {
        private AppDatabase db;
        private OnDataRetrievedListener listener;

        RetrieveDataTask(AppDatabase db, OnDataRetrievedListener listener) {
            this.db = db;
            this.listener = listener;
        }

        @Override
        protected List<PinturaInfo> doInBackground(Void... voids) {
            return db.pinturaDao().getPinturaDetails();
        }

        @Override
        protected void onPostExecute(List<PinturaInfo> pinturaInfos) {
            if (listener != null) {
                listener.onDataRetrieved(pinturaInfos);
            }
        }
    }

    interface OnDataRetrievedListener {
        void onDataRetrieved(List<PinturaInfo> pinturaInfos);
    }
}
