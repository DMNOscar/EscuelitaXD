package com.example.oscar.escuelaapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oscar.escuelaapp.Entidades.Materia;
import com.example.oscar.escuelaapp.R;

import java.util.List;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Oscar on 14/06/2018.
 */

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.MaterialHolder> {

    private List<Materia> materiaList;
    private Context context;

    public MateriaAdapter(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }



    @Override
    public MaterialHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_materia,parent,false);

        return new MaterialHolder(view);
    }

    @Override
    public void onBindViewHolder(MaterialHolder holder, int position) {

        holder.txtID.setText(String.valueOf(materiaList.get(position).getId()));
        holder.txtClave.setText(materiaList.get(position).getClave());
        holder.txtNombre.setText(materiaList.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return materiaList.size();
    }


    public static  class MaterialHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView txtID, txtClave, txtNombre;

        public MaterialHolder(View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtClave = itemView.findViewById(R.id.txtClave);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}