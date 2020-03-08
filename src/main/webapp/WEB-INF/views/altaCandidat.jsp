<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
          
          <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous" />
        
        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Oswald&display=swap" rel="stylesheet">
        
        <!-- Tipografia-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">

      <!-- Estils afegits -->
        <spring:url value="/resources/css/estils.css" var="mainCss" />  
        <link href="${mainCss}" rel="stylesheet" />

        <title>2Work</title>


    </head>

    <body>
          
        <!--- Barra de navegació -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                         
              <a class="navbar-brand" href="#">
                    <img src="${pageContext.request.contextPath}/resources/svg/logo_2work.svg" id="navbarlogo" alt="logo2Work">
              </a>
                              
              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHomeToggler" aria-controls="navbarHomeToggler" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
              </button>
       
               <div class="collapse navbar-collapse" id="navbarHomeToggler">
                  <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                        <li class="nav-item">
                              <a class="nav-link" href="#">Login</a>
                        </li>
                  </ul>
              </div>

        </nav>

        <section>
            <div class="jumbotron">
                <div class="container ">
                    <h1> ${banner} </h1>
                    <p> ${tagline} </p>
                </div>
        </section> 


        <form>
            <div class="form-group" style="margin-left:500px;margin-right:500px">
                <label for="nom">Nom</label>
                <input type="text" class="form-control" id="nom" aria-describedby="emailHelp" placeholder="Introdueix el nom" required>

            </div>
            <div class="form-group"style="margin-left:500px;margin-right:500px" required>
                <label for="cognoms">Cognoms</label>
                <input type="text" class="form-control" id="cognoms" placeholder="Introdueix els cognoms" required>
            </div>
            <div class="form-group" style="margin-left:500px;margin-right:500px">
                <label for="data">Data de naixament</label>
                <input type="date" class="form-control" id="data" required>

            </div>
            <div class="form-group" style="margin-left:500px;margin-right:500px" >
                <label for="adreça">Adreça</label>
                <input type="text" class="form-control" id="adreça"  placeholder="Introdueix l´adreça" required>

            </div>
            <div class="form-group" style="margin-left:500px;margin-right:500px">
                <label for="telefon">Telèfon</label>
                <input type="text" class="form-control" id="telefon"  placeholder="Introdueix el telèfon" required>

            </div>
            <div class="form-group" style="margin-left:500px;margin-right:500px" >
                <label for="mail">E-mail</label>
                <input type="email" class="form-control" id="mail" placeholder="Introdueix l´e-mail" required>

            </div>
            <div class="form-group" style="margin-left:500px;margin-right:500px" >
                <label for="pass">Password</label>
                <input type="password" class="form-control" id="pass" placeholder="Mínim 8 caràcters" required>

            </div>
            <div class="form-group" style="margin-left:500px;margin-right:500px" >
                <label for="mail">E-mail</label>
                <input type="email" class="form-control" id="mail" placeholder="Introdueix l´e-mail" required>

            </div>
            <div class="form-group" style="margin-left:500px;margin-right:500px">
                <label for="exampleFormControlSelect1">Elegeix el sector laboral</label>
                <select class="form-control" id="exampleFormControlSelect2">
                    <option>Activitats físiques i esportves</option>
                    <option>Adminstració i gestió</option>
                    <option>Agricultura i ganaderia</option>
                    <option>Industria alimentària</option>
                    <option>Comerç</option>
                    <option>Construcció</option>
                    <option>Disseny i arts gràfiques</option>
                    <option>Educació</option>
                    <option>Finances</option>
                    <option>Informàtica</option>
                    <option>Enyingeria</option>
                    <option>Legal</option>
                    <option>Marketing i comunicació</option>
                    <option>Recursos humans</option>
                    <option>Sanitari</option>
                    <option>Turisme</option>
                    <option>Hosteleria i turisme</option>
                    <option>Mediambiental</option>
                    <option>Pesca i agricultura</option>
                    <option>Química</option>
                    <option>Estètica i perruqueria</option>


                </select>
            </div>
            <div class="form-group" style="margin-left:500px;margin-right:500px" >
                <label for="cv">Adjunta el teu CV</label>
                <input type="file" class="form-control-file" id="cv">

            </div>
            <button type="submit" style="margin-left:650px;margin-right:650px" class="btn btn-primary">Submit</button>
        </form>
                
        <footer class="footer">
            <div class="container">
                  <!-- No li estem passant la variable! -->
                  <span class="text-muted">${footer}</footer>
              </div>
        </footer>


    </body>

</html>