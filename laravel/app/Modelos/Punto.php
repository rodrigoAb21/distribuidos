<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Punto extends Model
{
    protected $table = 'punto';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
    ];
}
