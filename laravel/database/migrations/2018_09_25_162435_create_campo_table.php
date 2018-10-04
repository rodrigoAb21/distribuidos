<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateCampoTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('campo', function (Blueprint $table) {
            $table->increments('id');
            $table->string('nombre');
            $table->boolean('obligatorio');
            $table->boolean('varios');

            //$table->timestamps();

            $table->unsignedInteger('pregunta_id');
            $table->unsignedInteger('dominio_id');
            $table->foreign('pregunta_id')->references('id')->on('pregunta')->onDelete('cascade');
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
