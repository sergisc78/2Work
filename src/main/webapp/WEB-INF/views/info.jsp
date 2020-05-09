<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>
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

        <sec:authorize access="isAuthenticated()">
            <%@include  file='/resources/html/barra-usuari.html' %>
        </sec:authorize>

        <section class="container" id="info">

            <section class="container-sm" id="nosaltres">
                <h3 class="text-center blau">Informació sobre nosaltres</h3>
                <p>2Work va ser creada per tres emprenedors entusiastes del disseny i la programació. Amb formació en desenvolupament d´aplicacions web i disseny gràfic, sempre havíem somiat tenir la nostra pròpia empresa.</p><p>Després d´analitzar força webs relacionades amb la cerca de treball, vam arribar a la conclusió que la gran majoria admenten ofertes amb sous baixos o molt baixos, contractes abusius i/o descripcions ambigües i excessivament exigents pel que després oferia l´empresa o discriminar per sexe o edat. </p>
                <p>Per això vam decidir crear 2Work, una web amb valors socials, que no admet cap tipus de discriminació i que lluita contra la precarierat laboral i els sous baixos.</p>
                <p>2Work el forma: Dani Sevilla, dissenyador gràfic, Mónica González, desenvolupadora frontend i Sergi Sánchez, desenvolupador backend.</p>

            </section>

            <section class="container-sm" id="newsletter">
                <h3 class="text-center blau">Dona't d'alta a la newsletter de 2Work</h3>
                <p>Si vols rebre informació mensual sobre la nostre aplicació, introdueix el teu email. Si vols donar-te de baixa, encriu-nos a la nostra direcció de email que trobaràs més abaix.</p>
                <div style="text-align: center">
                <label>Email: <input type="email" id="newsletter"/><label>
                <button class="btn-primary">Enviar</button>
                </div>
            </section>

            <section class="container-sm" id="politicaprivacitat">
                <h3 class="text-center blau">Informació sobre protecció de dades</h3>
                <%@include  file='/resources/html/proteccioDadesPrivacitat.html' %>
            </section>

            <section class="container-sm" id="contacte">
                <h3 class="text-center blau">Contacta´ns</h3>
                <p>Si us plau si tens qualsevol dubte o vols informació, escriu-nos o truca´ns !
                <p>Email de contacte: <a href="#">2work@2work.cat</a></p>
                <p>Telèfon de contacte: 658940123</p>
            </section>


            <section class="container-sm" id="galetes">
                <h3 class="text-center blau">Informació sobre galetes</h3>
                <p>Les galetes constitueixen procediments automàtics de recollida d'informació relativa a les preferències determinades per un usuari d'internet durant la teva visita a un determinat web amb la finalitat de reconèixer-te com a usuari recurrent, personalitzar-ne l'ús i facilitar una navegació més ràpida.</p>
                <p>La majoria dels navegadors web accepten galetes, però podeu canviar la configuració del navegador web perquè rebutgi noves galetes, desactivi les que ja existeixen o bé que us comuniqui quan algú us envia una nova galeta al vostre dispositiu.</p>
                <p>Bloquejar l'ús de les galetes en la vostra navegació pot fer que alguns serveis o característiques del lloc web no estiguin disponibles.</p>
            </section>

        </section>

        <%@include  file='/resources/html/footer.html' %>

    </body>
</html>