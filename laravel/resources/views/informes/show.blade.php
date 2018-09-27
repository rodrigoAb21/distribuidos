@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Informe estadistico</h4>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div id="map" style="height: 350px;"></div>
                            </div>
                        </div>




                        <div class="row">
                            <div class="col-md-4">
                                <div class="card ">
                                    <div class="card-header ">
                                        <h5 class="card-title">Email Statistics</h5>
                                        <p class="card-category">Last Campaign Performance</p>
                                    </div>
                                    <div class="card-body ">
                                        <canvas id="chartEmail"></canvas>
                                    </div>
                                    <div class="card-footer ">
                                        <div class="legend">
                                            <i class="fa fa-circle text-primary"></i> Opened
                                            <i class="fa fa-circle text-warning"></i> Read
                                            <i class="fa fa-circle text-danger"></i> Deleted
                                            <i class="fa fa-circle text-gray"></i> Unopened
                                        </div>
                                        <hr>
                                        <div class="stats">
                                            <i class="fa fa-calendar"></i> Number of emails sent
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="card card-chart">
                                    <div class="card-header">
                                        <h5 class="card-title">NASDAQ: AAPL</h5>
                                        <p class="card-category">Line Chart with Points</p>
                                    </div>
                                    <div class="card-body">
                                        <canvas id="speedChart" width="400" height="100"></canvas>
                                    </div>
                                    <div class="card-footer">
                                        <div class="chart-legend">
                                            <i class="fa fa-circle text-info"></i> Tesla Model S
                                            <i class="fa fa-circle text-warning"></i> BMW 5 Series
                                        </div>
                                        <hr/>
                                        <div class="card-stats">
                                            <i class="fa fa-check"></i> Data information certified
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>




                    </div>
                </div>
            </div>
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
    @endpush

@endsection