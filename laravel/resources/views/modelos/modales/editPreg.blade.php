<div class="modal fade" id="editPreg" tabindex="-1" role="dialog" aria-labelledby="editPregLabel" aria-hidden="true" style="overflow-y:auto;">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Editar Pregunta</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="formulario_edit" action="{{url('/modelos/pregunta/'.$pregunta->id.'/edit')}}" method="POST">
                {{method_field('PATCH')}}
                {{csrf_field()}}

                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="form-group">
                                <label>Enunciado</label>
                                <textarea id="enun_edit" name="enunciado" class="form-control" rows="2"></textarea>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                            <div class="checkbox">
                                <label>
                                        <input id="check_edit" name="obligatoria" type="checkbox">
                                        Pregunta obligatoria
                                </label>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div id="contOpciones_edit" >
                        <div class="row" >
                            <div class="container">
                                <h4>Opciones</h4>
                            </div>

                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="form-group">
                                    <label>Texto</label>
                                    <input id="texto_edit" name="texto" type="text" class="form-control" >
                                </div>
                                <div class="pull-right">
                                    <button id="btnAgregar_edit" class="btn btn-default" type="button" onclick="agregarEdit()">Agregar</button>
                                </div>
                            </div>

                        </div>

                        <div class="table-responsive-sm">
                            <table id="tabla_edit" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="w-25">Opciones</th>
                                    <th>Nombre</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

