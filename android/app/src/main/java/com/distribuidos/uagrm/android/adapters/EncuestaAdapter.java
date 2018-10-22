package com.distribuidos.uagrm.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.distribuidos.uagrm.android.R;
import com.distribuidos.uagrm.android.entities.Encuesta;

import java.util.List;

public class EncuestaAdapter
        extends RecyclerView.Adapter<EncuestaAdapter.ViewHolderEncuestas>
        implements View.OnClickListener {

    List<Encuesta> encuestas;
    private View.OnClickListener listener;

    public EncuestaAdapter(List<Encuesta> encuestas) {
        this.encuestas = encuestas;
    }

    @Override
    public ViewHolderEncuestas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_encuesta_adapter, null, false);

        view.setOnClickListener(this);

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolderEncuestas holder, int position) {
        holder.asignarDatos(encuestas.get(position));
    }

    @Override
    public int getItemCount() {
        return encuestas.size();
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

    public class ViewHolderEncuestas extends RecyclerView.ViewHolder {

        TextView id, fecha;

        public ViewHolderEncuestas(View itemView) {
            super(itemView);

            this.id = (TextView) itemView.findViewById(R.id.encuesta_id);
            this.fecha = (TextView) itemView.findViewById(R.id.encuesta_fecha);
        }

        public void asignarDatos(Encuesta encuesta) {
            this.id.setText(String.valueOf(encuesta.getId()));
            this.fecha.setText(encuesta.getFecha());
        }
    }
}
