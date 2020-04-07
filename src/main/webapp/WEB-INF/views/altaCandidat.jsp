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
        <script type="text/javascript" src="resources/validarForm.js"></script>
        
        <title>2Work - ${ubicacio}</title>
    </head>

    <body>

      <!-- Modal info política de protecció de dades i privacitat-->
      <div class="modal fade" id="modalPrivacitat" tabindex="-1" role="dialog" aria-labelledby="proteccioDadesPrivacitat" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Política de protecció de dades i privacitat</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <%@include  file='/resources/html/proteccioDadesPrivacitat.html' %>
            </div>
          </div>
        </div>
      </div>
          
        <!--- Barra de navegació -->
        <nav class="navbar navbar-expand-lg navbar-dark">

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

        <section>
            <div class="jumbotron">
                <div class="container ">
                    <h1>La teva web de cerca de feina</h1>
                </div>
        </section>

        <section id="alta-candidat" class="container-sm formulari-alta">        
            <h3 class="text-center blau">Enregistra't com a candidat</h3><br>

            <form class="needs-validation" novalidate onsubmit="return validarCand();">

                <div class="form-group">
                    <label for="nomCand">Nom</label>
                    <input type="text" class="form-control" id="nomCand" aria-describedby="emailHelp" placeholder="Introdueix el nom" required>
                    <div class="invalid-feedback">
                        Introdueix el teu nom
                    </div>
                </div>

                <div class="form-group">
                    <label for="cognomsCand">Cognoms</label>
                    <input type="text" class="form-control" id="cognomsCand" aria-describedby="emailHelp" placeholder="Introdueix els cognoms" required>
                    <div class="invalid-feedback">
                        Introdueix el cognoms
                    </div>
                </div>

                <div class="form-group">
                    <label for="dniCand">DNI</label>
                    <input type="text" class="form-control" id="dniCand" aria-describedby="emailHelp" placeholder="Introdueix el DNI" required>
                    <div class="invalid-feedback">
                        Introdueix el DNI
                    </div>
                </div>

                <div class="form-group">
                    <label for="dataCand">Data de naixament</label>
                    <input type="date" class="form-control" id="dataCand" required>
                    <div class="invalid-feedback">
                        Introdueix la data de naixament
                    </div>
                </div>

                <div class="form-group">
                    <label for="adrecaCand">Adreça</label>
                    <input type="text" class="form-control" id="adrecaCand"  placeholder="Introdueix l´adreça" required>
                    <div class="invalid-feedback">
                        Introdueix l'adreça
                    </div>
                </div>

                <div class="form-group">
                    <label for="telfCand">Telèfon</label>
                    <input type="text" class="form-control" id="telfCand"  placeholder="Introdueix el telèfon" required>
                    <div class="invalid-feedback">
                        Introdueix el telèfon
                    </div>
                </div>

                <div class="form-group">
                    <label for="mailCand">E-mail</label>
                    <input type="email" class="form-control" id="mailCand" placeholder="Introdueix l´e-mail" >
                    <div class="invalid-feedback">
                        Introdueix l'email
                    </div>
                </div>
                <div class="form-group">
                    <label for="passCand">Password</label>
                    <input type="password" class="form-control" id="passCand"  pattern=".{8,}"  placeholder="Mínim 8 caràcters" required>
                    <div class="invalid-feedback">
                        Introdueix password
                    </div>
                </div>

                <div class="form-group">
                    <label for="cpassCand">Confirmar password</label>
                    <input type="password" class="form-control" id="cpassCand" placeholder="Torna a escriure el password" required>
                    <div class="invalid-feedback">
                        Confirma el password
                    </div>
                </div>

                <div class="form-group">
                    <label for="sectorCand">Elegeix el sector laboral</label>
                    <select class="form-control" id="sectorCand" required>
                        <option value="" selected="true" disabled="disabled"></option>
                        <option>Activitats físiques i esportives</option>
                        <option>Adminstració i gestió</option>
                        <option>Agricultura i ramaderia</option>
                        <option>Indústria alimentària</option>
                        <option>Comerç</option>
                        <option>Construcció</option>
                        <option>Disseny i arts gràfiques</option>
                        <option>Educació</option>
                        <option>Finances</option>
                        <option>Informàtica</option>
                        <option>Enyingeria</option>
                        <option>Legal</option>
                        <option>Màrqueting i comunicació</option>
                        <option>Recursos humans</option>
                        <option>Sanitari</option>
                        <option>Turisme</option>
                        <option>Hostaleria i turisme</option>
                        <option>Mediambiental</option>
                        <option>Pesca i agricultura</option>
                        <option>Química</option>
                        <option>Estètica i perruqueria</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="formacioCand">Formació</label>
                    <select class="form-control" id="formacioCand" required>
                        <option value="" selected="true" selected="false"></option>
                        <option>Graduat escolar</option>
                        <option>Batxillerat</option>

                        <option selected="true" disabled="disabled" style="color: blue">Grau mig de FP</option>
                        <option>Grau mig en conducció d'Activitats Fisicoesportives en el Medi Natural</option>
                        <option>Grau mig en Jardineria i Floristeria</option>
                        <option>Grau mig en Producció Agroecològica</option>
                        <option>Grau mig en Activitats Comercials</option>
                        <option>Grau mig en Instal·lacions de Telecomunicacions</option>
                        <option>Grau mig en Instal·lacions Elèctriques i Automàtiques</option>
                        <option>Grau mig en Mecanització</option>
                        <option>Grau mig en Soldadura i Caldereria</option>
                        <option>Grau mig en Perruqueria i Cosmètica Capil·lar</option>
                        <option>Grau mig en Estètica i Bellesa</option>
                        <option>Grau mig en Operacions de Laboratori</option>
                        <option>Grau mig en Planta Química, perfil professional Productes Farmacèutics i Cosmètics</option>
                        <option>Grau mig en Emergències i Protecció Civil</option>
                        <option>Grau mig en Gestió Administrativa</option>
                        <option>Grau mig en Impressió Gràfica</option>
                        <option>Grau mig en Vídeo, Discjòquei i So</option>
                        <option>Grau mig en Forneria, Pastisseria i Confiteria</option>
                        <option>Grau mig en Sistemes Microinformàtics i Xarxes</option>
                        <option>Grau mig en Emergències Sanitàries</option>
                        <option>Grau mig en Atenció a Persones en Situació de Dependència</option>
                        <option>Grau mig en Electromecànica de Maquinària</option>

                        <option selected="true" disabled="disabled" style="color: blue">Grau superior de FP</option>

                        <option>Grau superior en Ensenyament i Animació Socioesportiva</option>
                        <option>Grau superior en Condicionament Físic</option>
                        <option>Grau superior en Comerç Internacional</option>
                        <option>Grau superior en Màrqueting i Publicitat</option>
                        <option>Grau superior en Transport i Logística</option>
                        <option>Grau superior en Gestió de Vendes i Espais Comercials</option>
                        <option>Grau superior en Automatització i Robòtica Industrial</option>
                        <option>Grau superior en Gestió Forestal i del Medi Natural</option>
                        <option>Grau superior en Sistemes Electrotècnics i Automatitzats</option>
                        <option>Grau superior en Direcció de Cuina</option>
                        <option>Grau superior en Gestió d'Allotjaments Turístics</option>
                        <option>Grau superior en Assessoria d'Imatge Personal i Corporativa</option>
                        <option>Grau superior en Administració de Sistemes Informàtics en Xarxa</option>
                        <option>Grau superior en Desenvolupament d'Aplicacions Web</option>
                        <option>Grau superior en Desenvolupament d'Aplicacions Multiplataforma</option>
                        <option>Grau superior en Higiene Bucodental</option>
                        <option>Grau superior en Imatge per al Diagnòstic i Medicina Nuclear</option>
                        <option>Grau superior en Educació Infantil</option>
                        <option>Grau superior en Integració Social</option>
                        <option>Grau superior en Automoció</option>
                        <option>Grau superior en Administració i Finances</option>
                        <option>Grau superior en Animacions en 3D, Jocs i Entorns Interactius</option>
                        <option>Grau superior en Processos i Qualitat en Indústries Alimentàries</option>
                        <option>Grau superior en Vitivinicultura</option>
                        <option>Grau superior en Química Industrial</option>
                        <option>Grau superior en Disseny Tècnic en Tèxtil i Pell</option>
                        <option>Grau superior en Laboratori d'Anàlisi i Control de Qualitat</option>
                        <option>Grau superior en Energies Renovables</option>
                        <option>Grau superior en Disseny i Edició de Publicacions Impreses i Multimèdia</option>
                        <option>Grau superior en Projectes d'Obra Civil</option>

                        <option selected="true" disabled="disabled" style="color: blue">Grau universitari</option>
                        <option>Grau universitari en Administració i Direcció d'Empreses</option>
                        <option>Grau universitari en Antropologia Social i Cultural</option>
                        <option>Grau universitari en Arqueologia</option>
                        <option>Grau universitari en Arquitectura</option>
                        <option>Grau universitari en Belles Arts</option>
                        <option>Grau universitari en Bioenginyeria</option>
                        <option>Grau universitari en Bioinformàtica</option>
                        <option>Grau universitari en Biologia</option>
                        <option>Grau universitari en Bioquímica</option>
                        <option>Grau universitari en Biotecnologia</option>
                        <option>Grau universitari en Ciència i Tecnologia dels Aliments</option>
                        <option>Grau universitari en Ciències Ambientals</option>
                        <option>Grau universitari en Ciències Biomèdiques</option>
                        <option>Grau universitari en Ciències Empresarials</option>
                        <option>Grau universitari en Ciències Polítiques</option>
                        <option>Grau universitari en Ciències de l'Activitat Física i de l'Esport</option>
                        <option>Grau universitari en Comunicació Audiovisual</option>
                        <option>Grau universitari en Criminologia</option>
                        <option>Grau universitari en Direcció Hotelera</option>
                        <option>Grau universitari en Dret</option>
                        <option>Grau universitari en Economia</option>
                        <option>Grau universitari en Educació Infantil</option>
                        <option>Grau universitari en Educació Primària</option>
                        <option>Grau universitari en Educació Social</option>
                        <option>Grau universitari en Empresa i Tecnologia</option>
                        <option>Grau universitari en Enginyeria Agroalimentària</option>
                        <option>Grau universitari en Enginyeria Alimentària</option>
                        <option>Grau universitari en Enginyeria Ambiental</option>
                        <option>Grau universitari en Enginyeria Biomèdica</option>
                        <option>Grau universitari en Enginyeria Alimentària</option>
                        <option>Grau universitari en Enginyeria Electrònica Industrial i Automàtica</option>
                        <option>Grau universitari en Enginyeria Forestal</option>
                        <option>Grau universitari en Enginyeria Informàtica</option>
                        <option>Grau universitari en Enginyeria Química</option>
                        <option>Grau universitari en Enginyeria Informàtica</option>
                        <option>Grau universitari en Enologia</option>
                        <option>Grau universitari en Estadística</option>
                        <option>Grau universitari en Estudis Anglesos</option>
                        <option>Grau universitari en Farmàcia</option>
                        <option>Grau universitari en Filologia Catalana</option>
                        <option>Grau universitari en Filologia Hispànica</option>
                        <option>Grau universitari en Filosofia</option>
                        <option>Grau universitari en Fisioteràpia</option>
                        <option>Grau universitari en Física</option>
                        <option>Grau universitari en Geografia</option>
                        <option>Grau universitari en Geologia</option>
                        <option>Grau universitari en Història</option>
                        <option>Grau universitari en Història de l'Art</option>
                        <option>Grau universitari en Humanitats</option>
                        <option>Grau universitari en Infermeria</option>
                        <option>Grau universitari en Logopèdia</option>
                        <option>Grau universitari en Matemàtiques</option>
                        <option>Grau universitari en Medicina</option>
                        <option>Grau universitari en Màrqueting</option>
                        <option>Grau universitari en Nutrició Humana i Dietètica</option>
                        <option>Grau universitari en Odontologia</option>
                        <option>Grau universitari en Pedagogia</option>
                        <option>Grau universitari en Periodisme</option>
                        <option>Grau universitari en Podologia</option>
                        <option>Grau universitari en Psicologia</option>
                        <option>Grau universitari en Publicitat i Relacions Públiques</option>
                        <option>Grau universitari en Química</option>
                        <option>Grau universitari en Relacions Internacionals</option>
                        <option>Grau universitari en Relacions Laborals</option>
                        <option>Grau universitari en Sociologia</option>
                        <option>Grau universitari en Teràpia Ocupacional</option>
                        <option>Grau universitari en Traducció i Interpretació -Alemany-</option>
                        <option>Grau universitari en Traducció i Interpretació -Anglès-</option>
                        <option>Grau universitari en Traducció i Interpretació -Francès-</option>
                        <option>Grau universitari en Treball Social</option>
                        <option>Grau universitari en Turisme</option>
                        <option>Grau universitari en Veterinària</option>
                        <option>Grau universitari en Òptica i Optometria</option>


                    </select>
                </div>

                <div class="form-group">
                    <label for="formaciopuCand">Formació postuniversitària</label>
                    <select class="form-control" id="formaciopuCand">
                        <option value="" selected="true" selected="false">No</option>
                        <option>Màster</option>
                        <option>Postgrau</option>
                        <option>Doctorat</option>
                    </select>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck3" required>
                        <label class="form-check-label" for="invalidCheck3">D'acord amb la política de privacitat i protecció de dades</label>
                        <a data-toggle="modal" data-target="#modalPrivacitat" ><span class="badge badge-pill badge-primary">Llegir</span></a>
                        <div class="invalid-feedback">
                            Has d'estar d'acord, abans d'enviar el formulari
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="cv">Adjunta el teu CV</label>
                    <input type="file" class="form-control-file" id="cv">
                </div>

                <button type="submit" class="btn btn-primary">Enviar</button>

            </form>
        </section>

        <%@include  file='/resources/html/footer.html' %>

        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict';
                window.addEventListener('load', function () {
                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.getElementsByClassName('needs-validation');
                    // Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();

        </script>
    </body>

</html>