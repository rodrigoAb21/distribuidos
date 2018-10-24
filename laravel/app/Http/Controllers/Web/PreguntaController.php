<?php

namespace App\Http\Controllers\Web;

use App\Modelos\Campo;
use App\Modelos\Cerrada;
use App\Modelos\Dominio;
use App\Modelos\Modelo;
use App\Modelos\Opcion;
use App\Modelos\Otro;
use App\Modelos\Pregunta;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\DB;

class PreguntaController extends Controller
{
    public function nuevaPregunta(Request $request, $id){

        $pregunta = new Pregunta();
        $pregunta->enunciado = $request->enunciado;
        $pregunta->modelo_id = $id;
        $pregunta->save();

        if( $request->tipoP == '1'){
            foreach ($request->etiquetas as $etiqueta){
                $dominio = new Dominio();
                $dominio->tipoDato = $etiqueta['tipoD'];
                $dominio->min = $etiqueta['min'];
                $dominio->max = $etiqueta['max'];
                $dominio->save();


                $campo = new Campo();
                $campo->etiqueta = $etiqueta['etiqueta'];
                $campo->obligatorio = (array_key_exists("obligatorio",$etiqueta));
               // $campo->varios = (array_key_exists("obligatorio",$etiqueta));
                $campo->pregunta_id = $pregunta->id;
                $campo->dominio_id = $dominio->id;
                $campo->save();
            }

        }else {
            foreach ($request->selector as $selector) {
                    $cerrada = new Cerrada();
                    $cerrada->etiqueta = $selector['etiqueta'];
                    $cerrada->tipoSeleccion= $selector['tipoP'];
                    $cerrada->obligatoria = false;
                    $cerrada->pregunta_id = $pregunta->id;
                    $cerrada->save();

                    foreach ($selector['opcion'] as $opcion){
                        $op = new Opcion();
                        $op->texto = $opcion;
                        $op->cerrada_id = $cerrada->id;
                        $op->save();
                    }

                    if(array_key_exists("otro", $selector)){
                    foreach ($selector['otro'] as $otro) {
                       // dd($otro['entrada']);
                        $dominio = new Dominio();
                        $dominio->tipoDato = $otro['tipoDato'];
                        $dominio->min = $otro['min'];
                        $dominio->max = $otro['max'];
                        $dominio->save();


                        $campoOtro = new Otro();
                        $campoOtro->etiqueta = $otro['entrada'];
                        //$campoOtro->varios = (array_key_exists("varios", $otro));
                        $campoOtro->cerrada_id = $cerrada->id;
                        $campoOtro->dominio_id = $dominio->id;
                        $campoOtro->save();
                    }
                    }
            }
        }
        return redirect('/modelos/'.$pregunta->modelo_id.'/edit');
    }

    public function editarPregunta(Request $request, $id){
        $pregunta = Pregunta::findOrFail($id);
        foreach ($pregunta->cerradas as $cerrada){
            $cerrada->delete();
        }
        foreach ($pregunta->campos as $campo){
            $campo->delete();
        }

        $pregunta->enunciado = $request->enunciado;
        $pregunta->modelo_id = $pregunta->modelo_id;
        $pregunta->save();

            if(($request->etiquetas)!=null){
            foreach ($request->etiquetas as $etiqueta){
                //dd($request['campos']);
                $dominio = new Dominio();
                $dominio->tipoDato = $etiqueta['tipoD'];
                $dominio->min = $etiqueta['min'];
                $dominio->max = $etiqueta['max'];
                $dominio->save();


                $campo = new Campo();
                $campo->etiqueta = $etiqueta['etiqueta'];
                $campo->obligatorio = (array_key_exists("obligatorio",$etiqueta));
                // $campo->varios = (array_key_exists("obligatorio",$etiqueta));
                $campo->pregunta_id = $pregunta->id;
                $campo->dominio_id = $dominio->id;
                $campo->save();
            }
            }else{
            foreach ($request->selector as $selector) {
                $cerrada = new Cerrada();
                $cerrada->etiqueta = $selector['etiqueta'];
                $cerrada->tipoSeleccion= $selector['tipoP'];
                $cerrada->obligatoria = false;
                $cerrada->pregunta_id = $pregunta->id;
                $cerrada->save();

                foreach ($selector['opcion'] as $opcion){
                    $op = new Opcion();
                    $op->texto = $opcion;
                    $op->cerrada_id = $cerrada->id;
                    $op->save();
                }

                if(array_key_exists("otro", $selector)){
                    foreach ($selector["otro"] as $otro) {
                        $dominio = new Dominio();
                        $dominio->tipoDato = $otro['tipoDato'];
                        $dominio->min = $otro['min'];
                        $dominio->max = $otro['max'];
                        $dominio->save();


                        $campoOtro = new Otro();
                        $campoOtro->etiqueta = $otro['entrada'];
                        //$campoOtro->varios = (array_key_exists("varios", $otro));
                        $campoOtro->cerrada_id = $cerrada->id;
                        $campoOtro->dominio_id = $dominio->id;
                        $campoOtro->save();
                    }
                }
            }
            }

        return redirect('/modelos/'.$pregunta->modelo_id.'/edit');

    }

    public function eliminarPregunta($id){
        $pregunta = Pregunta::findOrFail($id);
        $pregunta->delete();
        return redirect('/modelos/'.$pregunta->modelo_id.'/edit');
    }
}
