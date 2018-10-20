<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Otro extends Model
{
    protected $table = 'otro';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'etiqueta',
        'cerrada_id',
        'dominio_id'
    ];

    public function dominio(){
        return $this->belongsTo(Dominio::class);
    }
}
