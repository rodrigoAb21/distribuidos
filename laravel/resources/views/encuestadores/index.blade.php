@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Encuestadores</h4><a href="{{url('/encuestadores/create')}}"><button class="btn btn-info btn-sm"><i class="nc-icon nc-simple-add"></i></button></a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive-sm">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Nro</th>
                                    <th>Nombre</th>
                                    <th>CI</th>
                                    <th>Telefono</th>
                                    <th>Email</th>
                                    <th>Estado</th>
                                    <th>Opciones</th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr>
                                    <td>1</td>
                                    <td>Juan Perez</td>
                                    <td>8498657 SC</td>
                                    <td>79896649</td>
                                    <td>juanperez@gmail.com</td>
                                    <td><span class="label label-success">Activo</span></td>
                                    <td>
                                        <a href="{{url('encuestadores/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jose Claros</td>
                                    <td>8494516 SC</td>
                                    <td>79895248</td>
                                    <td>joseclaros@gmail.com</td>
                                    <td><span class="label label-success">Activo</span></td>
                                    <td>
                                        <a href="{{url('encuestadores/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Ricardo Montero</td>
                                    <td>5186249 SC</td>
                                    <td>72342342</td>
                                    <td>ricardomontero@gmail.com</td>
                                    <td><span class="label label-success">Activo</span></td>
                                    <td>
                                        <a href="{{url('encuestadores/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Roberto Rosales</td>
                                    <td>8546128 SC</td>
                                    <td>79894384</td>
                                    <td>robertorosales@gmail.com</td>
                                    <td><span class="label label-success">Activo</span></td>
                                    <td>
                                        <a href="{{url('encuestadores/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>Pedro Toledo</td>
                                    <td>6548123 SC</td>
                                    <td>72349548</td>
                                    <td>pedrotoledo@gmail.com</td>
                                    <td><span class="label label-success">Activo</span></td>
                                    <td>
                                        <a href="{{url('encuestadores/1/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                    </td>
                                </tr>

                                </tbody>

                                @include('encuestadores.modal')

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



@endsection