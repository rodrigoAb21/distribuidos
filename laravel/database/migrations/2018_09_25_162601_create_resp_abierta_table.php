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
            $table->string('descripcion');
            //$table->timestamps();

            $table->unsignedInteger('ficha_resp_id');
            $table->foreign('ficha_resp_id')->references('id')->on('ficha_resp')->onDelete('cascade');

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
