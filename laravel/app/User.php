<?php

namespace App;

use App\Modelos\Empresa;
use App\Modelos\Modelo;
use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;

class User extends Authenticatable
{
    use Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'ci',
        'nombre',
        'apellido',
        'direccion',
        'telefono',
        'tipo',
        'user_id',
        'email',
        'password'
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        'password', 'remember_token',
    ];

    public function empresa(){
        return $this->belongsTo(Empresa::class);
    }

    public function modelos(){
        return $this->hasMany(Modelo::class);
    }
}
