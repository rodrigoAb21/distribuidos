@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="card-title">Editar encuestador</h4>
                    </div>
                    <div class="card-body">
                        <form action="{{url('/encuestadores/'.$encuestador->id)}}" method="POST" autocomplete="off">
                            {{ method_field('PATCH') }}
                            {{csrf_field()}}
                            <div class="row">

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Nombres</label>
                                        <input type="text" value={{$encuestador->nombre}} name="nombre" class="form-control"  >
                                    </div>
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Apellidos</label>
                                        <input type="text" name = "apellido" value = {{$encuestador->apellido}} class="form-control"  >
                                    </div>
                                </div>


                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Telefono</label>
                                        <input type="number" name = "telefono" value = {{$encuestador->telefono}} class="form-control" >
                                    </div>
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Carnet de identidad</label>
                                        <input type="text" name="ci" value={{$encuestador->ci}} class="form-control"  >
                                    </div>
                                </div>

                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label>Direccion</label>
                                        <input type="text" name="direccion" value={{$encuestador->direccion}} class="form-control"  >
                                    </div>
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input type="email" name="email" value={{$encuestador->email}}  class="form-control"  >
                                    </div>
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input type="password" name="password" class="form-control"  >
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

@endsection