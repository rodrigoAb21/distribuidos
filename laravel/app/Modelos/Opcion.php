<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Opcion extends Model
{
    protected $table = 'opcion';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
    ];
}
