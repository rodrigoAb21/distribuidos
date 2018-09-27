<?php

namespace App\Modelos;

use App\User;
use Illuminate\Database\Eloquent\Model;

class Empresa extends Model
{
    protected $table = 'empresa';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'nombre',
        'direccion',
        'telefono',
        'email',
        'empresa_id'
    ];


}
