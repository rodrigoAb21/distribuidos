<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('home');
})->middleware('auth');

/* Rutas para el usuario */
Route::get('/login', 'Web\Auth\LoginController@showLoginForm')->name('login');
Route::post('/login', 'Web\Auth\LoginController@login')->name('login');

Route::get('/register', 'Web\Auth\RegisterController@showRegistrationForm')->name('register');
Route::post('/register', 'Web\Auth\RegisterController@register')->name('register');


Route::middleware('auth')->group(function () {

    Route::post('/logout', 'Web\Auth\LoginController@logout')->name('logout');

    Route::resource('/encuestadores', 'Web\EncuestadorController');

    /* Rutas para TOD0 lo que es modelos, preguntas y opciones */
    Route::resource('/modelos', 'Web\ModeloController');
    Route::post('/modelos/{id}/nuevaPregunta','Web\PreguntaController@nuevaPregunta');
    Route::patch('/modelos/pregunta/{pid}/edit','Web\PreguntaController@editarPregunta');
    Route::delete('/modelos/pregunta/{pid}', 'Web\PreguntaController@eliminarPregunta');



    /*      FIN       */
    Route::resource('/areas', 'Web\AreaController');
    Route::resource('/asignaciones', 'Web\AsignacionController');
    Route::resource('/informes', 'Web\InformeController');

});







