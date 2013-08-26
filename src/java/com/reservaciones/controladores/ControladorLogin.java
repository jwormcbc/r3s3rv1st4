/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservaciones.controladores;

import com.reservaciones.daos.*;
import com.reservaciones.mapeos.*;
/**
 *
 * @author DellXps15
 */

public class ControladorLogin {
    
    public static void main(String args[]){
    ControladorLogin c=new ControladorLogin();
    c.GetPermisos("060510");
    
    }
       
    public boolean LogIn(String matricula,String pass){
    LoginHelper lh= new LoginHelper();// desde hibernate helper
    for(Usuarios u: lh.findAllUser()){
    if(u.getMatricula().equals(matricula) && u.getPassword().equals(pass))
    return true;
    }
    return false;
    
    }
    
    public String GetPermisos(String matricula){
    LoginHelper lh= new LoginHelper();// desde hibernate helper
    PermisoAcciones p=lh.findUserPermisos(matricula);
    return p.getMascara().toString();
    }
    
    
}
