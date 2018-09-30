<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Pregunta extends Model
{
    protected $table = 'pregunta';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'enunciado',
        'tipo_preg',
        'obligatoria',
        'otro',
        'tipo_dato',
        'modelo_id',
    ];

    public function opciones(){
        return $this->hasMany(Opcion::class);
    }
}
