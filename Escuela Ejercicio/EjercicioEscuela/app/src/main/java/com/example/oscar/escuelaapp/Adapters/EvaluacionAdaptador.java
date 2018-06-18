package com.example.oscar.escuelaapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oscar.escuelaapp.Entidades.Evaluacion;
import com.example.oscar.escuelaapp.R;

import java.util.List;

/**
 * Created by Oscar on 15/06/2018.
 */

public class EvaluacionAdaptador extends RecyclerView.Adapter<EvaluacionAdaptador.EvaluacionHolder>{

    private List<Evaluacion> evaloacionList;
    private Context context;

    public EvaluacionAdaptador(List<Evaluacion> evaloacionList) {
        this.evaloacionList = evaloacionList;
    }

    @Override
    public EvaluacionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_evaluacion,parent,false);

        return new EvaluacionHolder(view);
    }

    @Override
    public void onBindViewHolder(EvaluacionHolder holder, int position) {

      holder.txtID.setText(String.valueOf(evaloacionList.get(position).getId()));
      holder.txClave.setText(evaloacionList.get(position).getMateria().getClave());
      holder.txtcalificacion.setText(String.valueOf(evaloacionList.get(position).getCalificacion()));
      holder.txtNombre.setText(evaloacionList.get(position).getMateria().getNombre());

    }

    @Override
    public int getItemCount() {
        return evaloacionList.size();
    }

    public class EvaluacionHolder extends RecyclerView.ViewHolder {

        protected TextView txtID,txtcalificacion, txtNombre, txClave;

        public EvaluacionHolder(View itemView) {
            super(itemView);

            txtID= itemView.findViewById(R.id.evalID);
            txClave= itemView.findViewById(R.id.evalClave);
            txtcalificacion= itemView.findViewById(R.id.evalCalificacion);
            txtNombre = itemView.findViewById(R.id.evalNombre);

        }
    }
}
