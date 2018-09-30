<?php

namespace App\Http\Controllers\Web;

use App\Modelos\Pregunta;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class PreguntaController extends Controller
{
    public function nuevaPregunta(Request $request, $id){
        $pregunta = new Pregunta();
        $pregunta->enunciado = $request->enunciado;
        $pregunta->tipo_preg = $request->tipop;
        $pregunta->obligatoria = ($request->obligatoria == 'on')||false;
        $pregunta->otro = ($request->otro == 'on')||false;
        $pregunta->tipo_dato = $request->tipod;
        $pregunta->modelo_id = $id;
        $pregunta->save();
        return redirect('/modelos/'.$id.'/edit');
    }

    public function eliminarPregunta($id){

    }
}
