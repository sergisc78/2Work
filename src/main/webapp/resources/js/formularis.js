$(function() {
      
      //
      // Gestió del nombre de camps ampliable pels items d'experiència
      // Mostrem camps ja presents a l'estructura HTML, inicialment ocults
      //
      
      var max_items = 5; // Màxim d'items d'experiència permesos (els que hi ha maquetats a la vista)
      var x = 0; // inicialitzem el comptador
      
      // Botó afegir camps d'items d'experiència
      $("button#boto_afegir_camps").click(function(e){
            e.preventDefault();
            
            if (x < max_items) {
                  // Ens carreguem l'opció d'esborrar el que no sigui l'últim
                  if (x < max_items-1) {
                        $(".item_experiencia#exp_"+x+" a").hide();
                  }
                  
                  x++;
                  
                  // Fem visible l'item d'experiència
                  $(".item_experiencia#exp_"+x).show(200).css('display', 'flex').find("input[type=text]").prop('required',true);
                  
                  if (x==max_items-1) {
                        // Amaguem el botó d'afegir items
                        $("button#boto_afegir_camps").hide();
                  } 
                  
            }
      });
      
      
      // Botó esborrar camps d'items d'experiència
      $(".item_experiencia").on("click",".esborrar_item", function(e) {
            e.preventDefault();
            
            // Esborrem les dades que hi ha als inputs abans d'amagar-lo.
            // Deixem els inputs que amagarem amb l'atribut 'disabled' (perque no s'enviïn amb valors "" al servidor).
            $(this).parent('div').find("input[type=text]").val("").prop('required',false);
                        
            // Amaguem l'item
            $(this).parent('div').hide(200);
            
            // Mostrem l'opció d'esborrar del que ara és ll'últim
            $(".item_experiencia#exp_"+(x-1)+" a").show();
            x--;
            
            if (x<max_items-1){
                  // Tornem a mostrar el botó d'afegir camps
                  $("button#boto_afegir_camps").show();
            }
            
      });
       
      
      // Abans d'enviar el contingut del formulari ens carreguem els items d'experiència que no s'han utilitzat
      $("#formulariAltaCandidat").submit(function() {
            $(this).find("div.item_experiencia").filter(":hidden").remove();
            return true; // perque el formulari s'enviï igualment
      });
      
      

      //
      // Omplir select d'habilitats segons la ocupació seleccionada al select anterior
      // 
      
      $("#select_ocupacio").change(function(){
            // Quan hi hagi canvis al select_ocupació...
            
            var ocupacio_id = $(this).val();
              
            // Fem la crida al mètode del controlador mitjançant Ajax
            $.ajax({
                  type: 'GET',
                  url: "getHabilitats/" + ocupacio_id,
                  
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
      });
         
    
});