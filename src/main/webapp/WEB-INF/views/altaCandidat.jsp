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
                        <form:option value="1"label="Sense estudis finalitzats"/>     
                        <form:option value="2" label="Graduat escolar"/>
                        <form:option value="3" label="ESO"/>
                        <form:option value="4" label="Batxillerat"/>
                        <form:option value="5" label="Grau mig en conducció Activitats Fisicoesportives en el Medi Natural"/>
                        <form:option value="6" label="Grau mig en Jardineria i Floristeria"/>
                        <form:option value="7" label="Grau mig en Producció Agroecològica"/>
                        <form:option value="8" label="Grau mig en Activitats Comercials"/>
                        <form:option value="9" label="Grau mig en Instal·lacions de Telecomunicacions"/>
                        <form:option value="10" label="Grau mig en Instal·lacions Elèctriques i Automàtiques"/>
                        <form:option value="11" label="Grau mig en Instal·lacions Elèctriques i Automàtiques"/>
                        <form:option value="12" label="Grau mig en Mecanització"/>
                        <form:option value="13" label="Grau mig en Soldadura i Caldereria"/>
                        <form:option value="14" label="Grau mig en Perruqueria i Cosmètica"/>
                        <form:option value="15" label="Grau mig en Estètica i Bellesa"/>
                        <form:option value="16" label="Grau mig en Operacions de Laboratori"/>
                        <form:option value="17" label="Grau mig en Operacions de Laboratori"/>
                        <form:option value="18" label="Grau mig en Operacions de Laboratori"/>
                        <form:option value="19" label="Grau mig en Planta Química perfil professional Productes Farmacèutics i Cosmètics"/>                       
                        <form:option value="20" label="Grau mig en Emergències i Protecció Civil"/>
                        <form:option value="21" label="Grau mig en Gestió Administrativa"/>
                        <form:option value="22" label="Grau mig en Impressió Gràfica"/>
                        <form:option value="23" label="Grau mig en Vídeo Discjòquei i So"/>
                        <form:option value="24" label="Grau mig en Forneria Pastisseria i Confiteria"/>
                        <form:option value="25" label="Grau mig en Sistemes Microinformàtics i Xarxes"/>
                        <form:option value="26" label="Grau mig en Emergències Sanitàries"/>
                        <form:option value="27" label="Grau mig en Atenció a Persones en Situació de Dependència"/>
                        <form:option value="28" label="Grau mig en Electromecànica de Maquinària"/>
                        <form:option value="29" label="Grau superior en Ensenyament i Animació Socioesportiva"/>
                        <form:option value="30" label="Grau superior en Condicionament Físic"/>
                        <form:option value="31" label="Grau superior en Comerç Internacional"/>
                        <form:option value="32" label="Grau superior en Màrqueting i Publicitat"/>
                        <form:option value="33" label="Grau superior en Transport i Logística"/>
                        <form:option value="34" label="Grau superior en Gestió de Vendes i Espais Comercials"/>
                        <form:option value="35" label="Grau superior en Automatització i Robòtica Industrial"/>
                        <form:option value="36" label="Grau superior en Gestió Forestal i del Medi Natural"/>
                        <form:option value="37" label="Grau superior en Sistemes Electrotècnics i Automatitzats"/>
                        <form:option value="38" label="Grau superior en Direcció de Cuina"/>
                        <form:option value="39" label="Grau superior en Gestió Allotjaments Turístics"/>
                        <form:option value="40" label="Grau superior en Assessoria d'Imatge Personal i Corporativa"/>
                        <form:option value="41" label="Grau superior en Administració de Sistemes Informàtics en Xarxa"/>
                        <form:option value="42" label="Grau superior en Desenvolupament Aplicacions Web"/>
                        <form:option value="43" label="Grau superior en Desenvolupament Aplicacions Multiplataforma"/>
                        <form:option value="44" label="Grau superior en Higiene Bucodental"/>
                        <form:option value="45" label="Grau superior en Imatge per al Diagnòstic i Medicina Nuclear"/>
                        <form:option value="46" label="Grau superior en Educació Infantil"/>
                        <form:option value="47" label="Grau superior en Integració Social"/>
                        <form:option value="48" label="Grau superior en Automoció"/>
                        <form:option value="49" label="Grau superior en Administració i Finances"/>
                        <form:option value="50" label="Grau superior en Animacions en 3D, Jocs i Entorns Interactius"/>
                        <form:option value="51" label="Grau superior en Processos i Qualitat en Indústries Alimentàries"/>
                        <form:option value="52" label="Grau superior en Vitivinicultura"/>
                        <form:option value="53" label="Grau superior en Química Industrial"/>
                        <form:option value="54" label="Grau superior en Disseny Tècnic en Tèxtil i Pell"/>
                        <form:option value="55" label="Grau superior en Laboratori d'Anàlisi i Control de Qualitat"/>                      
                        <form:option value="56" label="Grau superior en Energies Renovables"/>
                        <form:option value="57" label="Grau superior en Disseny i Edició de Publicacions Impreses i Multimèdia"/>    
                        <form:option value="58" label="Grau superior en Projectes d'Obra Civil"/>
                        <form:option value="59" label="Grau universitari en Administració i Direcció d'Empreses"/>    
                        <form:option value="60" label="Grau universitari en Antropologia Social i Cultural"/>    
                        <form:option value="61" label="Grau universitari en Arqueologia"/>    
                        <form:option value="62" label="Grau universitari en Arquitectura"/>    
                        <form:option value="63" label="Grau universitari en Belles Arts"/>    
                        <form:option value="64" label="Grau universitari en Bioenginyeria"/>    
                        <form:option value="65" label="Grau universitari en Bioinformàtica"/>    
                        <form:option value="66" label="Grau universitari en Biologia"/>    
                        <form:option value="67" label="Grau universitari en Bioquímica"/>    
                        <form:option value="68" label="Grau universitari en Biotecnologia"/> 
                        <form:option value="69" label="Grau universitari en Ciència i Tecnologia dels Aliments"/> 
                        <form:option value="70" label="Grau universitari en Ciències Ambientals"/> 
                        <form:option value="71" label="Grau universitari en Ciències Biomèdiques"/>
                        <form:option value="72" label="Grau universitari en Ciències Empresarials"/>
                        <form:option value="73" label="Grau universitari en Ciències Polítiques"/>
                        <form:option value="74" label="Grau universitari en Ciències Activitat Física i Esport"/>
                        <form:option value="75" label="Grau universitari en Comunicació Audiovisual"/>
                        <form:option value="76" label="Grau universitari en Criminologia"/>
                        <form:option value="77" label="Grau universitari en Direcció Hotelera<"/>
                        <form:option value="78" label="Grau universitari en Dret"/>
                        <form:option value="79" label="Grau universitari en Economia"/>                       
                        <form:option value="80" label="Grau universitari en Educació Infantil"/>  
                        <form:option value="81" label="Grau universitari en Educació Primària"/>  
                        <form:option value="82" label="Grau universitari en Educació Social"/>  
                        <form:option value="83" label="Grau universitari en Empresa i Tecnologia"/>  
                        <form:option value="84" label="Grau universitari en Enginyeria Agroalimentària"/>
                        <form:option value="85" label="Grau universitari en Enginyeria Alimentària"/> 
                        <form:option value="86" label="Grau universitari en Enginyeria Ambiental"/> 
                        <form:option value="87" label="Grau universitari en Enginyeria Biomèdica"/>
                        <form:option value="88" label="Grau universitari en Enginyeria Alimentària"/> 
                        <form:option value="89" label="Grau universitari en Enginyeria Electrònica Industrial i Automàtica"/> 
                        <form:option value="90" label="Grau universitari en Enginyeria Forestal"/> 
                        <form:option value="91" label="Grau universitari en Enginyeria Informàtica"/> 
                        <form:option value="92" label="Grau universitari en Enginyeria Química"/> 
                        <form:option value="93" label="Grau universitari en Enginyeria Informàtica"/> 
                        <form:option value="94" label="Grau universitari en Enologia"/> 
                        <form:option value="95" label="Grau universitari en Estadística"/> 
                        <form:option value="96" label="Grau universitari en Estudis Anglesos"/> 
                        <form:option value="97" label="Grau universitari en Farmàcia"/> 
                        <form:option value="98" label="Grau universitari en Filologia Catalana"/> 
                        <form:option value="99" label="Grau universitari en Filologia Hispànica"/> 
                        <form:option value="100" label="Grau universitari en Filosofia"/>
                        <form:option value="101" label="Grau universitari en Fisioteràpia"/> 
                        <form:option value="102" label="Grau universitari en Física"/>
                        <form:option value="103" label="Grau universitari en Geografia"/>
                        <form:option value="104" label="Grau universitari en Geologia"/>
                        <form:option value="105" label="Grau universitari en Història"/>
                        <form:option value="106" label="Grau universitari en Història Art"/>
                        <form:option value="107" label="Grau universitari en Humanitats"/>
                        <form:option value="108" label="Grau universitari en Infermeria"/>
                        <form:option value="109" label="Grau universitari en Logopèdia"/>
                        <form:option value="110" label="Grau universitari en Matemàtiques"/>
                        <form:option value="111" label="Grau universitari en Medicina"/>
                        <form:option value="112" label="Grau universitari en Màrqueting"/>
                        <form:option value="113" label="Grau universitari en Nutrició Humana i Dietètica"/>
                        <form:option value="114" label="Grau universitari en Odontologia"/>
                        <form:option value="115" label="Grau universitari en Pedagogia"/>
                        <form:option value="116" label="Grau universitari en Periodisme"/>
                        <form:option value="117" label="Grau universitari en Podologia"/>
                        <form:option value="118" label="Grau universitari en Psicologia"/>
                        <form:option value="119" label="Grau universitari en Publicitat i Relacions Públiques"/>
                        <form:option value="120" label="Grau universitari en Química"/>
                        <form:option value="121" label="Grau universitari en Relacions Internacionals"/>
                        <form:option value="122" label="Grau universitari en Relacions Laborals"/>
                        <form:option value="123" label="Grau universitari en Sociologia"/>
                        <form:option value="124" label="Grau universitari en Teràpia Ocupacional"/>
                        <form:option value="125" label="Grau universitari en Traducció i Interpretació -Alemany-"/>
                        <form:option value="126" label="Grau universitari en Traducció i Interpretació -Anglès-"/>
                        <form:option value="127" label="Grau universitari en Traducció i Interpretació -Francès-"/>
                        <form:option value="128" label="Grau universitari en Treball Social"/>
                        <form:option value="129" label="Grau universitari en Turisme"/>
                        <form:option value="130" label="Grau universitari en Veterinària"/>                        
                        <form:option value="131" label="Grau universitari en Òptica i Optometria"/>
                        <form:option value="132" label="Màster"/>
                        <form:option value="133" label="Postgrau"/>
                        <form:option value="134" label="Doctorat"/>                        
                    </form:select>
                </div>
                <div class="form-group">
                    <label for="ocupacio">Elegeix l'ocupació</label>
                    <form:select class="form-control" id="ocupacio" path="ocupacio" required="required">
                        <form:option value="0"> --SELECT--</form:option>
                        <form:option value="1" label="Acoblador elements prefabricats edificis"/>
                        <form:option value="2" label="Acompanyant o persona de companyia"/>
                        <form:option value="3" label="Actor"/>
                        <form:option value="4" label="Adober de cuirs i pells"/>
                        <form:option value="5" label="Adornista de pedra o marbre"/>
                        <form:option value="6" label="Advocat"/>
                        <form:option value="7" label="Agent assegurances"/>
                        <form:option value="8" label="Agent enquestes per telèfon"/>
                        <form:option value="9" label="Agent de la propietat immobiliària"/>
                        <form:option value="10" label="Agent de turisme en viatges"/>
                        <form:option value="11" label="Ajudant esteticista"/>
                        <form:option value="12" label="Ajudant odontologia"/>
                        <form:option value="13" label="Ajudant de perruqueria"/>
                        <form:option value="14" label="Ajustador mecànic de fabricació eines"/>
                        <form:option value="15" label="Analista de sistemes informàtics"/>
                        <form:option value="16" label="Artesà"/>
                        <form:option value="17" label="Auxiliar ajuda a domicili"/>
                        <form:option value="18" label="Auxiliar de laboratori anàlisis clíniques"/>
                        <form:option value="19" label="Biòleg"/>
                        <form:option value="20" label="Bugader a mà en bugaderia"/>
                        <form:option value="21" label="Caixer autoservei"/>
                        <form:option value="22" label="Calefactor lampista"/>
                        <form:option value="23" label="Cambrer de bar"/>
                        <form:option value="24" label="Cangur"/>
                        <form:option value="25" label="Cap de cuina"/>
                        <form:option value="26" label="Cap equip ajustadors equips elèctrics"/>
                        <form:option value="27" label="Comercial de vendes"/>
                        <form:option value="28" label="Comptable en comptabilitat de costos"/>
                        <form:option value="29" label="Conductor ambulància"/>
                        <form:option value="30" label="Conductor"/>
                        <form:option value="31" label="Cuiner"/>
                        <form:option value="32" label="Cultivador"/>
                        <form:option value="33" label="Dependent"/>
                        <form:option value="34" label="Director general empreses privades"/>
                        <form:option value="35" label="Dissenyador"/>
                        <form:option value="36" label="Dona o mosso de neteja oficines"/>
                        <form:option value="37" label="Economista"/>
                        <form:option value="38" label="Educador social"/>
                        <form:option value="39" label="Electricista"/>
                        <form:option value="40" label="Empleat agència de viatges"/>
                        <form:option value="41" label="Enginyer en electrònica"/>
                        <form:option value="42" label="Enginyer en logística"/>
                        <form:option value="43" label="Enginyer tècnic telecomunicacions"/>
                        <form:option value="44" label="Esportista professional"/>
                        <form:option value="45" label="Farmacèutic"/>
                        <form:option value="46.Filoleg" label="Filòleg"/>
                        <form:option value="47" label="Fuster taller tancaments metall"/>
                        <form:option value="48" label="Gerent bar"/>
                        <form:option value="49" label="Gerent empresa"/>
                        <form:option value="50" label="Guia"/>
                        <form:option value="51" label="Historiador"/>
                        <form:option value="52" label="Hostessa i auxiliar de vol"/>
                        <form:option value="53" label="Infermer"/>
                        <form:option value="54" label="Conduccions ventilació"/>
                        <form:option value="55" label="Instrumentista"/>
                        <form:option value="56" label="Joier"/>
                        <form:option value="57" label="Lampista"/>
                        <form:option value="58" label="Locutor de ràdio o televisió"/>
                        <form:option value="59" label="Logopeda"/>
                        <form:option value="60" label="Llegidor de comptadors"/>
                        <form:option value="61" label="Llevadora"/>
                        <form:option value="62" label="Manyà"/>
                        <form:option value="63" label="Maquinista"/>
                        <form:option value="64" label="Matemàtic"/>
                        <form:option value="65" label="Mecànic automòbils turismes i furgonetes"/>
                        <form:option value="66" label="Mestre ensenyament"/>
                        <form:option value="67" label="Metge"/>
                        <form:option value="68" label="Model"/>
                        <form:option value="69" label="Muntador"/>
                        <form:option value="70" label="Músic de sala de festes"/>
                        <form:option value="71" label="Naturopata"/>
                        <form:option value="72" label="Netejador"/>
                        <form:option value="73" label="Odontòleg"/>
                        <form:option value="74" label="Operador"/>
                        <form:option value="75" label="Paleta"/>
                        <form:option value="76" label="Pastisser"/>
                        <form:option value="77" label="Peó"/>
                        <form:option value="78" label="Pintor automòbils"/>
                        <form:option value="79" label="Pintor"/>
                        <form:option value="80" label="Professor classes particulars"/>
                        <form:option value="81" label="Programador informàtic aplicacions de gestió"/>
                        <form:option value="82" label="Programador informàtic nivell mitjà"/>
                        <form:option value="83" label="Químic"/>
                        <form:option value="84" label="Recepcionista"/>
                        <form:option value="85" label="Rellotger"/>
                        <form:option value="86" label="Restaurador obres art"/>
                        <form:option value="87" label="Sabater"/>
                        <form:option value="88" label="Socorrista"/>
                        <form:option value="89" label="Soldador"/>
                        <form:option value="90" label="Tallador"/>
                        <form:option value="91" label="Tastador"/>
                        <form:option value="92" label="Traductor"/>
                        <form:option value="93" label="Treballador social"/>
                        <form:option value="94" label="Venedor a domicili"/>
                        <form:option value="95" label="Veterinari"/>
                        <form:option value="96" label="Vidrier"/>
                        <form:option value="97" label="Xarcuter"/>
                        <form:option value="98" label="Zelador hospitalari"/>
                        <form:option value="99" label="Altres"/>                  
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