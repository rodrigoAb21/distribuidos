@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="card-title">Editar modelo de encuesta</h4>
                    </div>
                    <div class="card-body">
                        <form action="{{url('/modelos/'.$modelo->id)}}" method="POST" autocomplete="off">
                            {{ method_field('PATCH') }}
                            {{csrf_field()}}
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label>Nombre</label>
                                        <input name="nombre" type="text" class="form-control" value={{$modelo->nombre}}>
                                    </div>
                                </div>

                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label>Descripcion</label>
                                        <textarea name="descripcion" rows="3" class="form-control">{{$modelo->descripcion}}</textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="update ml-auto mr-auto">
                                    <button class="btn btn-warning btn-round btn-sm" type="submit">Guardar</button>
                                </div>
                            </div>

                        </form>

                        <hr>


                        <button class="btn btn-success" data-toggle="modal" data-target="#myModal2">
                            <i class="fa fa-plus"> Nueva pregunta</i>
                        </button>


                            <div class="table-responsive-sm">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>Nro</th>
                                        <th>Nombre</th>
                                        <th>Tipo</th>
                                        <th>Opciones</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <tr>
                                        <td>1</td>
                                        <td>Le gustaria actualizar su PC?</td>
                                        <td>Seleccion unica</td>
                                        <td>
                                            <button class="btn btn-warning" data-toggle="modal" data-target="#myModal4"><i class="fa fa-pencil-alt"></i></button>
                                            <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3"><i class="fa fa-trash"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>Que busca en una PC?</td>
                                        <td>Seleccion multiple</td>
                                        <td>
                                            <button class="btn btn-warning" data-toggle="modal" data-target="#myModal4"><i class="fa fa-pencil-alt"></i></button>
                                            <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3"><i class="fa fa-trash"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>Cuanto espera pagar por una nueva PC?</td>
                                        <td>Seleccion unica</td>
                                        <td>
                                            <button class="btn btn-warning" data-toggle="modal" data-target="#myModal4"><i class="fa fa-pencil-alt"></i></button>
                                            <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3"><i class="fa fa-trash"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>Porque esta dispuesto a pagar ese monto?</td>
                                        <td>Entrada de texto</td>
                                        <td>
                                            <button class="btn btn-warning" data-toggle="modal" data-target="#myModal4"><i class="fa fa-pencil-alt"></i></button>
                                            <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3"><i class="fa fa-trash"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>Como le gustaria pagar su nueva PC?</td>
                                        <td>Seleccion unica</td>
                                        <td>
                                            <button class="btn btn-warning" data-toggle="modal" data-target="#myModal4"><i class="fa fa-pencil-alt"></i></button>
                                            <button class="btn btn-danger" data-toggle="modal" data-target="#myModal3"><i class="fa fa-trash"></i></button>
                                        </td>
                                    </tr>

                                    </tbody>

                                </table>
                            </div>

                    </div>

                    @include('modelos.modales.pmodal')
                    @include('modelos.modales.epmodal')
                    @include('modelos.modales.editmodal')


                </div>
            </div>
        </div>
    </div>

@endsection