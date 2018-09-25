<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateFichaRespTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ficha_resp', function (Blueprint $table) {
            $table->increments('id');
            //$table->timestamps();

            $table->unsignedInteger('encuesta_id');
            $table->unsignedInteger('pregunta_id');
            $table->foreign('encuesta_id')->references('id')->on('encuesta')->onDelete('cascade');
            $table->foreign('pregunta_id')->references('id')->on('pregunta')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('ficha_resp');
    }
}
