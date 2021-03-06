<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateRespAbiertaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('resp_abierta', function (Blueprint $table) {
            $table->increments('id');
            $table->string('tag');
            $table->string('valor');
            //$table->timestamps();

            $table->unsignedInteger('ficha_id');
            $table->unsignedInteger('campo_id');
            $table->foreign('ficha_id')->references('id')->on('ficha_resp')->onDelete('cascade');
            $table->foreign('campo_id')->references('id')->on('campo')->onDelete('cascade');

        });


        //
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('resp_abierta');
    }
}
