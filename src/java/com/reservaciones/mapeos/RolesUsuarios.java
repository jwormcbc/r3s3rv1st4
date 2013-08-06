package com.reservaciones.mapeos;
// Generated 30/07/2013 03:44:33 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * RolesUsuarios generated by hbm2java
 */
public class RolesUsuarios  implements java.io.Serializable {


     private int id;
     private Usuarios usuarios;
     private Roles roles;
     private Date fechacreacion;

    public RolesUsuarios() {
    }

	
    public RolesUsuarios(int id) {
        this.id = id;
    }
    public RolesUsuarios(int id, Usuarios usuarios, Roles roles, Date fechacreacion) {
       this.id = id;
       this.usuarios = usuarios;
       this.roles = roles;
       this.fechacreacion = fechacreacion;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public Roles getRoles() {
        return this.roles;
    }
    
    public void setRoles(Roles roles) {
        this.roles = roles;
    }
    public Date getFechacreacion() {
        return this.fechacreacion;
    }
    
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }




}

