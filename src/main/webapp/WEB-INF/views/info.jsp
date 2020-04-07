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

    <body id="info_body">

        <!--- Barra de navegació -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">

            <%@include  file='/resources/html/linkLogo.html' %>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHomeToggler" aria-controls="navbarHomeToggler" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarHomeToggler">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                         <a class="nav-link" href="<spring:url value='/'/>">Inici</a>
                    </li>
                </ul>
            </div>  

        </nav>
                    
        <section class="container" id="info">
              
              <section class="container-sm" id="nosaltres">
                  <h3 class="text-center blau">Informació sobre nosaltres</h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla ac elit sed nisl sollicitudin facilisis. Cras non luctus ante. Mauris mollis fermentum turpis, sit amet cursus libero egestas eu. Integer euismod odio sit amet erat scelerisque vulputate. Duis eu ante cursus, fermentum ipsum in, cursus augue. Praesent hendrerit maximus purus, eu porta libero bibendum ut. Cras suscipit mi vel consequat consequat. Maecenas faucibus non massa sit amet luctus. Fusce congue augue non arcu cursus iaculis. Proin nisl est, sollicitudin nec condimentum sed, pharetra dignissim nisl.</p>
                  <p>Proin ut nisl id felis iaculis blandit. Sed ac posuere nibh. Praesent lobortis, enim ac interdum tempor, purus ante blandit mauris, sit amet malesuada nunc purus eu tortor. Maecenas aliquet libero sed dolor suscipit ultricies. Aliquam iaculis orci sed est commodo vulputate. Donec laoreet id nunc id scelerisque. Phasellus lorem nunc, semper sit amet ipsum a, suscipit porttitor nisi. Ut feugiat aliquet sem sit amet ornare. Curabitur rhoncus arcu sed eros bibendum, id facilisis sem finibus.</p>
                  <p>Ut mauris enim, tempor eu dui eget, malesuada lacinia justo. Nam accumsan maximus diam eu faucibus. Fusce non ipsum convallis nibh lacinia malesuada. Phasellus scelerisque, nibh at maximus venenatis, quam elit hendrerit est, at interdum ligula turpis vel justo. Ut sagittis commodo eros quis tempor. Aliquam a enim nulla. Donec faucibus, nisi eu pulvinar luctus, massa dolor vestibulum odio, quis tincidunt leo nisl vehicula sem. Nulla consequat orci eget sem ultricies sollicitudin. In condimentum, metus quis vestibulum posuere, lectus metus pharetra dui, et suscipit mi magna ut justo. Morbi sit amet tortor arcu. Morbi a felis ut elit commodo scelerisque id pharetra tellus. Sed posuere arcu tristique elit sollicitudin, ut eleifend diam tristique.</p>
                  <p> Aenean lacinia finibus pulvinar. Aliquam erat volutpat. Donec rhoncus lobortis dapibus. Curabitur iaculis magna at tempor eleifend. Sed a nulla quis odio venenatis euismod. Sed vitae diam lorem. Donec et mauris molestie, bibendum sapien eget, cursus sapien.</p>
                  <p>Proin luctus, justo elementum semper pharetra, lacus nunc dapibus ante, ut gravida tellus erat at justo. Duis lacinia, justo vitae fermentum consequat, nunc quam euismod metus, non mollis mi arcu a metus. Donec ut mi augue. Sed consectetur porta velit, quis faucibus elit luctus in. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce sagittis metus at egestas auctor. Etiam eget metus tellus. In pellentesque pellentesque feugiat. Pellentesque pharetra odio sed congue tincidunt. Cras at risus odio. Sed ultricies suscipit tempus. Proin euismod blandit diam, non malesuada augue blandit quis. Cras et risus quis dolor cursus tempor. Sed justo felis, ullamcorper sit amet sem et, eleifend faucibus eros. Vivamus feugiat felis vitae felis aliquet, sit amet rhoncus sem dapibus.</p>
              </section>
              
              <section class="container-sm" id="newsletter">
                  <h3 class="text-center blau">Dona't d'alta a la newsletter de 2Work</h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla ac elit sed nisl sollicitudin facilisis. Cras non luctus ante. Mauris mollis fermentum turpis, sit amet cursus libero egestas eu. Integer euismod odio sit amet erat scelerisque vulputate. Duis eu ante cursus, fermentum ipsum in, cursus augue. Praesent hendrerit maximus purus, eu porta libero bibendum ut. Cras suscipit mi vel consequat consequat. Maecenas faucibus non massa sit amet luctus. Fusce congue augue non arcu cursus iaculis. Proin nisl est, sollicitudin nec condimentum sed, pharetra dignissim nisl.</p>
                  <p>Proin ut nisl id felis iaculis blandit. Sed ac posuere nibh. Praesent lobortis, enim ac interdum tempor, purus ante blandit mauris, sit amet malesuada nunc purus eu tortor. Maecenas aliquet libero sed dolor suscipit ultricies. Aliquam iaculis orci sed est commodo vulputate. Donec laoreet id nunc id scelerisque. Phasellus lorem nunc, semper sit amet ipsum a, suscipit porttitor nisi. Ut feugiat aliquet sem sit amet ornare. Curabitur rhoncus arcu sed eros bibendum, id facilisis sem finibus.</p>
                  <p>Ut mauris enim, tempor eu dui eget, malesuada lacinia justo. Nam accumsan maximus diam eu faucibus. Fusce non ipsum convallis nibh lacinia malesuada. Phasellus scelerisque, nibh at maximus venenatis, quam elit hendrerit est, at interdum ligula turpis vel justo. Ut sagittis commodo eros quis tempor. Aliquam a enim nulla. Donec faucibus, nisi eu pulvinar luctus, massa dolor vestibulum odio, quis tincidunt leo nisl vehicula sem. Nulla consequat orci eget sem ultricies sollicitudin. In condimentum, metus quis vestibulum posuere, lectus metus pharetra dui, et suscipit mi magna ut justo. Morbi sit amet tortor arcu. Morbi a felis ut elit commodo scelerisque id pharetra tellus. Sed posuere arcu tristique elit sollicitudin, ut eleifend diam tristique.</p>
                  <p> Aenean lacinia finibus pulvinar. Aliquam erat volutpat. Donec rhoncus lobortis dapibus. Curabitur iaculis magna at tempor eleifend. Sed a nulla quis odio venenatis euismod. Sed vitae diam lorem. Donec et mauris molestie, bibendum sapien eget, cursus sapien.</p>
                  <p>Proin luctus, justo elementum semper pharetra, lacus nunc dapibus ante, ut gravida tellus erat at justo. Duis lacinia, justo vitae fermentum consequat, nunc quam euismod metus, non mollis mi arcu a metus. Donec ut mi augue. Sed consectetur porta velit, quis faucibus elit luctus in. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce sagittis metus at egestas auctor. Etiam eget metus tellus. In pellentesque pellentesque feugiat. Pellentesque pharetra odio sed congue tincidunt. Cras at risus odio. Sed ultricies suscipit tempus. Proin euismod blandit diam, non malesuada augue blandit quis. Cras et risus quis dolor cursus tempor. Sed justo felis, ullamcorper sit amet sem et, eleifend faucibus eros. Vivamus feugiat felis vitae felis aliquet, sit amet rhoncus sem dapibus.</p>
              </section>
              
            <section class="container-sm" id="politicaprivacitat">
                  <h3 class="text-center blau">Informació sobre protecció de dades</h3>
                  <%@include  file='/resources/html/proteccioDadesPrivacitat.html' %>
              </section>
              
        </section>

        <%@include  file='/resources/html/footer.html' %>
        
    </body>
</html>