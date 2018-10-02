var cont = 0;
$('#tipoP').change(evaluar);

function agregar() {
    var texto =$('#texto').val();
    if(texto.trim()!=""){
    cont++;
        var fila='<tr id="fila'+cont+'"><td><button type="button" class="btn btn-danger btn-sm" onclick="eliminar('+cont+');"><i class="fa fa-trash" aria-hidden="true"></i></button></td><td><input type="hidden" name="textoT[]" value="'+texto+'"/>'+texto+'</td></tr>';
    $("#tabla").append(fila);

    }
    $('#texto').val("");
    $('#texto').focus();
}

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

function evaluar() {
    if($('#tipoP option:selected').text() == 'Entrada de texto'){
        esconder();
    }else {
        $('#contOpciones').show();
        $('#checkOtro').show();
    }
}