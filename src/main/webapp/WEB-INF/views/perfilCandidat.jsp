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
        <script type="text/javascript" src="<spring:url value="/resources/js/validarForm.js"/>"></script>
        <script type="text/javascript" src="<spring:url value="/resources/js/formularis.js"/>"></script>
        
        <!-- El jQuery extern utilitzarà aquestes variables -->
        <script type="text/javascript">
            var habilitatsCandidat= ${candidat.habilitats};
            var codiCandidat= ${candidat.codi};
        </script>
        
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
        
            <c:if test="${not empty feedback}">
                  <%@include  file='/resources/html/feedback.html' %> 
            </c:if>
                  
            <section class="barra-ubicacio">
                  <div class="container">
                        <p>${ubicacio}</p>
                  </div>
            </section>

            <section id="perfil-candidat" class="container-sm formulari-alta">        
                  <h3 class="text-center blau">Dades del candidat</h3><br>

                  <form:form id="formulariPerfilCandidat" modelAttribute="formCandidat" class="needs-validation" novalidate="novalidate" onsubmit="return validarCand();" method="post" action="actualitza">
               
                <div class="form-group">
                    <label for="nom">Nom</label>
                    <form:input type="text" class="form-control" id="nom" path="nom" maxlength="20" aria-describedby="nom" value="${candidat.nom}" placeholder="${candidat.nom}" required="required"/>
                    <div class="invalid-feedback">
                        ${candidat.nom}
                    </div>
                </div>

                <div class="form-group">
                    <label for="cognoms">Cognoms</label>
                    <form:input type="text" class="form-control" id="cognoms" path="cognoms" maxlength="50" aria-describedby="cognoms" value="${candidat.cognoms}" placeholder="${candidat.cognoms}" required="required"/>
                    <div class="invalid-feedback">
                        ${candidat.cognoms}
                    </div>
                </div>

                <div class="form-group">
                    <label for="dniNif">DNI</label>
                    <form:input type="text" class="form-control" id="dniNif" path="dniNif" maxlength="9" aria-describedby="dni" value="${candidat.dniNif}" placeholder="${candidat.dniNif}" required="required"/>
                    <div class="invalid-feedback">
                        ${candidat.dniNif}
                    </div>
                </div>

                <div class="form-group">
                    <label for="dataNaix">Data de naixement</label>
                    <form:input type="date" class="form-control" id="dataNaix" value="${candidat.dataNaix}" path="dataNaix" required="required"/>
                    <div class="invalid-feedback">
                        ${candidat.dataNaix}
                    </div>
                </div>

                <div class="form-group">
                    <label for="adreca">Adreça</label>
                    <form:input type="text" class="form-control" id="adreca" path="adreca" maxlength="50" value="${candidat.adreca}" placeholder="${candidat.adreca}" required="required"/>
                    <div class="invalid-feedback">
                        ${candidat.adreca}
                    </div>
                </div>
                <div class="form-group">
                    <label for="poblacio">Població</label>
                    <form:input type="text" class="form-control" id="poblacio" path="poblacio"  maxlength="50" value="${candidat.poblacio}" placeholder="${candidat.poblacio}" required="required"/>
                    <div class="invalid-feedback">
                        ${candidat.poblacio}
                    </div>
                </div>
                <div class="form-group">
                    <label for="provincia">Província</label>
                    <form:input type="text" class="form-control" id="provincia" path="provincia" maxlength="50" value="${candidat.provincia}" placeholder="${candidat.provincia}" required="required"/>
                    <div class="invalid-feedback">
                        ${candidat.provincia}
                    </div>
                </div>
                <div class="form-group">
                    <label for="telefon">Telèfon</label>
                    <form:input type="text" class="form-control" id="telefon" path="telefon" maxlength="12" value="${candidat.telefon}" placeholder="${candidat.telefon}" required="required"/>
                    <div class="invalid-feedback">
                        ${candidat.telefon}
                    </div>
                </div>

                <div class="form-group">
                    <label for="email">E-mail</label>
                    <form:input type="email" class="form-control" id="email" path="email" maxlength="50" value="${candidat.email}" placeholder="${candidat.email}" />
                    <div class="invalid-feedback">
                        ${candidat.email}
                    </div>
                </div>
                <div class="form-group">
                    <label for="observacions">Observacions</label>
                    <form:textarea rows="8" cols="30" class="form-control" id="observacions" path="observacions" maxlength="500" value="${candidat.observacions}" placeholder="${candidat.observacions}"/>
                    <div class="invalid-feedback">
                       ${candidat.observacions}
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
                    <label for="cPass">Confirmar password</label>
                    <form:input type="password" class="form-control" id="cPass" path="cPass" pattern=".{8,}" placeholder="Torna a escriure el password" required="required"/>
                    <div class="invalid-feedback">
                        Confirma el password
                    </div>
                </div>               

                  <div class="form-group">
                        <label for="formacio">Formació</label>
                        <form:select class="form-control" id="select_formacio" path="formacio" required="required">
                              <c:set var="llistaF" value="${llistaFormacions.llista}"/>
                              <form:option value="${candidat.formacio}">${llistaF[candidat.formacio].nomFormacio}</form:option>
                              <form:options items="${llistaF}" itemValue="codiFormacio" itemLabel="nomFormacio" />
                        </form:select>
                </div>
                    
                <div class="form-group">
                    <label for="ocupacio">Ocupació</label>
                    <form:select class="form-control" id="select_ocupacio" path="ocupacio" required="required">
                              <c:set var="llistaO" value="${llistaOcupacions.llista}"/>
                              <form:option selected="true" value="${candidat.ocupacio}">${llistaO[candidat.ocupacio-1].nomOcupacio}</form:option>
                              <form:options items="${llistaO}" itemValue="codiOcupacio" itemLabel="nomOcupacio" />
                    </form:select>
                </div>
                    
                <div class="form-group">
                    <label for="habilitats">Habilitats addicionals </label>
                    <form:select class="form-control" id="select_habilitats" path="habilitats">
                    </form:select>
                    <small class="text-muted">Pots seleccionar diverses de les habilitats disponibles: selecciona una opció i amb la
                    tecla "ctrl" premuda selecciona la resta habilitats.</small>
                </div>
                    
                <div id="experiencia" class="form-group" >
                        <label for="experiencia">Experiència</label>
                        
                        <button type="button" class="btn btn-outline-primary btn-sm float-right" id="boto_afegir_camps">Afegir</button>
                        
                        <div id="avis_zero_items_exp" class="alert-primary">No hi ha cap item d'experiència definit</div>
                        
                        <div class="row item_experiencia" id="exp_0">
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_temps_0" path="experiencies[0].anys" placeholder="Temps" required="required"/>
                              </div>
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_empresa_0" path="experiencies[0].nomEmpresa" placeholder="Empresa" required="required"/>
                              </div>
                              <div class="col-sm-12">
                                    <form:input type="text" class="form-control" id="exp_descripcio_0" path="experiencies[0].descripcio" placeholder="Descripció" required="required"/>
                              </div>
                              <a class="esborrar_item"><small class="text-muted">Esborrar item d'experiència</small></a>
                        </div>
                              
                        <div class="row item_experiencia" id="exp_1">
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_temps_1" path="experiencies[1].anys" placeholder="Temps" />
                              </div>
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_empresa_1" path="experiencies[1].nomEmpresa" placeholder="Empresa" />
                              </div>
                              <div class="col-sm-12">
                                    <form:input type="text" class="form-control" id="exp_descripcio_1" path="experiencies[1].descripcio" placeholder="Descripció" />
                              </div>
                              <a class="esborrar_item"><small class="text-muted">Esborrar item d'experiència</small></a>
                        </div>
                              
                        <div class="row item_experiencia" id="exp_2">
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_temps_2" path="experiencies[2].anys" placeholder="Temps" />
                              </div>
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_empresa_2" path="experiencies[2].nomEmpresa" placeholder="Empresa" />
                              </div>
                              <div class="col-sm-12">
                                    <form:input type="text" class="form-control" id="exp_descripcio_2" path="experiencies[2].descripcio" placeholder="Descripció" />
                              </div>
                              <a class="esborrar_item"><small class="text-muted">Esborrar item d'experiència</small></a>
                        </div>
                              
                        <div class="row item_experiencia" id="exp_3">
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_temps_3" path="experiencies[3].anys" placeholder="Temps" />
                              </div>
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_empresa_3" path="experiencies[3].nomEmpresa" placeholder="Empresa" />
                              </div>
                              <div class="col-sm-12">
                                    <form:input type="text" class="form-control" id="exp_descripcio_3" path="experiencies[3].descripcio" placeholder="Descripció" />
                              </div>
                              <a class="esborrar_item"><small class="text-muted">Esborrar item d'experiència</small></a>
                        </div>
                        
                        <div class="row item_experiencia" id="exp_4">
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_temps_4" path="experiencies[4].anys" placeholder="Temps" />
                              </div>
                              <div class="col-sm-6">
                                    <form:input type="text" class="form-control" id="exp_empresa_4" path="experiencies[4].nomEmpresa" placeholder="Empresa" />
                              </div>
                              <div class="col-sm-12">
                                    <form:input type="text" class="form-control" id="exp_descripcio_4" path="experiencies[4].descripcio" placeholder="Descripció" />
                              </div>
                              <a class="esborrar_item"><small class="text-muted">Esborrar item d'experiència</small></a>
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