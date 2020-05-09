/*
 *
 * Gestió de l'avís sobre l'acceptació de cookies
 * Basat en la proposta de Wruczek (https://github.com/Wruczek/Bootstrap-Cookie-Alert)
 * 
 */

(function () {
      
    "use strict";

    var cookieAlert = document.querySelector(".avisgaletes");
    var acceptCookies = document.querySelector(".acceptagaletes"); 

    if (!cookieAlert) { 
       return;
    }

    cookieAlert.offsetHeight; // Force browser to trigger reflow (https://stackoverflow.com/a/39451131)

    // Si no trobem la galeta 'acceptCookies' mostrem l'alerta.
    if (!getCookie("acceptaGaletes")) {
        cookieAlert.classList.add("show");
    }

    // Quan premem el botó "D'acord" generem una cookie d'1 any de duració
    // per recordar l'elecció de l'usuari i tancar l'avís
    acceptCookies.addEventListener("click", function () {
        setCookie("acceptaGaletes", true, 365);
        cookieAlert.classList.remove("show");
    });

    // Funcions relacionades amb les galates, de w3schools
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    function getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }
    
})();