<?php

namespace App\Modelos;

use App\Campo;
use Illuminate\Database\Eloquent\Model;

class RespAbierta extends Model
{
    protected $table = 'resp_abierta';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $fillable = [
        'tag',
        'valor',
        'ficha_id',
        'campo_id',
    ];

    public function campo(){
        return $this->belongsTo(Campo::class);
    }
}
