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

    public function resp_abierta(){
        return $this->hasMany(RespAbierta::class);
    }

    public function resp_cerrada(){
        return $this->hasMany(RespCerrada::class);
    }

    public function pregunta(){
        return $this->belongsTo(Pregunta::class);
    }
}
