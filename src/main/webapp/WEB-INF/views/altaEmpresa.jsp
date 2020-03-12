<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <!-- Requerits per Bootstrap -->
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous" />

        <!-- Bootstrap JS + jQuery -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

        <!-- Tipografia-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">

        <!-- Estils afegits -->
        <spring:url value="/resources/css/estils.css" var="estilsCSS" />
        <link href="${estilsCSS}" rel="stylesheet" />

        <title>2Work</title>


    </head>

    <body>

        <!--- Barra de navegació -->
        <nav class="navbar navbar-expand-lg navbar-dark">

            <a class="navbar-brand" href="#">
                <img src="${pageContext.request.contextPath}/resources/svg/logo_2work.svg" id="navbarlogo" alt="logo2Work">
            </a>
        
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHomeToggler" aria-controls="navbarHomeToggler" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarHomeToggler">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value='/'/>">Inici</a>
                    </li>
                </ul>
            </div>  


        </nav>

        <section>
            <div class="jumbotron">
                <div class="container ">
                    <h1> ${tagline} </h1>
                </div>
        </section>
        
                
        <section class="container-sm formulari-alta">       
            <h3 class="text-center blau">Enregistra't com empresa</h3><br>

                <form class="needs-validation" novalidate>

                    <div class="form-group">
                        <label for="nom">Nom de l'empresa</label>
                        <input type="text" class="form-control" id="nom" aria-describedby="emailHelp" placeholder="Introdueix el nom" required>
                        <div class="invalid-feedback">
                            Introdueix el nom de l'empresa
                        </div>
                    </div>

                    <div class="form-group ">
                        <label for="nom">NIF</label>
                        <input type="text" class="form-control" id="nom" aria-describedby="emailHelp" placeholder="Introdueix el NIF" required>
                        <div class="invalid-feedback">
                            Introdueix el NIF
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="adreça">Adreça</label>
                        <input type="text" class="form-control" id="adreça"  placeholder="Introdueix l'adreça" required>
                        <div class="invalid-feedback">
                            Introdueix l'adreça
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="telefon">Telèfon</label>
                        <input type="text" class="form-control" id="telefon"  placeholder="Introdueix el telèfon" required>
                        <div class="invalid-feedback">
                            Introdueix el telèfon
                        </div>
                    </div>

                    <div class="form-group">
                          <label for="mail">Web de l'empresa <small>(* Camp no obligatori)</small></label>
                        <input type="email" class="form-control" id="mail" placeholder="Introdueix la web">

                    </div>
                    <div class="form-group">
                        <label for="tamany">Tamany l'empresa</label>
                        <input type="text" class="form-control" id="mail" placeholder="Introdueix el número de treballadors" required>
                        <div class="invalid-feedback">
                            Introdueix el tamany
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="mail">E-mail</label>
                        <input type="email" class="form-control" id="mail" placeholder="Introdueix l'e-mail" required>
                        <div class="invalid-feedback">
                            Introdueix l'email
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="pass1">Password</label>
                        <input type="password" class="form-control" id="pass" pattern=".{8,}"  placeholder="Mínim 8 caràcters" required>
                        <div class="invalid-feedback">
                            Introdueix password
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="pass2">Validar password</label>
                        <input type="password" class="form-control" id="cpass" placeholder="Validar password" required>
                        <div class="invalid-feedback">
                            Confirma password
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="exampleFormControlSelect1">Sector laboral de l'empresa</label>
                        <select class="form-control" id="sectorLaboral" required>
                            <option value="" selected="true" selected="false"></option>
                            <option>Activitats físiques i esportives</option>
                            <option>Adminstració i gestió</option>
                            <option>Agricultura i ramaderia</option>
                            <option>Indústria alimentària</option>
                            <option>Comerç</option>
                            <option>Construcció</option>
                            <option>Disseny i arts gràfiques</option>
                            <option>Educació</option>
                            <option>Finances</option>
                            <option>Informàtica</option>
                            <option>Enyingeria</option>
                            <option>Legal</option>
                            <option>Màrqueting i comunicació</option>
                            <option>Recursos humans</option>
                            <option>Sanitari</option>
                            <option>Turisme</option>
                            <option>Hostaleria i turisme</option>
                            <option>Mediambiental</option>
                            <option>Pesca i agricultura</option>
                            <option>Química</option>
                            <option>Estètica i perruqueria</option>
                        </select>
                    </div>
                    
                     <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck3" required>
                        <label class="form-check-label" for="invalidCheck3">
                            D´acord amb la política de privacitat i protecció de dades
                        </label>
                        <div class="invalid-feedback">
                            Has d´estar d´acord, abans d´enviar el formulari
                        </div>
                    </div>
                </div>

                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
        </section>

        <footer class="footer">
            <div class="container">
                <span class="text-muted">2Work Copyright 2020</footer>
            </div>
        </footer>

        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict';
                window.addEventListener('load', function () {
                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.getElementsByClassName('needs-validation');
                    // Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();

        </script>
    </body>
</html>
