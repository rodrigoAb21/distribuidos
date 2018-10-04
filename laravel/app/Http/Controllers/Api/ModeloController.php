<?php

namespace App\Http\Controllers\Api;

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

        $contador = 0;
        foreach ($preguntas as $pregunta){
            $opciones = Pregunta::findOrFail($pregunta->id)->opciones;
            $modelo->preguntas[$contador]->opciones = $opciones;
            $contador++;
        }

        return response()->json(['data' => $modelo], 200, [], JSON_NUMERIC_CHECK);
    }

}
