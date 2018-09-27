<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="{{asset('img/apple-icon.png')}}">
    <link rel="icon" type="image/png" href="{{asset('img/favicon.png')}}">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>
        Login
    </title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <!-- CSS Files -->
    <link href="{{asset('css/terceros/fontawesome/css/all.css')}}" rel="stylesheet" />

    <link href="{{asset('css/terceros/bootstrap/bootstrap.min.css')}}" rel="stylesheet" />
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #17a2b8;
        }
        #login .container #login-row #login-column #login-box {
            margin-top: 5em;
            border: 1px solid #ffffff;
            background-color: #ffffff;
            border-radius: 2em;
            margin-bottom: 5em;
        }
        #login .container #login-row #login-column #login-box #login-form {
            padding: 20px;
        }
    </style>
</head>

<body>
<div id="login">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" method="POST" action="{{ route('login') }}">
                        {{ csrf_field() }}
                        <h3 class="text-center text-info">Login</h3>
                        <div class="form-group">
                            <label for="email" class="text-info">Email:</label><br>
                            <input type="email" name="email" id="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Password:</label><br>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-info btn-block" type="submit">Login</button>
                        </div>
                        <div class="form-group">
                            <a href="{{url('/register')}}">
                                <button class="btn btn-default btn-block" type="button">Registrarse</button>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--   Core JS Files   -->
<script src="{{asset('js/terceros/jquery/jquery.min.js')}}"></script>
<script src="{{asset('js/terceros/bootstrap/popper.min.js')}}"></script>
<script src="{{asset('js/terceros/bootstrap/bootstrap.min.js')}}"></script>
</body>

</html>