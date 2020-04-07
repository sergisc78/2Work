<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
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
        <script type="text/javascript" src="resources/js/validarForm.js"></script>
        
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

            <form:form modelAttribute="formCandidat" class="needs-validation" novalidate="novalidate" onsubmit="return validarCand();" action="${act}">

                <div class="form-group">
                    <label for="nom">Nom</label>
                    <form:input type="text" class="form-control" id="nom" path="nom" aria-describedby="nomCandidat" placeholder="Introdueix el nom" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix el teu nom
                    </div>
                </div>

                <div class="form-group">
                    <label for="cognoms">Cognoms</label>
                    <form:input type="text" class="form-control" id="cognoms" path="cognoms" aria-describedby="cognoms" placeholder="Introdueix els cognoms" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix el cognoms
                    </div>
                </div>

                <div class="form-group">
                    <label for="dniNif">DNI</label>
                    <form:input type="text" class="form-control" id="dniNif" path="dniNif" aria-describedby="dni" placeholder="Introdueix el DNI" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix el DNI
                    </div>
                </div>

                <div class="form-group">
                    <label for="dataNaix">Data de naixament</label>
                    <form:input type="date" class="form-control" id="dataNaix" path="dataNaix" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix la data de naixement
                    </div>
                </div>

                <div class="form-group">
                    <label for="adreca">Adreça</label>
                    <form:input type="text" class="form-control" id="adreca" path="adreca"  placeholder="Introdueix l´adreça" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix l'adreça
                    </div>
                </div>
                <div class="form-group">
                    <label for="ciutat">Ciutat</label>
                    <form:input type="text" class="form-control" id="ciutat" path="ciutat"  placeholder="Introdueix la ciutat" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix la ciutat
                    </div>
                </div>
                <div class="form-group">
                    <label for="provincia">Provincia</label>
                    <form:input type="text" class="form-control" id="provincia" path="provincia"  placeholder="Introdueix la provincia" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix la provincia
                    </div>
                </div>
                <div class="form-group">
                    <label for="telefon">Telèfon</label>
                    <form:input type="text" class="form-control" id="telefon" path="telefon" placeholder="Introdueix el telèfon" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix el telèfon
                    </div>
                </div>

                <div class="form-group">
                    <label for="email">E-mail</label>
                    <form:input type="email" class="form-control" id="email" path="email" placeholder="Introdueix l´e-mail" />
                    <div class="invalid-feedback">
                        Introdueix l'email
                    </div>
                </div>
                <div class="form-group">
                    <label for="observacions">Observacions</label>
                    <form:textarea rows="8" cols="30" class="form-control" id="observacions" path="observacions"placeholder="Observacions"/>
                    <div class="invalid-feedback">
                       Observacions
                    </div>
                </div>
                <div class="form-group">
                    <label for="pass">Password</label>
                    <form:input type="password" class="form-control" id="pass" path="pass" pattern=".{8,}"  placeholder="Mínim 8 caràcters" required="required"/>
                    <div class="invalid-feedback">
                        Introdueix password
                    </div>
                </div>

                <div class="form-group">
                    <label for="cPass">Confirmar password</label>
                    <form:input type="password" class="form-control" id="cPass" path="cPass" placeholder="Torna a escriure el password" required="required"/>
                    <div class="invalid-feedback">
                        Confirma el password
                    </div>
                </div>               

                <div class="form-group">
                    <label for="formacio">Formació</label>
                    <form:select class="form-control" id="formacio" path="formacio" required="required">
                        <form:option value="0"> --SELECT--</form:option>
                        <form:option value="1"label="1.Sense estudis finalitzats"/>     
                        <form:option value="2" label="2.Graduat escolar"/>
                        <form:option value="3" label="3.ESO"/>
                        <form:option value="4" label="4.Batxillerat"/>
                        <form:option value="5" label="5.Grau mig en conducció Activitats Fisicoesportives en el Medi Natural"/>
                        <form:option value="6" label="6.Grau mig en Jardineria i Floristeria"/>
                        <form:option value="7" label="7.Grau mig en Producció Agroecològica"/>
                        <form:option value="8" label="8.Grau mig en Activitats Comercials"/>
                        <form:option value="9" label="9.Grau mig en Instal·lacions de Telecomunicacions"/>
                        <form:option value="10" label="10.Grau mig en Instal·lacions Elèctriques i Automàtiques"/>
                        <form:option value="11" label="11.Grau mig en Instal·lacions Elèctriques i Automàtiques"/>
                        <form:option value="12" label="12.Grau mig en Mecanització"/>
                        <form:option value="13" label="13.Grau mig en Soldadura i Caldereria"/>
                        <form:option value="14" label="14.Grau mig en Perruqueria i Cosmètica"/>
                        <form:option value="15" label="15.Grau mig en Estètica i Bellesa"/>
                        <form:option value="16" label="16.Grau mig en Operacions de Laboratori"/>
                        <form:option value="17" label="17.Grau mig en Operacions de Laboratori"/>
                        <form:option value="18" label="18.Grau mig en Operacions de Laboratori"/>
                        <form:option value="19" label="19.Grau mig en Planta Química perfil professional Productes Farmacèutics i Cosmètics"/>                       
                        <form:option value="20" label="20.Grau mig en Emergències i Protecció Civil"/>
                        <form:option value="21" label="21.Grau mig en Gestió Administrativa"/>
                        <form:option value="22" label="22.Grau mig en Impressió Gràfica"/>
                        <form:option value="23" label="23.Grau mig en Vídeo Discjòquei i So"/>
                        <form:option value="24" label="23.Grau mig en Forneria Pastisseria i Confiteria"/>
                        <form:option value="25" label="25.Grau mig en Sistemes Microinformàtics i Xarxes"/>
                        <form:option value="26" label="26.Grau mig en Emergències Sanitàries"/>
                        <form:option value="27" label="27.Grau mig en Atenció a Persones en Situació de Dependència"/>
                        <form:option value="28" label="28.Grau mig en Electromecànica de Maquinària"/>
                        <form:option value="29" label="29.Grau superior en Ensenyament i Animació Socioesportiva"/>
                        <form:option value="30" label="30.Grau superior en Condicionament Físic"/>
                        <form:option value="31" label="31.Grau superior en Comerç Internacional"/>
                        <form:option value="32" label="32.Grau superior en Màrqueting i Publicitat"/>
                        <form:option value="33" label="33.Grau superior en Transport i Logística"/>
                        <form:option value="34" label="34.Grau superior en Gestió de Vendes i Espais Comercials"/>
                        <form:option value="35" label="35.Grau superior en Automatització i Robòtica Industrial"/>
                        <form:option value="36" label="36.Grau superior en Gestió Forestal i del Medi Natural"/>
                        <form:option value="37" label="37.Grau superior en Sistemes Electrotècnics i Automatitzats"/>
                        <form:option value="38" label="38.Grau superior en Direcció de Cuina"/>
                        <form:option value="39" label="39.Grau superior en Gestió Allotjaments Turístics"/>
                        <form:option value="40" label="40.Grau superior en Assessoria d'Imatge Personal i Corporativa"/>
                        <form:option value="41" label="41.Grau superior en Administració de Sistemes Informàtics en Xarxa"/>
                        <form:option value="42" label="42.Grau superior en Desenvolupament Aplicacions Web"/>
                        <form:option value="43" label="43.Grau superior en Desenvolupament Aplicacions Multiplataforma"/>
                        <form:option value="44" label="44.Grau superior en Higiene Bucodental"/>
                        <form:option value="45" label="45.Grau superior en Imatge per al Diagnòstic i Medicina Nuclear"/>
                        <form:option value="46" label="46.Grau superior en Educació Infantil"/>
                        <form:option value="47" label="47.Grau superior en Integració Social"/>
                        <form:option value="48" label="48.Grau superior en Automoció"/>
                        <form:option value="49" label="49.Grau superior en Administració i Finances"/>
                        <form:option value="50" label="50.Grau superior en Animacions en 3D, Jocs i Entorns Interactius"/>
                        <form:option value="51" label="51.Grau superior en Processos i Qualitat en Indústries Alimentàries"/>
                        <form:option value="52" label="52.Grau superior en Vitivinicultura"/>
                        <form:option value="53" label="53.Grau superior en Química Industrial"/>
                        <form:option value="54" label="54.Grau superior en Disseny Tècnic en Tèxtil i Pell"/>
                        <form:option value="55" label="55.Grau superior en Laboratori d'Anàlisi i Control de Qualitat"/>                      
                        <form:option value="56" label="56.Grau superior en Energies Renovables"/>
                        <form:option value="57" label="57.Grau superior en Disseny i Edició de Publicacions Impreses i Multimèdia"/>    
                        <form:option value="58" label="58.Grau superior en Projectes d'Obra Civil"/>
                        <form:option value="59" label="59.Grau universitari en Administració i Direcció d'Empreses"/>    
                        <form:option value="60" label="60.Grau universitari en Antropologia Social i Cultural"/>    
                        <form:option value="61" label="61.Grau universitari en Arqueologia"/>    
                        <form:option value="62" label="62.Grau universitari en Arquitectura"/>    
                        <form:option value="63" label="63.Grau universitari en Belles Arts"/>    
                        <form:option value="64" label="64.Grau universitari en Bioenginyeria"/>    
                        <form:option value="65" label="65.Grau universitari en Bioinformàtica"/>    
                        <form:option value="66" label="66.Grau universitari en Biologia"/>    
                        <form:option value="67" label="67.Grau universitari en Bioquímica"/>    
                        <form:option value="68" label="68.Grau universitari en Biotecnologia"/> 
                        <form:option value="69" label="69.Grau universitari en Ciència i Tecnologia dels Aliments"/> 
                        <form:option value="70" label="70.Grau universitari en Ciències Ambientals"/> 
                        <form:option value="71" label="71.Grau universitari en Ciències Biomèdiques"/>
                        <form:option value="72" label="72.Grau universitari en Ciències Empresarials"/>
                        <form:option value="73" label="73.Grau universitari en Ciències Polítiques"/>
                        <form:option value="74" label="74.Grau universitari en Ciències Activitat Física i Esport"/>
                        <form:option value="75" label="75.Grau universitari en Comunicació Audiovisual"/>
                        <form:option value="76" label="76.Grau universitari en Criminologia"/>
                        <form:option value="77" label="77.Grau universitari en Direcció Hotelera<"/>
                        <form:option value="78" label="78.Grau universitari en Dret"/>
                        <form:option value="79" label="79.Grau universitari en Economia"/>                       
                        <form:option value="80" label="80.Grau universitari en Educació Infantil"/>  
                        <form:option value="81" label="81.Grau universitari en Educació Primària"/>  
                        <form:option value="82" label="82.Grau universitari en Educació Social"/>  
                        <form:option value="83" label="83.Grau universitari en Empresa i Tecnologia"/>  
                        <form:option value="84" label="84.Grau universitari en Enginyeria Agroalimentària"/>
                        <form:option value="85" label="85.Grau universitari en Enginyeria Alimentària"/> 
                        <form:option value="86" label="86.Grau universitari en Enginyeria Ambiental"/> 
                        <form:option value="87" label="87.Grau universitari en Enginyeria Biomèdica"/>
                        <form:option value="88" label="88.Grau universitari en Enginyeria Alimentària"/> 
                        <form:option value="89" label="89.Grau universitari en Enginyeria Electrònica Industrial i Automàtica"/> 
                        <form:option value="90" label="90.Grau universitari en Enginyeria Forestal"/> 
                        <form:option value="91" label="91.Grau universitari en Enginyeria Informàtica"/> 
                        <form:option value="92" label="92.Grau universitari en Enginyeria Química"/> 
                        <form:option value="93" label="93.Grau universitari en Enginyeria Informàtica"/> 
                        <form:option value="94" label="94.Grau universitari en Enologia"/> 
                        <form:option value="95" label="95.Grau universitari en Estadística"/> 
                        <form:option value="96" label="96.Grau universitari en Estudis Anglesos"/> 
                        <form:option value="97" label="97.Grau universitari en Farmàcia"/> 
                        <form:option value="98" label="98.Grau universitari en Filologia Catalana"/> 
                        <form:option value="99" label="99.Grau universitari en Filologia Hispànica"/> 
                        <form:option value="100" label="100.Grau universitari en Filosofia"/>
                        <form:option value="101" label="101.Grau universitari en Fisioteràpia"/> 
                        <form:option value="102" label="102.Grau universitari en Física"/>
                        <form:option value="103" label="103.Grau universitari en Geografia"/>
                        <form:option value="104" label="104.Grau universitari en Geologia"/>
                        <form:option value="105" label="105.Grau universitari en Història"/>
                        <form:option value="106" label="106.Grau universitari en Història Art"/>
                        <form:option value="107" label="107.Grau universitari en Humanitats"/>
                        <form:option value="108" label="108.Grau universitari en Infermeria"/>
                        <form:option value="109" label="109.Grau universitari en Logopèdia"/>
                        <form:option value="110" label="110.Grau universitari en Matemàtiques"/>
                        <form:option value="111" label="111.Grau universitari en Medicina"/>
                        <form:option value="112" label="112.Grau universitari en Màrqueting"/>
                        <form:option value="113" label="113.Grau universitari en Nutrició Humana i Dietètica"/>
                        <form:option value="114" label="114.Grau universitari en Odontologia"/>
                        <form:option value="115" label="115.Grau universitari en Pedagogia"/>
                        <form:option value="116" label="116.Grau universitari en Periodisme"/>
                        <form:option value="117" label="117.Grau universitari en Podologia"/>
                        <form:option value="118" label="118.Grau universitari en Psicologia"/>
                        <form:option value="119" label="119.Grau universitari en Publicitat i Relacions Públiques"/>
                        <form:option value="120" label="120.Grau universitari en Química"/>
                        <form:option value="121" label="121.Grau universitari en Relacions Internacionals"/>
                        <form:option value="122" label="122.Grau universitari en Relacions Laborals"/>
                        <form:option value="123" label="123.Grau universitari en Sociologia"/>
                        <form:option value="124" label="124.Grau universitari en Teràpia Ocupacional"/>
                        <form:option value="125" label="125.Grau universitari en Traducció i Interpretació -Alemany-"/>
                        <form:option value="126" label="126.Grau universitari en Traducció i Interpretació -Anglès-"/>
                        <form:option value="127" label="127.Grau universitari en Traducció i Interpretació -Francès-"/>
                        <form:option value="128" label="128.Grau universitari en Treball Social"/>
                        <form:option value="129" label="129.Grau universitari en Turisme"/>
                        <form:option value="130" label="130.Grau universitari en Veterinària"/>                        
                        <form:option value="131" label="131.Grau universitari en Òptica i Optometria"/>
                        <form:option value="132" label="132.Màster"/>
                        <form:option value="133" label="133.Postgrau"/>
                        <form:option value="134" label="134.Doctorat"/>                        
                    </form:select>
                </div>
                <div class="form-group">
                    <label for="ocupacio">Elegeix l'ocupació</label>
                    <form:select class="form-control" id="ocupacio" path="ocupacio" required="required">
                        <form:option value="0"> --SELECT--</form:option>
                        <form:option value="1" label="1.Acoblador elements prefabricats edificis"/>
                        <form:option value="2" label="2.Acompanyant o persona de companyia"/>
                        <form:option value="3" label="3.Actor"/>
                        <form:option value="4" label="4.Adober de cuirs i pells"/>
                        <form:option value="5" label="5.Adornista de pedra o marbre"/>
                        <form:option value="6" label="6.Advocat"/>
                        <form:option value="7" label="7.Agent assegurances"/>
                        <form:option value="8" label="8.Agent enquestes per telèfon"/>
                        <form:option value="9" label="9.Agent de la propietat immobiliària"/>
                        <form:option value="10" label="10.Agent de turisme en viatges"/>
                        <form:option value="11" label="11.Ajudant esteticista"/>
                        <form:option value="12" label="12.Ajudant odontologia"/>
                        <form:option value="13" label="13.Ajudant de perruqueria"/>
                        <form:option value="14" label="14.Ajustador mecànic de fabricació eines"/>
                        <form:option value="15" label="15.Analista de sistemes informàtics"/>
                        <form:option value="16" label="16.Artesà"/>
                        <form:option value="17" label="17.Auxiliar ajuda a domicili"/>
                        <form:option value="18" label="18.Auxiliar de laboratori anàlisis clíniques"/>
                        <form:option value="19" label="19.Biòleg"/>
                        <form:option value="20" label="20.Bugader a mà en bugaderia"/>
                        <form:option value="21" label="Caixer autoservei"/>
                        <form:option value="22" label="22.Calefactor lampista"/>
                        <form:option value="23" label="23.Cambrer de bar"/>
                        <form:option value="24" label="24.Cangur"/>
                        <form:option value="25" label="25.Cap de cuina"/>
                        <form:option value="26" label="26.Cap equip ajustadors equips elèctrics"/>
                        <form:option value="27" label="27.Comercial de vendes"/>
                        <form:option value="28" label="28.Comptable en comptabilitat de costos"/>
                        <form:option value="29" label="29.Conductor ambulància"/>
                        <form:option value="30" label="30.Conductor"/>
                        <form:option value="31" label="31.Cuiner"/>
                        <form:option value="32" label="32.Cultivador"/>
                        <form:option value="33" label="33.Dependent"/>
                        <form:option value="34" label="34.Director general empreses privades"/>
                        <form:option value="35" label="35.Dissenyador"/>
                        <form:option value="36" label="36.Dona o mosso de neteja oficines"/>
                        <form:option value="37" label="37.Economista"/>
                        <form:option value="38" label="38.Educador social"/>
                        <form:option value="39" label="39.Electricista"/>
                        <form:option value="40" label="40.Empleat agència de viatges"/>
                        <form:option value="41" label="41.Enginyer en electrònica"/>
                        <form:option value="42" label="42.Enginyer en logística"/>
                        <form:option value="43" label="43.Enginyer tècnic telecomunicacions"/>
                        <form:option value="44" label="44.Esportista professional"/>
                        <form:option value="45" label="45.Farmacèutic"/>
                        <form:option value="46.Filoleg" label="46.Filòleg"/>
                        <form:option value="47" label="47.Fuster taller tancaments metall"/>
                        <form:option value="48" label="48.Gerent bar"/>
                        <form:option value="49" label="49.Gerent empresa"/>
                        <form:option value="50" label="50.Guia"/>
                        <form:option value="51" label="51.Historiador"/>
                        <form:option value="52" label="52.Hostessa i auxiliar de vol"/>
                        <form:option value="53" label="53.Infermer"/>
                        <form:option value="54" label="54.Conduccions ventilació"/>
                        <form:option value="55" label="55.Instrumentista"/>
                        <form:option value="56" label="56.Joier"/>
                        <form:option value="57" label="57.Lampista"/>
                        <form:option value="58" label="58.Locutor de ràdio o televisió"/>
                        <form:option value="59" label="59.Logopeda"/>
                        <form:option value="60" label="60.Llegidor de comptadors"/>
                        <form:option value="61" label="61.Llevadora"/>
                        <form:option value="62" label="62.Manyà"/>
                        <form:option value="63" label="63.Maquinista"/>
                        <form:option value="64" label="64.Matemàtic"/>
                        <form:option value="65" label="65.Mecànic automòbils turismes i furgonetes"/>
                        <form:option value="66" label="66.Mestre ensenyament"/>
                        <form:option value="67" label="67.Metge"/>
                        <form:option value="68" label="68.Model"/>
                        <form:option value="69" label="69.Muntador"/>
                        <form:option value="70" label="70.Músic de sala de festes"/>
                        <form:option value="71" label="71.Naturopata"/>
                        <form:option value="72" label="72.Netejador"/>
                        <form:option value="73" label="73.Odontòleg"/>
                        <form:option value="74" label="74.Operador"/>
                        <form:option value="75" label="75.Paleta"/>
                        <form:option value="76" label="76.Pastisser"/>
                        <form:option value="77" label="77.Peó"/>
                        <form:option value="78" label="78.Pintor automòbils"/>
                        <form:option value="79" label="79.Pintor"/>
                        <form:option value="80" label="80.Professor classes particulars"/>
                        <form:option value="81" label="81.Programador informàtic aplicacions de gestió"/>
                        <form:option value="82" label="82.Programador informàtic nivell mitjà"/>
                        <form:option value="83" label="83.Químic"/>
                        <form:option value="84" label="84.Recepcionista"/>
                        <form:option value="85" label="85.Rellotger"/>
                        <form:option value="86" label="86.Restaurador obres art"/>
                        <form:option value="87" label="87.Sabater"/>
                        <form:option value="88" label="88.Socorrista"/>
                        <form:option value="89" label="89.Soldador"/>
                        <form:option value="90" label="90.Tallador"/>
                        <form:option value="91" label="91.Tastador"/>
                        <form:option value="92" label="92.Traductor"/>
                        <form:option value="93" label="93.Treballador social"/>
                        <form:option value="94" label="94.Venedor a domicili"/>
                        <form:option value="95" label="95.Veterinari"/>
                        <form:option value="96" label="96.Vidrier"/>
                        <form:option value="97" label="97.Xarcuter"/>
                        <form:option value="98" label="98.Zelador hospitalari"/>
                        <form:option value="99" label="99.Altres"/>                  
                    </form:select>
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

             </form:form>
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