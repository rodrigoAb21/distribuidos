<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="{{asset('plantilla/img/apple-icon.png')}}">
    <link rel="icon" type="image/png" href="{{asset('plantilla/img/favicon.png')}}">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>
        Proyecto Software I
    </title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />

    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link href="{{asset('plantilla/css/fontawesome/css/all.css')}}" rel="stylesheet" />

    <link href="{{asset('plantilla/css/bootstrap.min.css')}}" rel="stylesheet" />
    <link href="{{asset('plantilla/css/paper-dashboard.css')}}" rel="stylesheet" />

    @stack('shead')
</head>

<body class="">
    <div class="wrapper ">
        <div class="sidebar" data-color="white" data-active-color="danger">
            <div class="logo">
                <a href="{{url('/')}}" class="simple-text logo-mini">
                    <div class="logo-image-small">
                        <img src="{{asset('plantilla/img/logo-small.png')}}">
                    </div>
                </a>
                <a href="{{url('/')}}" class="simple-text logo-normal">
                    Dashboard
                </a>
            </div>
            <div class="sidebar-wrapper">
                <ul class="nav">
                    <li class="">
                        <a href="{{url('/empleados')}}">
                            <i class="nc-icon nc-single-02"></i>
                            <p>Perfil de Usuario</p>
                        </a>
                    </li>
                    <li class="">
                        <a href="{{url('/modelos')}}">
                            <i class="nc-icon nc-money-coins"></i>
                            <p>Cartera</p>
                        </a>
                    </li>
                    <li class="">
                        <a href="{{url('/areas')}}">
                            <i class="nc-icon nc-bus-front-12"></i>
                            <p>Vehiculos</p>
                        </a>
                    </li>
                    <li class="">
                        <a href="{{url('/asignaciones')}}">
                            <i class="nc-icon nc-world-2"></i>
                            <p>Rutas</p>
                        </a>
                    </li>
                    <li class="">
                        <a href="{{url('/reportes')}}">
                            <i class="nc-icon nc-spaceship"></i>
                            <p>Servicios</p>
                        </a>
                    </li>
                    <li class="">
                        <a href="{{url('/mensajes')}}">
                            <i class="nc-icon nc-email-85"></i>
                            <p>Mensajes</p>
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
                        <a class="navbar-brand" href="{{url('/')}}">Proyecto Software I</a>
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
                                    <i class="nc-icon nc-circle-10"></i>
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

<script src="{{asset('plantilla/js/core/jquery.min.js')}}"></script>
<script src="{{asset('plantilla/js/core/popper.min.js')}}"></script>
<script src="{{asset('plantilla/js/core/bootstrap.min.js')}}"></script>
<script src="{{asset('plantilla/js/plugins/perfect-scrollbar.jquery.min.js')}}"></script>

<script src="{{asset('plantilla/js/plugins/bootstrap-notify.js')}}"></script>

<script src="{{asset('plantilla/js/paper-dashboard.min.js')}}" type="text/javascript"></script>
    @stack('scripts')
</body>

</html>