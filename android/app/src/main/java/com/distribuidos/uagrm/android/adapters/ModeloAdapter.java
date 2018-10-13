package com.distribuidos.uagrm.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.entities.Modelo_cabecera;

import java.util.List;


public class ModeloAdapter extends RecyclerView.Adapter<ModeloAdapter.ViewHolderModelos>{

    List<Modelo_cabecera> listaModelos;

    public ModeloAdapter(List<Modelo_cabecera> listaModelos) {
        this.listaModelos = listaModelos;
    }

    @Override
    public ModeloAdapter.ViewHolderModelos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_modelo_adapter, null, false);
        return new ViewHolderModelos(view);
    }

    @Override
    public void onBindViewHolder(ModeloAdapter.ViewHolderModelos holder, int position) {
        holder.asignarDatos(listaModelos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaModelos.size();
    }

    public class ViewHolderModelos extends RecyclerView.ViewHolder {

        TextView nombre, descripcion;

        public ViewHolderModelos(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre_item);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion_item);
        }

        public void asignarDatos(Modelo_cabecera modelo){
            this.nombre.setText(modelo.getNombre());
            this.descripcion.setText(modelo.getDescripcion());
        }
    }
}
