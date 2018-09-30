<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateAsignacionTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('asignacion', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('cantidad');
            $table->dateTime('hora_inicio');
            $table->dateTime('hora_final');
            //$table->timestamps();

            $table->unsignedInteger('encuestador_id');
            $table->unsignedInteger('modelo_id');
            $table->unsignedInteger('area_id');
            $table->foreign('encuestador_id')->references('id')->on('users')->onDelete('cascade');
            $table->foreign('modelo_id')->references('id')->on('modelo')->onDelete('cascade');
            $table->foreign('area_id')->references('id')->on('area')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('asignacion');
    }
}
