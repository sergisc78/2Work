$(function() {
      
      //
      // Gestió del nombre de camps ampliable pels items d'experiència
      // Mostrem camps ja presents a l'estructura HTML, inicialment ocults per CSS
      //
      
      /** @type {integer} */
      var max_items = 5; // Màxim d'items d'experiència permesos (els que hi ha maquetats a la vista)
      // alert("Nombre d'items d'experiencia "+ $(".item_experiencia").filter(":visible").length);
      
      
      /**
       * Botó afegir camps d'items d'experiència
       */
      $("button#boto_afegir_camps").click(function(e){
            e.preventDefault();
            
            /**
             *  @type {integer} 
             *  Hi desem el nombre d'items d'experiència visibles en aquest moment
             * */
            var visibles = $(".item_experiencia").filter(":visible").length;
            
            // Si no hi ha items visibles, a continuació afegirem el primer item d'experiencia. Per tant  treiem l'avís de zero items
            if(visibles===0) { 
                  $("#avis_zero_items_exp").hide(200);
            } 
            
            // Si encara podem afegir items d'experiència
            if (visibles < max_items) {
                  
                  // Ens carreguem l'opció d'esborrar el que no sigui l'últim
                  $(".item_experiencia#exp_"+(visibles-1)+" a").hide();
                  
                  // Fem visible l'item d'experiència
                  $(".item_experiencia#exp_"+(visibles)).show(200).css('display', 'flex').find("input[type=text]").prop('required',true);
                  
                  // Si la propera vegada no podrem afegir-ne més
                  if (visibles==max_items-1) {
                        // Amaguem el botó d'afegir items
                        $("button#boto_afegir_camps").hide();
                  } 
                  
            }
      });
      
      
      /**
       * Botó esborrar camps d'items d'experiència
       */
      $(".item_experiencia").on("click",".esborrar_item", function(e) {
            e.preventDefault();
            
            /**
            *  @type {integer} 
            *  Hi desem el nombre d'items d'experiència visibles en aquest moment
            * */
            var visibles = $(".item_experiencia").filter(":visible").length;
            
            // Esborrem les dades que hi ha als inputs abans d'amagar-lo.
            $(this).parent('div').find("[type=text]").val("").empty().prop('required',false);
            // Afegim els placeholders            
            $(this).parent('div').find("[id^='exp_temps_']").attr("placeholder","Temps");
            $(this).parent('div').find("[id^='exp_empresa_']").attr("placeholder","Empresa");
            $(this).parent('div').find("[id^='exp_descripcio_']").attr("placeholder","Descripció");
            
            // Amaguem l'item
            $(this).parent('div').hide(200);
            
            // Mostrem l'opció d'esborrar del que ara és ll'últim
            $(".item_experiencia#exp_"+(visibles-2)+" a").show();
            
            // Si la propera vegada podem tornar a afegir més items d'experiència
            if (visibles<=max_items){
                  // Tornem a mostrar el botó d'afegir camps
                  $("button#boto_afegir_camps").show();
            }
            
            // Si ens quedem sense items d'experiència mostrem l'avís
            if(visibles===1) { 
                  $("#avis_zero_items_exp").show(200);
            }
            
      });
       
      
      // Abans d'enviar el contingut del formulari ens carreguem els items d'experiència que no s'han utilitzat
      $("#formulariAltaCandidat,#formulariPerfilCandidat").submit(function() {
            $(this).find("div.item_experiencia").filter(":hidden").remove();
            return true; // perque el formulari s'enviï igualment
      });
      
     
     /**
      * 
      * Actualitza les opcions del select #select-activitats segons el contingut del select #select_ocupacio
      * @returns {undefined}
      */
      var actualitzaHabilitats = function() {
                  
            //var ocupacio_id = $(this).val();
            var ocupacio_id = $("#select_ocupacio").val();
              
            // Fem la crida al mètode del controlador mitjançant Ajax
            $.ajax({
                  type: 'GET',
                  url: "/2Work/getHabilitats/" + ocupacio_id,

                  success: function(data){                     
                        var slctSubcat=$('#select_habilitats'), option="";
                        slctSubcat.empty();

                        $.each(data, function(index, item) {
                              option = option + "<option value='"+item.codiHab +"'>"+item.nomHab + "</option>";
                        }); 

                        slctSubcat.append($.parseHTML(option));
                  },
                  
                  error:function(){
                        alert("Error rebent les dades del servidor");
                  }

            });
      };
      
      
      /**
       * 
       * Omplim el select d'habilitats segons les habilitats corresponents al sector seleccionat al corresponent select
       * Les hem d'anar a buscar a la base de dades mitjançant una crida Ajax
       * @returns {undefined}
       */
      var ompleSelectHabilitats = function () {
            
            var ocupacio_id = $("#select_ocupacio").val();
              
            // Fem la crida al mètode del controlador mitjançant Ajax
            $.ajax({
                  type: 'GET',
                  url: "/2Work/getHabilitats/" + ocupacio_id,

                  success: function(data){                     
                        var slctSubcat=$('#select_habilitats'), option="";
                        slctSubcat.empty();

                        $.each(data, function(index, item) {
                              // La variable habilitatCandidat conté un array d'enters amb els codis de les habilitats incials que tenia el candidat
                              // Al jsp l'agafem de l'atribut i el desem en aquesta variable
                              if (habilitatsCandidat&&habilitatsCandidat.includes(item.codiHab)){
                                    option = option + "<option selected value='"+item.codiHab +"'>"+item.nomHab + "</option>";
                              } else {
                                    option = option + "<option value='"+item.codiHab +"'>"+item.nomHab + "</option>";
                              }
                        }); 

                        slctSubcat.append($.parseHTML(option));
                  },
                  
                  error:function(){
                        alert("Error rebent les dades del servidor");
                  }

            });
            
            
      };
      
      /**
       * 
       *  Generem els camps d'experiència (agafant aquesta informació de la base de dades mitjançant una crida Ajax)
       *  Els injectem a l'html, de manera que siguin editables com la resta del formulari. 
       *  Només aplicable al formulari d'editar les dades del candidat, no a les d'alta de candidat
       * @returns {undefined}
       */
      var ompleExperiencies = function() {
            
            // Fem la crida al mètode del controlador mitjançant Ajax
            $.ajax({
                  type: 'GET',
                  url: "/2Work/getExperiencies/" + codiCandidat,
                  
                  success: function(data) {
                        
                        var numItems=data.length;
                        
                        // Si no hi ha cap element d'experiència
                        if(numItems===0) {
                              $("#avis_zero_items_exp").show(200);
                        }
                        
                        $.each(data, function(index, item) {
                              
                              // Al tanto perque jQuery ens canvia el tag <form:input> per <input>
                              
                              var contingut="";
                              
                              contingut += "<div class='col-sm-6'>";
                              contingut += "<input type='text' class='form-control' id='exp_temps_"+index+"' name='experiencies["+index+"].anys'  value='"+item.anys+"' required='required'></input>";
                              contingut += "</div>";
                              contingut += "<div class='col-sm-6'>";
                              contingut += "<input type='text' class='form-control' id='exp_empresa_"+index+"' name='experiencies["+index+"].nomEmpresa' value='"+item.nomEmpresa+"' required='required'></input>";
                              contingut += "</div>";
                              contingut += "<div class='col-sm-12'>";
                              contingut += "<input type='text' class='form-control' id='exp_descripcio_"+index+"' name='experiencies["+index+"].descripcio' value='"+item.descripcio+"' required='required'></input>";
                              contingut += "</div>";
                              contingut += "<a class='esborrar_item'><small class='text-muted'>Esborrar item d\'experiència</small></a>";
                              
                              $("#exp_"+index).html(contingut);
                              if(index <(numItems-1)){
                                    // Tots excepte el darrer item
                                    $("#exp_"+index+" .esborrar_item").hide();
                              }
                              $("#exp_"+index).show(200).css('display', 'flex').find("input[type=text]").prop('required',true);
                              
                        });
                        
                  },
                  
                  error:function(){
                        alert("Error rebent les dades del servidor");
                  }
                  
            });
            
      };
            
      /**
      * Comprovem si hi ha definida la variable que conté l'array d'habilitats del candidat
      * (Si ho està és que som al formulari de modificació de les dades del candidat, no d'alta)
      */      
      if (typeof habilitatsCandidat !== 'undefined'){      
            // Si aquesta variable està disponible és que no estem a l'alta si no a la modificació de les dades
            // Una altra opció seria comprovar el valor del select #select_ocupacio. Si està buit és que som a alta, sino a editar perfil.
            $("#select_ocupacio").ready(ompleSelectHabilitats);
      }
      
      /**
      * Comprovem si hi ha definida la variable que conté el codi del candidat
      * (Si ho està és que som al formulari de modificació de les dades del candidat, no d'alta)
      */  
      if (typeof codiCandidat !== 'undefined'){
            // Si aquesta variable està disponible és que estem a modificació de dades i hem d'omplir aquests camps amb la info que vé del controlador
            $("#experiencia").ready(ompleExperiencies);
      }
      
      // Si hi ha cap canvi per part de l'usuari en el select #select_ocupacio haurem d'actualitrzar el contingut del d'habilitats
      $("#select_ocupacio").change(actualitzaHabilitats);
          
});