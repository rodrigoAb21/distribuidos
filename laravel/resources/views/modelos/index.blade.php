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
                                @foreach($modelos as $modelo)
                                <tr>
                                    <td>{{$loop->iteration}}</td>
                                    <td>{{$modelo->nombre}}</td>
                                    <td><span class="label label-success">{{$modelo->estado}}</span></td>
                                    <td>0</td>
                                    <td>
                                        @if($modelo->estado == 'en edición')
                                        <a href="{{url('/modelos/'.$modelo->id.'/edit')}}">
                                        <button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button>
                                        </a>
                                        <button class="btn btn-danger" onclick="eliminarModelo('{{$modelo->nombre}}', '{{url('modelos/'.$modelo->id)}}')"><i class="fa fa-trash"></i></button>
                                        <form action="{{url('modelos/'.$modelo->id.'/finalizar')}}" method="POST">
                                            {{method_field("patch")}}
                                            {{csrf_field()}}
                                            <button class="btn btn-success" type="submit"><i class="fa fa-check"></i></button>
                                        </form>
                                        @endif
                                    </td>
                                </tr>
                                @endforeach

                                </tbody>
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

            function eliminarModelo(nombre, url) {
                $('#modalEliminarForm').attr("action", url);
                $('#modalEliminarTitulo').html("Eliminar Modelo");
                $('#modalEliminarEnunciado').html("Realmente desea eliminar la pregunta: " + nombre + "?");
                $('#modalEliminar').modal('show');

            }

        </script>

    @endpush()

@endsection