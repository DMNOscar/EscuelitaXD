package com.example.oscar.escuelaapp.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.oscar.escuelaapp.DAO.DAO.OnResultadoConsulta;
import com.example.oscar.escuelaapp.Entidades.Materia;

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
 * Created by Oscar on 14/06/2018.
 */

public class MateriaDAO {

    private static MateriaDAO dao;
    private ProgressDialog progressDialog;

    public MateriaDAO() {
    }


    public static MateriaDAO getInstance(){
        if (dao == null){
            dao = new MateriaDAO();
        }
        return dao;
    }

    public void getListaMaterias(Context context, final DAO.OnResultListaConsulta<Materia> listener){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Camate po favo...esta cargando");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String url = HOST + CARPETA_DAO+"main/listamateria.php";

        PeticionHTTP.GET get = new PeticionHTTP.GET(context,url);
        get.getResponseString(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {

                progressDialog.dismiss();
                try {
                    JSONArray array  = new JSONArray(respuesta);
                    if(array.length()>0){

                        List<Materia> lista = new ArrayList<Materia>();
                                for(int i=0; i<array.length();i++){
                                    JSONObject object =array.getJSONObject(i);
                                    Materia materia = new Materia(Integer.parseInt(object.getString("id")),object.getString("clave"),object.getString("nombre"),Boolean.parseBoolean(object.getString("activo")));
                                    lista.add(materia);
                                }
                        listener.consultaSucess(lista);
                    }else {
                        listener.consultaSucess(null);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.consultaSucess(null);
                }

            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {
                progressDialog.dismiss();

                listener.consultaFailed(error, respuestaHTTP);

            }
        });
    }


    public void registrarMateria(Context context, final String clave, final String nombre, final DAO.OnResultadoConsulta listener) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Camate po favo...esta cargando");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String url = HOST + CARPETA_DAO + "main/RegistrarMateria.php";
        Map<String , String> parametros= new HashMap<>();
        parametros.put("clave", clave);
        parametros.put("nombre", nombre);

        PeticionHTTP.POST post = PeticionHTTP.POST.getInstance(context,url,parametros);

        post.getResponse(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {
                progressDialog.dismiss();
                Materia materia = new Materia();
                listener.consultaSuccess(materia);
            }

            @Override
            public void onFailed(String error, int respuestaHTTP)    {
                progressDialog.dismiss();
            }
        });
    }

}
