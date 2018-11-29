<?php

namespace App\Http\Controllers\Web;

use App\Modelos\Cerrada;
use App\Modelos\Encuesta;
use App\Modelos\FichaResp;
use App\Modelos\Modelo;
use App\Modelos\Pregunta;
use App\Modelos\Punto;
use App\Modelos\RespAbierta;
use App\Modelos\RespCerrada;
use App\Modelos\RespOtro;
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
            ->where('estado', '<>', 'en edición')->get();
        return view('informes.index', ['modelos' => $modelos]);
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
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {

        $preguntas = DB::select('select pregunta.id,pregunta.enunciado from pregunta where pregunta.modelo_id=?', [$id]);

        $areas = DB::select('select distinct asignacion.area_id from  asignacion where asignacion.modelo_id = ?' ,[$id]);

        foreach ($areas as $area){
            $areaP[$area->area_id]=DB::table('punto')->where('area_id',$area->area_id)->get();
        }

        $cont=0;
        foreach ($areas as $area){
            $areaN[$cont]["area_id"]=$area->area_id;
            $areaN[$cont][]=(DB::select('select area.nombre from area where area.id = ?',[$area->area_id]))[0]->nombre;
            $cont=$cont+1;
        }

        foreach ($preguntas as $pregunta) {
            if (DB::select('select * from cerrada where pregunta_id=?', [$pregunta->id]) == true) {
                $pregunta->tipo = (DB::table('cerrada')->where('pregunta_id', $pregunta->id)->select('tipoSeleccion')->get())[0]->tipoSeleccion;

                    $pregunta->opciones = DB::select('select opcion.id, opcion.texto from cerrada,opcion where cerrada.pregunta_id=? and opcion.cerrada_id= cerrada.id', [$pregunta->id]);

                    foreach ($pregunta->opciones as $opcion) {
                        $opcion->resultado = (DB::select('select count(resp_cerrada.id) from resp_cerrada where resp_cerrada.opcion_id = ? ', [$opcion->id]))[0]->count;
                    }

            } else {
                $pregunta->tipo = "Entrada";
            }
        }

        $marcadores = DB::select('select encuesta.latitud, encuesta.longitud from encuesta where encuesta.asignacion_id in (select asignacion.id from asignacion where asignacion.modelo_id = ?)',[$id]);

        return view('informes.show', ['preguntas' => $preguntas, 'areasP'=>$areaP, 'areasN'=>$areaN, 'marcadores'=>$marcadores]);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
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
            ->where('asignacion.modelo_id', $modelo_id)
            ->select('encuesta.id')
            ->get();
//        dd($encuestas);
        return view('informes.listarEncuestas', ['encuestas' => $encuestas, 'modelo' => $modelo_id]);
    }

    public function verEncuesta($modelo_id, $encuesta_id)
    {

        $fichas = DB::table('ficha_resp')
            ->where('encuesta_id', '=', $encuesta_id)
            ->select('id', 'pregunta_id')
            ->get();

        foreach ($fichas as $ficha) {
            $ficha->enunciado = DB::table('pregunta')->where('id', '=', $ficha->pregunta_id)->select('enunciado')->first();

            $ficha->cerradas = DB::table('resp_cerrada')
                ->join('opcion', 'resp_cerrada.opcion_id', 'opcion.id')
                ->where('resp_cerrada.ficha_id', '=', $ficha->id)
                ->select('opcion.texto')
                ->get();
            $ficha->otros = DB::table('resp_otro')
                ->where('ficha_id', '=', $ficha->id)
                ->select('valor')
                ->get();
            $ficha->abiertas = DB::table('resp_abierta')
                ->where('ficha_id', '=', $ficha->id)
                ->select('valor')
                ->get();
        }

        return view('informes.verEncuesta', ['fichas' => $fichas]);


    }
}
