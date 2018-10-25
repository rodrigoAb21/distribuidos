@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Encuestas Realizadas</h4>
                    </div>
                    <div class="card-body">
                        @foreach($fichas as $ficha)
                            {{$ficha->enunciado->enunciado}}
                            <br>
                            @foreach($ficha->cerradas as $cerrada)
                                {{$cerrada -> texto}}
                                <br>
                            @endforeach

                            @foreach($ficha->otros as $otro)
                                {{$otro -> valor}}
                                <br>
                            @endforeach

                            @foreach($ficha->abiertas as $abierta)
                                {{$abierta -> valor}}
                                <br>
                            @endforeach



                            <br>
                            <br>
                        @endforeach
                    </div>
                </div>
            </div>
        </div>
    </div>

@endsection