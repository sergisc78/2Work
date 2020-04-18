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
        
            <!-- JS / Query afegit -->
            <script type="text/javascript" src="resources/js/auxiliars.js"></script>

            <title>2Work - ${ubicacio}</title>
      </head>

      <body>
            
            <%@include  file='/resources/svg/icones_llistes.svg' %>

            <!--- Barra de navegació -->
            <nav class="navbar navbar-expand-lg navbar-dark">

            <%@include  file='/resources/html/linkLogo.html' %>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHomeToggler" aria-controls="navbarHomeToggler" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarHomeToggler">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                    
                      <c:forEach items="${opcions}" var="map">
                      
                        <li class="nav-item">
                              <a class="nav-link" href="<spring:url value='${map.url}'/>">${map.paraula}</a>
                        </li>
                              
                     </c:forEach>

                </ul>
            </div>

      </nav>

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
        

        <section class="container" id="candidats">

            <ul class="list-group">
                  
                  <!-- PENDENT D'IMPLEMENTAR L'AVÍS QUE MOSTRAREM QUAN NO HI HAGI CAP CANDIDAT PER ENSENYAR -->
                  
                  <c:forEach items="${candidats}" var="candidat">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                    
                              <a href="<spring:url value= '/candidat/${candidat.codi}' />"><p>${candidat.nom} ${candidat.cognoms}</p></a>
                                  
                              <a href="<spring:url value= '/esborraCandidat/${candidat.codi}' />" onclick="return confirm('Segur que vols eliminar el candidat?');" class="icona_accio">
                                    <svg><use xlink:href="#esborrar" /></svg>
                              </a>
                                    
                        </li>
                  </c:forEach>
                        
            </ul>

        </section>

        <%@include  file='/resources/html/footer.html' %>
        
    </body>

</html>