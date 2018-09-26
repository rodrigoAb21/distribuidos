<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class PregRequisito extends Model
{
    protected $table = 'preg_requisito';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'pregunta_id',
        'pregunta_requisito_id',
        'opcion_id',
        'empresa_id'
    ];

    public function pregunta(){
        return $this->belongsTo(Pregunta::class);
    }
}
