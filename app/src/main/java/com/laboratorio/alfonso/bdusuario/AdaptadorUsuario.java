package com.laboratorio.alfonso.bdusuario;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class AdaptadorUsuario extends RecyclerView.Adapter<ViewHolderUsuario> implements View.OnClickListener {
    List<Usuario> Listado;

    public AdaptadorUsuario(List<Usuario> listado) {
        Listado = listado;
    }

    @NonNull
    @Override
    public ViewHolderUsuario onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);

        ViewHolderUsuario viewHolder = new ViewHolderUsuario(vista);

        viewHolder.Editar.setOnClickListener(AdaptadorUsuario.this);

        viewHolder.Eliminar.setOnClickListener(AdaptadorUsuario.this);

        viewHolder.Editar.setTag(viewHolder);
        viewHolder.Eliminar.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUsuario holder, int position) {

        Usuario objeto =  Listado.get(position);
        holder.Cedula.setText(objeto.getCedula());
        holder.Nombre.setText(objeto.getNombre());
        holder.Apellido.setText(objeto.getApellido());
        holder.Edad.setText(objeto.getEdad());

    }

    @Override
    public int getItemCount() {
        return Listado.size() ;
    }

    @Override
    public void onClick(View v) {

        ViewHolderUsuario holder = (ViewHolderUsuario) v.getTag();

        Usuario objeto = Listado.get(holder.getAdapterPosition());

        Log.e("Listado", String.valueOf(Listado));

        if (v.getId() == holder.Editar.getId()) {

            Toast.makeText(v.getContext(), "Editar", Toast.LENGTH_SHORT).show();
            Intent editar = new Intent(v.getContext(), AdminUsuarioActivity.class);
            editar.putExtra("opcion", 2);

            Log.i("id objeto", String.valueOf(objeto.getId()));

            editar.putExtra("u_id", objeto.getId());

            Log.i("editar", String.valueOf(editar));

            ((Activity) v.getContext()).startActivityForResult(editar, 1000);
        }
        if (v.getId() == holder.Eliminar.getId()) {

            Toast.makeText(v.getContext(), "elimino " + objeto.getNombre(), Toast.LENGTH_SHORT).show();
            UsuarioDao usuarioDao = new UsuarioDao(v.getContext());
            usuarioDao.eliminarUsuario(objeto.getId());
            Log.i("Listado", String.valueOf(Listado));

            Log.i("holder", String.valueOf(holder));
            Log.i("posicion", String.valueOf(holder.getAdapterPosition()));
            remove(holder.getAdapterPosition());
            //remove(holder.getAdapterPosition());
            //int posicion = holder.getAdapterPosition();

            //Log.i("remove" , String.valueOf(Listado.remove(holder.getLayoutPosition())));
            //notifyDataSetChanged();
           // notifyItemRemoved(holder.getAdapterPosition());
           // notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());

            //holder.itemView.setVisibility(View.GONE);
        }
    }

    public void remove(int position) {
        Listado.remove(position);
        Log.i("removido", String.valueOf((position)));

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
        Log.e("item", String.valueOf(getItemCount()));
        //holder.itemView.setVisibility(View.GONE);

    }
}
