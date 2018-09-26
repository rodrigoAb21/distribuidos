<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class Encuestado extends Model
{
    protected $table = 'encuestado';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
    ];
}
