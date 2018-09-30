<?php

namespace App\Http\Controllers\Web;

use App\Modelos\Modelo;
use App\Modelos\Pregunta;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class ModeloController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //$modelos = DB::table('modelo_encuesta')->where('user_id', '=', Auth::user()->id)->paginate();

        return view('modelos.index',['modelos'=>Auth::user()->modelos]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('modelos.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $modelo = new Modelo();
        $modelo->nombre = $request->nombre;
        $modelo->descripcion = $request->descripcion;
        $modelo->estado = 'en edición';
        $modelo->user_id = Auth::user()->id;
        $modelo->save();
        return redirect('/modelos');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $modelo = Modelo::findOrFail($id);
        return view('modelos.edit',['modelo'=>$modelo]);
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
        $modelo = Modelo::findOrFail($id);
        if($modelo->estado == 'en edición'){
            $modelo->nombre = $request->nombre;
            $modelo->descripcion = $request->descripcion;
            $modelo->update();
        }
        return redirect('/modelos/'.$id.'/edit');

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $modelo = Modelo::findOrFail($id);
        if($modelo->estado == 'en edición'){
            $modelo->delete();
        }
        return redirect('/modelos');
    }

    public function guardarPregunta(Request $request, $id){
        $pregunta = new Pregunta();
        $pregunta->enunciado = $request->enunciado;
        $pregunta->tipo_pregunta = $request->tipop;
        $pregunta->obligatoira = $request->obligatoria;
        $pregunta->tipo_dato = $request->tipod;
        $pregunta->modelo_encuesta_id = $id;
        $pregunta->save();
        return redirect('/modelos/'.$id.'/edit');

    }

    public function eliminarPregunta($mid, $pid){
        return redirect('/modelos/'.$mid.'/edit');
    }
}
