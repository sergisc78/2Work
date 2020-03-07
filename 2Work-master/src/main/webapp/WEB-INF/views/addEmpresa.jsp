<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <!-- Latest compiled and minified CSS -->
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
         <!-- Optional theme -->
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
         <!-- jQuery -->
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
         <!-- Latest compiled and minified JavaScript -->
         <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css?family=Oswald&display=swap" rel="stylesheet">
        <link href="<c:url value="resources/css/home.css"/>" rel="styleheet"/>

        <title>2Work</title>


    </head>

    <body>

        <section>
            <div class="jumbotron">
                <div class="container ">
                    <h1 style="font-family: 'Oswald', sans-serif;"> ${banner} </h1>
                    <p> ${tagline} </p>
                </div>
        </section> 


        <form>
            <div class="form-group" style="margin-left:500px;margin-right:500px">
                <label for="nom">Empresa</label>
                <input type="text" class="form-control" id="nom" aria-describedby="emailHelp" placeholder="Introdueix el nom" required>

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
            
            <button type="submit" style="margin-left:650px;margin-right:650px" class="btn btn-primary">Submit</button>
        </form>


    </body>

</html>