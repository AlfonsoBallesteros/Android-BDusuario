package com.laboratorio.alfonso.bdusuario;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewHolderUsuario extends RecyclerView.ViewHolder {

    TextView Cedula;
    TextView Nombre;
    TextView Apellido;
    TextView Edad;

    Button Editar;
    Button  Eliminar;

    public ViewHolderUsuario(@NonNull View vistaPadre) {
        super(vistaPadre);

        Editar = (Button) vistaPadre.findViewById(R.id.Editar);
        Eliminar= (Button) vistaPadre.findViewById(R.id.Eliminar);

        Cedula = (TextView) vistaPadre.findViewById(R.id.Cedula);
        Nombre = (TextView) vistaPadre.findViewById(R.id.Nombre);
        Apellido = (TextView) vistaPadre.findViewById(R.id.Apellido);
        Edad = (TextView) vistaPadre.findViewById(R.id.Edad);
    }
}
