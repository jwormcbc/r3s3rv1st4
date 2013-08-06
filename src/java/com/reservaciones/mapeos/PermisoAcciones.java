package com.reservaciones.mapeos;
// Generated 30/07/2013 03:44:33 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PermisoAcciones generated by hbm2java
 */
public class PermisoAcciones  implements java.io.Serializable {


     private int id;
     private String nombre;
     private Long mascara;
     private String descripcion;
     private Date fechacreacion;
     private Set permisosAccioneses = new HashSet(0);

    public PermisoAcciones() {
    }

	
    public PermisoAcciones(int id) {
        this.id = id;
    }
    public PermisoAcciones(int id, String nombre, Long mascara, String descripcion, Date fechacreacion, Set permisosAccioneses) {
       this.id = id;
       this.nombre = nombre;
       this.mascara = mascara;
       this.descripcion = descripcion;
       this.fechacreacion = fechacreacion;
       this.permisosAccioneses = permisosAccioneses;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Long getMascara() {
        return this.mascara;
    }
    
    public void setMascara(Long mascara) {
        this.mascara = mascara;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechacreacion() {
        return this.fechacreacion;
    }
    
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }
    public Set getPermisosAccioneses() {
        return this.permisosAccioneses;
    }
    
    public void setPermisosAccioneses(Set permisosAccioneses) {
        this.permisosAccioneses = permisosAccioneses;
    }




}

