@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Asignaciones</h4><a href="{{url('/asignaciones/create')}}"><button class="btn btn-info btn-sm"><i class="nc-icon nc-simple-add"></i></button></a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive-sm">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Nro</th>
                                        <th>Modelo</th>
                                        <th>Encuestador</th>
                                        <th>Area</th>
                                        <th>Estado</th>
                                        <th>Progreso</th>
                                        <th>Opcion</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <tr>
                                        <td>1</td>
                                        <td>Encuesta Tecnologia</td>
                                        <td>Pedro Toledo</td>
                                        <td>El Centro 1</td>
                                        <td><span class="label label-success">Activa</span></td>
                                        <td>13/50</td>
                                        <td>
                                            <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                        </td>
                                    </tr>


                                @include('asignaciones.modal')

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



@endsection