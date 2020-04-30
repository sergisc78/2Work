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
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">

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
            
      <section id="admin">
            
            <div class="container">
                  
                  <div class="row ">
                        <div class="col col-md-4">
                              <a href="<spring:url value='/candidats'/>" class="btn btn-lg btn-primary" role="button">Candidats</a>
                        </div>
                        <div class="col col-md-4">
                              <a href="<spring:url value='/empreses'/>" class="btn btn-lg btn-primary" role="button">Empreses</a>
                        </div>
                        <div class="col col-md-4">
                              <a href="<spring:url value='/ofertesAdmin'/>" class="btn btn-lg btn-primary" role="button">Ofertes</a>
                        </div>
                  </div>
                  
                   
                  <div class=row" id="numeros">
                        <ul class="list-group list-group-flush text-center">
                              <c:if test="${not empty numCandidats}">
                                    <li class="list-group-item">Hi ha ${numCandidats} candidats donats d'alta a l'aplicació</li>
                              </c:if>
                              <c:if test="${not empty numEmpreses}">
                                    <li class="list-group-item">Hi ha ${numEmpreses} empreses donades d'alta a l'aplicació</li>
                              </c:if>
                              <c:if test="${not empty numOfertes}">
                                    <li class="list-group-item">Hi ha ${numOfertes} ofertes generades a l'aplicació</li>
                              </c:if>
                        </ul>
                  </div>
                  
                  
            </div> 
      </section>

        <%@include  file='/resources/html/footer.html' %>

    </body>
</html>