<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateRespCerradaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('resp_cerrada', function (Blueprint $table) {
            $table->increments('id');
            $table->string('tag');
            //$table->timestamps();

            $table->unsignedInteger('ficha_id');
            $table->unsignedInteger('opcion_id');
            $table->foreign('ficha_id')->references('id')->on('ficha_resp')->onDelete('cascade');
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
        Schema::dropIfExists('resp_cerrada');
    }
}
