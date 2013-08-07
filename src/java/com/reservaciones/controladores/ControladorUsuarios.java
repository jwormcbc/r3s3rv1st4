/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservaciones.controladores;

import com.reservaciones.daos.LoginHelper;
import com.reservaciones.daos.UsuariosHelper;
import com.reservaciones.mapeos.PermisoAcciones;
import com.reservaciones.mapeos.Roles;
import com.reservaciones.mapeos.Usuarios;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DellXps15
 */
public class ControladorUsuarios {
        private UsuariosHelper uh=new UsuariosHelper();
        private static UsuariosHelper uhTest=new UsuariosHelper();
        
        public static void main(String args[]){
        
           /*Usuarios u=uhTest.findUser("8");
           try{
            System.out.println("--->>>" + u.getNombre() + u.getMatricula());
           }catch(NullPointerException e){
            System.out.println("---> Usuario no encontrado :" + e.toString());
           }*/
            
            ControladorUsuarios cu=new ControladorUsuarios();
            //cu.getRoles();
            //cu.actualizacionUsuarios("000000", "julio", "rivera", "rojas", "sexo", "1900-09-09", "pais", "estado", "puesto", "descripcion","CRUD-RWX1");
           // System.out.println("" + cu.actualizaRol("CRUD-RWX2", "000000"));
        }
        
      public boolean altaUsuario(String matricula,String nombre,String password,String apellidop,String apellidom,String sexo,
                                 String fechaNacimiento,String pais,String estado,String puesto,String descripcion,
                                 String fechaCreacion,String rol_nombre){
       
      return uh.altaUsuarios(matricula,nombre,password,apellidop,apellidom,sexo,
                                 fechaNacimiento,pais,estado,puesto,descripcion,
                                 fechaCreacion,rol_nombre);
          
       }
       
      
      public boolean actualizacionUsuarios(String matricula,String nombre,String apellidop,String apellidom,String sexo,
                                 String fechaNacimiento,String pais,String estado,String puesto,String descripcion,String rol){
       
      return uh.doActualizacionDeUsuario(matricula, nombre, apellidop, apellidom, sexo, fechaNacimiento, pais, estado, puesto, descripcion,rol);
          
       }
      
       public Usuarios buscarUsuario(String matricula){
           Usuarios u=uh.findUser(matricula);
           try{
           System.out.println("--->>>" + u.getNombre() + u.getMatricula());
           }catch(NullPointerException e){
           System.out.println("--->>> Usuario no encontrado :" + e.toString());
           }
           
           return u;
       }
       
         public String buscarRolDeUsuario(String matricula){
           String u=uh.getRolDeUsuario(matricula);
           if(!u.equals("0")){
           System.out.println("--rol id ->>>" + u);
           }else{
           System.out.println("--->>> Rol de Usuario no encontrado :");
           }
           
           return u;
       }
         
          public Map<String,Roles> getRoles(){
                    List<Roles> roles=uh.getRoles();
                    Map<String,Roles> mrols=new HashMap<String, Roles>();

                    for(int i=0;i<roles.size();i++)
                    {
                    mrols.put(""+i,roles.get(i));
                     //System.out.println(""+mrols.get(""+i).getNombre());
                    }
                    return mrols;
                    }
         
        public String getPermisoNombreByNombreRol(String rolNombre){
        String permisoNombre="";
        permisoNombre=uh.getNombrePermisoUsuario(rolNombre);
        return permisoNombre;
         }
          
         public boolean actualizaRol(String rol, String matricula){
         
             if(uh.updateRolUser(rol, matricula))
             return true;
             else
             return false;
         }
    
}
