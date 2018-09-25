@extends('layouts.dashboard')
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="card-title">Nueva Ruta</h4>
                    </div>
                    <div class="card-body">
                        <form action="{{url('/rutas')}}" method="POST" autocomplete="off">
                            {{csrf_field()}}
                            <div class="row">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div id="map" style="height: 500px;"></div>
                                </div>
                            </div>
                            <div class="row justify-content-center mt-4">

                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">

                                        <div class="row">
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                                <button type="button" class="btn btn-warning btn-block" onclick="eliminarUltimo()">Retroceder</button>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                                <button type="button" class="btn btn-danger btn-block" onclick="eliminarPuntos()">Limpiar Mapa</button>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <label>Nombre</label>
                                            <input type="text" name="nombre" class="form-control" >
                                        </div>

                                        <div class="form-group">
                                            <label>Descripcion</label>
                                            <textarea name="descripcion" class="form-control"></textarea>
                                        </div>

                                    </div>

                                    <table id="tabla" hidden></table>

                            </div>

                            <div class="row">
                                <div class="update ml-auto mr-auto">
                                    <button class="btn btn-warning btn-round" type="submit">Guardar</button>
                                </div>
                            </div>
                        </form>
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

        var cont = 0;

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

            map.on('click', function(e) {
                ruta.features[0].geometry.coordinates.push([e.lngLat.lng, e.lngLat.lat]);
                map.getSource('linea').setData(ruta);
                agregarPunto(e.lngLat);
            });

        });



        function eliminarPuntos() {

            for (var l = ruta.features[0].geometry.coordinates.length; l != 0; l--){
                eliminarUltimo();
                eliminar();
            }

        }

        function eliminarUltimo() {
            if(ruta.features[0].geometry.coordinates.length > 0){
                ruta.features[0].geometry.coordinates.pop();
                map.getSource('line-animation').setData(ruta);
                eliminar();
            }
        }

        function agregarPunto(location) {
            cont++;
            var latitud = location.lat;
            var longitud = location.lng;
            var fila = '<tr id="fila'+cont+'"><td><input name="longitudT[]" value="'+longitud+'"/><input name="latitudT[]" value="'+latitud+'"/></td></tr>';
            $("#tabla").append(fila);
        }

        function eliminar(){
            if (cont > 0){
                $("#fila" + cont).remove();
                cont--;
            }
        }

    </script>
    @endpush
@endsection