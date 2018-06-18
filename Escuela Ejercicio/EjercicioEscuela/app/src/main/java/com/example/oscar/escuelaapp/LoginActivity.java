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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText edtNombre = (EditText) findViewById(R.id.edtNickName);
        final EditText edtpass = (EditText) findViewById(R.id.edtPass);

        Button btAceptar = (Button) findViewById(R.id.btnLogin);

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNombre.getText().length() == 0 && edtpass.getText().length() == 0) {

                    Toast.makeText(LoginActivity.this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show();
                } else {
                    UsuarioDAO dao = UsuarioDAO.getInstnce();
                    dao.obtenerDatosLogin(LoginActivity.this, edtNombre.getText().toString(), edtpass.getText().toString(),
                            new DAO.OnResultadoConsulta<Usuario>() {
                                @Override
                                public void consultaSuccess(Usuario s) {
                                    if (s != null) {
                                        Intent acceder = new Intent(LoginActivity.this, MainActivity.class);
                                        acceder.putExtra("USUARIO", s);
                                        startActivity(acceder);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "No hay datos", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void consultaFailed(String error, int codigo) {
                                    Toast.makeText(LoginActivity.this, error + " " + codigo, Toast.LENGTH_SHORT).show();
                                }

                            });
                }
            }
        });
    }
}
