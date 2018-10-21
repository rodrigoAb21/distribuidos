<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Cerrada extends Model
{
    protected $table = 'cerrada';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'tipoSeleccion',
        'obligatoria',
        'pregunta_id'
    ];

    public function opciones(){
        return $this->hasMany(Opcion::class);
    }


    public function otros(){
        return $this->hasMany(Otro::class);
    }
}
