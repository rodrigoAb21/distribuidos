<?php

namespace App\Http\Controllers\Web;

use App\Modelos\Modelo;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class InformeController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $modelos = DB::table('modelo')->where('user_id', Auth::id())
                                             ->where('estado' , '<>', 'en edición')->get();
        return view('informes.index',['modelos'=>$modelos]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        return view('informes.show');
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }

    public function listarEncuestas($modelo_id)
    {
        $encuestas = DB::table('asignacion')
            ->join('encuesta', 'asignacion.id', 'encuesta.asignacion_id')
            ->where('asignacion.modelo_id',$modelo_id)
            ->select('encuesta.id')
            ->get();
//        dd($encuestas);
        return view('informes.listarEncuestas',['encuestas'=>$encuestas,'modelo'=>$modelo_id]);
    }

    public function verEncuesta($modelo_id,$encuesta_id){
        $preguntas = DB::table('ficha_resp')
                        ->join('pregunta','ficha_resp.pregunta_id','pregunta.id')
                        ->join('resp_abierta','ficha_resp.id','resp_abierta.ficha_id')
                        ->join('resp_cerrada','ficha_resp.id','resp_cerrada.ficha_id')
                        ->join('resp_otro','ficha_resp.id','resp_otro.ficha_id')
                        ->join('opcion','resp_cerrada.opcion_id','opcion.id')
                        ->where('ficha_resp.encuesta_id',$encuesta_id)
                        ->select('pregunta.enunciado','resp_abierta.valor','opcion.texto', 'resp_otro.valor')
                        ->get();
            dd($preguntas);
    }
}
