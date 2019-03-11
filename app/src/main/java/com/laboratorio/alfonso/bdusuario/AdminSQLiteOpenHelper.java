package com.laboratorio.alfonso.bdusuario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.lang.annotation.Target;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="personas";
    static int VERSION = 1;
    static String TABLA = "usuario";

    String sql = "create table " + TABLA + "(u_id integer primary key, u_ced text, u_nom text, u_ape text, u_eda text)";

    public AdminSQLiteOpenHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if EXISTS " + TABLA);
        db.execSQL(sql);
    }
}
