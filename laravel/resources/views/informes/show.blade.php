@extends('layouts.dashboard')
@push('shead')
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.js"></script>
    <script>
        var lineasAll=[];
        var lineas=[];
        var marcadoresAll=[];
        function dibujarAreas(areas){
            // console.log("areas:",areas);

            for (var elem in areas){
                // console.log(areas[elem]);
                lineas =[];
                for(var cont in areas[elem]){
                    lineas.push([parseFloat(areas[elem][cont].latitud),parseFloat(areas[elem][cont].longitud)]);
                }
                lineasAll.push(lineas);
            }
        }
        function dibujarMarcadores(marcadoresE) {
            for (var elem in marcadoresE){
                console.log(marcadoresE[elem]);
                marcadoresAll.push([parseFloat(marcadoresE[elem].latitud),parseFloat(marcadoresE[elem].longitud)]);
            }
        }

        function crear(id, pregunta) {
            var tipo = pregunta['tipo'];
            var opciones = pregunta['opciones'];

            var opcionesL=[];
            var valoresL=[];

            for(let i = 0; i < opciones.length; i++){
                opcionesL.push([opciones[i]['texto']]);
                valoresL.push([opciones[i]['resultado']]);
            }

            if(tipo == "Unica") {
            console.log("entro");
            var datos ={
                type: 'pie',
                data: {
                    datasets: [{

                        backgroundColor: [
                            '#e3e3e3',
                            '#4acccd',
                            '#fcc468',
                            '#ef8157'
                        ],
                        pointRadius: 0,
                        pointHoverRadius: 0,
                        borderWidth: 0,
                        data: valoresL

                    }],
                    labels: opcionesL
                },
                options: {}
            };
            var dibujo = document.getElementById(id).getContext('2d');
            new Chart(dibujo,datos);
            }else{

                var valores= [];
                for(let i = 0; i < valoresL.length; i++){
                    valores.push(parseInt([valoresL[i][0]]));
                }
                var datos ={
                    type: 'bar',
                    data: {
                        datasets:[{
                            data: valores,
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.6)',
                                'rgba(54, 162, 235, 0.6)',
                                'rgba(255, 206, 86, 0.6)',
                                'rgba(75, 192, 192, 0.6)',
                                'rgba(153, 102, 255, 0.6)',
                                'rgba(255, 159, 64, 0.6)',
                                'rgba(255, 99, 132, 0.6)',
                                'rgba(54, 162, 235, 0.6)',
                                'rgba(255, 206, 86, 0.6)',
                                'rgba(75, 192, 192, 0.6)',
                                'rgba(153, 102, 255, 0.6)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 0.6)',
                                'rgba(54, 162, 235, 0.6)',
                                'rgba(255, 206, 86, 0.6)',
                                'rgba(75, 192, 192, 0.6)',
                                'rgba(153, 102, 255, 0.6)',
                                'rgba(255, 159, 64, 0.6)',
                                'rgba(255, 99, 132, 0.6)',
                                'rgba(54, 162, 235, 0.6)',
                                'rgba(255, 206, 86, 0.6)',
                                'rgba(75, 192, 192, 0.6)',
                                'rgba(153, 102, 255, 0.6)'
                            ],
                            borderWidth: 1
                        }],
                        labels: opcionesL
                    },
                    options: {
                        scales: {
                            xAxes:[

                            ],
                            yAxes: [{
                                ticks: {
                                    min: 0,
                                    max: 10,
                                    beginAtZero:true
                                }
                            }]
                        },
                        legend: {
                            display: false
                        }
                    }
                };

                var dibujo = document.getElementById(id).getContext('2d');
                new Chart(dibujo, datos);
            }

        };
    </script>
