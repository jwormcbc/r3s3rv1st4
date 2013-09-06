/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.reservaciones;

import com.mb.login.LoginBean;
import com.reservaciones.controladores.ControladorReservaciones;
import com.reservaciones.mapeos.reserv.Motivo;
import com.reservaciones.mapeos.reserv.ObjetoReservable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DellXps15
 */
@ManagedBean
@RequestScoped
public class ReservacionesBean {
    private String objReservable="ninguno";
    private String motivo="ninguno";
    private int    motivoId;
    private String objDescripcion="ninguno";
    private String objCategoria="ninguna";
    private Date desde;
    private Date hasta;
    private Map<String,String> reservables=new HashMap<String, String>();
    private Map<String,String> motivos=new HashMap<String, String>();
    private ControladorReservaciones  cr;
    private Map<String,Map<String,String>> suburbsData = new HashMap<String, Map<String,String>>();
    private Map<String, ObjetoReservable> objsHM;
    private Map<String, Motivo> mtvsHM;
    
     public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Map<String, String> getMotivos() {
        return motivos;
    }

    public void setMotivos(Map<String, String> motivos) {
        this.motivos = motivos;
    }
    
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
        
        LoginBean loginBean = (LoginBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        Path.PATH=loginBean.getRealPath();
        System.out.println("Path:  " + Path.PATH );
        cr=new ControladorReservaciones();
        
        objsHM=new HashMap<String, ObjetoReservable>();
        objsHM=cr.getObjReserv();
        for(int i=0;i<objsHM.size();i++)
        reservables.put(objsHM.get(""+i).getNombre(), objsHM.get(""+i).getNombre());
        
        mtvsHM=new HashMap<String, Motivo>();
        mtvsHM=cr.getMotivos();
        for(int i=0;i<mtvsHM.size();i++)
        motivos.put( mtvsHM.get(""+i).getDescripcion(),Integer.toString(mtvsHM.get(""+i).getId()));
    
        
        
        
    }
    
        /*
         * Combo de Objeetos reservables
         */
        public void handleValores() {
        for(int i=0;i<objsHM.size();i++)
            if(objsHM.get(""+i).getNombre().equals(objReservable)){ 
                this.objDescripcion=objsHM.get(""+i).getDescripcion();
                this.objCategoria=objsHM.get(""+i).getCategoria().getDescripcion();
            }
        cr.creaHistoricoAJsonTimeline(objReservable); 
            } 
        
        /*
         * Combo de Motivos: estado en el que se pondra el objeto reservable
         */
         public void handleValores2() {
        for(int i=0;i<mtvsHM.size();i++)
            if(Integer.toString(mtvsHM.get(""+i).getId()).equals(motivo)){
                System.out.println("-------> Selecccionado Motivo:  "  +  mtvsHM.get(""+i).getDescripcion() + "  " + mtvsHM.get(""+i).getId());
                motivoId=mtvsHM.get(""+i).getId();
            }
            } 
    
         
     public void altaReservacion(){
         LoginBean loginBean = (LoginBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
         System.out.println("-------> altaeservacion.." + motivoId + "   " +  motivo + "   "  + desde  + "   " +  hasta);
                
                if(cr.altaReservacion(objReservable, Integer.parseInt(motivo), desde, hasta, loginBean.getUsername()))
                     RequestContext.getCurrentInstance().execute("dlg_reservar.hide(); alert('Accion Exitosa'); refreshLinea();");
               else
                     RequestContext.getCurrentInstance().execute("alert('Problema Grave')");
                
    }
}   
