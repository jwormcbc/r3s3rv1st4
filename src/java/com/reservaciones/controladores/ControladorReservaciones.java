/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservaciones.controladores;

import com.reservaciones.daos.ReservacionesHelper;
import com.reservaciones.jsoneitor.JsonSimple;
import com.reservaciones.mapeos.Roles;
import com.reservaciones.mapeos.reserv.Historico;
import com.reservaciones.mapeos.reserv.ObjetoReservable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DellXps15
 */
public class ControladorReservaciones {
    ReservacionesHelper rh=new ReservacionesHelper();
    JsonSimple jsoneitor=new JsonSimple("C:\\Lanconta\\Proyectos\\JSFLogin\\web\\secured\\recursos\\json");
    
    public static void main(String args[]){
    
        ControladorReservaciones cr=new ControladorReservaciones();
        cr.creaHistoricoAJsonTimeline("habitacion2");
        
        
    }
    
    
    public Map<String,ObjetoReservable> getObjReserv(){
                    List<ObjetoReservable> roles=rh.getObjReservables();
                    Map<String,ObjetoReservable> mobjs=new HashMap<String, ObjetoReservable>();

                    for(int i=0;i<roles.size();i++)
                    {
                    mobjs.put(""+i,roles.get(i));
                    System.out.println("id:"+mobjs.get(""+i).getId() +"     "+mobjs.get(""+i).getNombre() + "   desc: "+mobjs.get(""+i).getDescripcion());
                    }
                    return mobjs;
                    }
    
    public boolean creaHistoricoAJsonTimeline(String nombre){
    
        if(jsoneitor.escribidor(rh.getObjHistorico(rh.getObjReservableIDByName(nombre)),nombre))
            return true;
        else 
            return false;
    
    }
    
    
    
    public boolean altaReservacion(int id_objres,int id_motivo,Date dede,Date hasta){
    
        if(rh.altaReserva(id_objres,id_motivo,dede, hasta))
            return true;
        
    return false;
    }
    
}