@endpush
@section('contenido')
    <div class="content">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="card-header form-inline">
                        <h4 class="pr-2">Informe estadistico</h4>

                        <div class="ml-auto mr-0">
                            {{--<div class="form-inline">--}}
                                {{--<select class="form-control" name="areas">--}}
                                    {{--<option selected value="">General</option>--}}
                                    {{--@foreach($areasN as $area)--}}
                                    {{--<option value={{$area["area_id"]}}>{{$area[0]}}</option>--}}
                                    {{--@endforeach--}}
                                {{--</select>--}}
                                {{--<button class="btn btn-success" >Ver</button>--}}
                            {{--</div>--}}
                        </div>
                    </div>



                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div id="map" style="height: 350px;"></div>
                                <script type="text/javascript" language="javascript">
                                    var areasx=<?=json_encode($areasP)?>;
                                    var marcadores=<?=json_encode($marcadores)?>;
                                    dibujarAreas(areasx);
                                    dibujarMarcadores(marcadores);
                                </script>
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
                    <div id="card-body" class="card-body ">
                        <canvas id="pregunta-{{$pregunta->id}}"></canvas>
                        @if($pregunta->tipo != "Entrada")
                            <script language="javascript" type="text/javascript">
                               var x=<?=json_encode($pregunta)?>;

                               crear("pregunta-"+x['id'],x);

                            </script>
                        @endif
                    </div>
                    <div class="card-footer ">
                        <div class="legend">
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


            // var lineas = [
            //     [-17.793, -63.186],
            //     [-17.793, -63.167],
            //     [-17.779, -63.167],
            //     [-17.779, -63.186],
            //     [-17.793, -63.186],
            // ];
            console.log("das:",lineasAll);
            for (var elem in lineasAll){
                new L.polygon(lineasAll[elem]).addTo(map);
            }

            for (var elem in marcadoresAll){
                new L.marker(marcadoresAll[elem]).addTo(map);
            }

            // var area = L.polygon(lineas).addTo(map);
            //
            // L.marker([-17.782, -63.180]).addTo(map);
            // L.marker([-17.780, -63.181]).addTo(map);
            // L.marker([-17.784, -63.183]).addTo(map);
            // L.marker([-17.783, -63.177]).addTo(map);
            // L.marker([-17.785, -63.180]).addTo(map);



        </script>
        <script src="{{asset('js/plantilla/demo.js')}}"
                type="text/javascript"></script>
        <script src="{{asset('js/terceros/chartjs/chartjs.min.js')}}"></script>

        <script>

            // $(document).ready(function() {
            //     demo.initChartsPages();
            // });
            // var ctx = "graficaChart";
            //
            // var graficaChart = new Chart(ctx,{
            //     type: 'pie',
            //     data: {
            //         datasets: [{
            //             data: [10, 20, 30,60],
            //             backgroundColor: [
            //                 'rgba(255, 99, 132, 0.2)',
            //                 'rgba(54, 162, 235, 0.2)',
            //                 'rgba(255, 206, 86, 0.2)','rgba(54, 162, 235, 0.2)',
            //                 'rgba(255, 206, 86, 0.2)',
            //             ],
            //
            //         }],
            //
            //         labels: ['1','2','3']
            //
            //     },
            //     options: {}
            // });


                // var idPregunta= document.getElementById("1").getContext("2d");
                // var opciones= ["si","no","tal vez"];
                // var valores = [20, 30, 50];
                // var ctx = idPregunta;
                // var grafica = new Chart(ctx,{
                //     type: 'pie',
                //     data: {
                //         datasets: [{
                //             data: valores,
                //             backgroundColor: [
                //                 'rgba(255, 99, 132, 0.2)',
                //                 'rgba(54, 162, 235, 0.2)',
                //                 'rgba(255, 206, 86, 0.2)',
                //                 'rgba(54, 162, 235, 0.2)',
                //                 'rgba(255, 206, 86, 0.2)',
                //             ],
                //
                //         }],
                //         labels: opciones
                //     },
                //     options: {}
                // });





        </script>
    @endpush

@endsection