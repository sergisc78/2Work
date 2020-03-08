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
            <div class="form-group">
                <label for="exampleInputEmail1">Introdueix Email</label>
                <input type="email" class="form-control" id="loginMail" aria-describedby="emailHelp" placeholder="Introdueix l´email">
                
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Introdueix Password</label>
                <input type="password" class="form-control" id="loginPass" placeholder="Introdueix Password">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>