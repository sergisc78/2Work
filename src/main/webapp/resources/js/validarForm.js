
    function validarCand(){
        var nom,cognoms,dniNif,dataNaix,adreca,ciutat,provincia,telefon,email,
                observacions,pass,cPass,expressioMail,expressioDNI;
        nom=document.getElementById('nom').value;
        cognoms=document.getElementById('cognoms').value;
        dniNif=document.getElementById('dniNif').value;
        dataNaix=document.getElementById('dataNaix').value;
        adreca=document.getElementById('adreca').value;
        ciutat=document.getElementById('ciutat').value;
        provincia=document.getElementById('provincia').value;
        telefon=document.getElementById('telefon').value;
        email=document.getElementById('email').value;
        observacions=document.getElementById('observacions').value;
        pass=document.getElementById('pass').value;
        cPass=document.getElementById('cPass').value;
              
        
        expressioMail=/\w+@\w+\.+[a-z]+/;
        //expressioDNI=/^\d{8}[a-zA-Z]$/;
        expressioDNI=/^\d{8}[T|R|W|A|G|M|Y|F|P|D|X|B|N|J|Z|S|Q|V|H|L|C|K|E|t|r|w|a|g|m|y|f|p|d|x|b|n|j|z|s|q|v|h|l|c|k|e]$/;
        if(nom===''||cognoms===''||dniNif===''||dataNaix===''||adreca===''||ciutat===''||
                provincia===''||telefon===''||pass===''||cPass===''){
            alert("Hi ha camps obligatoris sense omplir");
            return false;
        }else if(nom.length>20){
            alert('El nom és massa llarg. Màxim 20 caracters');
            return false;
        }else if(cognoms.length>50){
            alert('El cognom és massa llarg. Màxim 50 caracters');
            return false;
        }else if(dniNif.length>9){
            alert('El dni és massa llarg. Màxim 9 caracters');
            return false;
        }else if(dataNaix.length>10){
            alert('La data de naxement és massa llarga. Màxim 10 caracters');
            return false;
        }else if(adreca.length>50){
            alert('Adreça és massa llarga. Màxim 50 caracters');
            return false;
        }else if(ciutat.length>50){
            alert('Ciutat és massa llarga. Màxim 50 caracters');
            return false;
        }else if(provincia.length>50){
            alert('Provincia és massa llarga. Màxim 50 caracters');
            return false;
        }else if(telefon.length>12){
            alert('El telèfon és massa llarg. Màxim 12 caracters');
            return false;
        }else if(email.length>20){
            alert('El mail és massa llarg. Màxim 20 caracters');
            return false;
        }else if(observacions.length>500){
            alert('A les observacions màxim 500 caracters');
            return false;
        }else if(pass.length>8){
            alert('El password és massa llarg. Màxim 8 caracters');
            return false;
        }else if(cPass.length>8){
            alert('El control de password és massa llarg. Màxim 8 caracters');
            return false;
        }else if(isNaN(telefon)){
            alert('El telèfon no és un número');
            return false;
        }else if(!expressioMail.test(email)){
            alert('El mail no és vàlid. Ha de contenir text@text.text');
            return false;
        }else if(!expressioDNI.test (dniNif)){
           alert('El DNI no és vàlid. Ha de contenir 8 caracters i una lletra permesa.Esborra-ho i torna a escriure-ho');
           return false;
        }else if (pass !== cPass) {
            alert("Els passwords han de coincidir");
            return false;
        }
    
    }
    function validarEmp(){
        var nom,responsable,dniNif,adreca,ciutat,provincia,telefon,email,
                observacions,pass,cPass,web,tamany,expressioMail;
        nom=document.getElementById('nom').value;
        responsable=document.getElementById('responsable').value;
        dniNif=document.getElementById('dniNif').value;
        adreca=document.getElementById('adreca').value;
        ciutat=document.getElementById('ciutat').value;
        provincia=document.getElementById('provincia').value;
        telefon=document.getElementById('telefon').value;        
        email=document.getElementById('email').value;
        observacions=document.getElementById('observacions').value;
        pass=document.getElementById('pass').value;
        cPass=document.getElementById('cPass').value;       
        web=document.getElementById('web').value;
        tamany=document.getElementById('tamany').value;
        
        
        expressioMail=/\w+@\w+\.+[a-z]+/;
        
        if(nom===''||responsable===''||dniNif===''||adreca===''||ciutat===''||provincia===''||
                telefon===''||pass===''||cPass===''){
            alert("Hi ha camps obligatoris sense omplir");
            return false;
        }else if(nom.length>20){
            alert('El nom és massa llarg. Màxim 20 caracters.');
            return false;
        }else if(responsable.length>50){
            alert('El responsable és massa llarg. Màxim 50 caracters.');
            return false;
        }else if(dniNif.length>9){
            alert('El nif és massa llarg. Màxim 9 caracters');
            return false;
        }else if(adreca.length>50){
            alert('Adreça és massa llarga. Màxim 50 caracters');
            return false;
        }else if(ciutat.length>50){
            alert('Ciutat és massa llarga. Màxim 50 caracters');
            return false;
        }else if(provincia.length>50){
            alert('Provincia és massa llarga. Màxim 50 caracters');
            return false;
        }else if(telefon.length>20){
            alert('El telèfon és massa llarg. Màxim 12 caracters');
            return false;   
        }else if(web.length>30){
            alert('La web és massa llarga. Màxim 30 caracters');
            return false;   
        }else if(email.length>50){
            alert('El mail és massa llarg. Màxim 50 caracters');
            return false;
        }else if(observacions.length>500){
            alert('A les observacions màxim 500 caracters');
            return false;
        }else if(pass.length>8){
            alert('El password és massa llarg. Màxim 8 caracters');
            return false;
        }else if(cPass.length>8){
            alert('El control de password és massa llarg. Màxim 8 caracters');
            return false;
        }else if(isNaN(telefon)){
            alert('El telèfon no és un número');
            return false;
        }else if(isNaN(tamany)){
            alert('El tamany no és un número');
            return false;            
        }else if(!expressioMail.test(email)){
            alert('El mail no és vàlid. Ha de contenir text@text.text');
            return false;
        }else if (pass !== cPass) {
            alert("Els passwords han de coincidir");
            return false;
        }
    }
    function validarOferta(){
        
        var codiOferta,nifEmpresa,titolOferta,ciutat,provincia,sou,horari,
            tipusContracte,estat; 
    
            codiOferta=document.getElementById('codiOferta').value;
            nifEmpresa=document.getElementById('nifEmpresa').value;
            titolOferta=document.getElementById('titolOferta').value;
            ciutat=document.getElementById('ciutat').value;
            provincia=document.getElementById('provincia').value;
            sou=document.getElementById('sou').value;
            horari=document.getElementById('horari').value;
            tipusContracte=document.getElementById('tipusContracte').value;
            estat=document.getElementById('estat').value;
            
        if(nifEmpresa===''||titolOferta===''||ciutat===''||
                provincia===''||sou===''||horari===''){
            alert("Hi ha camps obligatoris sense omplir");
            return false;
        }else if(nifEmpresa.length>9){
            alert('El nif és massa llarg. Màxim 9 caracters.');
            return false;
        }else if(titolOferta.length>50){
            alert('El titol és massa llarg. Màxim 50 caracters.');
            return false;
        }else if(ciutat.length>50){
            alert('La ciutat és massa llarga. Màxim 50 caracters.');
            return false;
        }else if(provincia.length>50){
            alert('La provincia és massa llarga. Màxim 50 caracters.');
            return false;
        }else if(horari.length>15){
            alert('Horari és massa llarg. Màxim 15 caracters.');
            return false;
        }     
    }
    



