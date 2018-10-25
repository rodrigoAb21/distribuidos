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
                                @foreach($encuestadores as $encuestador)
                                <tr>
                                    <td>{{$loop->iteration}}</td>
                                    <td>{{$encuestador->nombre}}</td>
                                    <td>{{$encuestador->ci}}</td>
                                    <td>{{$encuestador->telefono}}</td>
                                    <td>{{$encuestador->email}}</td>
                                    <td><span class="label label-success">Activo</span></td>
                                    <td>
                                        <a href="{{url('encuestadores/'.$encuestador->id.'/edit')}}"><button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button></a>
                                        <button class="btn btn-danger" onclick="eliminarEncuestador('{{$encuestador->nombre}}', '{{url('encuestadores/'.$encuestador->id)}}')"><i class="fa fa-trash"></i></button>
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
            function eliminarEncuestador(nombre, url) {
                $('#modalEliminarForm').attr("action", url);
                $('#modalEliminarTitulo').html("Eliminar Encuestador");
                $('#modalEliminarEnunciado').html("Realmente desea eliminar al encuestador: " + nombre + "?");
                $('#modalEliminar').modal('show');
            }
        </script>
    @endpush

@endsection