/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.usuarios;

import com.mb.login.LoginBean;
import com.reservaciones.controladores.ControladorUsuarios;
import com.reservaciones.mapeos.Roles;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DellXps15
 */
@ManagedBean
@RequestScoped
public class AltaUsuarioBean {
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
    private String permiso;

    private Map<String,String> permisos=new HashMap<String, String>();
    private Map<String,String> roles=new HashMap<String, String>();
    private ControladorUsuarios cu=new ControladorUsuarios();
    private Map<String,Map<String,String>> suburbsData = new HashMap<String, Map<String,String>>();
    /**
     * Creates a new instance of AltaUsuarioBean
     */
    
        public AltaUsuarioBean() {
        
        Map<String, Roles> rolesHM=new HashMap<String, Roles>();
        rolesHM=cu.getRoles();
        for(int i=0;i<rolesHM.size();i++){
        roles.put(rolesHM.get(""+i).getNombre(), rolesHM.get(""+i).getNombre());
        
        }
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
    
    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }
    
    
    public Map<String, String> getPermisos() {
        return permisos;
    }

    public void setPermisos(Map<String, String> permisos) {
        this.permisos = permisos;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }

    public void doAlta(){
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    System.out.println("=================>>>"+dateFormat.format(date) +"  nombre " + nombre + "   fn:" + fechaNacmiento);
        
        
        if(cu.altaUsuario(matricula, nombre, password, apellidop, apellidom, sexo, fechaNacmiento, pais, estado, puesto, descripcion,dateFormat.format(date),rol))
        RequestContext.getCurrentInstance().execute("dlg_altas.hide(); alert('Alta Exitosa')");
        else
        RequestContext.getCurrentInstance().execute("alert('Problema Grave..');");
        
    }
    
    public void handleCityChange() { 
        Map<String,String>  mUtil=new HashMap<String, String>();
        suburbsData.clear();
        mUtil.put(cu.getPermisoNombreByNombreRol(rol), rol+"mas");
        suburbsData.put("perms",mUtil);
        
        if(rol !=null && !rol.equals(""))
            permisos = suburbsData.get("perms");  
        else  
            permisos = new HashMap<String, String>();  
    
        
    
    }  
    
    
}
