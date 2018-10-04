<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Campo extends Model
{
    protected $table = 'campo';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'etiqueta',
        'obligatorio',
        'varios',
        'pregunta_id',
        'dominio_id'
    ];

    public function dominio(){
        return $this->belongsTo(Dominio::class);
    }
}
