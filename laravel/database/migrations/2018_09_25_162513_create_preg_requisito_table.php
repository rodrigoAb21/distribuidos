<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePregRequisitoTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('preg_requisito', function (Blueprint $table) {
            $table->increments('id');
            //$table->timestamps();

            $table->unsignedInteger('pregunta_id');
            $table->unsignedInteger('pregunta_requisito_id');
            $table->unsignedInteger('opcion_id');
            $table->foreign('pregunta_id')->references('id')->on('pregunta')->onDelete('cascade');
            $table->foreign('pregunta_requisito_id')->references('id')->on('pregunta')->onDelete('cascade');
            $table->foreign('opcion_id')->references('id')->on('opcion')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('preg_requisito');
    }
}
