<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
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
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

        <!-- Tipografia-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">

        <!-- Estils afegits -->
        <spring:url value="/resources/css/estils.css" var="estilsCSS" />
        <link href="${estilsCSS}" rel="stylesheet" />
        
        <!-- JS / Query afegit -->
        <script type="text/javascript" src="resources/js/validarForm.js"></script>
        <script type="text/javascript" src="resources/js/formularis.js"></script>        
        
        <title>2Work - ${ubicacio}</title>

    </head>

    <body>

            <!--- Barra de navegació -->
            <nav class="navbar navbar-expand-lg navbar-dark">
                  <%@include  file='/resources/html/linkLogo.html' %>
        
                  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHomeToggler" aria-controls="navbarHomeToggler" aria-expanded="false" aria-label="Toggle navigation">
                      <span class="navbar-toggler-icon"></span>
                  </button>

                  <%@include  file='/resources/html/opcions_nav.html' %>
            </nav>
            
            <sec:authorize access="isAuthenticated()">
            <%@include  file='/resources/html/barra-usuari.html' %>
            </sec:authorize>
            
            <c:if test="${not empty missatgeFeedback}">
            <section id="feedback" role="alert" class="${classeFeedback} alert  alert-dismissible fade show">
                  <div class="container">
                        <div>
                              ${missatgeFeedback}
                              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                              </button>
                        </div>
                  </div>
            </section>                  
            </c:if>
                              
            <section class="barra-ubicacio">
                  <div class="container">
                        <p>${ubicacio}</p>
                  </div>
            </section>
                    
            <section id="perfil-empresa" class="container-sm formulari-alta">       
                  <h3 class="text-center blau">Dades de l'empresa</h3><br>

                  <spring:url value="actualitza" var="act" />
                  <form:form modelAttribute="formEmpresa" class="needs-validation" novalidate="novalidate" onsubmit="return validarEmp();" action="${act}">

                        <div class="form-group">
                              <label for="nom">Nom de l'empresa</label>
                              <form:input type="text" class="form-control" id="nom"path="nom" maxlength="20" aria-describedby="nomEmpresa" value="${empresa.nom}" placeholder="${empresa.nom}" required="required" readonly="true" />
                              <div class="invalid-feedback">
                                    ${empresa.nom}
                              </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="responsable">Responsable</label>
                            <form:input type="text" class="form-control" id="responsable" path="responsable" maxlength="50" aria-describedby="responsable" value="${empresa.responsable}" placeholder="${empresa.responsable}" required="required"/>
                            <div class="invalid-feedback">
                                ${empresa.responsable}
                            </div>
                        </div>
                        
                        <div class="form-group ">
                            <label for="dniNif">NIF</label>
                            <form:input type="text" class="form-control" id="dniNif"path="dniNif" maxlength="9" aria-describedby="nif" value="${empresa.dniNif}" placeholder="${empresa.dniNif}" required="required" readonly="true"/>
                            <div class="invalid-feedback">
                                ${empresa.dniNif}
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="adreca">Adreça</label>
                            <form:input type="text" class="form-control" id="adreca" path="adreca" maxlength="50" value="${empresa.adreca}" placeholder="${empresa.adreca}" required="required"/>
                            <div class="invalid-feedback">
                                ${empresa.adreca}
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="poblacio">Població</label>
                            <form:input type="text" class="form-control" id="poblacio" path="poblacio"  maxlength="50" value="${empresa.poblacio}" placeholder="${empresa.poblacio}" required="required"/>
                            <div class="invalid-feedback">
                                ${empresa.poblacio}
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="provincia">Província</label>
                            <form:input type="text" class="form-control" id="provincia" path="provincia"  maxlength="50" value="${empresa.provincia}" placeholder="${empresa.provincia}" required="required"/>
                            <div class="invalid-feedback">
                                  ${empresa.provincia}
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="telefon">Telèfon</label>
                            <form:input type="text" class="form-control" id="telefon" path="telefon" maxlength="20" value="${empresa.telefon}" placeholder="${empresa.telefon}" required="required"/>
                            <div class="invalid-feedback">
                                ${empresa.telefon}
                            </div>
                        </div>

                        <div class="form-group">
                              <label for="web">Web de l'empresa <small>(* Camp no obligatori)</small></label>
                            <form:input type="text" class="form-control" id="web"path="web" maxlength="30" value="${empresa.web}" placeholder="${empresa.web}"/>
                        </div>

                        <div class="form-group">
                            <label for="tamany">Tamany l'empresa</label>
                            <form:input type="text" class="form-control" id="tamany" path="tamany" value="${empresa.tamany}" placeholder="${empresa.tamany}" />
                            <div class="invalid-feedback">
                                ${empresa.tamany}
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="email">E-mail</label>
                            <form:input type="email" class="form-control" id="email" path="email" maxlength="50" value="${empresa.email}" placeholder="${empresa.email}"/>
                            <div class="invalid-feedback">
                                ${empresa.email}
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="observacions">Observacions</label>
                            <form:textarea rows="8" cols="30" class="form-control" id="observacions" maxlength="500" path="observacions" value="${empresa.observacions}" placeholder="${empresa.observacions}"/>
                            <div class="invalid-feedback">
                               ${empresa.observacions}
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pass">Password (mínim 8 caràcters)</label>
                            <form:input type="password" class="form-control" id="pass" path="pass" pattern=".{8,}" placeholder="Mínim 8 caràcters" required="required"/>
                            <div class="invalid-feedback">
                                Introdueix password
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="cPass">Validar password</label>
                            <form:input type="password" class="form-control" id="cPass" path="cPass" pattern=".{8,}" placeholder="Validar password" required="required"/>
                            <div class="invalid-feedback">
                                Confirma password
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="sector">Sector d'activitat de l'empresa</label>
                            <c:set var="sectors" value="${llistaSectors.llista}" />
                            <form:select class="form-control" id="select_sector" path="sector" required="required">
                                    <form:option value="${empresa.sector}">${sectors[empresa.sector]}</form:option>
                                    <form:options items="${sectors}" />
                            </form:select>
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
                  </form:form>
                              
            </section>
            
            <section id="esborrar-empresa" class="container-sm">
                  <div class="alert alert-danger" role="alert">
                        <h4 class="alert-heading">Baixa de l'empresa</h4>
                        <p>Si prems el botó estaràs esborrant l'empresa de l'aplicació i totes les seves ofertes associades. Aquesta operació és irreversible.</p>
                        <hr>
                        <a href="<spring:url value='/empresa/${empresa.codi}/esborrar'/>" class="text-center btn btn-warning" onclick="return confirm('Segur que vols eliminar l\'empresa?');" role="button">Esborrar empresa</a>
                  </div>
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
