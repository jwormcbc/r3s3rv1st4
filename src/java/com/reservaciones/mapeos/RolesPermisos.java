package com.reservaciones.mapeos;
// Generated 30/07/2013 03:44:33 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * RolesPermisos generated by hbm2java
 */
public class RolesPermisos  implements java.io.Serializable {


     private int id;
     private Roles roles;
     private Permisos permisos;
     private Date fechacreacion;

    public RolesPermisos() {
    }

	
    public RolesPermisos(int id) {
        this.id = id;
    }
    public RolesPermisos(int id, Roles roles, Permisos permisos, Date fechacreacion) {
       this.id = id;
       this.roles = roles;
       this.permisos = permisos;
       this.fechacreacion = fechacreacion;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Roles getRoles() {
        return this.roles;
    }
    
    public void setRoles(Roles roles) {
        this.roles = roles;
    }
    public Permisos getPermisos() {
        return this.permisos;
    }
    
    public void setPermisos(Permisos permisos) {
        this.permisos = permisos;
    }
    public Date getFechacreacion() {
        return this.fechacreacion;
    }
    
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }




}


