<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="{{asset('img/apple-icon.png')}}">
    <link rel="icon" type="image/png" href="{{asset('img/favicon.png')}}">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>
        Encuestas
    </title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />

    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link href="{{asset('css/terceros/fontawesome/css/all.css')}}" rel="stylesheet" />

    <link href="{{asset('css/terceros/bootstrap/bootstrap.min.css')}}" rel="stylesheet" />
    <link href="{{asset('css/plantilla/paper-dashboard.css')}}" rel="stylesheet" />

    @stack('shead')

</head>

<body class="">
    <div class="wrapper ">
        <div class="sidebar" data-color="white" data-active-color="danger">
            <div class="logo">
                <a href="{{url('/')}}" class="simple-text logo-mini">
                    <div class="logo-image-small">
                        <img src="{{asset('img/logo-small.png')}}">
                    </div>
                </a>
                <a href="{{url('/')}}" class="simple-text logo-normal">
                    Dashboard
                </a>
            </div>
            <div class="sidebar-wrapper">
                <ul class="nav">
                    <li class="">
                        <a href="{{url('/encuestadores')}}">
                            <i class="fa fa-users"></i>
                            <p>Encuestadores</p>
                        </a>
                    </li>
                    <li class="">
                        <a href="{{url('/modelos')}}">
                            <i class="fa fa-file-invoice"></i>
                            <p>Modelos de Encuesta</p>
                        </a>
                    </li>
                    <li class="">
                        <a href="{{url('/areas')}}">
                            <i class="fa fa-map-marked-alt"></i>
                            <p>Areas</p>
                        </a>
                    </li>
                    <li class="">
                        <a href="{{url('/asignaciones')}}">
                            <i class="fa fa-hand-point-right"></i>
                            <p>Asignaciones</p>
                        </a>
                    </li>
                    <li class="">
                        <a href="{{url('/informes')}}">
                            <i class="fa fa-chart-pie"></i>
                            <p>Informes estadisticos</p>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="main-panel">

            <nav class="navbar navbar-expand-lg navbar-absolute fixed-top navbar-transparent">
                <div class="container-fluid">
                    <div class="navbar-wrapper">
                        <div class="navbar-toggle">
                            <button type="button" class="navbar-toggler">
                                <span class="navbar-toggler-bar bar1"></span>
                                <span class="navbar-toggler-bar bar2"></span>
                                <span class="navbar-toggler-bar bar3"></span>
                            </button>
                        </div>
                        <a class="navbar-brand" href="{{url('/')}}">Encuestas</a>
                    </div>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-bar navbar-kebab"></span>
                        <span class="navbar-toggler-bar navbar-kebab"></span>
                        <span class="navbar-toggler-bar navbar-kebab"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-end" id="navigation">
                        <ul class="navbar-nav">
                            <li class="nav-item btn-rotate dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fa fa-user-circle"></i>
                                    <p>
                                        <span class="d-lg-none d-md-block">{{ Auth::user()->nombre }} {{ Auth::user()->apellido }}</span>
                                    </p>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                                    <a class="dropdown-item" href="{{ route('logout') }}"
                                       onclick="event.preventDefault();
                                                     document.getElementById('logout-form').submit();">
                                        Cerrar Sesion
                                    </a>

                                    <form id="logout-form" action="{{ route('logout') }}" method="POST" style="display: none;">
                                        {{ csrf_field() }}
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            @yield('contenido')
        </div>
    </div>

<script src="{{asset('js/terceros/jquery/jquery.min.js')}}"></script>
<script src="{{asset('js/terceros/bootstrap/popper.min.js')}}"></script>
<script src="{{asset('js/terceros/bootstrap/bootstrap.min.js')}}"></script>
<script src="{{asset('js/terceros/jquery/perfect-scrollbar.jquery.min.js')}}"></script>

<script src="{{asset('js/terceros/bootstrap/bootstrap-notify.js')}}"></script>

<script src="{{asset('js/plantilla/paper-dashboard.min.js')}}" type="text/javascript"></script>
    @stack('scripts')
</body>

</html>