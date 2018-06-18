package com.example.oscar.escuelaapp.DAO;

import java.util.List;

/**
 * Created by Oscar on 13/06/2018.
 */

public abstract class DAO<T> {

    public interface OnResultadoConsulta<T>{

        void consultaSuccess(T t);
        void consultaFailed(String error, int codigo);

    }


    public interface OnResultListaConsulta<T>{

        void  consultaSucess(List<T> t);
        void  consultaFailed(String error, int codigo);

    }

}
