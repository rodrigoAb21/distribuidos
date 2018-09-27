@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Modelos de encuesta</h4><a href="{{url('/modelos/create')}}"><button class="btn btn-info btn-sm"><i class="nc-icon nc-simple-add"></i></button></a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive-sm">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Nro</th>
                                    <th>Nombre</th>
                                    <th>Estado</th>
                                    <th>Asignaciones</th>
                                    <th>Opciones</th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr>
                                    <td>1</td>
                                    <td>Encuesta Tecnologia</td>
                                    <td><span class="label label-success">Edicion</span></td>
                                    <td>0</td>
                                    <td>
                                        <a href="{{url('/modelos/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Encuesta Prestamos</td>
                                    <td><span class="label label-success">Edicion</span></td>
                                    <td>0</td>
                                    <td>
                                        <a href="{{url('/modelos/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Encuesta Inmuebles</td>
                                    <td><span class="label label-warning">Enviada</span></td>
                                    <td>14</td>
                                    <td>
                                        <a href="{{url('/modelos/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Encuesta Celulares</td>
                                    <td><span class="label label-warning">Enviada</span></td>
                                    <td>27</td>
                                    <td>
                                        <a href="{{url('/modelos/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>Encuesta Moda</td>
                                    <td><span class="label label-danger">Terminada</span></td>
                                    <td>124</td>
                                    <td>
                                        <a href="{{url('/modelos/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>

                                </tbody>

                                @include('modelos.modales.modal')

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



@endsection