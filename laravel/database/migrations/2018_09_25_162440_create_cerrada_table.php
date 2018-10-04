<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateCerradaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('cerrada', function (Blueprint $table) {
            $table->increments('id');
            $table->string('tipoSeleccion');
            $table->boolean('obligatoria');
            //$table->timestamps();

            $table->unsignedInteger('pregunta_id');
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
        //
    }
}
