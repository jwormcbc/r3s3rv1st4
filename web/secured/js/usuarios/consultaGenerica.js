/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 function callServlet(){
                
               //$("#form_consultas\\:nombre").val("fdsdf");
               alert($('#form_actualizaciones\\:matricula1').val());
               //var serverA="http://localhost:8084/JSFLogin/ServiciosReservista"; serverP
               //var serverA="http://54.213.21.117:8080/Lanconta_Reservista/ServiciosReservista"
               
            $.ajax(
    	    {
    	        url: serverP,
    	        data: {matricula:$('#form_actualizaciones\\:matricula').val()},
    	        success: function(response)
    	        {
                        
                         $("#form_actualizaciones\\:nombre").val(response.split(",")[0]);
                         $("#form_actualizaciones\\:apellidop").val(response.split(",")[1]);
                         $("#form_actualizaciones\\:apellidom").val(response.split(",")[2]);
                         if(response.split(",")[3] === 'h')
                         $("#form_actualizaciones\\:sexo").val('Hombre');
                         else if(response.split(",")[3] === 'm')
                         $("#form_actualizaciones\\:sexo").val('Mujer');
                         else
                         $("#form_actualizaciones\\:sexo").val('-');
                     
                         $("#form_actualizaciones\\:fechanacimiento").val(response.split(",")[4]);
                         $("#form_actualizaciones\\:pais").val(response.split(",")[5]);
                         $("#form_actualizaciones\\:estado").val(response.split(",")[6]);
                         $("#form_actualizaciones\\:puesto").val(response.split(",")[7]);
                         $("#form_actualizaciones\\:descripcion").val(response.split(",")[8]);
                         $("#form_actualizaciones\\:rol").val(response.split(",")[9]);
                         $("#form_actualizaciones\\:fechaalta").val(response.split(",")[10]);
		    	 
    	        }//success
	     });//.ajax
               
            }
