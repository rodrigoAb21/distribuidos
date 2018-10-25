<?php

namespace App\Modelos;

use App\User;
use Illuminate\Database\Eloquent\Model;

class Modelo extends Model
{
    protected $table = 'modelo';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'nombre',
        'estado',
        'descripcion',
        'user_id',
    ];

    public function preguntas(){
        return $this->hasMany(Pregunta::class);
    }

    public function asignaciones(){
        return $this->hasMany(Asignacion::class);
    }
}
