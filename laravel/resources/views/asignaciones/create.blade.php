@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="card-title">Nueva asignacion</h4>
                    </div>
                    <div class="card-body">
                        <form action="{{url('/asignaciones')}}" method="POST" autocomplete="off">
                            {{csrf_field()}}
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Modelos de encuesta</label>
                                        <select class="form-control" name="modelo_id">
                                            @foreach($modelos as $modelo)
                                            <option value="{{$modelo->id}}">{{$modelo->nombre}}</option>
                                            @endforeach
                                        </select>
                                    </div>
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Encuestador</label>
                                        <select class="form-control" name="encuestador_id">
                                            @foreach($encuestadores as $encuestador)
                                            <option value="{{$encuestador->id}}">{{$encuestador->nombre}}</option>
                                            @endforeach
                                        </select>
                                    </div>
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <label>Area</label>
                                    <select class="form-control" name="area_id">
                                        @foreach($areas as $area)
                                        <option value="{{$area->id}}">{{$area->nombre}}</option>
                                        @endforeach
                                    </select>
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Cantidad Min.</label>
                                        <input type="number" name="cantidad" class="form-control"  >
                                    </div>
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Hora inicio</label>
                                        <input type="time" name="inicio" value="00:00" class="form-control"  >
                                    </div>
                                </div>

                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label>Hora fin</label>
                                        <input type="time" name="fin" value="12:00" class="form-control"  >
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