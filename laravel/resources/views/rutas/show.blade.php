@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="card-title">Ruta: {{$ruta -> nombre}}</h4>
                    </div>
                    <div class="card-body">

                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div id="map" style="height: 500px;"></div>
                            </div>
                        </div>
                        <div class="row mt-4">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div class="form-group">
                                    <label>Descripcion</label>
                                    <p class="form-control">{{$ruta -> descripcion}}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    @push('shead')
        <script src='https://api.tiles.mapbox.com/mapbox-gl-js/v0.49.0/mapbox-gl.js'></script>
        <link href='https://api.tiles.mapbox.com/mapbox-gl-js/v0.49.0/mapbox-gl.css' rel='stylesheet' />
    @endpush
    @push('scripts')
        <script>

            mapboxgl.accessToken = 'pk.eyJ1Ijoicm9kcmlnb2FiMjEiLCJhIjoiY2psenZmcDZpMDN5bTNrcGN4Z2s2NWtqNSJ9.bSdjQfv-28z1j4zx7ljvcg';
            var map = new mapboxgl.Map({
                container: 'map', // container id
                style: 'mapbox://styles/mapbox/streets-v9',
                center: [-63.180547, -17.783603], // starting position
                zoom: 12 // starting zoom
            });

            // Add geolocate control to the map.
            map.addControl(new mapboxgl.GeolocateControl({
                positionOptions: {
                    enableHighAccuracy: true
                },
                trackUserLocation: true
            }));

            // Create a GeoJSON source with an empty lineString.
            var ruta = {
                "type": "FeatureCollection",
                "features": [{
                    "type": "Feature",
                    "geometry": {
                        "type": "LineString",
                        "coordinates": []
                    }
                }]
            };


            map.on('load', function() {

                @foreach($puntos as $punto)
                    ruta.features[0].geometry.coordinates.push([parseFloat('{{$punto -> longitud}}'),parseFloat('{{$punto -> latitud}}')]);
                @endforeach

                map.addLayer({
                    'id': 'linea',
                    'type': 'line',
                    'source': {
                        'type': 'geojson',
                        'data': ruta
                    },
                    'layout': {
                        'line-cap': 'round',
                        'line-join': 'round'
                    },
                    'paint': {
                        'line-color': '#338ced',
                        'line-width': 8,
                        'line-opacity': .8
                    }
                });



            });


        </script>

    @endpush
@endsection