package com.example.oscar.escuelaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.oscar.escuelaapp.Entidades.Materia;
import com.example.oscar.escuelaapp.Entidades.Usuario;
import com.example.oscar.escuelaapp.R;
import com.example.oscar.escuelaapp.RegistrarCalificacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar on 17/06/2018.
 */

public class SpinnerMateriaAdapter extends BaseAdapter {

    List<Materia> list = new ArrayList<>();
    Context context ;

    public SpinnerMateriaAdapter(List<Materia> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Materia getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View vista = view;
        if (view == null)
            vista = inflater.inflate(R.layout.item_spinner, null);

        TextView txtTexto = (TextView)vista.findViewById(R.id.txtSpinner);
        txtTexto.setText(getItem(i).getNombre());

        return vista;
    }
}
