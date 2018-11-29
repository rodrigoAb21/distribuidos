<?php

namespace App\Http\Controllers\Api;


use App\Http\Controllers\Controller;
use App\Modelos\Asignacion;
use App\Modelos\Encuesta;
use App\Modelos\FichaResp;
use App\Modelos\RespAbierta;
use App\Modelos\RespCerrada;
use App\Modelos\RespOtro;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AsignacionController extends Controller
{

    public function obtenerTodos(){
        $asignaciones = Asignacion::with('area','modelo','modelo.preguntas', 'modelo.preguntas.cerradas', 'modelo.preguntas.campos','modelo.preguntas.campos.dominio','modelo.preguntas.cerradas.opciones', 'modelo.preguntas.cerradas.otros', 'modelo.preguntas.cerradas.otros.dominio', 'area.puntos')->where('encuestador_id', '=', Auth::user()->id)->get();

        return response()->json(['data' => $asignaciones], 200, [], JSON_NUMERIC_CHECK);
    }

    public function guardar(Request $request){

        try{
            $encuestas = $request['encuestas'];

            foreach ($encuestas as $encuest){
                $encuesta = new Encuesta();
                $encuesta->fecha = $encuest['fecha'];
                $encuesta->estado = $encuest['estado'];
                $encuesta->latitud = $encuest['latitud'];
                $encuesta->longitud = $encuest['longitud'];
                $encuesta->asignacion_id = $encuest['asignacion_id'];
                $encuesta->save();

                $fichas = $encuest['fichas'];
                foreach ($fichas as $fich){
                    $ficha = new FichaResp();
                    $ficha->encuesta_id = $encuesta->id;
                    $ficha->pregunta_id = $fich['pregunta_id'];
                    $ficha->save();

                    $abiertas = $fich['respAbiertas'];
                    foreach ($abiertas as $abi){
                        $abierta = new RespAbierta();
                        $abierta -> tag = $abi['tag'];
                        $abierta -> valor = $abi['valor'];
                        $abierta -> campo_id = $abi['campo_id'];
                        $abierta -> ficha_id = $ficha -> id;
                        $abierta -> save();
                    }

                    $cerradas = $fich['respCerradas'];
                    foreach ($cerradas as $cer){
                        $cerrada = new RespCerrada();
                        $cerrada -> tag = $cer['tag'];
                        $cerrada -> opcion_id = $cer['opcion_id'];
                        $cerrada -> ficha_id = $ficha -> id;
                        $cerrada->save();
                    }

                    $otros = $fich['respOtros'];
                    foreach ($otros as $otro){
                        $ottro = new RespOtro();
                        $ottro -> tag = $otro['tag'];
                        $ottro -> valor = $otro['valor'];
                        $ottro -> otro_id = $otro['otro_id'];
                        $ottro -> ficha_id = $ficha -> id;
                        $ottro -> save();
                    }
                }

            }
            return response()->json([], 204);
        }catch (Exception $e){
            return response()->json([], 500);
        }




        
    }
    
}