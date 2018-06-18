package com.example.oscar.escuelaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oscar.escuelaapp.DAO.DAO;
import com.example.oscar.escuelaapp.DAO.MateriaDAO;
import com.example.oscar.escuelaapp.Entidades.Materia;
import com.example.oscar.escuelaapp.Entidades.Usuario;

public class RegistrarMateriaActivity extends AppCompatActivity {
    private EditText claveMateria, nombreMateria;
    private Button btnRegistrar;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_materia);
        usuario = getIntent().getParcelableExtra("USUARIO");
        claveMateria= (EditText) findViewById(R.id.edtNombreMateriaRegistrar);
        nombreMateria= (EditText) findViewById(R.id.edtClaveMateriaRegistrar);
        btnRegistrar= (Button) findViewById(R.id.btnRegistrarMateria);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarMateria();
            }
        });
    }

    private void registrarMateria(){

        MateriaDAO.getInstance().registrarMateria(this, claveMateria.getText().toString(), nombreMateria.getText().toString(), new DAO.OnResultadoConsulta()
        {
            @Override
            public void consultaSuccess(Object o) {
                Intent abrirActivity = new Intent(RegistrarMateriaActivity.this, MainActivity.class);
                abrirActivity.putExtra("USUARIO",usuario);
                startActivity(abrirActivity);
                Toast.makeText(RegistrarMateriaActivity.this, "Materia regsitrada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void consultaFailed(String error, int codigo) {
                Toast.makeText(RegistrarMateriaActivity.this, "Parece que algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
