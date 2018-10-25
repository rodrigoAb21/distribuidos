<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class FichaResp extends Model
{
    protected $table = 'ficha_resp';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'encuesta_id',
        'pregunta_id'
    ];

    public function resp_abiertas(){
        return $this->hasMany(RespAbierta::class);
    }

    public function resp_cerradas(){
        return $this->hasMany(RespCerrada::class);
    }

    public function resp_otros(){
        return $this->hasMany(RespOtro::class);
    }

    public function pregunta(){
        return $this->belongsTo(Pregunta::class);
    }

    public function encuesta(){
        return $this->belongsTo(Encuesta::class);
    }
}
