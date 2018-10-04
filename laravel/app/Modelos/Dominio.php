<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Dominio extends Model
{
    protected $table = 'dominio';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'tipoDato',
        'min',
        'max'
    ];

}
