<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
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

            <!-- Modal info política de protecció dels drets candidat-->
            <div class="modal fade" id="modalProteccioCandidat" tabindex="-1" role="dialog" aria-labelledby="proteccioDadesPrivacitat" aria-hidden="true">
                  <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                              <div class="modal-header">
                                    <h5 class="modal-title">Política de protecció de drets del candidat</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                          <span aria-hidden="true">&times;</span>
                                    </button>
                              </div>
                              <div class="modal-body">
                                    <%@include  file='/resources/html/proteccioDretsCandidat.html' %>
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
                        <c:forEach items="${opcions}" var="map">
                      
                              <c:choose>
                                    
                                    <c:when test="${map.paraula != 'Logout'}">
                                          <li class="nav-item">
                                                <a class="nav-link" href="<spring:url value='${map.url}${map.usuari}'/>">${map.paraula}</a>
                                          </li>
                                    </c:when>
                                    
                                    <c:otherwise>
                                          <!-- Opció Logout -->
                                          <sec:authorize access="hasRole('ROLE_EMPRESA')">
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

            <section class="barra-ubicacio">
                  <div class="container">
                        <p>${ubicacio}</p>
                  </div>
            </section>

            <section id="alta-oferta" class="container-sm formulari-alta">        
            
                  <form:form id="formulariAltaOferta" modelAttribute="formOferta" class="needs-validation" novalidate="novalidate" onsubmit="return validarOferta();" method="post" action="executaAltaOferta">
               
                  <div class="form-group">
                        <label for="titolOferta">Títol de l'oferta</label>
                        <form:input type="text" class="form-control" id="titolOferta" path="titolOferta" aria-describedby="titolOferta" placeholder="Introdueix el títol de l'oferta" required="required"/>
                        <div class="invalid-feedback">
                              Introdueix el titol de l'oferta
                        </div>
                </div>
                        
                <div class="form-group">
                    <label for="ocupacio">Ocupació</label>
                    <form:select class="form-control" id="select_ocupacio" path="ocupacio" required="required">
                              <form:option value="">Selecciona l'ocupació associada a l'oferta</form:option>
                              <form:options items="${llistaOcupacions.llista}" itemValue="codiOcupacio" itemLabel="nomOcupacio" />
                        </form:select>
                </div>
                        
                <div class="form-group">
                    <label for="poblacio">Població</label>
                    <form:input type="text" class="form-control" id="poblacio" path="poblacio"  placeholder="Introdueix la població" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix la població
                    </div>
                </div>

                <div class="form-group">
                    <label for="provincia">Provincia</label>
                    <form:input type="text" class="form-control" id="provincia" path="provincia"  placeholder="Introdueix la província" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix la provincia
                    </div>
                </div>

                <div class="form-group">
                    <label for="sou">Sou brut anual</label>
                    <form:input type="text" class="form-control" id="sou" path="sou"  placeholder="Introdueix el sou brut anual" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix el sou brut anual
                    </div>
                </div>
                    
                <div class="form-group">
                    <label for="horari">Horari</label>
                    <form:input type="text" class="form-control" id="horari" path="horari"  placeholder="Introdueix l'horari de feina" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix l'horari de feina
                    </div>
                </div>                    
                    
                <div class="form-group">
                    <label for="tipusContracte">Tipus de contracte</label>
                    <form:select class="form-control" id="select_tipusContracte" path="tipusContracte" required="required">
                              <form:option value="">Selecciona el tipus de contracte ofert</form:option>
                              <form:options items="${llistaTipusContracte}" />
                        </form:select>
                </div>
                    
                <div class="form-group">
                        <label for="formacio">Formació requerida</label>
                        <form:select class="form-control" id="select_formacio" path="formacio" required="required">
                              <form:option value="">Selecciona el nivell de formació requerida</form:option>
                              <form:options items="${llistaFormacions.llista}" itemValue="codiFormacio" itemLabel="nomFormacio" />
                        </form:select>
                </div>
                    
                <div class="form-group">
                    <label for="descripcio">Descripció</label>
                    <form:textarea rows="8" cols="30" class="form-control" id="descripcio" path="descripcio" placeholder="Descripció"/>
                    <div class="invalid-feedback">
                       Descripció
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="habilitats">Habilitats addicionals requerides</label>
                    <form:select class="form-control" id="select_habilitats" path="habilitats">
                    </form:select>
                    <small class="text-muted">Pots seleccionar diverses de les habilitats disponibles</small>
                </div>
                                       
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck3" required>
                        <label class="form-check-label" for="invalidCheck3">D'acord amb la política de política de protecció del candidat de 2Work</label>
                        <a data-toggle="modal" data-target="#modalProteccioCandidat" ><span class="badge badge-pill badge-primary">Llegir</span></a>
                        <div class="invalid-feedback">
                            Has d'estar d'acord, abans d'enviar el formulari
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