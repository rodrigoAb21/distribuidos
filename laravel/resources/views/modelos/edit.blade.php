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
                                                <button class="btn btn-warning" type="button" onclick="editarPreguntaM('{{Pregunta::with('cerradas','campos')->where()->get()}}',)"><i class="fa fa-pencil-alt"></i></button>
                                                <button class="btn btn-danger" onclick="eliminarPreguntaM('{{$pregunta->enunciado}}','{{url('modelos/pregunta/'.$pregunta->id)}}')"><i class="fa fa-trash"></i></button>
                                            </td>
                                        </tr>
                                    @endforeach
                                    </tbody>

                                </table>
                            </div>

                    </div>
                    @include('modelos.modales.formPreg')
                    @include('modelos.modales.editPreg')
                    @include('modelos.modales.eliminar')



                </div>
            </div>
        </div>
    </div>

    @push('scripts')
        <script>

            //----------- VARIABLES
            var cont = 0;
            var contadorSeleccion = 0;
            var contadorOpcion = 0;
            $('input[type=radio][name=tipoP]').change(function() {
                if (this.value == 1) {
                    abiertaSeleccionada();
                }
                else {
                   cerradaSeleccionada();
                }
            });


            function abiertaSeleccionada() {
                $('#tablaCerrada').hide();
                vaciarTablaAbierta();

                $('#tablaAbierta').show();
            }

            function cerradaSeleccionada() {
                $('#tablaAbierta').hide();
                vaciarTablaCerrada();

                $('#tablaCerrada').show();
            }

            function nuevaPregunta() {
                abiertaSeleccionada();
                $('#formPreg').modal('show');
            }

            function eliminarPreguntaM(nombre, url) {
                $('#formulario').attr("action", url);
                $('#titulo').html("Eliminar pregunta");
                $('#enunciado').html("Realmente desea eliminar la pregunta: " + nombre + "?");
                $('#eliminar').modal('show');
            }

            function eliminar(index) {
                $("#fila" + index).remove();

            }

            function agregarEtiqueta() {
                cont++;
                var fila = '<tr id="fila'+cont+'"><td><input class="form-control"  name="etiquetas['+cont+'][etiqueta]" /></td><td><select class="form-control" name="etiquetas['+cont+'][tipoD]" id=""><option value="Texto">Texto</option><option value="Entero">Entero</option><option value="Decimal">Decimal</option><option value="Fecha">Fecha</option></select></td><td><input name= "etiquetas['+cont+'][min]" class="form-control"/></td><td><input name="etiquetas['+cont+'][max]"class="form-control"/></td><td><input name = "etiquetas['+cont+'][obligatorio]" type="checkbox"></td><td><button type="button" class="btn btn-danger btn-sm" onclick="eliminar('+cont+');"><i class="fa fa-trash" aria-hidden="true"></i></button></td></tr>';
                $("#tablaA").append(fila);
            }

            function agregarSeleccion() {
                contadorSeleccion++;
                var ind=contadorSeleccion;
                var miniForm = '<div id="seleccion'+contadorSeleccion+'" name="seleccion[]" class="card-body" ><div class="mr-0 ml-auto pull-right"><button type="button" class="btn btn-warning" style="width: 30px;height: 30px;text-align: center;padding: 6px 0;font-size: 12px;line-height: 1.428571429;border-radius: 15px;margin-right: fill;" onclick="eliminarSeleccion('+contadorSeleccion +')"><i class="fa fa-times"></i></button></div><br><br><div class="mr-0 ml-auto pull-right"><input id="otro'+ind+'" name="selector['+ind+']" type="checkbox" onclick="agregarOtro('+ind+')"><label>Otros</label></div> <div class="row"><input id="miniForm'+contadorSeleccion+'" name="selector['+ind+'][etiqueta]" ><span><select name="selector['+ind+'][tipoP]" class="form-control" class="mr-0 ml-auto pull-right" ></span><option value="Multiple">Selección Múltiple</option><option value="Unica">Selección única</option></select></div><div><table id = "opciones'+ind+'"><tbody></tbody></table><label id="labelMasOp'+ind+'" onclick="agregarOpcion('+ ind +')">Agregar opción </label></div></div>';

                $("#tablaC").append(miniForm);
            }

            function agregarOpcion(idSeleccion) {
                contadorOpcion++;
                var x= '<tr id="opcion'+contadorOpcion+'" class="row"><td><input type="text" name="selector['+idSeleccion+'][opcion][]"></td><td><button type="button" onclick="eliminarOpcion('+ contadorOpcion +')">x</button></td></tr>';
                $("#opciones"+idSeleccion).append(x);
            }

            function agregarOtro(idSeleccion) {
                if( $("#otro"+idSeleccion).prop('checked') ) {

                    cont++;
                    var fila = '<div id="otros'+idSeleccion+'"><table id="tablaOtros'+idSeleccion+'"></table><label  onclick="masCamposOtro('+ idSeleccion +')">Agregar campo</label></div>';
                    $("#seleccion"+idSeleccion).append(fila);
                }else{
                    $("#otros"+idSeleccion).remove();
                }
            }

            function masCamposOtro(idSeleccion) {
                cont++;
                var fila = '<tr id="fila'+cont+'" ><td><input  name="selector['+idSeleccion+'][otro]['+cont+'][entrada]" /></td><td><select class="form-control" name="selector['+idSeleccion+'][otro]['+cont+'][tipoDato]" id=""><option value="Texto">Texto</option><option value="Entero">Entero</option><option value="Decimal">Decimal</option><option value="Fecha">Fecha</option></select></td><td><input name="selector['+idSeleccion+'][otro]['+cont+'][min]"  /></td><td><input class="form-control"  name="selector['+idSeleccion+'][otro]['+cont+'][max]" /></td><td><button type="button" class="btn btn-danger btn-sm" onclick="eliminar('+cont+');"><i class="fa fa-trash" aria-hidden="true"></i></button></td></tr>';
            $('#tablaOtros'+idSeleccion).append(fila);

            }




            function eliminarSeleccion(index) {
                $("#seleccion" + index).remove();
            }

            function eliminarOpcion(index) {
                $("#opcion"+index).remove();
            }

            function vaciarTablaAbierta() {
                $("#tablaAbierta").remove();
                contador=0;
                contadorSeleccion=0;
                contadorOpcion=0;

                var tabla = '<div id="tablaAbierta"><div class="table-responsive text-center"><table id="tablaA" class="table table-bordered table-hover"><thead><tr><th>Etiqueta</th><th>Tipo de dato</th><th>Min</th><th>Max</th><th>Oblig.</th><th></th></tr></thead><tbody></tbody></table></div><hr><div class="text-center"><button class="btn btn-default " type="button" onclick="agregarEtiqueta()"><i class="fa fa-plus"></i></button></div></div>';
                $("#abierta").append(tabla);
                agregarEtiqueta();
            }

            function vaciarTablaCerrada() {
                $("#tablaCerrada").remove();
                var tabla = '<div id="tablaCerrada"><div id="tablaC"></div><hr><div class="text-center"><button class="btn btn-primary" type="button" onclick="agregarSeleccion()">Más Secciones</button></div></div></div>';
                $("#cerrada").append(tabla);
                agregarSeleccion();
            }

            function editarPreguntaM(pregunta) {
                console.log(pregunta);
                //$('#formPreg').modal('show');
                //if(pregunta)

            }


        </script>
    @endpush


@endsection
