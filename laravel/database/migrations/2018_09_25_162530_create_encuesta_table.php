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
            $table->increments('id');
            $table->string('fecha');
            $table->string('estado');

            //$table->double('longitud');
            //$table->double('latitud');
            //$table->timestamps();

            $table->unsignedInteger('asignacion_id');
            $table->foreign('asignacion_id')->references('id')->on('asignacion')->onDelete('cascade');

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
