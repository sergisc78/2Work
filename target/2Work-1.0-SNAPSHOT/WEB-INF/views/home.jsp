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
         <link href="<c:url value="resources/css/home.css"/>" rel="styleheet"/>

        <title>2Work</title>

    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container ">
                    <h1> ${banner} </h1>
                    <p> ${tagline} </p>
                </div>
        </section> 


        <section class="container">
            <div class="row">

                <c:forEach items="${options}" var="item">
                    <div class="col-md" style="padding-bottom: 15px; margin-left:200px;margin-right: 200px">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3 class="text-center">${item.desc}</h3>
                                <a href=" <spring:url value= "${item.url}" /> " class="btn btn-primary" style="margin-left: 300px">Dona´t d´alta</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>                                    
            </div>
        </section>

        <br>
        <br>
        <div class="container">
            <footer class="text-center">${footer}</footer>
        </div>
    </body>
</html>

