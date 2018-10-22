package com.distribuidos.uagrm.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.entities.AsignacionLocal;

import java.util.List;

public class AsignacionAdapter
        extends RecyclerView.Adapter<AsignacionAdapter.ViewHolderAsignaciones>
        implements View.OnClickListener {

    List<AsignacionLocal> asignaciones;
    private View.OnClickListener listener;

    public AsignacionAdapter(List<AsignacionLocal> asignaciones) {
        this.asignaciones = asignaciones;
    }

    @Override
    public AsignacionAdapter.ViewHolderAsignaciones onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_asignacion_adapter, null, false);

        view.setOnClickListener(this);

        return null;
    }

    @Override
    public void onBindViewHolder(AsignacionAdapter.ViewHolderAsignaciones holder, int position) {
        holder.asignarDatos(asignaciones.get(position));
    }

    @Override
    public int getItemCount() {
        return asignaciones.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }


    public class ViewHolderAsignaciones extends RecyclerView.ViewHolder {

        TextView modelo, area, cantidad, descripcion;

        public ViewHolderAsignaciones(View itemView) {
            super(itemView);
            this.modelo = (TextView) itemView.findViewById(R.id.asignacion_nombre);
            this.area = (TextView) itemView.findViewById(R.id.asignacion_area);
            this.cantidad = (TextView) itemView.findViewById(R.id.asignacion_cantidad);
            this.descripcion = (TextView) itemView.findViewById(R.id.asignacion_descripcion);
        }

        public void asignarDatos(AsignacionLocal asignacion) {
            this.modelo.setText(asignacion.getModelo());
            this.area.setText(asignacion.getArea());
            this.cantidad.setText(String.valueOf(asignacion.getCantidad()));
            this.descripcion.setText(asignacion.getDescripcion());
        }
    }
}
