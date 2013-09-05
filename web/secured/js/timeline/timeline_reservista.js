/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

        var timeline;
        var data;


          function getSelectedRow() {
            var row = undefined;
            var sel = timeline.getSelection();
            if (sel.length) {
                if (sel[0].row != undefined) {
                    row = sel[0].row;
                }
            }
            return row;
        }



     
        function drawVisualization(jsonName) {
   //$.getJSON(server+"/recursos/test.json", function(dataj) {
   
   
   
   
   /*
    * Realiza la opcion 2 de /TMServlet pues es la que nos devuelve la consulta historica en Json del elemento
    */
$(document).ready(function() {
$.ajaxSetup({ cache: false });
});
     $.ajax(
    {
        url: server+'/TMServlet',
        data: {opc:'2',nom:jsonName},
        success: function(response)
        {
                           var dataj=null;
                           console.log(response);
                           eval("dataj="+ response + ";"); // Codifica a Json 
                           
                           
    var dataSize=dataj.length;
    for(var i=0;i<dataSize;i++){
    dataj[i].start=eval(dataj[i].start);
    dataj[i].end=eval(dataj[i].end);
    console.log("start:" +dataj[i].start +"  end:" + dataj[i].end + "   index:" + i);
   }
       data=dataj;
   
   console.log(data);
            // opciones
            var options = {
                'width':  '100%',
                'height': '300px',
                'editable': true,   // temporal, eventos
                'style': 'box',
                'locale' : 'es'
            };
            

            // Instanciamos
            timeline = new links.Timeline(document.getElementById('mytimeline'));
            function onRangeChanged(properties) {
                document.getElementById('info').innerHTML += 'rangechanged ' +
                        properties.start + ' - ' + properties.end + '<br>';
            }

            // attach an event listener using the links events handler
            links.events.addListener(timeline, 'rangechanged', onRangeChanged);
            links.events.addListener(timeline, 'select', onselect);

            // Draw our timeline with the created data and options
            timeline.draw(data, options);
                           
                           
        }
    });
   
   
  /*
   $.getJSON("recursos/json/"+jsonName+".json", function(dataj) {
    console.log('shialesss---- - - - -');
    var dataSize=dataj.length;
    for(var i=0;i<dataSize;i++){
    dataj[i].start=eval(dataj[i].start);
    dataj[i].end=eval(dataj[i].end);
    console.log("start:" +dataj[i].start +"  end:" + dataj[i].end + "   index:" + i);
    data=dataj;
   }
   
            // specify options
            var options = {
                'width':  '100%',
                'height': '300px',
                'editable': true,   // enable dragging and editing events
                'style': 'box',
                'locale' : 'es'
            };
            

            // Instantiate our timeline object.
            timeline = new links.Timeline(document.getElementById('mytimeline'));

            function onRangeChanged(properties) {
                document.getElementById('info').innerHTML += 'rangechanged ' +
                        properties.start + ' - ' + properties.end + '<br>';
            }

            // attach an event listener using the links events handler
            links.events.addListener(timeline, 'rangechanged', onRangeChanged);
            links.events.addListener(timeline, 'select', onselect);

            // Draw our timeline with the created data and options
            timeline.draw(data, options);
   
   });*/
            
        }
        
        
         // Make a callback function for the select item
        var onselect = function (event) {
            var row = getSelectedRow();
            if (row != undefined) {
                document.getElementById("info").innerHTML += "item " + row + " selected ::  datos: "+ data[row].content +" start: "+data[row].start+"<br>";
                // Note: you can retrieve the contents of the selected row with
                //       data.getValue(row, 2);
            }
            else {
                document.getElementById("info").innerHTML += "no item selected<br>";
            }
            
            alert(" " + row);
        };
