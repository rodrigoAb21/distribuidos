<?php

namespace App\Http\Controllers\Web;

use App\User;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class EncuestadorController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $encuestadores = DB::table('users')
                        ->where('tipo','E')
                        ->orderBy('id', 'asc')
                        ->get();

        return view('encuestadores.index',['encuestadores'=>$encuestadores]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('encuestadores.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $encuestador = new User();
        $encuestador->nombre = $request->nombre;
        $encuestador->apellido = $request->apellido;
        $encuestador->telefono = $request->telefono;
        $encuestador->direccion = $request->direccion;
        $encuestador->email = $request->email;
        $encuestador->password = bcrypt($request->password);
        $encuestador->tipo = 'E';
        $encuestador->ci = $request->ci;
        $encuestador->user_id = Auth::id();
        $encuestador->save();

        return redirect('/encuestadores');
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
        $encuestador = User::findOrFail($id);
        return view('encuestadores.edit',['encuestador'=>$encuestador]);
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
        $encuestador = User::findOrFail($id);
        $encuestador->nombre = $request->nombre;
        $encuestador->apellido = $request->apellido;
        $encuestador->telefono = $request->telefono;
        $encuestador->direccion = $request->direccion;
        $encuestador->email = $request->email;
        $encuestador->ci = $request->ci;
        if(trim($request->password)!=""){
            $encuestador->password = bcrypt($request->password);
            $encuestador->update();
        }

        return redirect('/encuestadores');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return redirect('/encuestadores');
    }
}
