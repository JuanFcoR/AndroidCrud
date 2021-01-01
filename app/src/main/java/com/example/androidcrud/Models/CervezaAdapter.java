package com.example.androidcrud.Models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcrud.MainActivity;
import com.example.androidcrud.R;

import java.util.List;

public class CervezaAdapter extends RecyclerView.Adapter<CervezaAdapter.CervezaViewHolder> implements View.OnClickListener {

    List<cerveza> lista;
    private View.OnClickListener listener;
    public CervezaAdapter(List<cerveza> Lista) {
        this.lista=Lista;

        if(this.lista==null){
            cerveza cerv = new cerveza();
            cerv.setNombre("temp");
            cerv.setMarca("temp");

            Lista.add(cerv);
        }

    }

    @NonNull
    @Override
    public CervezaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cervezas_row,parent,false);

        v.setOnClickListener(this);
        return new CervezaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CervezaViewHolder holder, int position) {
        holder.bindCerveza(lista.get(position));
        holder.setOnClickListeners();


    }

    @Override
    public int getItemCount() {

        int numero=0;
        try{
            numero=lista.size();

        }catch(Exception exe) {
           numero=0;
        }
        return numero;
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener=listener;

    }

    @Override
    public void onClick(View v) {
        if(listener!=null) {
            listener.onClick(v);
        }

    }

    public class CervezaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvId;
        TextView tvNombre;
        TextView tvMarca;
        Button btAEditar;
        Context context;


        public CervezaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId= (TextView) itemView.findViewById(R.id.tvId);
            tvNombre= (TextView) itemView.findViewById(R.id.tvNombre);
            tvMarca= (TextView) itemView.findViewById(R.id.txtVMarca);
            btAEditar = (Button)itemView.findViewById(R.id.btAEditar);

            context =itemView.getContext();
        }

        private void bindCerveza(cerveza cerveza) {
            tvId.setText(String.valueOf(cerveza.getId()));
            tvNombre.setText(cerveza.getNombre());
            tvMarca.setText(cerveza.getMarca());
        }
        void setOnClickListeners()
        {
            btAEditar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("valorId",Integer.parseInt(tvId.getText().toString()));
            int accion=0;

            intent.putExtra("accion",accion);
            context.startActivity(intent);
        }
    }
}
