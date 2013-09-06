/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.login;

import com.mb.reservaciones.Path;
import com.reservaciones.controladores.ControladorLogin;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Julius jw0rmc
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
 
    private static final long serialVersionUID = 7765876811740798583L;
  
    private String username;
    private String password;
    private ControladorLogin controladorLogin=new ControladorLogin();
    private boolean loggedIn;
    private String realPath;
    @ManagedProperty(value="#{navigationBean}")
    private NavigationBean navigationBean;
     
    
    
    public LoginBean(){
         
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            System.out.println("PATHREAL: " + ctx.getRealPath("/"));  
            setRealPath(ctx.getRealPath("/"));//Obtenemos la ruta completa al servidor. Para Anular la necesidad de config files
            
    }
    
    
    /**
     * Login operation.
     * @return
     */
    public String doLogin() {
            // Successful login
            if (controladorLogin.LogIn(username, password)) {
                loggedIn = true;
                return navigationBean.redirectToWelcome();
            }
        
         
        // Set login ERROR
        FacesMessage msg = new FacesMessage("Login error!", "NO AUTORIZADO");
        msg.setSeverity(FacesMessage.SEVERITY_FATAL);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    
        // To to login page
        return navigationBean.toLogin();
         
    }
     
    /**
     * Logout operation.
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
         
        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "Saliste Correctamente");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
         
        return navigationBean.toLogin();
    }
 
    // ------------------------------
    // Getters & Setters
     
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public boolean isLoggedIn() {
        return loggedIn;
    }
 
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
 
    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }
    
       public ControladorLogin getControladorLogin() {
        return controladorLogin;
    }

    public void setControladorLogin(ControladorLogin controladorLogin) {
        this.controladorLogin = controladorLogin;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }
     
}
