package com.example.oscar.escuelaapp.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.oscar.escuelaapp.Entidades.Evaluacion;
import com.example.oscar.escuelaapp.Entidades.Materia;
import com.example.oscar.escuelaapp.Entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.oscar.escuelaapp.Recursos.Constante.CARPETA_DAO;
import static com.example.oscar.escuelaapp.Recursos.Constante.HOST;

/**
 * Created by Oscar on 15/06/2018.
 */

public class EvaluacionDAO {

    private static EvaluacionDAO dao;
    private ProgressDialog progressDialog;

    public EvaluacionDAO() {
    }

    public static EvaluacionDAO getInstance(){

        if(dao == null){
            dao = new EvaluacionDAO();
        }
        return dao;
    }

    public void getCalificaciones(final Context context, final Usuario usuario, final DAO.OnResultListaConsulta<Evaluacion>listener){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Camate po favo...esta cargando");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        String url = HOST + CARPETA_DAO+"main/CalificacionMateria.php?usuario="+usuario.getId();

        PeticionHTTP.GET get = new PeticionHTTP.GET(context, url);

        get.getResponseString(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {

                progressDialog.dismiss();
                try {
                    JSONArray array  = new JSONArray(respuesta);

                    if(array.length()>0) {

                        List<Evaluacion> lista = new ArrayList<Evaluacion>();

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject object =array.getJSONObject(i);

                            Materia materia = new Materia( object.getInt("materia_id"),object.getString("clave"),object.getString("nombre"),Boolean.parseBoolean(object.getString("activo")));

                            Evaluacion evaluacion = new Evaluacion(object.getInt("evaluacion_id"),object.getDouble("calificacion"),usuario,materia);

                            lista.add(evaluacion);

                        }
                        listener.consultaSucess(lista);
                    }
                    listener.consultaSucess(null);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {
                progressDialog.dismiss();
            }
        });

    }


    public void ingresarCalificacion (Context context, final Double calificacion, int materiaID, int usuarioID,final DAO.OnResultadoConsulta listener){

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Camate po favo...esta cargando");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = HOST + CARPETA_DAO + "main/RegistrarCalificacion.php";
        Map<String,String> params = new HashMap<>();
        params.put("calificacion",String.valueOf(calificacion));
        params.put("materiaID",String.valueOf(materiaID));
        params.put("usuarioID",String.valueOf(usuarioID));

        Log.i("url",params.toString());

        PeticionHTTP.POST post = PeticionHTTP.POST.getInstance(context,url,params);
        post.getResponse(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Evaluacion calificacion =  new Evaluacion();
                listener.consultaSuccess(calificacion);

            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                listener.consultaFailed(error,respuestaHTTP);
            }
        });




    }
}
