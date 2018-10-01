<div class="modal fade" id="formEdit" tabindex="-1" role="dialog" aria-labelledby="formEditLabel" aria-hidden="true" style="overflow-y:auto;">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nueva Pregunta</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="" method="POST">
                {{method_field('PATCH')}}
                {{csrf_field()}}

                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="form-group">
                                <label>Enunciado</label>
                                <textarea id="enunciado" name="enunciado" class="form-control" rows="2"></textarea>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                            <div class="checkbox">
                                <label>
                                        <input id="check" name="obligatoria" type="checkbox">
                                        Pregunta obligatoria
                                </label>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div id="contOpciones" >
                        <div class="row" >
                            <div class="container">
                                <h4>Opciones</h4>
                            </div>

                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="form-group">
                                    <label>Texto</label>
                                    <input id="texto" name="texto" type="text" class="form-control" >
                                </div>
                                <div class="pull-right">
                                    <button id="btnAgregar" class="btn btn-default" type="button" onclick="agregar()">Agregar</button>
                                </div>
                            </div>

                        </div>

                        <div class="table-responsive-sm">
                            <table id="tabla" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th class="w-25">Opciones</th>
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
