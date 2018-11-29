@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Areas</h4><a href="{{url('/areas/create')}}"><button class="btn btn-info btn-sm"><i class="nc-icon nc-simple-add"></i></button></a>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive-sm">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Nro</th>
                                        <th>Nombre</th>
                                        <th class="w-25">Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    @foreach($areas as $area)
                                    <tr>
                                        <td>{{$loop->iteration}}</td>
                                        <td>{{$area->nombre}}</td>
                                        <td>
                                            <a href="{{url('areas/'.$area->id.'/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                            <button class="btn btn-danger" data-toggle="modal" data-target="#myModal"><i class="fa fa-trash"></i></button>
                                        </td>
                                    </tr>
                                    @endforeach
                                </tbody>
                            </table>
                            @include('areas.modal')
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



@endsection