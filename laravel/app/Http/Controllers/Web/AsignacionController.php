<?php

namespace App\Http\Controllers\Web;


use App\Modelos\Asignacion;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class AsignacionController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $asignaciones = Asignacion::with('modelo','area','user')->where('admin_id','=', Auth::id())->orderBy('id','desc')->get();
        return view('asignaciones.index', ['asignaciones'=>$asignaciones]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $modelos = DB::table('modelo')
                    ->where('user_id', Auth::id())
                    ->where('estado','finalizado')
                    ->orderBy('nombre','asc')
                    ->select('id','nombre')
                    ->get();

        $areas = DB::table('area')
                    ->where('user_id', Auth::id())
                    ->orderBy('nombre','asc')
                    ->select('id','nombre')
                    ->get();

        $encuestadores = DB::table('users')
                             ->where('user_id',Auth::id())
                             ->orderBy('nombre','asc')
                             ->select('id','nombre')
                            ->get();

        return view('asignaciones.create', ['modelos'=>$modelos, 'areas'=>$areas, 'encuestadores'=>$encuestadores]);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $asignacion = new Asignacion();
        $asignacion->cantidad = $request->cantidad;
        $asignacion->hora_inicio = $request->inicio;
        $asignacion->hora_final = $request->fin;
        $asignacion->encuestador_id = $request->encuestador_id;
        $asignacion->modelo_id = $request->modelo_id;
        $asignacion->area_id = $request->area_id;
        $asignacion->admin_id = Auth::id();
        $asignacion->save();

        return redirect('/asignaciones');
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
        $asignacion = Asignacion::findOrFail($id);
        $asignacion->delete();
        return redirect('/asignaciones');
    }
}
