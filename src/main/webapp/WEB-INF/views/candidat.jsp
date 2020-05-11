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
        
      <section class="container" id="candidat">
            <div class="card">
                  
                  <div class="card-header">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                              <h1>${candidat.nom} ${candidat.cognoms}<h1>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_EMPRESA')">
                          <h1>Candidat ${candidat.email}<h1>
                        </sec:authorize>
                  </div>
                  
                  <div class="card-body container">
                        
                        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-2">
                              <div class="col">
                                    <h4>E-mail</h4>
                                    <p>${candidat.email}</p>
                                    <h4>Telèfon</h4>
                                    <p>${candidat.telefon}</p>
                              </div>
                              <div class="col">
                                    <h4>Formació</h4>
                                    <p>${formacio}</p>
                                    <h4>Habilitats addicionals</h4>
                                    <p>
                                    <c:forEach items="${habilitats}" var="hab" varStatus="loop">
                                          ${hab}
                                          <c:if test="${!loop.last}">, </c:if>
                                    </c:forEach>
                                    </p>
                              </div>
                        </div>
                                    
                        <h4>Observacions</h4>
                        <p>Aquest text té 500 caracters. Això ens pot servir de guia per la mida del text que podem admetre a la base de dades per aquest camp. Maecenas ipsum velit, consectetuer eu, lobortis ut, dictum at, dui. In rutrum. Sed ac dolor sit amet purus malesuada congue. In laoreet, magna id viverra tincidunt, sem odio bibendum justo, vel imperdiet sapien wisi sed libero. Suspendisse sagittis ultrices augue. Mauris metus. Sit amet purus malesuada congue. In laoreet, magna viverra tincidunt, sem vel imperdiet.</p>
                              
            </div>
            </div>
                                    
            <!--- NOMÉS PER QUAN VENIM DE LA VISTA DE DETALL D'OFERTA --->
            <c:if test="${not empty referer}">
            <p>
                  <a href="<spring:url value='${referer}'/>" class="text-center btn btn-primary" role="button">Tornar</a>
            </p>
            </c:if>
            
      </section>
        

        <%@include  file='/resources/html/footer.html' %>
        
    </body>
</html>