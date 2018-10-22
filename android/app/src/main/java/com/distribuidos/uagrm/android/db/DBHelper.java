package com.distribuidos.uagrm.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.distribuidos.uagrm.android.entities.Asignacion;
import com.distribuidos.uagrm.android.entities.AsignacionLocal;
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
    String query_create_asignacion = "CREATE TABLE `asignacion` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `asignacion_id` INTEGER NOT NULL," +
            " `modelo` TEXT NOT NULL," +
            " `area` TEXT NOT NULL," +
            " `cantidad` INTEGER NOT NULL," +
            " `descripcion` TEXT," +
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
        db.execSQL(query_create_asignacion);
//        db.execSQL(query_create_ficha);
//        db.execSQL(query_create_cerrada);
//        db.execSQL(query_create_abierta);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS asignacion";
        db.execSQL(query);
        onCreate(db);
    }


    // Funciones

    public long addAsignacion(AsignacionLocal asignacion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("asignacion_id", asignacion.getAsignacion_id());
        values.put("modelo", asignacion.getModelo());
        values.put("area", asignacion.getArea());
        values.put("cantidad", asignacion.getCantidad());
        values.put("descripcion", asignacion.getDescripcion());
        values.put("json", asignacion.getJson());


        long x = db.insert("asignacion", null, values);
        db.close();

        return x;
    }

    public int updateAsignacion(AsignacionLocal asignacion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("asignacion_id", asignacion.getAsignacion_id());
        values.put("modelo", asignacion.getModelo());
        values.put("area", asignacion.getArea());
        values.put("cantidad", asignacion.getCantidad());
        values.put("descripcion", asignacion.getDescripcion());
        values.put("json", asignacion.getJson());

        int x = db.update("asignacion", values, "id = ?",
                new String[]{String.valueOf(asignacion.getId())});

        db.close();

        return x;
    }

    public int deleteAsignacion(int asignacion_id){
        SQLiteDatabase db = this.getWritableDatabase();

        int x = db.delete("asignacion", "asignacion_id = ?",
                new String[]{String.valueOf(asignacion_id)});
        db.close();

        return x;
    }

    public AsignacionLocal getAsignacion(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM asignacion WHERE id = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            AsignacionLocal asignacion = new AsignacionLocal();
            asignacion.setId(cursor.getInt(0));
            asignacion.setAsignacion_id(cursor.getInt(1));
            asignacion.setModelo(cursor.getString(2));
            asignacion.setArea(cursor.getString(3));
            asignacion.setCantidad(cursor.getInt(4));
            asignacion.setDescripcion(cursor.getString(5));
            asignacion.setJson(cursor.getString(6));

            db.close();
            return asignacion;

        }

        db.close();

        return null;
    }


    public List<AsignacionLocal> getAsignaciones(){
        List<AsignacionLocal> asignaciones = new ArrayList<>();

        String query = "SELECT * FROM asignacion";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0){
            do {
                AsignacionLocal asignacion = new AsignacionLocal();

                asignacion.setId(cursor.getInt(0));
                asignacion.setAsignacion_id(cursor.getInt(1));
                asignacion.setModelo(cursor.getString(2));
                asignacion.setArea(cursor.getString(3));
                asignacion.setCantidad(cursor.getInt(4));
                asignacion.setDescripcion(cursor.getString(5));
                asignacion.setJson(cursor.getString(6));

                asignaciones.add(asignacion);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return asignaciones;
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
