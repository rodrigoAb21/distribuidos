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
        dd($request);
    }

    public function editarPregunta(Request $request, $id){

    }

    public function eliminarPregunta($id){
        $pregunta = Pregunta::findOrFail($id);
        $pregunta->delete();
        return redirect('/modelos/'.$pregunta->modelo_id.'/edit');
    }
}
