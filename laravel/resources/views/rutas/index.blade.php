@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Gestionar Rutas</h4><a href="{{url('/rutas/create')}}"><button class="btn btn-info btn-sm"><i class="nc-icon nc-simple-add"></i></button></a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive-sm">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <th>Nro</th>
                                <th>Nombre</th>
                                <th>Descripcion</th>
                                <th>Opciones</th>
                                </thead>
                                <tbody>
                                @foreach($rutas as $ruta)
                                    <tr>
                                        <td>{{$loop->iteration}}</td>
                                        <td>{{$ruta->nombre}}</td>
                                        <td>{{$ruta->descripcion}}</td>
                                        <td>
                                            <a href="{{url('/rutas/'.$ruta->id)}}">
                                                <button class="btn btn-default"><i class="nc-icon nc-zoom-split"></i></button>
                                            </a>
                                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal-{{$ruta->id}}"><i class="nc-icon nc-simple-remove"></i></button>
                                        </td>
                                        @include('rutas.modal')
                                    </tr>
                                @endforeach
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



@endsection