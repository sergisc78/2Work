<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
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

        <title>2Work - ${ubicacio}</title>
    </head>

    <body>

        <!--- Barra de navegació -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">

            <%@include  file='/resources/html/linkLogo.html' %>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHomeToggler" aria-controls="navbarHomeToggler" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarHomeToggler">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                      
                        <c:forEach items="${opcions}" var="map">
                      
                              <c:choose>
                                    
                                    <c:when test="${map.paraula != 'Logout'}">
                                          <li class="nav-item">
                                                <a class="nav-link" href="<spring:url value='${map.url}'/>">${map.paraula}</a>
                                          </li>
                                    </c:when>
                                    
                                    <c:otherwise>
                                          <!-- Opció Logout -->
                                          <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_EMPRESA')">
                                          <li role="Presentation" class="nav-item">
                                                <a class="nav-link" href="<c:url value='${map.url}' />">${map.paraula}</a>
                                          </li>
                                          </sec:authorize>
                                    </c:otherwise>
                                    
                              </c:choose>
                        
                        </c:forEach>
                    
                </ul>
            </div>  

      </nav>
            
      <sec:authorize access="isAuthenticated()">
            <%@include  file='/resources/html/barra-usuari.html' %>
      </sec:authorize>
                    
      <section class="barra-ubicacio">
            <div class="container">
                  <p>${ubicacio}</p>
            </div>
      </section>
        
      <section class="container" id="empresa">
            <div class="card">
                  
                  <div class="card-header">
                          <h1>${empresa.nom}<h1>
                  </div>
                  
                  <div class="card-body container">
                        
                        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-2">
                              <div class="col">
                                    <h4>Adreça</h4>
                                    <p>${empresa.adreca}</p>
                                    <h4>Població</h4>
                                    <p>${empresa.poblacio}</p>
                                    <h4>Província</h4>
                                    <p>${empresa.provincia}</p>
                                    <h4>E-mail</h4>
                                    <p>${empresa.email}</p>
                                    <h4>Telèfon</h4>
                                    <p>${empresa.telefon}</p>
                              </div>
                              <div class="col">
                                    <h4>NIF</h4>
                                    <p>${empresa.dniNif}</p>
                                    <h4>Sector</h4>
                                    <p>Sector número ${empresa.sector}</p>
                                    <h4>Mida</h4>
                                    <p>${empresa.tamany} empleats/des</p>
                                    <h4>Responsable</h4>
                                    <p>${empresa.responsable}</p>
                                    <h4>Web</h4>
                                    <p>${empresa.web}</p>
                              </div>
                        </div>
                                    
                        <h4>Observacions</h4>
                        <p>${empresa.observacions}</p>
                              
                  </div>
            </div>
            
            <p>
            <!--- NOMÉS PER QUAN VENIM DE LA VISTA DE DETALL D'OFERTA --->
            <c:if test="${not empty referer}">            
                  <a href="<spring:url value='${referer}'/>" class="text-center btn btn-primary" role="button">Tornar</a>
            </c:if>
            
            <!-- NOMÉS PER USUARI TIPUS ADMIN -->
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                  <a href="<spring:url value='/ofertesAdmin/${empresa.codi}' />" class="text-center btn btn-primary" role="button">Veure ofertes de l'empresa</a>
            </sec:authorize>
            </p>
            
      </section>
         </section>
         <section id="alta-empresa" class="container-sm formulari-alta">       
                  <h3 class="text-center blau">Enregistra't com empresa</h3><br>

                  <form:form modelAttribute="formEmpresa" class="needs-validation" novalidate="novalidate" onsubmit="return validarEmp();" action="updateEmp">

                        <div class="form-group">
                              <label for="nom">Nom de l'empresa</label>
                              <form:input type="text" class="form-control" id="nom"path="nom" maxlength="20" aria-describedby="nomEmpresa" placeholder="Introdueix el nom empresa" required="required"/>
                              <div class="invalid-feedback">
                                    Introdueix el nom de l'empresa
                              </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="responsable">Responsable</label>
                            <form:input type="text" class="form-control" id="responsable" path="responsable" maxlength="50" aria-describedby="responsable" placeholder="Introdueix el nom del responsable" required="required"/>
                            <div class="invalid-feedback">
                                Introdueix el nom del responsable de l'empresa
                            </div>
                        </div>
                        
                        <div class="form-group ">
                            <label for="dniNif">NIF</label>
                            <form:input type="text" class="form-control" id="dniNif"path="dniNif" maxlength="9" aria-describedby="nif" placeholder="Introdueix el NIF" required="required"/>
                            <div class="invalid-feedback">
                                Introdueix el NIF
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="adreca">Adreça</label>
                            <form:input type="text" class="form-control" id="adreca" path="adreca" maxlength="50" placeholder="Introdueix l'adreça" required="required"/>
                            <div class="invalid-feedback">
                                Introdueix l'adreça
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="poblacio">Població</label>
                            <form:input type="text" class="form-control" id="poblacio" path="poblacio"  maxlength="50" placeholder="Introdueix la població" required="required"/>
                            <div class="invalid-feedback">
                                Introdueix la població
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="provincia">Provincia</label>
                            <form:input type="text" class="form-control" id="provincia" path="provincia"  maxlength="50" placeholder="Introdueix la província" required="required"/>
                            <div class="invalid-feedback">
                                  Introdueix la província
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="telefon">Telèfon</label>
                            <form:input type="text" class="form-control" id="telefon" path="telefon" maxlength="20" placeholder="Introdueix el telèfon" required="required"/>
                            <div class="invalid-feedback">
                                Introdueix el telèfon
                            </div>
                        </div>

                        <div class="form-group">
                              <label for="web">Web de l'empresa <small>(* Camp no obligatori)</small></label>
                            <form:input type="text" class="form-control" id="web"path="web" maxlength="30" placeholder="Introdueix la web"/>
                        </div>

                        <div class="form-group">
                            <label for="tamany">Tamany l'empresa</label>
                            <form:input type="text" class="form-control" id="tamany" path="tamany" placeholder="Introdueix el número de treballadors" />
                            <div class="invalid-feedback">
                                Introdueix el nombre de treballadors
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="email">E-mail</label>
                            <form:input type="email" class="form-control" id="email" path="email" maxlength="50" placeholder="Introdueix l'e-mail"/>
                            <div class="invalid-feedback">
                                Introdueix l'email
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="observacions">Observacions</label>
                            <form:textarea rows="8" cols="30" class="form-control" id="observacions" maxlength="500" path="observacions" placeholder="Observacions"/>
                            <div class="invalid-feedback">
                               Observacions
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pass">Password (max 8 caracters)</label>
                            <form:input type="password" class="form-control" id="pass" path="pass" pattern=".{8,}"  maxlength="8" placeholder="Mínim 8 caràcters" required="required"/>
                            <div class="invalid-feedback">
                                Introdueix password
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="cPass">Validar password</label>
                            <form:input type="password" class="form-control" id="cPass" path="cPass" maxlength="8" placeholder="Validar password" required="required"/>
                            <div class="invalid-feedback">
                                Confirma password
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="sector">Sector d'activitat de l'empresa</label>
                            <form:select class="form-control" id="select_sector" path="sector" required="required">
                                    <form:option value="">Selecciona el sector d'activitat de l'empresa</form:option>
                                    <form:options items="${llistaSectors.llista}" />
                            </form:select>

                        </div>
                    
                        <div class="form-group">
                              <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck3" required>
                                    <label class="form-check-label" for="invalidCheck3">D'acord amb la política de privacitat i protecció de dades</label>
                                    <a data-toggle="modal" data-target="#modalPrivacitat" ><span class="badge badge-pill badge-primary">Llegir</span></a>
                                    <div class="invalid-feedback">
                                          Has d'estar d'acord abans d'enviar el formulari
                                    </div>
                              </div>
                        </div>

                        <button type="submit" class="btn btn-primary">Enviar</button>
                  </form:form>
            </section>
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

        <%@include  file='/resources/html/footer.html' %>
        
    </body>
</html>