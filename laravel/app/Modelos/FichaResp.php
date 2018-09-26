<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class FichaResp extends Model
{
    protected $table = 'ficha_resp';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
    ];
}
