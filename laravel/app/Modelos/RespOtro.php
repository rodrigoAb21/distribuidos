<?php

namespace App\Modelos;


use Illuminate\Database\Eloquent\Model;

class RespOtro extends Model
{
    protected $table = 'resp_otro';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'tag',
        'valor',
        'ficha_id',
        'otro_id',
    ];


}