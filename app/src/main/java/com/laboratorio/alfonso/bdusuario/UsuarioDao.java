package com.laboratorio.alfonso.bdusuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends AdminSQLiteOpenHelper {

    private SQLiteDatabase db;

    public UsuarioDao(Context context) {
        super(context);
    }

    public void crearUsuario(Usuario objeto){

        db = this.getReadableDatabase();

        ContentValues objetoInsert = new ContentValues();
        objetoInsert.put("u_ced", objeto.getCedula().toString());
        objetoInsert.put("u_nom", objeto.getNombre().toString());
        objetoInsert.put("u_ape", objeto.getApellido().toString());
        objetoInsert.put("u_eda", objeto.getEdad().toString());

        db.insert(TABLA, null, objetoInsert);

    }
    public void actualizarUsuario(Usuario objeto){

        db = this.getReadableDatabase();

        ContentValues objetoActualizar = new ContentValues();

        objetoActualizar.put("u_id", objeto.getId());

        objetoActualizar.put("u_ced", objeto.getCedula().toString());
        objetoActualizar.put("u_nom", objeto.getNombre().toString());
        objetoActualizar.put("u_ape", objeto.getApellido().toString());
        objetoActualizar.put("u_eda", objeto.getEdad().toString());

        db.update(TABLA, objetoActualizar,"u_id=?",new String[] { String.valueOf(objeto.getId())} );

    }
    public void eliminarUsuario (int Id){

        db = this.getReadableDatabase();

        db.delete(TABLA, "u_id=?", new String[] { String.valueOf(Id)});
    }
    public List<Usuario> obtenerUsuario (){
        List<Usuario> Listado = new ArrayList<Usuario>();

        String sql = "SELECT u_id, u_ced, u_nom, u_ape, u_eda from " + TABLA;

        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do{
                Usuario objLector= new Usuario();
                objLector.setId( cursor.getInt( cursor.getColumnIndex("u_id")));
                objLector.setCedula( cursor.getString( cursor.getColumnIndex("u_ced")));
                objLector.setNombre( cursor.getString( cursor.getColumnIndex("u_nom")));
                objLector.setApellido( cursor.getString( cursor.getColumnIndex("u_ape")));
                objLector.setEdad( cursor.getString( cursor.getColumnIndex("u_eda")));

                Listado.add(objLector);

            }while(cursor.moveToNext());
        }

        return Listado;
    }
    public Usuario obtenerUsu(int id){
       Usuario retorno = new Usuario();

        db = this.getReadableDatabase();

        String sql = "SELECT u_id, u_ced, u_nom, u_ape, u_eda from " + TABLA + " WHERE u_id=?";

        Cursor cursor = db.rawQuery(sql , new String[] {String.valueOf(id)});

        if(cursor.moveToFirst()){
                retorno.setId( cursor.getInt( cursor.getColumnIndex("u_id")));
                retorno.setCedula( cursor.getString( cursor.getColumnIndex("u_ced")));
                retorno.setNombre( cursor.getString( cursor.getColumnIndex("u_nom")));
                retorno.setApellido( cursor.getString( cursor.getColumnIndex("u_ape")));
                retorno.setEdad( cursor.getString( cursor.getColumnIndex("u_eda")));

        }

        return retorno;
    }
}
