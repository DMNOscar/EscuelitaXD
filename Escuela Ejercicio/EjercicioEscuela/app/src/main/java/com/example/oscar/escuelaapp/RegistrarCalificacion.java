package com.example.oscar.escuelaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oscar.escuelaapp.Adapters.SinnerAlumnoAdapter;
import com.example.oscar.escuelaapp.Adapters.SpinnerMateriaAdapter;
import com.example.oscar.escuelaapp.DAO.DAO;
import com.example.oscar.escuelaapp.DAO.EvaluacionDAO;
import com.example.oscar.escuelaapp.DAO.MateriaDAO;
import com.example.oscar.escuelaapp.DAO.UsuarioDAO;
import com.example.oscar.escuelaapp.Entidades.Materia;
import com.example.oscar.escuelaapp.Entidades.Usuario;

import java.util.List;

public class RegistrarCalificacion extends AppCompatActivity {
    Spinner spMaterias, spAlumnos;
    Button btnRegistrar;
    EditText calificacion;
    Usuario usuario;
    String txtCalificacion;
    int materiaID, usuariID;

    SpinnerMateriaAdapter spinnerAdapterMateria;
    SinnerAlumnoAdapter spinerAdapterAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_calificacion);

        usuario = getIntent().getParcelableExtra("USUARIO");
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarCalificacion);
        calificacion = (EditText) findViewById(R.id.edtRegistrarCalificacion);
        spMaterias = (Spinner) findViewById(R.id.spMaterias);
        spAlumnos = (Spinner) findViewById(R.id.spNombreAlumno);

        llenarSpinerMaterias();
        llenarSpinnerAlumnos();

        spMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                materiaID = (int)spinerAdapterAlumno.getItemId(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spAlumnos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                usuariID = (int)spinnerAdapterMateria.getItemId(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarCalificacion();
            }
        });

    }


    private void registrarCalificacion() {
        EvaluacionDAO.getInstance().ingresarCalificacion(RegistrarCalificacion.this, Double.parseDouble(calificacion.getText().toString()), materiaID, usuariID, new DAO.OnResultadoConsulta() {
            @Override
            public void consultaSuccess(Object o) {
                Toast.makeText(RegistrarCalificacion.this, "Calificacion agregada", Toast.LENGTH_SHORT).show();
                Intent abrirActivity = new Intent(RegistrarCalificacion.this, MainActivity.class);
                abrirActivity.putExtra("USUARIO",usuario);
                startActivity(abrirActivity);

            }

            @Override
            public void consultaFailed(String error, int codigo) {
                Toast.makeText(RegistrarCalificacion.this, "Algo salio mal", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void llenarSpinerMaterias() {
        MateriaDAO.getInstance().getListaMaterias(RegistrarCalificacion.this, new DAO.OnResultListaConsulta<Materia>() {
            @Override
            public void consultaSucess(List<Materia> t) {

                    spinnerAdapterMateria = new SpinnerMateriaAdapter(t,RegistrarCalificacion.this);
                    spMaterias.setAdapter(spinnerAdapterMateria);

            }

            @Override
            public void consultaFailed(String error, int codigo) {

                Toast.makeText(RegistrarCalificacion.this, "Parece que algo no salio bien XD", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void llenarSpinnerAlumnos(){

        UsuarioDAO.getInstnce().obtenerAlumnos(this, new DAO.OnResultListaConsulta<Usuario>() {

            @Override
            public void consultaSucess(List<Usuario> t) {
                spinerAdapterAlumno = new SinnerAlumnoAdapter(t,RegistrarCalificacion.this);
                spAlumnos.setAdapter(spinerAdapterAlumno);
            }

            @Override
            public void consultaFailed(String error, int codigo) {
                Toast.makeText(RegistrarCalificacion.this, "Parece que algo no salio bien XD", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
