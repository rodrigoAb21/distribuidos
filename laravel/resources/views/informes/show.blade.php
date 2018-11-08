@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Informe estadistico</h4>

                        <div class="ml-auto mr-0">
                            <div class="form-inline">
                                <select class="form-control" name="areas">
                                    <option value="">General</option>
                                    <option selected value="">El Bajio 1</option>
                                    <option value="">Equipetrol</option>
                                    <option value="">La guardia</option>
                                    <option value="">Parque Industrial</option>
                                </select>
                                <button class="btn btn-success">Ver</button>
                            </div>
                        </div>
                    </div>



                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div id="map" style="height: 350px;"></div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="row">
           @foreach($preguntas as $pregunta)
            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <div class="card ">
                    <div class="card-header ">
                        <h5 class="card-title">{{$pregunta->enunciado}}</h5>
                    </div>
                    <div class="card-body ">
                        <canvas id="grafica-{{$pregunta->id}}"></canvas>
                    </div>
                    <div class="card-footer ">
                        <div class="legend">
                            @foreach($pregunta->opciones as $opcion)
                            <i class="fa fa-circle text-primary"></i> {{$opcion->texto}}
                            @endforeach
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
            @endforeach
        </div>










    </div>

    @push('shead')
        <script src='https://api.mapbox.com/mapbox.js/v3.1.1/mapbox.js'></script>
        <link href='https://api.mapbox.com/mapbox.js/v3.1.1/mapbox.css' rel='stylesheet'/>

    @endpush

    @push('scripts')
        <link href='https://api.mapbox.com/mapbox.js/plugins/leaflet-draw/v0.4.10/leaflet.draw.css' rel='stylesheet' />
        <script src='https://api.mapbox.com/mapbox.js/plugins/leaflet-draw/v0.4.10/leaflet.draw.js'></script>
        <script src='https://api.mapbox.com/mapbox.js/plugins/leaflet-geodesy/v0.1.0/leaflet-geodesy.js'></script>

        <script>
            L.mapbox.accessToken = 'pk.eyJ1Ijoicm9kcmlnb2FiMjEiLCJhIjoiY2psenZmcDZpMDN5bTNrcGN4Z2s2NWtqNSJ9.bSdjQfv-28z1j4zx7ljvcg';
            var map = L.mapbox.map('map', 'mapbox.streets')
                .setView([-17.783346, -63.180589], 14);


            var lineas = [
                [-17.793, -63.186],
                [-17.793, -63.167],
                [-17.779, -63.167],
                [-17.779, -63.186],
                [-17.793, -63.186],
            ];



            var area = L.polygon(lineas).addTo(map);

            L.marker([-17.782, -63.180]).addTo(map);
            L.marker([-17.780, -63.181]).addTo(map);
            L.marker([-17.784, -63.183]).addTo(map);
            L.marker([-17.783, -63.177]).addTo(map);
            L.marker([-17.785, -63.180]).addTo(map);



        </script>
        <script src="{{asset('js/plantilla/demo.js')}}" type="text/javascript"></script>
        <script src="{{asset('js/terceros/chartjs/chartjs.min.js')}}"></script>

        <script>
            $(document).ready(function() {
            demo.initChartsPages();
            });
        </script>
    @endpush

@endsection