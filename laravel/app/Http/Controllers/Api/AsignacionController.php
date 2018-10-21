<?php

namespace App\Http\Controllers\Api;


use App\Http\Controllers\Controller;
use App\Modelos\Asignacion;
use Illuminate\Support\Facades\Auth;

class AsignacionController extends Controller
{
    public function obtenerTodos(){
        $asignaciones = Asignacion::with('area','modelo','modelo.preguntas', 'modelo.preguntas.cerradas', 'modelo.preguntas.campos','modelo.preguntas.campos.dominio','modelo.preguntas.cerradas.opciones', 'modelo.preguntas.cerradas.otros', 'modelo.preguntas.cerradas.otros.dominio')->where('encuestador_id', '=', Auth::user()->id)->get();

        return response()->json(['data' => $asignaciones], 200, [], JSON_NUMERIC_CHECK);
    }
}