/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.reservaciones;

import com.reservaciones.controladores.ControladorReservaciones;
import com.reservaciones.mapeos.Roles;
import com.reservaciones.mapeos.reserv.ObjetoReservable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author DellXps15
 */
@ManagedBean
@RequestScoped
public class ReservacionesBean {
    private String objReservable="ninguno";
    private String objDescripcion="ninguno";
    private String objCategoria="ninguna";
    private Date desde;
    private Date hasta;
    private Map<String,String> reservables=new HashMap<String, String>();
    private ControladorReservaciones cr=new ControladorReservaciones();
    private Map<String,Map<String,String>> suburbsData = new HashMap<String, Map<String,String>>();
    private Map<String, ObjetoReservable> objsHM;
    
    
     public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
    
     public String getObjCategoria() {
        return objCategoria;
    }

    public void setObjCategoria(String objCategoria) {
        this.objCategoria = objCategoria;
    }
    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    
    public String getObjReservable() {
        return objReservable;
    }

      public String getObjDescripcion() {
        return objDescripcion;
    }

    public void setObjDescripcion(String objDescripcion) {
        this.objDescripcion = objDescripcion;
    }
    
    public void setObjReservable(String objReservable) {
        this.objReservable = objReservable;
    }

    public Map<String, String> getReservables() {
        return reservables;
    }

    public void setReservables(Map<String, String> reservables) {
        this.reservables = reservables;
    }

    public ControladorReservaciones getCr() {
        return cr;
    }

    public void setCr(ControladorReservaciones cr) {
        this.cr = cr;
    }

    public ReservacionesBean() {
        objsHM=new HashMap<String, ObjetoReservable>();
        objsHM=cr.getObjReserv();
        for(int i=0;i<objsHM.size();i++)
        reservables.put(objsHM.get(""+i).getNombre(), objsHM.get(""+i).getNombre());
        
    
    }
    
    
        public void handleValores() {
        for(int i=0;i<objsHM.size();i++)
            if(objsHM.get(""+i).getNombre().equals(objReservable)){ 
                this.objDescripcion=objsHM.get(""+i).getDescripcion();
                this.objCategoria=objsHM.get(""+i).getCategoria().getDescripcion();
            }
    
        /*
         * Crea Json que sera consumido para e timeline del objeto.
         */
        cr.creaHistoricoAJsonTimeline(objReservable); 
        
        } 
    
     public void mensaje(){
    }
}   
