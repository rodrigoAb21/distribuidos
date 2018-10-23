<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Encuesta extends Model
{
    protected $table = 'encuesta';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'estado',
        'fecha',
        //'longitud',
        //'latitud',
        'asignacion_id',
//        'empresa_id'
    ];


    public function fichas_resp(){
        return $this->hasMany(FichaResp::class);
    }

}
