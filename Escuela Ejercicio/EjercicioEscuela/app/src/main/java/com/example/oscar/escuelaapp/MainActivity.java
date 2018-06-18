package com.example.oscar.escuelaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscar.escuelaapp.Adapters.EvaluacionAdaptador;
import com.example.oscar.escuelaapp.Adapters.MateriaAdapter;
import com.example.oscar.escuelaapp.DAO.DAO;
import com.example.oscar.escuelaapp.DAO.EvaluacionDAO;
import com.example.oscar.escuelaapp.DAO.MateriaDAO;
import com.example.oscar.escuelaapp.Entidades.Evaluacion;
import com.example.oscar.escuelaapp.Entidades.Materia;
import com.example.oscar.escuelaapp.Entidades.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    Usuario usuario;
    MenuItem registrarAlumno,registrarMateria,registrarCalificacion,editarDatos;
    Intent abrirActivity;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        registrarAlumno = menu.findItem(R.id.resgistra_alumno);
        registrarMateria = menu.findItem(R.id.resgistra_materia);
        registrarCalificacion = menu.findItem(R.id.resgistra_calificacion);
        editarDatos = menu.findItem(R.id.editar_datos);

        if (usuario.getCategoria().getId()==1) {
            registrarAlumno.setVisible(false);
            registrarMateria.setVisible(false);
            registrarCalificacion.setVisible(false);
        }
         return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = getIntent().getParcelableExtra("USUARIO");
        TextView texBienevenida = (TextView) findViewById(R.id.usuario);
        recycler = (RecyclerView) findViewById(R.id.rcvMaterias);

        String mensaje= "Bienvenido "+ usuario.getCategoria().getNombre()+ " "+ usuario.getNombre()+". Esta es la lista de materias";
        texBienevenida.setText(mensaje);

        if (usuario.getCategoria().getId()==2) {
            esMaestro();
        }else{
            esAlumno();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.resgistra_alumno:
                abrirActivity = new Intent(MainActivity.this, RegistrarAlumnoActivity.class);
                abrirActivity.putExtra("USUARIO",usuario);
                startActivity(abrirActivity);

            break;

            case R.id.resgistra_materia:

                 abrirActivity = new Intent(MainActivity.this, RegistrarMateriaActivity.class);
                abrirActivity.putExtra("USUARIO",usuario);
                startActivity(abrirActivity);
                break;

            case R.id.resgistra_calificacion:

                abrirActivity = new Intent(MainActivity.this, RegistrarCalificacion.class);
                abrirActivity.putExtra("USUARIO",usuario);
                startActivity(abrirActivity);
                break;

            case R.id.editar_datos:
                abrirActivity = new Intent(MainActivity.this, ActulizarusuarioActivity.class);
                abrirActivity.putExtra("USUARIO",usuario);
                startActivity(abrirActivity);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    private void esMaestro(){

        MateriaDAO.getInstance().getListaMaterias(this, new DAO.OnResultListaConsulta<Materia>() {
            @Override
            public void consultaSucess(List<Materia> t) {

                if(t != null){
                    MateriaAdapter adaptador = new MateriaAdapter(t);
                    recycler.setAdapter(adaptador);
                    recycler.setHasFixedSize(true);
                    recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }

            @Override
            public void consultaFailed(String error, int codigo) {
                Toast.makeText(MainActivity.this,error+" "+codigo, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void esAlumno(){


        EvaluacionDAO.getInstance().getCalificaciones(this, usuario, new DAO.OnResultListaConsulta<Evaluacion>() {
            @Override
            public void consultaSucess(List<Evaluacion> t) {

                if(t !=null){

                    EvaluacionAdaptador adaptador = new EvaluacionAdaptador(t);
                    recycler.setAdapter(adaptador);
                    recycler.setHasFixedSize(true);
                    recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }

            @Override
            public void consultaFailed(String error, int codigo) {
                Toast.makeText(MainActivity.this,error+" "+codigo, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
