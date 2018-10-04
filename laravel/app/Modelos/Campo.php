<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Campo extends Model
{
    protected $table = 'campo';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'nombre',
        'obligatorio',
        'varios',
        'pregunta_id',
        'dominio_id'
    ];

    public function dominio(){
        return $this->belongsTo(Dominio::class);
    }
}
