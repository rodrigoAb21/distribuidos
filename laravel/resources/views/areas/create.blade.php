@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="card-title">Nueva area</h4>
                    </div>
                    <div class="card-body">
                        <form action="{{url('/areas')}}" method="POST" autocomplete="off">
                            {{csrf_field()}}
                            <div class="row">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div id="map" style="height: 350px;"></div>
                                </div>
                            </div>
                            <div class="row justify-content-center mt-4">

                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <div class="form-group">
                                            <label>Nombre</label>
                                            <input type="text" name="nombre" class="form-control" >
                                            <input name="coordenadas" id="coordenadas" value="" hidden>
                                        </div>
                                    </div>

                            </div>

                            <div class="row">
                                <div class="update ml-auto mr-auto">
                                    <button class="btn btn-warning btn-round" type="submit" onclick="obtenerCoordenadas()">Guardar</button>
                                </div>
                            </div>
                        </form>
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
            var coordenadas;
            L.mapbox.accessToken = 'pk.eyJ1Ijoicm9kcmlnb2FiMjEiLCJhIjoiY2psenZmcDZpMDN5bTNrcGN4Z2s2NWtqNSJ9.bSdjQfv-28z1j4zx7ljvcg';
            var map = L.mapbox.map('map', 'mapbox.streets')
                .setView([-17.783346, -63.180589], 13);

            var featureGroup = L.featureGroup().addTo(map);

            var drawControl = new L.Control.Draw({
                edit: {
                    featureGroup: featureGroup
                },
                draw: {
                    polygon: true,
                    polyline: false,
                    rectangle: false,
                    circle: false,
                    marker: false
                }
            }).addTo(map);

            map.on('draw:created', showPolygonArea);
            map.on('draw:edited', showPolygonAreaEdited);

            function showPolygonAreaEdited(e) {
                e.layers.eachLayer(function(layer) {
                    showPolygonArea({ layer: layer });
                });
            }
            function showPolygonArea(e) {
                featureGroup.clearLayers();
                featureGroup.addLayer(e.layer);
                e.layer.bindPopup((LGeo.area(e.layer) / 1000000).toFixed(2) + ' km<sup>2</sup>');
                e.layer.openPopup();
            }

            map.on('draw:created', function (e) {
                var layer = e.layer;
                var shape = layer.toGeoJSON();
                coordenadas = shape['geometry']['coordinates'][0];
            });
            function obtenerCoordenadas(){
                document.getElementById('coordenadas').value = coordenadas;
            }
        </script>

    @endpush
@endsection