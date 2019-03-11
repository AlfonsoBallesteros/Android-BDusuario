package com.laboratorio.alfonso.bdusuario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnAccion;
    RecyclerView contenedor;

    UsuarioDao usuarioDao;

    List<Usuario> Listado;

    AdaptadorUsuario adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAccion = (FloatingActionButton) findViewById(R.id.btnAccion);
        contenedor = (RecyclerView) findViewById(R.id.contenedor);

        usuarioDao = new UsuarioDao(getApplicationContext());

        Listado = usuarioDao.obtenerUsuario();

        adaptador = new AdaptadorUsuario(Listado);

        contenedor.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        contenedor.setAdapter(adaptador);
        contenedor.setLayoutManager(layout);

        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear = new Intent(getApplicationContext(), AdminUsuarioActivity.class);
                crear.putExtra("operacion",1);
                startActivityForResult(crear, 1000);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 1000){
            Toast.makeText(getApplicationContext(),"Actualizar Listado",Toast.LENGTH_SHORT).show();

            contenedor.swapAdapter(new AdaptadorUsuario(usuarioDao.obtenerUsuario()),false);
        }

    }
}
