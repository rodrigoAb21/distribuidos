<?php

namespace App\Http\Controllers\Web;

use App\Modelos\Area;
use App\Modelos\Punto;
use App\User;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use function MongoDB\BSON\toJSON;
use Psy\Util\Json;
use function Sodium\add;

class AreaController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $areas = DB::table('area')->where('user_id',Auth::user()->id)->get();
        return view('areas.index',['areas'=>$areas]);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('areas.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $area= new Area();
        $area->nombre = $request->nombre;
        $area->user_id = Auth::user()->id;
        $area->save();

        $cont = 0;
        $cont2=0;
        $numero="";
        $punto = new Punto();
//        dd(strlen($request->coordenadas));
        print_r($request->coordenadas);
        while ($cont<strlen($request->coordenadas)){

            if($request->coordenadas[$cont]!="," and $cont!=strlen($request->coordenadas)-1){
              $numero = $numero.$request->coordenadas[$cont];

            }else{
                $cont2 = $cont2+1;
                print_r("\n");
                print_r($numero);
                $numero=(double)$numero;
                print_r("\n");
                print_r($numero);
                if(($cont2) % 2 == 0){
                    $punto->latitud = $numero;
                    $punto->area_id = $area->id;
                    $punto->save();
                }else{
                    $punto = new Punto();
                    $punto->longitud = $numero;
                }
                $numero="";
            }
            $cont=$cont+1;
        }
        return redirect('/areas');
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
        $area = Area::findOrFail($id);
        $puntos= DB::table('punto')->where('area_id',$id)->get();
        return view('areas.edit',['puntos'=>$puntos,'area'=>$area]);
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
        return redirect('/areas');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return redirect('areas');
    }
}
