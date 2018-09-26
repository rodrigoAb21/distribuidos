<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePreguntaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('pregunta', function (Blueprint $table) {
            $table->increments('id');
            $table->string('enunciado');
            $table->char('tipo_preg');
            $table->boolean('obligatoria');
            $table->string('tipo_dato');
            //$table->timestamps();

            $table->unsignedInteger('modelo_encuesta_id');
            $table->foreign('modelo_encuesta_id')->references('id')->on('modelo_encuesta')->onDelete('cascade');
            $table->string('empresa_id');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('pregunta');
    }
}
