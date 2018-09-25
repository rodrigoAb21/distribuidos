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
            //$table->timestamps();

            $table->unsignedInteger('opcion_id');
            $table->unsignedInteger('ficha_resp_id');
            $table->foreign('opcion_id')->references('id')->on('opcion')->onDelete('cascade');
            $table->foreign('ficha_resp_id')->references('id')->on('ficha_resp')->onDelete('cascade');
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
