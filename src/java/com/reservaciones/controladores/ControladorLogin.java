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
    System.out.println("id  " + u.getId() +"  matricula  " + u.getMatricula() + "  password: " + u.getPassword());
    if(u.getMatricula().equals(matricula) && u.getPassword().equals(pass))
    return true;
    }
    return false;
    
    }
    
    public String GetPermisos(String matricula){
    LoginHelper lh= new LoginHelper();// desde hibernate helper
    PermisoAcciones p=lh.findUserPermisos(matricula);
    System.out.println("id  " + p.getId() +" Nombre:  " + p.getNombre() + "  Mask: " + p.getMascara() + " Descripcion:" + p.getDescripcion());
    return p.getMascara().toString();
    }
    
    
}
