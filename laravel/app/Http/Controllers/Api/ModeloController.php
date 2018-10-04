<?php

namespace App\Http\Controllers\Api;

use App\Modelos\Campo;
use App\Modelos\Cerrada;
use App\Modelos\Modelo;
use App\Modelos\Pregunta;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;

class ModeloController extends Controller
{
    public function obtenerTodos(){
        $modelos = Auth::user()->modelos;
        return response()->json(['data' => $modelos], 200, [], JSON_NUMERIC_CHECK);
    }


    public function obtener($id){
        $modelo = Modelo::findOrFail($id);
        $preguntas = $modelo->preguntas;
        $modelo->preguntas = $preguntas;

        $contador = 0;
        foreach ($preguntas as $pregunta){
            $cerradas = Pregunta::findOrFail($pregunta->id)->cerradas;
            $modelo->preguntas[$contador]->cerradas = $cerradas;
            $cont2 = 0;
            foreach ($cerradas as $cerrada){
                $opciones = Cerrada::findOrFail($cerrada->id)->opciones;
                $modelo->preguntas[$contador]->cerradas[$cont2]->opciones = $opciones;
                $cont2++;
            }


            $campos = Pregunta::findOrFail($pregunta->id)->campos;
            $modelo->preguntas[$contador]->campos = $campos;
            $cont3 = 0;
            foreach ($campos as $campo){
                $dominio = Campo::findOrFail($campo->id)->dominio;
                $modelo->preguntas[$contador]->campos[$cont3]->dominio = $dominio;
                $cont3++;
            }

            $contador++;
        }

/*
        $modelo = Modelo::findOrFail($id);
        $preguntas = $modelo->preguntas;

        $contador = 0;
        foreach ($preguntas as $pregunta){
            $opciones = Pregunta::findOrFail($pregunta->id)->opciones;
            $modelo->preguntas[$contador]->opciones = $opciones;
            $contador++;
        }

*/

        return response()->json(['data' => $modelo], 200, [], JSON_NUMERIC_CHECK);


    }

}
