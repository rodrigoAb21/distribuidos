package com.distribuidos.uagrm.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.distribuidos.uagrm.android.entities.Asignacion;
import com.distribuidos.uagrm.android.entities.AsignacionLocal;
import com.distribuidos.uagrm.android.entities.Encuesta;
import com.distribuidos.uagrm.android.entities.Ficha;
import com.distribuidos.uagrm.android.entities.MLocal;
import com.distribuidos.uagrm.android.entities.Otro;
import com.distribuidos.uagrm.android.entities.RespAbierta;
import com.distribuidos.uagrm.android.entities.RespCerrada;
import com.distribuidos.uagrm.android.entities.RespOtro;

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


    String query_create_encuesta = "CREATE TABLE `encuesta` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `fecha` TEXT NOT NULL," +
            " `estado` TEXT NOT NULL," +
            " `asignacion_id` INTEGER NOT NULL" +
            "); ";



    String query_create_ficha = "CREATE TABLE `ficha` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `encuesta_id` INTEGER NOT NULL," +
            " `pregunta_id` INTEGER NOT NULL" +
            "); ";



    String query_create_abierta = "CREATE TABLE `resp_abierta` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `tag` TEXT NOT NULL," +
            " `valor` TEXT NOT NULL," +
            " `ficha_id` INTEGER NOT NULL," +
            " `campo_id` INTEGER NOT NULL" +
            "); ";


    String query_create_cerrada = "CREATE TABLE `resp_cerrada` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `tag` TEXT NOT NULL," +
            " `ficha_id` INTEGER NOT NULL," +
            " `opcion_id` INTEGER NOT NULL" +
            "); ";

    String query_create_otro = "CREATE TABLE `resp_otro` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
            " `tag` TEXT NOT NULL," +
            " `valor` TEXT NOT NULL," +
            " `ficha_id` INTEGER NOT NULL," +
            " `otro_id` INTEGER NOT NULL" +
            "); ";



    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query_create_asignacion);
        db.execSQL(query_create_encuesta);
        db.execSQL(query_create_ficha);
        db.execSQL(query_create_abierta);
        db.execSQL(query_create_cerrada);
        db.execSQL(query_create_otro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS asignacion";
        db.execSQL(query);
        onCreate(db);
    }


    // Funciones

    // ------------------------------   ASIGNACION   -------------------------------------------

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



    // ------------------------------   ENCUESTA   -------------------------------------------

    public long addEncuesta(Encuesta encuesta){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("fecha", encuesta.getFecha());
        values.put("estado", encuesta.getEstado());
        values.put("asignacion_id", encuesta.getAsignacion_id());

        long x = db.insert("encuesta", null, values);
        db.close();

        return x;
    }

    public int updateEncuesta(int id, String estado){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("estado", estado);

        int x = db.update("encuesta", values, "id = ?",
                new String[]{String.valueOf(id)});

        db.close();

        return x;

    }

    public int deleteEncuesta(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        int x = db.delete("encuesta", "id = ?",
                new String[]{String.valueOf(id)});
        db.close();

        return x;
    }

    public Encuesta getEncuesta(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM encuesta WHERE id = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            Encuesta encuesta = new Encuesta();
            encuesta.setId(cursor.getInt(0));
            encuesta.setFecha(cursor.getString(1));
            encuesta.setEstado(cursor.getString(2));
            encuesta.setAsignacion_id(cursor.getInt(3));

            db.close();
            return encuesta;

        }

        db.close();

        return null;
    }

    public Encuesta getLastEncuesta(int asignacion_id){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM encuesta WHERE estado = 'En proceso' AND asignacion_id = "
                + asignacion_id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            Encuesta encuesta = new Encuesta();
            encuesta.setId(cursor.getInt(0));
            encuesta.setFecha(cursor.getString(1));
            encuesta.setEstado(cursor.getString(2));
            encuesta.setAsignacion_id(cursor.getInt(3));

            db.close();
            return encuesta;

        }

        db.close();

        return null;
    }

    public List<Encuesta> getEncuestas(int asignacion_id){
        List<Encuesta> encuestas = new ArrayList<>();

        String query = "SELECT * FROM encuesta WHERE asignacion_id = " + asignacion_id + 
                " AND estado != 'En proceso' ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0){
            do {
                Encuesta encuesta = new Encuesta();

                encuesta.setId(cursor.getInt(0));
                encuesta.setFecha(cursor.getString(1));
                encuesta.setEstado(cursor.getString(2));
                encuesta.setAsignacion_id(cursor.getInt(3));

                encuestas.add(encuesta);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return encuestas;
    }

    public List<Encuesta> getEncuestasFinalizadas(int asignacion_id){
        List<Encuesta> encuestas = new ArrayList<>();

        String query = "SELECT * FROM encuesta WHERE asignacion_id = " + asignacion_id + 
                " AND estado = 'Finalizada'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0){
            do {
                Encuesta encuesta = new Encuesta();

                encuesta.setId(cursor.getInt(0));
                encuesta.setFecha(cursor.getString(1));
                encuesta.setEstado(cursor.getString(2));
                encuesta.setAsignacion_id(cursor.getInt(3));

                encuestas.add(encuesta);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return encuestas;
    }




    // ------------------------------   FICHA   -------------------------------------------

    public long addFicha(Ficha ficha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("encuesta_id", ficha.getEncuesta_id());
        values.put("pregunta_id", ficha.getPregunta_id());

        long x = db.insert("ficha", null, values);
        db.close();

        return x;
    }

    public int deleteFicha(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        int x = db.delete("ficha", "id = ?",
                new String[]{String.valueOf(id)});
        db.close();

        return x;
    }

    public Ficha getFicha(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM ficha WHERE id = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            Ficha ficha = new Ficha();
            ficha.setId(cursor.getInt(0));
            ficha.setEncuesta_id(cursor.getInt(1));
            ficha.setPregunta_id(cursor.getInt(2));

            db.close();
            return ficha;

        }

        db.close();

        return null;
    }

    public Ficha getFicha(int encuesta_id, int pregunta_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM ficha WHERE encuesta_id = " + encuesta_id + 
                " AND pregunta_id = " + pregunta_id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            Ficha ficha = new Ficha();
            ficha.setId(cursor.getInt(0));
            ficha.setEncuesta_id(cursor.getInt(1));
            ficha.setPregunta_id(cursor.getInt(2));

            db.close();
            return ficha;

        }

        db.close();

        return null;
    }

    public List<Ficha> getFichas(int encuesta_id){
        List<Ficha> fichas = new ArrayList<>();

        String query = "SELECT * FROM ficha WHERE encuesta_id = " + encuesta_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0){
            do {
                Ficha ficha = new Ficha();
                ficha.setId(cursor.getInt(0));
                ficha.setEncuesta_id(cursor.getInt(1));
                ficha.setPregunta_id(cursor.getInt(2));

                fichas.add(ficha);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return fichas;
    }






    // ------------------------------   RESP ABIERTA   ----------------------------------------

    public long addRespAbierta(RespAbierta respAbierta){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tag", respAbierta.getTag());
        values.put("valor", respAbierta.getValor());
        values.put("ficha_id", respAbierta.getFicha_id());
        values.put("campo_id", respAbierta.getCampo_id());

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

    public int deleteRespAbierta(String tag){
        SQLiteDatabase db = this.getWritableDatabase();

        int x = db.delete("resp_abierta", "tag = ?",
                new String[]{tag});
        db.close();

        return x;
    }

    public List<RespAbierta> getRespAbiertas(int ficha_id){
        List<RespAbierta> abiertaList = new ArrayList<>();

        String query = "SELECT * FROM resp_abierta WHERE ficha_id = " + ficha_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {

                RespAbierta respAbierta = new RespAbierta();

                respAbierta.setId(cursor.getInt(0));
                respAbierta.setTag(cursor.getString(1));
                respAbierta.setValor(cursor.getString(2));
                respAbierta.setFicha_id(cursor.getInt(3));
                respAbierta.setCampo_id(cursor.getInt(4));

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
            respAbierta.setFicha_id(cursor.getInt(3));
            respAbierta.setCampo_id(cursor.getInt(4));

            db.close();
            return respAbierta;

        }

        db.close();

        return null;
    }

    public RespAbierta getRespAbierta(int ficha_id, int campo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM resp_abierta WHERE ficha_id = " + ficha_id + " " +
                "AND campo_id = " + campo_id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            RespAbierta respAbierta = new RespAbierta();
            respAbierta.setId(cursor.getInt(0));
            respAbierta.setTag(cursor.getString(1));
            respAbierta.setValor(cursor.getString(2));
            respAbierta.setFicha_id(cursor.getInt(3));
            respAbierta.setCampo_id(cursor.getInt(4));

            db.close();
            return respAbierta;

        }

        db.close();

        return null;
    }






    // ------------------------------   RESP CERRADA  ----------------------------------------
    public long addRespCerrada(RespCerrada respCerrada){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tag", respCerrada.getTag());
        values.put("ficha_id", respCerrada.getFicha_id());
        values.put("opcion_id", respCerrada.getOpcion_id());

        long x = db.insert("resp_cerrada", null, values);
        db.close();

        return x;
    }

    public int deleteRespCerrada(String tag){
        SQLiteDatabase db = this.getWritableDatabase();
        int x = db.delete("resp_cerrada", "tag = ?",
                new String[]{tag});
        db.close();
        return x;
    }

    public List<RespCerrada> getRespCerradas(int ficha_id){
        List<RespCerrada> cerradas = new ArrayList<>();

        String query = "SELECT * FROM resp_cerrada WHERE ficha_id = " + ficha_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {

                RespCerrada respCerrada = new RespCerrada();
                respCerrada.setId(cursor.getInt(0));
                respCerrada.setTag(cursor.getString(1));
                respCerrada.setFicha_id(cursor.getInt(2));
                respCerrada.setOpcion_id(cursor.getInt(3));

                cerradas.add(respCerrada);
            }
            while (cursor.moveToNext());
        }
        db.close();

        return cerradas;
    }


    public RespCerrada getRespCerrada(String tag) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM resp_cerrada WHERE tag = '" + tag + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            RespCerrada respCerrada = new RespCerrada();
            respCerrada.setId(cursor.getInt(0));
            respCerrada.setTag(cursor.getString(1));
            respCerrada.setFicha_id(cursor.getInt(2));
            respCerrada.setOpcion_id(cursor.getInt(3));

            db.close();
            return respCerrada;

        }

        db.close();

        return null;
    }

    public RespCerrada getRespCerrada(int ficha_id, int opcion_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM resp_cerrada WHERE ficha_id = " + ficha_id + " " +
                "AND opcion_id = " + opcion_id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            RespCerrada respCerrada = new RespCerrada();
            respCerrada.setId(cursor.getInt(0));
            respCerrada.setTag(cursor.getString(1));
            respCerrada.setFicha_id(cursor.getInt(2));
            respCerrada.setOpcion_id(cursor.getInt(3));

            db.close();
            return respCerrada;

        }

        db.close();

        return null;
    }













        // --------------------------------   RESP OTRO  ----------------------------------------

    public long addRespOtro(RespOtro respOtro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tag", respOtro.getTag());
        values.put("valor", respOtro.getValor());
        values.put("ficha_id", respOtro.getFicha_id());
        values.put("otro_id", respOtro.getOtro_id());

        long x = db.insert("resp_otro", null, values);
        db.close();

        return x;
    }

    public int updateRespOtro(RespOtro respOtro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("valor", respOtro.getValor());

        int x = db.update("resp_otro", values, "id = ?",
                new String[]{String.valueOf(respOtro.getId())});
        db.close();

        return x;
    }

    public int deleteRespOtro(String tag){
        SQLiteDatabase db = this.getWritableDatabase();

        int x = db.delete("resp_otro", "tag = ?",
                new String[]{tag});
        db.close();

        return x;
    }

    public List<RespOtro> getRespOtros(int ficha_id){
        List<RespOtro> otroList = new ArrayList<>();

        String query = "SELECT * FROM resp_otro WHERE ficha_id = " + ficha_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {

                RespOtro respOtro = new RespOtro();

                respOtro.setId(cursor.getInt(0));
                respOtro.setTag(cursor.getString(1));
                respOtro.setValor(cursor.getString(2));
                respOtro.setFicha_id(cursor.getInt(3));
                respOtro.setOtro_id(cursor.getInt(4));

                otroList.add(respOtro);
            }
            while (cursor.moveToNext());
        }
        db.close();

        return otroList;
    }


    public RespOtro getRespOtro(String tag) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM resp_otro WHERE tag = '" + tag + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            RespOtro respOtro = new RespOtro();
            respOtro.setId(cursor.getInt(0));
            respOtro.setTag(cursor.getString(1));
            respOtro.setValor(cursor.getString(2));
            respOtro.setFicha_id(cursor.getInt(3));
            respOtro.setOtro_id(cursor.getInt(4));


            db.close();
            return respOtro;

        }

        db.close();

        return null;
    }

    public RespOtro getRespOtro(int ficha_id, int otro_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM resp_otro WHERE ficha_id = " + ficha_id + " " +
                "AND otro_id = " + otro_id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            RespOtro respOtro = new RespOtro();
            respOtro.setId(cursor.getInt(0));
            respOtro.setTag(cursor.getString(1));
            respOtro.setValor(cursor.getString(2));
            respOtro.setFicha_id(cursor.getInt(3));
            respOtro.setOtro_id(cursor.getInt(4));

            db.close();
            return respOtro;

        }

        db.close();

        return null;
    }









}
