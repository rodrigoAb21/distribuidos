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
        $modelos = Modelo::with('preguntas', 'preguntas.cerradas', 'preguntas.campos','preguntas.campos.dominio','preguntas.cerradas.opciones')->findOrFail($id);
        return response()->json(['data' => $modelos], 200, [], JSON_NUMERIC_CHECK);


    }

}
