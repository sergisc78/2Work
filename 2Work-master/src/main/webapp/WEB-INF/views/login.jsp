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
        <title>Benvinguda</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-1">
                     <img id="logo" style="width: 64px;" src="resources/img/portatil.jpg"/>
                </div>
                <div class="col-md-11">
                    <nav>
                        <ul class="nav nav-pills">
                            <li role="presentation" class="active">
                                <a href="<spring:url value= '/'/>">
                                    Inici
                                </a>
                            </li>
                            <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
                            <li role="Presentation" class="">
                                <a href="<c:url value="/j_spring_security_logout" />" class="btn btndanger btn-mini pull-right">desconnectar</a>
                            </li>
                            </sec:authorize>
                        </ul>    
                    </nav>
                </div>
            </div>
        </div>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Pàgina restringida!</h1>
                </div>
            </div>
        </section>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Si us plau, proporcioni les seves dades</h3>
                        </div>
                        <div class="panel-body">
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">
                                    Credencials incorrectes
                                </div>
                            </c:if>
                            <form action="<c:url value= "/j_spring_security_check"> </c:url>" method="post">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Usuari" name='j_username' type="text">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Contrasenya" name='j_password' type="password">
                                    </div>
                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Connectar">
                                </fieldset>
                                   
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>