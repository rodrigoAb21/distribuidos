<?php

namespace App\Modelos;

use App\User;
use Illuminate\Database\Eloquent\Model;

class Asignacion extends Model
{
    protected $table = 'asignacion';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'cantidad',
        'hora_inicio',
        'hora_final',
        'encuestador_id',
        'modelo_id',
        'area_id',
        'admin_id'
    ];

    public function area(){
        return $this->belongsTo(Area::class);
    }

    public function encuestas(){
        return $this->hasMany(Encuesta::class);
    }

    public function modelo(){
        return $this->belongsTo(Modelo::class);
    }

    public function user(){
        return $this->belongsTo(User::class,'encuestador_id');
    }

}
