@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                <a href="{{url('/encuestadores')}}">
                    <div class="card card-stats">
                        <div class="card-header"></div>
                        <div class="card-body ">
                            <div class="row">
                                <div class="col-2 col-md-2">
                                    <div class="icon-big text-center icon-warning">
                                        <i class="fa fa-users"></i>
                                    </div>
                                </div>
                                <div class="col-10 col-md-10">
                                    <div class="numbers">
                                        <p class="card-category">Ver</p>
                                        <p class="card-title">Encuestadores<p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer"></div>
                    </div>
                </a>
            </div>

            <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                <a href="{{url('/modelos')}}">
                    <div class="card card-stats">
                        <div class="card-header"></div>
                        <div class="card-body ">
                            <div class="row">
                                <div class="col-2 col-md-2">
                                    <div class="icon-big text-center icon-warning">
                                        <i class="fa fa-file-invoice"></i>
                                    </div>
                                </div>
                                <div class="col-10 col-md-10">
                                    <div class="numbers">
                                        <p class="card-category">Ver</p>
                                        <p class="card-title">Modelos<p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer"></div>
                    </div>
                </a>
            </div>
            <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                <a href="{{url('/areas')}}">
                    <div class="card card-stats">
                        <div class="card-header"></div>
                        <div class="card-body ">
                            <div class="row">
                                <div class="col-2 col-md-2">
                                    <div class="icon-big text-center icon-warning">
                                        <i class="fa fa-map-marked-alt"></i>
                                    </div>
                                </div>
                                <div class="col-10 col-md-10">
                                    <div class="numbers">
                                        <p class="card-category">Ver</p>
                                        <p class="card-title">Areas<p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer"></div>
                    </div>
                </a>
            </div>
            <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                <a href="{{url('/asignaciones')}}">
                    <div class="card card-stats">
                        <div class="card-header"></div>
                        <div class="card-body ">
                            <div class="row">
                                <div class="col-2 col-md-2">
                                    <div class="icon-big text-center icon-warning">
                                        <i class="fa fa-hand-point-right"></i>
                                    </div>
                                </div>
                                <div class="col-10 col-md-10">
                                    <div class="numbers">
                                        <p class="card-category">Ver</p>
                                        <p class="card-title">Asignaciones<p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer"></div>
                    </div>
                </a>
            </div>
            <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                <a href="{{url('/informes')}}">
                    <div class="card card-stats">
                        <div class="card-header"></div>
                        <div class="card-body ">
                            <div class="row">
                                <div class="col-2 col-md-2">
                                    <div class="icon-big text-center icon-warning">
                                        <i class="fa fa-chart-pie"></i>
                                    </div>
                                </div>
                                <div class="col-10 col-md-10">
                                    <div class="numbers">
                                        <p class="card-category">Ver</p>
                                        <p class="card-title">Informes<p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer"></div>
                    </div>
                </a>
            </div>
        </div>
    </div>
@endsection