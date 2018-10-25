<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Area extends Model
{
    protected $table = 'area';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'nombre',
        'user_id'
    ];

    public function puntos(){
        return $this->hasMany(Punto::class);
    }
}
