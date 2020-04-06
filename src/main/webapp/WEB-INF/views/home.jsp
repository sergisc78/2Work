<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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

        <title>2Work</title>
    </head>

    <body>

        <!--- Barra de navegació -->
        <nav class="navbar navbar-expand-lg navbar-dark">

            <a class="navbar-brand" href="#">
                  <svg id="navbarlogo" alt="logo2Work" width="280" height="48.105px" viewBox="0 0 280 48.105" enable-background="new 0 0 211.646 48.105"
   xml:space="preserve" >
                        <%@include  file='/resources/svg/logo_2work.svg' %>
                  </svg>
                <!-- <img src="${pageContext.request.contextPath}/resources/svg/logo_2work.svg" id="navbarlogo" alt="logo2Work"> -->
            </a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHomeToggler" aria-controls="navbarHomeToggler" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarHomeToggler">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value='/loginAdmin'/>">Administrador</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value='/loginCandidat'/>">Candidat</a>
                    </li>
                    <a class="nav-link" href="<spring:url value='/loginEmpresa'/>">Empresa</a>
                    </li>

                </ul>
            </div>

        </nav>

        <section>
            <div class="jumbotron">
                <div class="container ">
                    <h1> ${tagline} </h1>
                </div>
        </section> 

        <section id="cta">

            <c:forEach items="${options}" var="item">
                <div class="row">
                    <div class="col-md">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3 class="text-center">${item.desc}</h3>
                                <p class="text-center">
                                      <a href=" <spring:url value= "${item.url}" />" class="text-center btn btn-primary" role="button">Dona't d'alta
                                            <svg class="text-light" width="15" height="20" viewBox="3 0 15 20" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M6.646 3.646a.5.5 0 01.708 0l6 6a.5.5 0 010 .708l-6 6a.5.5 0 01-.708-.708L12.293 10 6.646 4.354a.5.5 0 010-.708z" clip-rule="evenodd"/> 
                                          </svg>
                                      </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>                                    

        </section>

        <section class="container" id="motius">

            <h3 class="text-center blau">Per què 2Work?</h3>

            <div class="row">

                <div class="col-lg-6">
                    <div class="media">
                        <img src="<spring:url value='/resources/svg/cor.svg'/>" class="mr-3" alt="bullet">
                        <div class="media-body">
                            <h5 class="mt-0 mb-1">No discriminem</h5>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="media">
                        <img src="<spring:url value='/resources/svg/somriure.svg'/>" class="mr-3" alt="bullet">
                        <div class="media-body">
                            <h5 class="mt-0 mb-1">Treballem per uns sous justos</h5>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                        </div>
                    </div>
                </div>

            </div>

            <div class="row">

                <div class="col-lg-6">
                    <div class="media">
                        <img src="<spring:url value='/resources/svg/euro_ok.svg'/>" class="mr-3" alt="bullet">
                        <div class="media-body">
                            <h5 class="mt-0 mb-1">Preu just</h5>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="media">
                        <img src="<spring:url value='/resources/svg/lupa_ok.svg'/>" class="mr-3" alt="bullet">
                        <div class="media-body">
                            <h5 class="mt-0 mb-1">Trobes allò que busques</h5>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                        </div>
                    </div>
                </div>

            </div>

        </section>

        <%@include  file='/resources/html/footer.html' %>
        
    </body>

</html>