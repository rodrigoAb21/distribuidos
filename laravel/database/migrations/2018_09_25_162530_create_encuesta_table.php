<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateEncuestaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('encuesta', function (Blueprint $table) {
            $table->increments('id')->primary();
            $table->date('fecha');
            $table->double('longitud');
            $table->double('latitud');
            //$table->timestamps();

            $table->unsignedInteger('asignacion_id');
            $table->unsignedInteger('encuestado_id');
            $table->foreign('asignacion_id')->references('id')->on('asignacion')->onDelete('cascade');
            $table->foreign('encuestado_id')->references('id')->on('encuestado')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('encuesta');
    }
}
