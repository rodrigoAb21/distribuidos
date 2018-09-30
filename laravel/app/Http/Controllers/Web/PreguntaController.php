<?php

namespace App\Http\Controllers\Web;

use App\Modelos\Opcion;
use App\Modelos\Pregunta;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\DB;

class PreguntaController extends Controller
{
    public function nuevaPregunta(Request $request, $id){
       try{
           DB::beginTransaction();
           $pregunta = new Pregunta();
           $pregunta->enunciado = $request->enunciado;
           $pregunta->tipo_preg = $request->tipop;
           $pregunta->obligatoria = ($request->obligatoria == 'on');
           $pregunta->otro = ($request->otro == 'on');
           $pregunta->tipo_dato = $request->tipod;
           $pregunta->modelo_id = $id;
           $pregunta->save();


           $texto = $request -> textoT;

           $cont = 0;
           while ($cont < count($texto)) {
               $opcion = new Opcion();
               $opcion -> pregunta_id = $pregunta->id;
               $opcion -> texto = $texto[$cont];
               $opcion -> save();
               $cont = $cont + 1;
           }
           DB::commit();
       }catch (Exception $e){
           DB::rollback();
       }

        return redirect('/modelos/'.$id.'/edit');
    }

    public function eliminarPregunta($id){

    }
}
