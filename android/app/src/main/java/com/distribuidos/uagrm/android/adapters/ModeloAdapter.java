package com.distribuidos.uagrm.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.entities.MLocal;


import java.util.List;


public class ModeloAdapter
        extends RecyclerView.Adapter<ModeloAdapter.ViewHolderModelos>
        implements View.OnClickListener{

    List<MLocal> modelos;
    private View.OnClickListener listener;

    public ModeloAdapter(List<MLocal> modelos) {
        this.modelos = modelos;
    }

    @Override
    public ModeloAdapter.ViewHolderModelos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_modelo_adapter, null, false);

        view.setOnClickListener(this);

        return new ViewHolderModelos(view);
    }

    @Override
    public void onBindViewHolder(ModeloAdapter.ViewHolderModelos holder, int position) {
        holder.asignarDatos(modelos.get(position));
    }

    @Override
    public int getItemCount() {
        return modelos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderModelos extends RecyclerView.ViewHolder {

        TextView nombre, descripcion;

        public ViewHolderModelos(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre_item);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion_item);
        }

        public void asignarDatos(MLocal modelo){
            this.nombre.setText(modelo.getNombre());
            this.descripcion.setText(modelo.getDescripcion());
        }
    }
}
