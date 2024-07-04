package com.example.appconbd;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = MyApp.getInstance().getDatabase();
        insertarDatosDePrueba();
        mostrarDatosInsertados();
    }

    private void insertarDatosDePrueba() {
        Autor autor = new Autor();
        autor.nombre = "Chalo";
        autor.apellido = "P";

        Sala sala = new Sala();
        sala.nombre = "Sala 1";
        sala.descripcion = "Sala principal";

        Pintura pintura = new Pintura();
        pintura.titulo = "Chalo";
        pintura.tecnica = "Colores";
        pintura.categoria = "Caricatura";
        pintura.descripcion = "Un autorretrato en caricatura";
        pintura.ano = 1997;
        pintura.enlace = "https://example.com/guernica.jpg";

        new InsertDataTask(db).execute(autor, sala, pintura);
    }

    private void mostrarDatosInsertados() {
        new RetrieveDataTask(db).execute();
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

        RetrieveDataTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected List<PinturaInfo> doInBackground(Void... voids) {
            return db.pinturaDao().getPinturaDetails();
        }

        @Override
        protected void onPostExecute(List<PinturaInfo> pinturaInfos) {
            super.onPostExecute(pinturaInfos);

            // Mostrar los datos recuperados en el log (o en una vista de tu elección)
            for (PinturaInfo pinturaInfo : pinturaInfos) {
                Log.d("MainActivity", "Titulo: " + pinturaInfo.titulo + ", Autor: " + pinturaInfo.autor +
                        ", Técnica: " + pinturaInfo.tecnica + ", Categoría: " + pinturaInfo.categoria +
                        ", Descripción: " + pinturaInfo.descripcion + ", Año: " + pinturaInfo.ano +
                        ", Enlace: " + pinturaInfo.enlace);
            }
        }
    }
}
