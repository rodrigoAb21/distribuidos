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


                        <button class="btn btn-success" data-toggle="modal" data-target="#modalForm">
                            <i class="fa fa-plus"></i>
                            Nueva pregunta
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
                                    @foreach($modelo -> preguntas as $pregunta)
                                        <tr>
                                            <td>{{$loop->iteration}}</td>
                                            <td>{{$pregunta->enunciado}}</td>
                                            <td>{{$pregunta->tipo_preg}}</td>
                                            <td>
                                                <button class="btn btn-warning" onclick="datosModal2('{{$pregunta}}', '{{$pregunta->opciones}}','{{url('modelos/pregunta/'.$pregunta->id.'/edit')}}')"><i class="fa fa-pencil-alt"></i></button>

                                                <button class="btn btn-danger" onclick="datosModalEliminar('{{$pregunta->enunciado}}','{{url('modelos/pregunta/'.$pregunta->id)}}')"><i class="fa fa-trash"></i></button>
                                            </td>
                                        </tr>
                                    @endforeach
                                    </tbody>

                                </table>
                            </div>

                    </div>
                    @include('modelos.modales.editPreg')
                    @include('modelos.modales.formPreg')
                    @include('modelos.modales.modal')



                </div>
            </div>
        </div>
    </div>

    @push('scripts')
        <script src="{{asset('js\propios\modalRegPreg.js')}}"></script>

    @endpush
    <script>

        function datosModalEliminar(nombre, url) {
            $('#formulario').attr("action", url);
            $('#enunciado').html("Realmente desea eliminar el modelo: " + nombre + "?");
            $('#myModal').modal('show');
        }


        function datosModal2(pregunta, opciones, url) {
            var contador=0;
            $('#formulario_edit').attr("action", url);
            var preg = JSON.parse(pregunta);
            var opc = JSON.parse(opciones);
            $('#enun_edit').val(preg.enunciado);
            $('#check_edit').prop('checked',preg.obligatoria);
            while(contador<opc.length ){

                contador++;
                var fila = '<tr id="fila' + contador + 'Edit"><td><button type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></button></td><td><input class="form-control" name="textoT[]" value="' + opc[contador-1].texto + '"/></td></tr>';
                $("#tabla_edit").append(fila);
            }

            $('#formEdit').modal('show');

        }

        function guardarEdit() {

        }






    </script>

@endsection