package com.laboratorio.alfonso.bdusuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminUsuarioActivity extends AppCompatActivity {

    Button  btnGuardar;
    Button  btnCancelar;
    TextView opcion;
    EditText Cedula;
    EditText Nombre;
    EditText Apellido;
    EditText Edad;
    int operacion;

    UsuarioDao usuarioDao;

    Usuario objBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuario);

        btnGuardar = (Button) findViewById(R.id.Guardar);
        btnCancelar = (Button) findViewById(R.id.Cancelar);

        Cedula = (EditText) findViewById(R.id.txt_ced);
        Nombre = (EditText) findViewById(R.id.txt_nombres);
        Apellido = (EditText) findViewById(R.id.text_apellidos);
        Edad= (EditText) findViewById(R.id.txt_edad);

        opcion = (TextView) findViewById(R.id.Regoperacion);

        usuarioDao = new UsuarioDao(getApplicationContext());

        Bundle parametros = getIntent().getExtras();
        operacion = parametros.getInt("operacion");

        if (operacion == 1){
            opcion.setText("Registro de usuario");
        }else{
            opcion.setText("Edicion de Usuario");
           objBuscar = usuarioDao.obtenerUsu(parametros.getInt("u_id"));
            Log.i("obtener usu", String.valueOf(objBuscar));

           Cedula.setText(objBuscar.getCedula().toString());
           Nombre.setText(objBuscar.getNombre().toString());
           Apellido.setText(objBuscar.getApellido().toString());
           Edad.setText(objBuscar.getEdad().toString());
        }

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operacion == 1){
                    Usuario objeto = new Usuario();
                    objeto.setCedula( Cedula.getText().toString());
                    objeto.setNombre( Nombre.getText().toString());
                    objeto.setApellido(Apellido.getText().toString());
                    objeto.setEdad(Edad.getText().toString());
                    usuarioDao.crearUsuario(objeto);

                    Toast.makeText(getApplicationContext(),"registro exitoso", Toast.LENGTH_SHORT).show();

                }else{
                    // opcion.setText("Edicion de Notas"); //editar
                    objBuscar.setCedula(Cedula.getText().toString());
                    objBuscar.setNombre( Nombre.getText().toString());
                    objBuscar.setApellido(Apellido.getText().toString());
                    objBuscar.setEdad(Edad.getText().toString());

                    Log.i("objeto actualizado", String.valueOf(objBuscar));

                    usuarioDao.actualizarUsuario(objBuscar);

                    Log.i("objeto Dao", String.valueOf(usuarioDao));

                    Toast.makeText(getApplicationContext(),"Actualizacion exitosa", Toast.LENGTH_SHORT).show();
                }

                Intent retorno = getIntent();
                setResult(RESULT_OK, retorno);
                finish();
            }
        });
    }
}
