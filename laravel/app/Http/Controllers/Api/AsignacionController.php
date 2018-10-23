<?php

namespace App\Http\Controllers\Api;


use App\Http\Controllers\Controller;
use App\Modelos\Asignacion;
use App\Modelos\Encuesta;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AsignacionController extends Controller
{
    private $client;

    public function __construct(){
        $this -> client = Client::find(1);
    }


    public function obtenerTodos(){
        $asignaciones = Asignacion::with('area','modelo','modelo.preguntas', 'modelo.preguntas.cerradas', 'modelo.preguntas.campos','modelo.preguntas.campos.dominio','modelo.preguntas.cerradas.opciones', 'modelo.preguntas.cerradas.otros', 'modelo.preguntas.cerradas.otros.dominio')->where('encuestador_id', '=', Auth::user()->id)->get();

        return response()->json(['data' => $asignaciones], 200, [], JSON_NUMERIC_CHECK);
    }

    public function guardar(Request $request){

        $encuestas = $request-> encuestas;

        foreach ($encuestas as $encuesta){
            $encuesta2 = new Encuesta();
            $encuesta2->fecha = $encuesta['fecha'];
            $encuesta2->estado = $encuesta['estado'];
            $encuesta2->asignacion_id = $encuesta['asignacion_id'];
            $encuesta2->save();

            foreach ()
        }


        return response()->json([], 200);
//        return response()->json(['data' => $request->all()], 200, [], JSON_NUMERIC_CHECK);
    }
    
}