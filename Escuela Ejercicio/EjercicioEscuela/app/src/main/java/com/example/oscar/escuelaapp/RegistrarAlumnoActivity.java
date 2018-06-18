package com.example.oscar.escuelaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oscar.escuelaapp.DAO.DAO;
import com.example.oscar.escuelaapp.DAO.PeticionHTTP;
import com.example.oscar.escuelaapp.DAO.UsuarioDAO;
import com.example.oscar.escuelaapp.Entidades.Usuario;

public class RegistrarAlumnoActivity extends AppCompatActivity {
    private String txtNick, txtPass, txtNombre;
    private EditText nombre, nick, pass;
    private Button btnAgregar;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);
        usuario = getIntent().getParcelableExtra("USUARIO");
        nick = (EditText) findViewById(R.id.edtNickName);
        pass = (EditText) findViewById(R.id.edtPass);
        nombre= (EditText) findViewById(R.id.edtNombreAlumno);
        btnAgregar = (Button) findViewById(R.id.btnRegistrarAlumno);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNick= nick.getText().toString();
                txtPass= pass.getText().toString();
                txtNombre = nombre.getText().toString();

                registrarAlumno();
            }
        });

    }


    private void registrarAlumno(){

        UsuarioDAO.getInstnce().regisrtarUsuario(this, txtNombre, txtNick, txtPass, new DAO.OnResultadoConsulta() {
            @Override
            public void consultaSuccess(Object o) {
                Intent abrirActivity = new Intent(RegistrarAlumnoActivity.this, MainActivity.class);
                abrirActivity.putExtra("USUARIO",usuario);
                startActivity(abrirActivity);

                Toast.makeText(RegistrarAlumnoActivity.this, "Datos: "+txtNombre+ txtNick+ txtPass, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void consultaFailed(String error, int codigo) {
                Toast.makeText(RegistrarAlumnoActivity.this, "Parece que algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
