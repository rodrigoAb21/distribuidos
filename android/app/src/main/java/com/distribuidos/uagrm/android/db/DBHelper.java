package com.distribuidos.uagrm.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.distribuidos.uagrm.android.entities.MLocal;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "distribuidos";

    // Tablas
    String query_create_modelo = "CREATE TABLE `modelo` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `id_modelo` INTEGER NOT NULL," +
            " `nombre` TEXT NOT NULL," +
            " `descripcion` TEXT," +
            " `estado` TEXT NOT NULL," +
            " `json` TEXT NOT NULL" +
            ");";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query_create_modelo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS modelo";
        db.execSQL(query);
        onCreate(db);
    }


    // Funciones para la tabla modelo

    // insertar un nuevo modelo
    public long addModelo(MLocal mLocal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_modelo", mLocal.getId_modelo());
        values.put("nombre", mLocal.getNombre());
        values.put("descripcion", mLocal.getDescripcion());
        values.put("estado", mLocal.getEstado());
        values.put("json", mLocal.getJson());

        long x = db.insert("modelo", null, values);
        db.close();

        return x;
    }

    // actualizar modelo
    public int updateModelo(MLocal mLocal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_modelo", mLocal.getId_modelo());
        values.put("nombre", mLocal.getNombre());
        values.put("descripcion", mLocal.getDescripcion());
        values.put("estado", mLocal.getEstado());
        values.put("json", mLocal.getJson());

        int x = db.update("modelo", values, "id = ?",
                new String[]{String.valueOf(mLocal.getId())});
        db.close();

        return x;
    }

    // eliminar modelo
    public int deleteModelo(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        int x = db.delete("modelo", "id = ?", new String[]{String.valueOf(id)});
        db.close();

        return x;
    }

    // obtener un modelo por id
    public MLocal getModelo(int id){
        MLocal mLocal = new MLocal();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.w("ID_ERROR", "id: "+id);
        String query = "SELECT * FROM modelo WHERE id_modelo = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null){
            cursor.moveToFirst();

            mLocal.setId(cursor.getInt(0));
            mLocal.setId_modelo(cursor.getInt(1));
            mLocal.setNombre(cursor.getString(2));
            mLocal.setDescripcion(cursor.getString(3));
            mLocal.setEstado(cursor.getString(4));
            mLocal.setJson(cursor.getString(5));
        }

        db.close();

        return mLocal;
    }

    // obtener todos los modelos
    public List<MLocal> getModelos(){
        List<MLocal> modelos = new ArrayList<>();

        String query = "SELECT * FROM modelo";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                MLocal mLocal = new MLocal();
                mLocal.setId(cursor.getInt(0));
                mLocal.setId_modelo(cursor.getInt(1));
                mLocal.setNombre(cursor.getString(2));
                mLocal.setDescripcion(cursor.getString(3));
                mLocal.setEstado(cursor.getString(4));
                mLocal.setJson(cursor.getString(5));

                modelos.add(mLocal);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return modelos;
    }



}
