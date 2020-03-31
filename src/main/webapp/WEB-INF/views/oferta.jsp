<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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

        <title>Detall de l'oferta</title>
    </head>

    <body>

        <!--- Barra de navegació -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">

            <a class="navbar-brand" href="<spring:url value='/'/>">
                <img src="${pageContext.request.contextPath}/resources/svg/logo_2work.svg" id="navbarlogo" alt="logo2Work">
            </a>

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
                    
        <section class="barra-ubicacio">
              <div class="container">
                  <p>${ubicacio}</p>
              </div>
        </section>
                    
        <section class="container" id="oferta">
              <div class="card">
                    
                    <div class="card-header">
                          <h1>${oferta.titolOferta}<h1>
                    </div>
                    
                    <div class="card-body container">
                          
                          <h4>Estat</h4>
                          <p>${oferta.estat}</p>
                          
                          <h4>Descripció</h4>
                          <p>${oferta.descripcio}</p>
                          
                          <div class="row row-cols-1 row-cols-md-2 row-cols-lg-2">
                                
                                <div class="col">
                                    <h4>Empresa</h4>
                                    <p>${oferta.nifEmpresa}</p>
                                    <h4>Localitat</h4>
                                    <p>${oferta.localitat}</p>
                                    <h4>Tipus de contracte</h4>
                                    <p>${oferta.tipusContracte}</p>
                                    <h4>Horari</h4>
                                    <p>${oferta.torn}</p>
                                    <h4>Remuneració anual</h4>
                                    
                                    <p><fmt:formatNumber groupingUsed = "false" value="${oferta.sou}"/> €</p>
                                </div>
                                
                                <div class="col">
                                      <h4>Formació requerida</h4>
                                      <p>${oferta.formacio}</p>
                                      <h4>Habilitats addicionals requerides</h4>
                                      <p>
                                            <c:forEach items="${oferta.habilitats}" var="map" varStatus="loop">
                                                  ${map.nomHab}
                                                  <c:if test="${!loop.last}">, </c:if>
                                            </c:forEach>
                                      </p>
                                </div>
                                
                          </div>
                          
                    </div>
                    
              </div>
                                      
              <p>
                        <a href="#" class="text-center btn btn-primary" role="button">Inscriure'm a l'oferta</a>
                        <a href="#" class="text-center btn btn-warning" role="button">Denunciar l'oferta</a>
             </p>
              
        </section>


        <%@include  file='/resources/html/footer.html' %>
        
    </body>
</html>