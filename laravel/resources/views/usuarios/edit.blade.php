@extends('layouts.dashboard')
@section('contenido')
<div class="content">
    <div class="row">
        <div class="col-xl-4 col-lg-4 col-md-5 col-sm-12 col-xs-12">
            <div class="card">
                <div class="card-body">
                    <img src="{{asset('img/'.$usuario->codigo.'/'.$usuario->foto)}}" class="img-thumbnail" style="margin: 0 auto; display: block;" >
                </div>
            </div>
        </div>

        <div class="col-xl-8 col-lg-8 col-md-7 col-sm-12 col-xs-12">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title">Informacion</h4>
                </div>
                <div class="card-body">
                    <div class="container">
                        <form action="{{url('usuario/editar')}}" method="POST" autocomplete="off" enctype="multipart/form-data">
                            {{ method_field('PATCH') }}
                            {{csrf_field()}}
                            <div class="row">
                                <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <div class="form-group">
                                        <label>Carnet</label>
                                        <p class="form-control">{{$usuario->carnet}}</p>
                                    </div>
                                </div>
                                <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <div class="form-group">
                                        <label>Codigo</label>
                                        <p class="form-control">{{$usuario->codigo}}</p>
                                    </div>
                                </div>

                                <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                    <div class="form-group">
                                        <label>Telefono</label>
                                        <input type="number" name="telefono" class="form-control" value="{{$usuario->telefono}}">
                                    </div>
                                </div>

                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label>Nombres</label>
                                        <input type="text" name="nombre" class="form-control" value="{{$usuario->nombre}}">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label>Apellidos</label>
                                        <input type="text" name="apellido" class="form-control" value="{{$usuario->apellido}}">
                                    </div>
                                </div>

                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label>Direccion</label>
                                        <input type="text" name="direccion" class="form-control" value="{{$usuario->direccion}}">
                                    </div>
                                </div>

                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label for="foto" >Foto carnet</label>
                                        <br>
                                        <span class="btn btn-warning btn-round btn-sm">Seleccionar
                                            <input type="file" class="form-control-file" name="foto" accept="image/jpeg, image/jpg, image/png, image/bmp">
                                        </span>
                                    </div>
                                </div>

                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input type="email" name="email" class="form-control" value="{{$usuario->email}}">
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input type="password" name="password" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="update ml-auto mr-auto">
                                    <button class="btn btn-warning btn-round" type="submit">Guardar</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection