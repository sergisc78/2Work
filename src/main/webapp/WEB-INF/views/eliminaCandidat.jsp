<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<html>

    <head>
        <!-- Requerits per Bootstrap -->
        
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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


        <title>2Work- ${ubicacio}</title>
    </head>


    <body>

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
            
        <section>
            <div class="jumbotron">
                <div class="container ">
                    <h1>Ens vols deixar? Ens sap greu :(</h1>
                </div>
        </section>
            
       <section class="container-sm" id="baixaCandidat">
             <div>
                  <p>Si us plau introdueix el teu correu electrònic per confirmar la baixa del perfil. <span>Aquesta operació és irreversible</span>.</p>
                  <spring:url value="executa" var="exec" />
                  <form:form method="POST" modelAttribute="formCandidat" action="${exec}">
                           <form:input type="email" class="form-control" placeholder="Email" path="email" required="true" autofocus="true"/>
                           <button type="submit" class="btn btn-warning">Confirmar</button>
                  </form:form>
                  <p><a class="small-text text-muted" href="<spring:url value='/'/>">Tornar</a></p>
             </div>
       </section>
      
      <%@include  file='/resources/html/footer.html' %>
                  
    </body>
</html>