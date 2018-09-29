@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Informes estadisticos</h4>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive-sm">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Nro</th>
                                        <th>Nombre</th>
                                        <th>Estado</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                @foreach($modelos as $modelo)
                                <tr>
                                    <td>1</td>
                                    <td>{{$modelo->nombre}}</td>
                                    <td><span class="label label-default">{{$modelo->estado}}</span></td>
                                    <td>
                                        <a href="{{url('/informes/1')}}">
                                            <button class="btn btn-primary"><i class="fa fa-eye"></i></button>
                                        </a>
                                    </td>
                                </tr>
                                @endforeach

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



@endsection