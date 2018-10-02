function datosModal2(pregunta, opciones, url) {
    $(".ff").remove();
    contador = 0;

    $('#formulario_edit').attr("action", url);
    var preg = JSON.parse(pregunta);
    var opc = JSON.parse(opciones);
    $('#enun_edit').val(preg.enunciado);
    $('#check_edit').prop('checked',preg.obligatoria);
    while(contador<opc.length ){
        contador++;
        var fila = '<tr class="ff" id="fila' + contador + 'Edit"><td><button type="button" class="btn btn-danger btn-sm" onclick="eliminarEdit('+contador+');"><i class="fa fa-trash" aria-hidden="true"></i></button></td><td><input class="form-control" name="textoT[]" value="' + opc[contador-1].texto + '"/></td></tr>';
        $("#tabla_edit").append(fila);
    }

    $('#editPreg').modal('show');

}

function agregarEdit() {
    var textoEdit = $('#texto_edit').val();
    contador++;
    var fila = '<tr class="ff" id="fila' + contador + 'Edit"><td><button type="button" class="btn btn-danger btn-sm" onclick="eliminarEdit('+contador+');"><i class="fa fa-trash" aria-hidden="true"></i></button></td><td><input class="form-control" name="textoT[]" value="' + textoEdit + '"/></td></tr>';
    $("#tabla_edit").append(fila);
    $('#texto_edit').val("");
}

function eliminarEdit(index) {
    $("#fila" + index + "Edit").remove();
    contador--;
}
