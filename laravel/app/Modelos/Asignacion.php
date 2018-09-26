<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Asignacion extends Model
{
    protected $table = 'asignacion';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
    ];
}
