/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservaciones.controladores;

import com.mb.login.LoginBean;
import com.mb.reservaciones.Path;
import com.reservaciones.daos.ReservacionesHelper;
import com.reservaciones.jsoneitor.JsonSimple;
import com.reservaciones.mapeos.Roles;
import com.reservaciones.mapeos.reserv.Historico;
import com.reservaciones.mapeos.reserv.Motivo;
import com.reservaciones.mapeos.reserv.ObjetoReservable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author DellXps15
 */
public class ControladorReservaciones {
    ReservacionesHelper rh=new ReservacionesHelper();
    //JsonSimple jsoneitor=new JsonSimple("C:\\Lanconta\\Proyectos\\JSFLogin\\web\\secured\\recursos\\json\\");
    //JsonSimple jsoneitor=new JsonSimple("/usr/local/tomcat7/webapps/Lanconta_Reservista/secured/recursos/json/");
    JsonSimple jsoneitor;
        
    public static void main(String args[]){
        Path.PATH="C:\\Lanconta\\Proyectos\\JSFLogin\\web\\secured\\recursos\\json\\";
        ControladorReservaciones cr=new ControladorReservaciones();
    }
    
    public ControladorReservaciones(){
    jsoneitor=new JsonSimple(com.mb.reservaciones.Path.PATH+"secured\\recursos\\json\\");
    }
    
    public Map<String,ObjetoReservable> getObjReserv(){
                    List<ObjetoReservable> roles=rh.getObjReservables();
                    Map<String,ObjetoReservable> mobjs=new HashMap<String, ObjetoReservable>();

                    for(int i=0;i<roles.size();i++)
                    {
                    mobjs.put(""+i,roles.get(i));
                    //System.out.println("id:"+mobjs.get(""+i).getId() +"     "+mobjs.get(""+i).getNombre() + "   desc: "+mobjs.get(""+i).getDescripcion());
                    }
                    return mobjs;
                    }
    
    public Map<String, Motivo> getMotivos(){
                    List<Motivo> roles=rh.getMotivos();
                    Map<String,Motivo> mobjs=new HashMap<String, Motivo>();

                    for(int i=0;i<roles.size();i++)
                    {
                    mobjs.put(""+i,roles.get(i));
                    //System.out.println("id:"+mobjs.get(""+i).getId() +"     "+mobjs.get(""+i).getNombre() + "   desc: "+mobjs.get(""+i).getDescripcion());
                    }
                    return mobjs;
                    }
    
    public boolean creaHistoricoAJsonTimeline(String nombre){
    
        if(jsoneitor.escribidor(rh.getObjHistorico(rh.getObjReservableIDByName(nombre)),nombre))
            return true;
        else 
            return false;
    
    }
    
      public String consultaHistoricoAJsonTimeline(String nombre){
    
        if(jsoneitor.escribidor(rh.getObjHistorico(rh.getObjReservableIDByName(nombre)),nombre))
            return jsoneitor.leedor(nombre);
        else 
            return "Err. consultaHistoricoJsonTimelLine!";
    
    }
    
    public boolean altaReservacion(String objres,int id_motivo,Date desde,Date hasta,String matricula){
    
        if(rh.altaReserva(rh.getObjReservableIDByName(objres),id_motivo,desde, hasta,matricula))
            return true;
        
    return false;
    }
    
}
