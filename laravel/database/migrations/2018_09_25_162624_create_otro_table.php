<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateOtroTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('otro', function (Blueprint $table) {
            $table->increments('id');
            $table->string('etiqueta');
//            $table->boolean('varios');

            //$table->timestamps();

            $table->unsignedInteger('cerrada_id');
            $table->unsignedInteger('dominio_id');
            $table->foreign('cerrada_id')->references('id')->on('cerrada')->onDelete('cascade');
            $table->foreign('dominio_id')->references('id')->on('dominio')->onDelete('cascade');

        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        //
    }
}
