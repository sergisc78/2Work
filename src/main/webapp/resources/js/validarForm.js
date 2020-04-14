
    function validarCand(){
        var nom,cognoms,dniNif,dataNaix,adreca,poblacio,provincia,telefon,email,
                pass,cPass,expressioMail,expressioDNI;
        nom=document.getElementById('nom').value;
        cognoms=document.getElementById('cognoms').value;
        dniNif=document.getElementById('dniNif').value;
        dataNaix=document.getElementById('dataNaix').value;
        adreca=document.getElementById('adreca').value;
        poblacio=document.getElementById('poblacio').value;
        provincia=document.getElementById('provincia').value;
        telefon=document.getElementById('telefon').value;
        email=document.getElementById('email').value;
        pass=document.getElementById('pass').value;
        cPass=document.getElementById('cPass').value;
              
        
        expressioMail=/\w+@\w+\.+[a-z]+/;
        //expressioDNI=/^\d{8}[a-zA-Z]$/;
        expressioDNI=/^\d{8}[T|R|W|A|G|M|Y|F|P|D|X|B|N|J|Z|S|Q|V|H|L|C|K|E|t|r|w|a|g|m|y|f|p|d|x|b|n|j|z|s|q|v|h|l|c|k|e]$/;
        while(nom===''||cognoms===''||dniNif===''||dataNaix===''||adreca===''||poblacio===''||
                provincia===''||telefon===''||pass===''||cPass===''){
            alert("Hi ha camps obligatoris sense omplir");
            return false;
        }
        while(isNaN(telefon)){
            alert('El telèfon no és un número. Esborra-ho i torna a escriure-ho');
            return false;
        }
        while(!expressioMail.test(email)){
            alert('El mail no és vàlid. Ha de contenir text@text.text. Esborra-ho i torna a escriure-ho');
            return false;
        }
        while(!expressioDNI.test (dniNif)){
           alert('El DNI no és vàlid. Ha de contenir 8 caracters i una lletra permesa.Esborra-ho i torna a escriure-ho');
           return false;
        }
        while(pass !== cPass) {
            alert("Els passwords han de coincidir. Esborra-ho i torna a escriure-ho");
            return false;
        }
    
    }
    function validarEmp(){
        var nom,responsable,dniNif,adreca,poblacio,provincia,telefon,email,
               pass,cPass,tamany,expressioMail;
        nom=document.getElementById('nom').value;
        responsable=document.getElementById('responsable').value;
        dniNif=document.getElementById('dniNif').value;
        adreca=document.getElementById('adreca').value;
        poblacio=document.getElementById('poblacio').value;
        provincia=document.getElementById('provincia').value;
        telefon=document.getElementById('telefon').value;
        email=document.getElementById('email').value;
        pass=document.getElementById('pass').value;
        cPass=document.getElementById('cPass').value;       
        tamany=document.getElementById('tamany').value;
        
        
        expressioMail=/\w+@\w+\.+[a-z]+/;
        
        while(nom===''||responsable===''||dniNif===''||adreca===''||poblacio===''||provincia===''||
                telefon===''||pass===''||cPass===''){
            alert("Hi ha camps obligatoris sense omplir");
            return false;
        }
        while(isNaN(telefon)){
            alert('El telèfon no és un número. Esborra-ho i torna a escriure-ho');
            return false;
        }
        while(isNaN(tamany)){
            alert('El tamany no és un número. Esborra-ho i torna a escriure-ho');
            return false;            
        }
        while(!expressioMail.test(email)){
            alert('El mail no és vàlid. Ha de contenir text@text.text. Esborra-ho i torna a escriure-ho');
            return false;
        }
        while (pass !== cPass) {
            alert("Els passwords han de coincidir. Esborra-ho i torna a escriure-ho");
            return false;
        }
    }
    function validarOferta(){
        
        var nifEmpresa,titolOferta,poblacio,provincia,sou,horari;  

            // codiOferta=document.getElementById('codiOferta').value;
            nifEmpresa=document.getElementById('nifEmpresa').value;
            titolOferta=document.getElementById('titolOferta').value;
            poblacio=document.getElementById('poblacio').value;
            provincia=document.getElementById('provincia').value;
            sou=document.getElementById('sou').value;
            horari=document.getElementById('horari').value;
            tipusContracte=document.getElementById('select_tipusContracte').value;
            
        while(nifEmpresa===''||titolOferta===''||poblacio===''||
                provincia===''||sou===''||horari===''){
            alert("Hi ha camps obligatoris sense omplir");
            return false;
        }
    }
    



