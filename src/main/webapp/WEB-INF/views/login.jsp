<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        <!-- Tipografia-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">

        <!-- Estils afegits -->
        <spring:url value="/resources/css/login.css" var="estilsCSS" />
        <link href="${estilsCSS}" rel="stylesheet" />

        <title>2Work</title>
    </head>
    <body>

                                    
      <section class="text-center" id="login">
              <h3 class="blau">Login</h3>
              <p>Introdueix les teves credencials</p>
              <form class="form-signin" action="<c:url value= "/j_spring_security_check"> </c:url>" method="post">
                  <c:if test="${not empty error}">
                        <div class="alert alert-danger">Email o password incorrecte</div>
                  </c:if>
                  <label for="inputEmail" class="sr-only">Adreça d'e-mail</label>
                  <input type="email" name='j_username' id="inputEmail" class="form-control" placeholder="Adreça d'e-mail" required autofocus>
                  <label for="inputPassword" class="sr-only">Password</label>
                  <input type="password" name='j_password' id="inputPassword" class="form-control" placeholder="Password" required>
                  
                  <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>  
                  
              </form>
              <p><a class="small-text text-muted" href="<spring:url value='/'/>">Tornar</a></p>
        </section>

        
    </body>
</html>