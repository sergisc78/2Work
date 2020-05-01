<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> -->
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

            <%@include  file='/resources/svg/icones_llistes.svg' %>
            <%@include  file='/resources/svg/icona_avis.svg' %>
          
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
        

            <section class="container" id="ofertes-empresa">
                
            <c:choose>
            <c:when test="${not empty ofertes}">
            
                  <ul class="list-group">
                      <c:forEach items="${ofertes}" var="oferta">
                      <li class="list-group-item d-flex justify-content-between align-items-center">
                            
                                  <!-- PENDENT AFEGIR-HI L'ENLLAÇ -->
                                  <a href="#" class="icona_accio">
                                        <svg><use xlink:href="#esborrar" /></svg>
                                  </a>
                                  
                                  <sec:authorize access="hasRole('ROLE_USER')">
                                        <a href="<spring:url value= "/oferta/${oferta.codiOferta}" />" class="flex-grow-1"><p>${oferta.titolOferta}</p></a>
                                  </sec:authorize>
                                        
                                  <sec:authorize access="hasRole('ROLE_EMPRESA')">
                                        <a href="<spring:url value= "/ofertaPropietari/${oferta.codiOferta}" />" class="flex-grow-1"><p>${oferta.titolOferta}</p></a>
                                  </sec:authorize>
                                        
                                  <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="<spring:url value= "/ofertaAdmin/${oferta.codiOferta}" />" class="flex-grow-1"><p>${oferta.titolOferta}</p></a>
                                  </sec:authorize>

                                 <span class="badge badge-primary badge-pill">${oferta.estat}</span>
                            
                      </li>
                      </c:forEach>
                </ul>                    
            </c:when>    
            <c:otherwise>
                   
                  <div class="alert alert-danger" role="alert">
                        <svg class="icona_no_dades"><use xlink:href="#triangle_avis" /></svg>No hi ha dades per mostrar.
                  </div>
       
            </c:otherwise>
            </c:choose>
                       
        </section>

        <%@include  file='/resources/html/footer.html' %>
        
    </body>

</html>