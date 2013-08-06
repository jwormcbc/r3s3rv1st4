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

/**
 *
 * @author DellXps15
 */
@ManagedBean(name = "actualizacionUsuarioBean")
@SessionScoped
public class ActualizacionUsuariosBean {

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
    public ActualizacionUsuariosBean() {
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

    public void doConsulta(){
    
        
    FacesMessage messageOK;
    FacesMessage messageERR;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
   
    if(matricula.length()>0 && matricula!=null){
    System.out.println("==consultando====>>>"+matricula);
        messageERR = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error Problema al consultar usuario","err realizado consulta");
        FacesContext.getCurrentInstance().addMessage(null, messageERR); 
        
        Usuarios u=cu.buscarUsuario(matricula);
        this.nombre=u.getNombre();
        this.apellidop=u.getApellido1();
        this.apellidom=u.getApellido2();
        this.sexo=u.getSexo();
        this.fechaNacmiento=dateFormat.format(u.getFechanacimiento());
        this.pais=u.getPais();
        this.estado=u.getEstado();
        this.puesto=u.getPuesto();
        this.descripcion=u.getDescripcion();
        this.rol=cu.buscarRolDeUsuario(matricula);
        this.fechaInicio=""+dateFormat.format(u.getFechacreacion());
        
        
        
        System.out.println("==vals====>>>"+nombre + "  " + apellidop + fechaInicio);
        
         FacesContext.getCurrentInstance().release();
        messageOK = new FacesMessage(FacesMessage.SEVERITY_INFO, "OK Consulta de usuario correcta","consulta realizada");
        FacesContext.getCurrentInstance().addMessage(null, messageOK);
    }else{
        FacesContext.getCurrentInstance().release();
        messageOK = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Matricula vacia o incorrecta"," vuelve a ingresar el dato");
        FacesContext.getCurrentInstance().addMessage(null, messageOK);
    }
    
    }
    
}
