package com.projects.bryan_g8.tourism.helpers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.bryan_g8.tourism.R;
import com.projects.bryan_g8.tourism.bean.Departamento;

import java.util.List;

/**
 * Created by bryan_g8 on 29/05/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{
    private List<Departamento> departamentos;

    public RVAdapter(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.nombreDepartamento.setText(departamentos.get(i).getNombre());
    }

    @Override
    public int getItemCount() {
        return departamentos.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_departamento, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nombreDepartamento;
        ImageView imagenDepartamento;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            nombreDepartamento = (TextView)itemView.findViewById(R.id.departamento_nombre);
            imagenDepartamento = (ImageView)itemView.findViewById(R.id.departamento_photo);
        }
    }


}