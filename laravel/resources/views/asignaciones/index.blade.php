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
                                        <th>Progreso</th>
                                        <th>Opcion</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    @foreach($asignaciones as $asignacion)
                                    <tr>
                                        <td>{{$loop->iteration}}</td>
                                        <td>{{$asignacion->modelo->nombre}}</td>
                                        <td>{{$asignacion->user->nombre}}</td>
                                        <td>{{$asignacion->area->nombre}}</td>
                                        <td>0/{{$asignacion->cantidad}}</td>
                                        <td>
                                            <button class="btn btn-danger" onclick="eliminarAsignacion('{{url('asignaciones/'.$asignacion->id)}}')"><i class="fa fa-trash"></i></button>
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
    @include('modalEliminar')
    @push('scripts')
        <script>
        function eliminarAsignacion(url){
            $('#modalEliminarForm').attr("action", url);
            $('#modalEliminarTitulo').html("Eliminar Asignación");
            $('#modalEliminarEnunciado').html("Realmente desea eliminar la asignación ?" +"<br><br> Las encuestas realizadas pertenecientes a esta asignación también serán eliminadas.  *");
            $('#modalEliminar').modal('show');
        }
        </script>
    @endpush


@endsection