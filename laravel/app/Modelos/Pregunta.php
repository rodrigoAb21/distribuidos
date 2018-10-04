<?php

namespace App\Modelos;

use App\Cerrada;
use Illuminate\Database\Eloquent\Model;

class Pregunta extends Model
{
    protected $table = 'pregunta';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'enunciado',
        'modelo_id'
    ];

    public function opciones(){
        return $this->hasMany(Cerrada::class);
    }

    public function campos(){
        return $this->hasMany(Campo::class);
    }
}
