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
            $table->string('tipo_preg');
            $table->boolean('obligatoria');
            $table->boolean('otro');
            $table->string('tipo_dato');
            //$table->timestamps();

            $table->unsignedInteger('modelo_id');
            $table->foreign('modelo_id')->references('id')->on('modelo')->onDelete('cascade');

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
