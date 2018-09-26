<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class RespAbierta extends Model
{
    protected $table = 'resp_abierta';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
    ];
}
