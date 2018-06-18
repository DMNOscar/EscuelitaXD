package com.example.oscar.escuelaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oscar.escuelaapp.DAO.DAO;
import com.example.oscar.escuelaapp.DAO.UsuarioDAO;
import com.example.oscar.escuelaapp.Entidades.Usuario;

public class ActulizarusuarioActivity extends AppCompatActivity {

    private String txtNick, txtPass, txtNombre;
    private EditText nombre, nick, pass;
    private Button btnAgregar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actulizarusuario);
        usuario = getIntent().getParcelableExtra("USUARIO");
        nick = (EditText) findViewById(R.id.edtActualizarNickName);
        pass = (EditText) findViewById(R.id.edtActualizarPass);
        nombre= (EditText) findViewById(R.id.edtActualizarNombre);
        btnAgregar = (Button) findViewById(R.id.btnRegistrarAlumno);


        nombre.setText( usuario.getNombre());
        nick.setText(usuario.getCredencial().getNick());
        pass.setText(usuario.getCredencial().getPass());

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNick= nick.getText().toString();
                txtPass= pass.getText().toString();
                txtNombre = nombre.getText().toString();

                actulizarDatosAlumno();
            }
        });

    }

    private void actulizarDatosAlumno(){

        UsuarioDAO.getInstnce().actualizarUsuario(this, usuario.getId(), txtNombre, txtNick, txtPass, new DAO.OnResultadoConsulta() {
            @Override
            public void consultaSuccess(Object o) {
                Intent abrirActivity = new Intent(ActulizarusuarioActivity.this, MainActivity.class);
                abrirActivity.putExtra("USUARIO",usuario);
                startActivity(abrirActivity);

                Toast.makeText(ActulizarusuarioActivity.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void consultaFailed(String error, int codigo) {
                Toast.makeText(ActulizarusuarioActivity.this, "Parece que algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
