<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
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
        <script type="text/javascript" src="resources/js/validarForm.js"></script>
        
        <title>2Work</title>

    </head>

    <body>
          
      <!-- Modal info política de protecció de dades i privacitat-->
      <div class="modal fade" id="modalPrivacitat" tabindex="-1" role="dialog" aria-labelledby="proteccioDadesPrivacitat" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Política de protecció de dades i privacitat</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <%@include  file='/resources/html/proteccioDadesPrivacitat.html' %>
            </div>
          </div>
        </div>
      </div>

        <!--- Barra de navegació -->
        <nav class="navbar navbar-expand-lg navbar-dark">

            <%@include  file='/resources/html/linkLogo.html' %>
        
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
        
                
        <section id="alta-empresa" class="container-sm formulari-alta">       
            <h3 class="text-center blau">Enregistra't com empresa</h3><br>

                 <form:form modelAttribute="formEmpresa" class="needs-validation" novalidate="novalidate" onsubmit="return validarEmp();" action="${act}">
                    <div class="form-group">
                    <label for="codi">Codi Empresa</label>
                    <form:input type="text" class="form-control" id="codi"path="codi" aria-describedby="codi" placeholder="NO OMPLIR" />
                    <div class="invalid-feedback">
                        NO OMPLIR 
                    </div>
                </div> 
                    <div class="form-group">
                        <label for="nom">Nom de l'empresa</label>
                        <form:input type="text" class="form-control" id="nom"path="nom" aria-describedby="nomEmpresa" placeholder="Introdueix el nom empresa" required="required"/>
                        <div class="invalid-feedback">
                            Introdueix el nom de l'empresa
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="responsable">Responsable</label>
                        <form:input type="text" class="form-control" id="responsable" path="responsable" aria-describedby="responsable" placeholder="Introdueix el nom del responsable" required="required"/>
                        <div class="invalid-feedback">
                            Introdueix el nom del responsable de l'empresa
                        </div>
                    </div>
                    <div class="form-group ">
                        <label for="dniNif">NIF</label>
                        <form:input type="text" class="form-control" id="dniNif"path="dniNif" aria-describedby="nif" placeholder="Introdueix el NIF" required="required"/>
                        <div class="invalid-feedback">
                            Introdueix el NIF
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="adreca">Adreça</label>
                        <form:input type="text" class="form-control" id="adreca" path="adreca" placeholder="Introdueix l'adreça" required="required"/>
                        <div class="invalid-feedback">
                            Introdueix l'adreça
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ciutat">Ciutat</label>
                        <form:input type="text" class="form-control" id="ciutat" path="ciutat"  placeholder="Introdueix la ciutat" required="required"/>
                        <div class="invalid-feedback">
                            Introdueix la ciutat
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="provincia">Provincia</label>
                        <form:input type="text" class="form-control" id="provincia" path="provincia"  placeholder="Introdueix la provincia" required="required"/>
                        <div class="invalid-feedback">
                            Introdueix la provincia
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="telefon">Telèfon</label>
                        <form:input type="text" class="form-control" id="telefon" path="telefon" placeholder="Introdueix el telèfon" required="required"/>
                        <div class="invalid-feedback">
                            Introdueix el telèfon
                        </div>
                    </div>

                    <div class="form-group">
                          <label for="web">Web de l'empresa <small>(* Camp no obligatori)</small></label>
                        <form:input type="text" class="form-control" id="web"path="web" placeholder="Introdueix la web"/>

                    </div>
                    <div class="form-group">
                        <label for="tamany">Tamany l'empresa</label>
                        <form:input type="text" class="form-control" id="tamany" path="tamany" placeholder="Introdueix el número de treballadors" />
                        <div class="invalid-feedback">
                            Introdueix el nommbre de treballadors
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="email">E-mail</label>
                        <form:input type="email" class="form-control" id="email" path="email" placeholder="Introdueix l'e-mail"/>
                        <div class="invalid-feedback">
                            Introdueix l'email
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="observacions">Observacions</label>
                        <form:textarea rows="8" cols="30" class="form-control" id="observacions" path="observacions"placeholder="Observacions"/>
                        <div class="invalid-feedback">
                           Observacions
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pass">Password</label>
                        <form:input type="password" class="form-control" id="pass" path="pass" pattern=".{8,}"  placeholder="Mínim 8 caràcters" required="required"/>
                        <div class="invalid-feedback">
                            Introdueix password
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="cPass">Validar password</label>
                        <form:input type="password" class="form-control" id="cPass" path="cPass"placeholder="Validar password" required="required"/>
                        <div class="invalid-feedback">
                            Confirma password
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sector">Sector laboral de l'empresa</label>
                        <form:select class="form-control" id="sector" path="sector" required="required">
                            <form:option value="0"> --SELECT--</form:option>
                            <form:option value="1" label="Activitats físiques i esportives"/>
                            <form:option value="2" label="Adminstració i gestió"/>
                            <form:option value="3" label="Agricultura i ramaderia"/>
                            <form:option value="4" label="Indústria alimentària"/>
                            <form:option value="5" label="Comerç"/>
                            <form:option value="6" label="Construcció"/>
                            <form:option value="7" label="Disseny i arts gràfiques"/>
                            <form:option value="8" label="Educació"/>
                            <form:option value="9" label="Finances"/>
                            <form:option value="10" label="Informàtica"/>
                            <form:option value="11" label="Enyingeria"/>
                            <form:option value="12" label="Legal"/>
                            <form:option value="13" label="Màrqueting i comunicació"/>
                            <form:option value="14" label="Recursos humans"/>
                            <form:option value="15" label="Sanitari"/>
                            <form:option value="16" label="Turisme"/>
                            <form:option value="17" label="Hostaleria i turisme"/>
                            <form:option value="18" label="Mediambiental"/>
                            <form:option value="19" label="Pesca i agricultura"/>
                            <form:option value="20" label="Química"/>
                            <form:option value="21" label="Estètica i perruqueria"/>
                        </form:select>
                    </div>
                    
                     <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck3" required>
                        <label class="form-check-label" for="invalidCheck3">D'acord amb la política de privacitat i protecció de dades</label>
                        <a data-toggle="modal" data-target="#modalPrivacitat" ><span class="badge badge-pill badge-primary">Llegir</span></a>
                        <div class="invalid-feedback">
                            Has d´estar d´acord, abans d´enviar el formulari
                        </div>
                    </div>
                </div>

                    <button type="submit" class="btn btn-primary">Enviar</button>
               </form:form>
        </section>

        <%@include  file='/resources/html/footer.html' %>

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
