<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" style="overflow-y:auto;">
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
                            <div class="form-group">
                                <label>Enunciado</label>
                                <textarea name="enunciado" class="form-control" rows="2"></textarea>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                            <div class="form-group">
                                <label>Tipo</label>
                                <select name="tipop" class="form-control">
                                    <option value="Seleccion Unica">Seleccion Unica</option>
                                    <option value="Seleccion Multiple">Seleccion Multiple</option>
                                    <option value="Entrada de texto">Entrada de texto</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                            <div class="form-group">
                                <label>Tipo de entrada</label>
                                <select name="tipod" class="form-control">
                                    <option selected value="Texto">Texto</option>
                                    <option value="Numerico">Numerico</option>
                                    <option value="Fecha">Fecha</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                            <div class="checkbox">
                                <label>
                                    <input name="obligatoria" type="checkbox">
                                    Pregunta obligatoria
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="otro" type="checkbox">
                                    Agregar campo "Otro"
                                </label>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="container">
                            <h4>Opciones</h4>
                        </div>

                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="form-group">
                                <label>Texto</label>
                                <input type="text" class="form-control" name="texto">
                            </div>
                            <div class="pull-right">
                                <button class="btn btn-default" type="button" id="button-addon2">Agregar</button>
                            </div>
                        </div>

                    </div>

                    <div class="table-responsive-sm">
                        <table class="table table-bordered table-hover">
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
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

