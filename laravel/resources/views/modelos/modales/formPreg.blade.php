<div class="modal fade" id="formPreg" tabindex="-1" role="dialog" aria-labelledby="formPregLabel" aria-hidden="true" style="overflow-y:auto;">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nueva Pregunta</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="{{url('/modelos/'.$modelo->id.'/nuevaPregunta')}}" method="POST">
                {{csrf_field()}}
                <div class="modal-body">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="mr-0 ml-auto pull-right">
                                <input type="radio" name="tipoP" value="1" checked>
                                <label>Abierta</label>
                                <input type="radio" name="tipoP" value="0">
                                <label>Cerrada</label>
                            </div>
                            <br>
                            <div class="form-group">
                                <label>Enunciado</label>
                                <textarea name="enunciado" class="form-control" rows="2"></textarea>
                            </div>
                        </div>


                    </div>

                    <hr>

                    <div id="tablaAbierta" >
                        <div class="table-responsive text-center">
                            <table id="tablaA" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Etiqueta</th>
                                    <th>Tipo de dato</th>
                                    <th>Min</th>
                                    <th>Max</th>
                                    <th>Oblig.</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <hr>
                        <div class="text-center">
                            <button class="btn btn-default " type="button" onclick="agregarEtiqueta()"><i class="fa fa-plus"></i></button>
                        </div>
                    </div>


                    <div id="tablaCerrada" >
                        <div id="tablaC">

                        </div>
                        <hr>
                        <div class="text-center">
                            <button class="btn btn-primary " type="button" onclick="agregarSeleccion()"><i class="fa fa-plus"></i></button>
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


