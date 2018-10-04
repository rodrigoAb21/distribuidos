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


                        <button class="btn btn-success" type="button" onclick="nuevaPregunta()">
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
                                                <button class="btn btn-warning"><i class="fa fa-pencil-alt"></i></button>
                                                <button class="btn btn-danger" onclick="eliminarPreguntaM('{{$pregunta->enunciado}}','{{url('modelos/pregunta/'.$pregunta->id)}}')"><i class="fa fa-trash"></i></button>
                                            </td>
                                        </tr>
                                    @endforeach
                                    </tbody>

                                </table>
                            </div>

                    </div>
                    @include('modelos.modales.formPreg')
                    @include('modelos.modales.eliminar')



                </div>
            </div>
        </div>
    </div>

    @push('scripts')
        <script>

            function eliminarPreguntaM(nombre, url) {
                $('#formulario').attr("action", url);
                $('#titulo').html("Eliminar pregunta");
                $('#enunciado').html("Realmente desea eliminar la pregunta: " + nombre + "?");
                $('#eliminar').modal('show');
            }



            function nuevaPregunta() {
                evaluar();
                $('#formPreg').modal('show');
            }



            var cont = 0;
            var contadorSeleccion = 0;
            
            $('#tipoP').change(evaluar);

            
            function eliminar(index) {
                $("#fila" + index).remove();
                cont--;
            }

            function esconder() {
                vaciarT();
                $('#checkInput').prop('checked',false);
                $('#checkOtro').hide();
                $('#contOpciones').hide();
            }

            function vaciarT() {
                while (cont>0){
                    eliminar(cont);
                }

            }

            function vaciarTablaAbierta() {
                //
            }
            function vaciarTablaCerrada() {
                //
            }

            function evaluar() {
                $('input[type=radio][name=tipoP]').change(function() {
                    if (this.value == 1) {
                        $('#tablaCerrada').hide();
                        vaciarTablaCerrada();

                        agregarEtiqueta();
                        $('#tablaAbierta').show();


                    }
                    else {
                        $('#tablaAbierta').hide();
                        vaciarTablaAbierta();

                        agregarSeleccion();
                        $('#tablaCerrada').show();
                    }
                });
            }
            
            function agregarOpcion(idMiniForm) {
                var opcion = '<input id="opcion'+contadorOpcion+'" type="text" >';

            }

            function agregarEtiqueta() {
                cont++;
                var fila='<tr id="fila'+cont+'"><td><input class="form-control"  name="etiquetaT[]" /></td><td><select class="form-control" name="" id=""><option value="">Texto</option><option value="">Entero</option><option value="">Decimal</option><option value="">Fecha</option></select></td><td><input class="form-control"  name="minT[]" /></td><td><input class="form-control"  name="maxT[]" /></td><td><input name="obligatoriaT[]" type="checkbox"></td><td><button type="button" class="btn btn-danger btn-sm" onclick="eliminar('+cont+');"><i class="fa fa-trash" aria-hidden="true"></i></button></td></tr>';
                $("#tablaA").append(fila);
            }
            
            function agregarSeleccion() {
                contadorSeleccion++;
                var miniForm = '<div><input id="miniForm'+contadorSeleccion+'"><select class="form-control"><option value="Seleccion multiple">Selección Múltiple</option><option value="Seleccion unica">Selección única</option></select><button type="button" class="btn btn-success" onclick="agregarOpcion("miniForm'+contadorSeleccion+')"><i class="fa fa-plus"></i></button></div>';
                $("#tablaC").append(miniForm);
            }

        </script>
    @endpush


@endsection