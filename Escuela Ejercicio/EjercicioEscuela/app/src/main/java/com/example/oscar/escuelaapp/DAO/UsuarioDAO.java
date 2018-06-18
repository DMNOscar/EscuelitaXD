package com.example.oscar.escuelaapp.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.oscar.escuelaapp.Entidades.Categoria;
import com.example.oscar.escuelaapp.Entidades.Credencial;
import com.example.oscar.escuelaapp.Entidades.Materia;
import com.example.oscar.escuelaapp.Entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.oscar.escuelaapp.Recursos.Constante.HOST;
import static com.example.oscar.escuelaapp.Recursos.Constante.CARPETA_DAO;

/**
 * Created by Oscar on 13/06/2018.
 */

public class UsuarioDAO {


    private static UsuarioDAO dao;
    private ProgressDialog progressDialog;

    private UsuarioDAO() {
    }

    public  static  UsuarioDAO getInstnce(){
        if (dao==null){
            dao= new UsuarioDAO();
        }
        return dao;
    }


    public void obtenerDatosLogin(Context context, final String nick, final String pass, final DAO.OnResultadoConsulta<Usuario> listener){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Camate po favo XD, esta cargando");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String url =  HOST + CARPETA_DAO + "login/acceso.php";

        Map<String, String> params = new HashMap<>();
        params.put("nick", nick);
        params.put("pass", pass);

        PeticionHTTP.POST pos= PeticionHTTP.POST.getInstance(context,url, params);

        pos.getResponse(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();

                    try {
                        JSONArray array = new JSONArray(respuesta);

                        JSONObject json;
                        if(array.length() >0) {
                            json = array.getJSONObject(0);

                            Credencial credencial = new Credencial(nick,pass);

                            Categoria categoria = new Categoria(Integer.parseInt(json.getString("categoriaid")),json.getString("categoria"),true);

                            Usuario usuario = new Usuario(Integer.parseInt(json.getString("usuarioid")),json.getString("nombre"), credencial,Boolean.parseBoolean(json.getString("activo")),categoria);

                            listener.consultaSuccess(usuario);
                        }else{

                            listener.consultaSuccess(null);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {

                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                    listener.consultaFailed(error, respuestaHTTP);
                }

            }
        });

    }

    public void regisrtarUsuario(Context context, final String nombre, final String nick, final String pass, final DAO.OnResultadoConsulta listener) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Camate po favo XD, esta cargando");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String url =  HOST + CARPETA_DAO +"main/RegistrarUsuario.php?";

        Map<String, String> params = new HashMap<>();
        params.put("nombre", nombre);
        params.put("nick", nick);
        params.put("pass", pass);


        Log.d("parametros",params.toString());
        PeticionHTTP.POST post= PeticionHTTP.POST.getInstance(context,url, params);

        post.getResponse(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {
                progressDialog.dismiss();
                Usuario usuario= new Usuario();
                listener.consultaSuccess(usuario);
            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {
                progressDialog.dismiss();
            }
        });
    }



    public void actualizarUsuario(Context context,final int id, final String nombre, final String nick, final String pass, final DAO.OnResultadoConsulta listener) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Camate po favo XD, esta cargando");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String url =  HOST + CARPETA_DAO +"main/ActualizarUsuario.php?";

        Map<String, String> params = new HashMap<>();
        params.put("id",id+"");
        params.put("nombre", nombre);
        params.put("nick", nick);
        params.put("pass", pass);


        Log.d("parametros",params.toString());
        PeticionHTTP.POST post= PeticionHTTP.POST.getInstance(context,url, params);

        post.getResponse(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {
                progressDialog.dismiss();
                Usuario usuario= new Usuario();
                listener.consultaSuccess(usuario);
            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {
                progressDialog.dismiss();
            }
        });
    }


    public void obtenerAlumnos (Context context, final DAO.OnResultListaConsulta listener){

        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);

        String url = HOST + CARPETA_DAO +"main/ListaUsuarios.php";
        PeticionHTTP.GET get =new PeticionHTTP.GET(context,url);
        get.getResponseString(new PeticionHTTP.OnConsultaListener<String>() {


            @Override
            public void onSuccess(String respuesta) {
                progressDialog.dismiss();

                try {
                JSONArray jsonArray = new JSONArray(respuesta);
                Log.i("tamano",String.valueOf(jsonArray.length()));
                if (jsonArray.length() >0){
                    List<Usuario>lista = new ArrayList<Usuario>();
                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Categoria categoria = new Categoria(Integer.parseInt(jsonObject.getString("categoria_id")),
                                jsonObject.getString("categoria_nombre"),Boolean.parseBoolean(jsonObject.getString("activo")));

                        Credencial credencial = new Credencial(jsonObject.getString("nick"),jsonObject.getString("pass"));

                        Usuario usuario = new Usuario(Integer.parseInt(jsonObject.getString("usuario_id")),jsonObject.getString("usuario_nombre"),
                                credencial,true,categoria);
                        lista.add(usuario);
                    }
                    listener.consultaSucess(lista);
                }else{
                    listener.consultaSucess(null);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                listener.consultaSucess(null);
            }
            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {

            }
        });
    }

}