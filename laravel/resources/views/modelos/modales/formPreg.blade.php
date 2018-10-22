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
                            <div class="mr-0 ml-auto pull-right" id="tipoPregunta">
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
                    <div id="abierta"></div>

                    <div id="cerrada"></div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>


