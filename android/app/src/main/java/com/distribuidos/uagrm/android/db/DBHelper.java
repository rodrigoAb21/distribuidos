package com.distribuidos.uagrm.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.distribuidos.uagrm.android.entities.Ficha;
import com.distribuidos.uagrm.android.entities.MLocal;
import com.distribuidos.uagrm.android.entities.RespAbierta;
import com.distribuidos.uagrm.android.entities.RespCerrada;

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
            "); ";

    String query_create_ficha = "CREATE TABLE `ficha` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `id_modelo_api` INTEGER NOT NULL," +
            " `id_modelo_local` INTEGER NOT NULL," +
            " `estado` TEXT NOT NULL" +
            "); ";

    String query_create_cerrada = "CREATE TABLE `resp_cerrada` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `id_view` TEXT NOT NULL," +
            " `estado` INTEGER NOT NULL," +
            " `id_ficha` INTEGER NOT NULL" +
            "); ";

    String query_create_abierta = "CREATE TABLE `resp_abierta` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `tag` TEXT NOT NULL," +
            " `valor` TEXT NOT NULL," +
            " `id_ficha` INTEGER NOT NULL" +
            "); ";




    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query_create_modelo);
        db.execSQL(query_create_ficha);
        db.execSQL(query_create_cerrada);
        db.execSQL(query_create_abierta);
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
        String query = "SELECT * FROM modelo WHERE id = " + id;
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




    //CRUD ficha

    // insertar un nuevo modelo
    public long addFicha(Ficha ficha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_modelo_api", ficha.getId_modelo_api());
        values.put("id_modelo_local", ficha.getId_modelo_local());
        values.put("estado", "No finalizada");

        long x = db.insert("ficha", null, values);
        db.close();

        return x;
    }

    public int updateficha(Ficha ficha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("estado", ficha.getEstado());

        int x = db.update("ficha", values, "id = ?",
                new String[]{String.valueOf(ficha.getId())});
        db.close();

        return x;
    }

    // obtener la ultima ficha en editar por id del modelo local
    public Ficha getLastFicha(int id_modelo_local){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM ficha WHERE id_modelo_local = " + id_modelo_local + " AND estado = 'No finalizada'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            Ficha ficha = new Ficha();
            ficha.setId(cursor.getInt(0));
            ficha.setId_modelo_api(cursor.getInt(1));
            ficha.setId_modelo_local(cursor.getInt(2));
            ficha.setEstado(cursor.getString(3));

            db.close();
            return ficha;

        }

        db.close();

        return null;
    }





























    //CRUD ficha

    // insertar un nuevo modelo
    public long addRespAbierta(RespAbierta respAbierta){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tag", respAbierta.getTag());
        values.put("valor", respAbierta.getValor());
        values.put("id_ficha", respAbierta.getId_ficha());

        long x = db.insert("resp_abierta", null, values);
        db.close();

        return x;
    }

    public int updateRespAbierta(RespAbierta respAbierta){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("valor", respAbierta.getValor());

        int x = db.update("resp_abierta", values, "id = ?",
                new String[]{String.valueOf(respAbierta.getId())});
        db.close();

        return x;
    }

    // obtener todas las Respuestas Abiertas
    public List<RespAbierta> getRespAbiertas(int id_ficha){
        List<RespAbierta> abiertaList = new ArrayList<>();

        String query = "SELECT * FROM resp_abierta WHERE id_ficha = " + id_ficha;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {

                RespAbierta respAbierta = new RespAbierta();

                respAbierta.setId(cursor.getInt(0));
                respAbierta.setTag(cursor.getString(1));
                respAbierta.setValor(cursor.getString(2));
                respAbierta.setId_ficha(cursor.getInt(3));

                abiertaList.add(respAbierta);
            }
            while (cursor.moveToNext());
        }
        db.close();

        return abiertaList;
    }


    public RespAbierta getRespAbierta(String tag) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM resp_abierta WHERE tag = '" + tag + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            RespAbierta respAbierta = new RespAbierta();
            respAbierta.setId(cursor.getInt(0));
            respAbierta.setTag(cursor.getString(1));
            respAbierta.setValor(cursor.getString(2));
            respAbierta.setId_ficha(cursor.getInt(3));


            db.close();
            return respAbierta;

        }

        db.close();

        return null;
    }

























    //CRUD ficha

    // insertar un nuevo modelo
    public long addRespCerrada(RespCerrada respCerrada){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_view", respCerrada.getId_view());
        values.put("estado", respCerrada.getEstado());
        values.put("id_ficha", respCerrada.getId_ficha());

        long x = db.insert("resp_cerrada", null, values);
        db.close();

        return x;
    }

    public int updateRespCerrada(RespCerrada respCerrada){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_view", respCerrada.getId_view());
        values.put("estado", respCerrada.getEstado());
        values.put("id_ficha", respCerrada.getId_ficha());

        int x = db.update("resp_cerrada", values, "id = ?",
                new String[]{String.valueOf(respCerrada.getId())});
        db.close();

        return x;
    }

    // obtener todas las Respuestas Abiertas
    public List<RespAbierta> getRespCerradas(int id_ficha){
        List<RespAbierta> abiertaList = new ArrayList<>();

        String query = "SELECT * FROM resp_cerrada WHERE id_ficha = " + id_ficha;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {

                RespAbierta respAbierta = new RespAbierta();

                respAbierta.setId(cursor.getInt(0));
//                respAbierta.setId_view(cursor.getString(1));
                respAbierta.setValor(cursor.getString(2));
                respAbierta.setId_ficha(cursor.getInt(3));

                abiertaList.add(respAbierta);
            }
            while (cursor.moveToNext());
        }
        db.close();

        return abiertaList;
    }



}