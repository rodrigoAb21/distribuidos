<?php

namespace App;

use App\Modelos\Opcion;
use Illuminate\Database\Eloquent\Model;

class Cerrada extends Model
{
    protected $table = 'cerrada';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'tipoSeleccion',
        'obligatoria',
        'pregunta_id'
    ];

    public function area(){
        return $this->hasMany(Opcion::class);
    }

}
