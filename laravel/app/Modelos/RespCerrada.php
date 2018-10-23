<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class RespCerrada extends Model
{
    protected $table = 'resp_cerrada';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'tag',
        'ficha_id',
        'opcion_id',
    ];

    public function opcion(){
        return $this->belongsTo(Opcion::class);
    }
}
