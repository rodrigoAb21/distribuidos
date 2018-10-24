<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateRespOtroTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('resp_otro', function (Blueprint $table) {
            $table->increments('id');
            $table->string('tag');
            $table->string('valor');
            //$table->timestamps();

            $table->unsignedInteger('ficha_id');
            $table->unsignedInteger('otro_id');
            $table->foreign('ficha_id')->references('id')->on('ficha_resp')->onDelete('cascade');
            $table->foreign('otro_id')->references('id')->on('otro')->onDelete('cascade');

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
