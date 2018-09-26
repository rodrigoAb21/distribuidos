<?php

namespace App\Modelos;

use Illuminate\Database\Eloquent\Model;

class PregRequisito extends Model
{
    protected $table = 'preg_requisito';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
    ];
}
