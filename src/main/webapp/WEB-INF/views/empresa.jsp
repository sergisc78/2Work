<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
        

        <%@include  file='/resources/html/footer.html' %>
        
    </body>
</html>