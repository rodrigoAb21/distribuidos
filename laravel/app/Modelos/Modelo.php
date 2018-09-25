<?php

namespace App\Modelos;

use App\User;
use Illuminate\Database\Eloquent\Model;

class Modelo extends Model
{
    protected $table = 'modelo_encuesta';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'nombre',
        'estado',
        'descripcion',
        'user_id',
    ];

    public function user(){
        return $this->belongsTo(User::class);
    }
}
