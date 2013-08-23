/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.usuarios;

import com.reservaciones.controladores.ControladorUsuarios;
import com.reservaciones.mapeos.Usuarios;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DellXps15
 */
@ManagedBean(name = "bajaUsuariosBean")
@SessionScoped
public class BajaUsuariosBean {

    private String matricula;
    private String nombre;
    private String password;
    private String apellidop;
    private String apellidom;
    private String sexo;
    private String fechaNacmiento;
    private String pais;
    private String estado;
    private String puesto;
    private String descripcion;
    private String rol;
    private String fechaInicio;
    private ControladorUsuarios cu=new ControladorUsuarios();
    
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    /**
     * Creates a new instance of AltaUsuarioBean
     */
    public BajaUsuariosBean() {
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacmiento() {
        return fechaNacmiento;
    }

    public void setFechaNacmiento(String fechaNacmiento) {
        this.fechaNacmiento = fechaNacmiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

   public void doBaja(){
   System.out.println("baja==========>>> nombre " + nombre + "   matricula:" + matricula);
        
        
        if(cu.eliminarUsuario(matricula))
        RequestContext.getCurrentInstance().execute("dlg_bajas.hide(); alert('Baja Exitosa')");
        else
        RequestContext.getCurrentInstance().execute("alert('Problema Grave..');");
        
    }
    
}
