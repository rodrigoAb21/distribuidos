package com.distribuidos.uagrm.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.distribuidos.uagrm.android.entities.AsignacionLocal;

import java.util.List;

public class AsignacionAdapter extends RecyclerView.Adapter<AsignacionAdapter.ViewHolderAdapter> {

    List<AsignacionLocal> asignaciones;
    private View.OnClickListener onClickListener;
    


    @Override
    public ViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolderAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {
        public ViewHolderAdapter(View itemView) {
            super(itemView);
        }
    }
}
