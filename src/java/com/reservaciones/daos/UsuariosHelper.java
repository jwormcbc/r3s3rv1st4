/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservaciones.daos;

import com.reservaciones.mapeos.HibernateUtil;
import com.reservaciones.mapeos.Roles;
import com.reservaciones.mapeos.RolesUsuarios;
import com.reservaciones.mapeos.Usuarios;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author DellXps15
 */
public class UsuariosHelper {
     Session session=null;
   
    
    public UsuariosHelper(){
   this.session=HibernateUtil.getSessionFactory().getCurrentSession();
    }  
    
    public boolean altaUsuarios(String matricula,String nombre,String password,String apellidop,String apellidom,String sexo,
                                 String fechaNacimiento,String pais,String estado,String puesto,String descripcion,
                                 String fechaCreacion,String rol_nombre) {
        
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaN = null;
        Date fechaC = null;
        try {
        fechaN = formatoDelTexto.parse(fechaNacimiento);
        fechaC = formatoDelTexto.parse(fechaCreacion);
        } catch (ParseException ex) {
        ex.printStackTrace();
        }
        
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery (" from Usuarios where id=(select max(id) from Usuarios) ");
        Usuarios usuarioUltimo = (Usuarios) q.uniqueResult();
        System.out.println("NUEVO USUARIO OK!!" + (usuarioUltimo.getId()+1));
        Usuarios nuevoUsuario=new Usuarios(usuarioUltimo.getId()+1, matricula, nombre, password, apellidom, apellidom, sexo,fechaN, pais, estado, puesto,false, descripcion,fechaC,new HashSet());
        session.save(nuevoUsuario);
        //session.getTransaction().commit();
        
        
        /*
         * nuevo objeto Roles Usuarios  que ligara rol a usuario.
         */
        q = session.createQuery ("from Roles where nombre='"+rol_nombre+"' ");
        Roles rol = (Roles) q.uniqueResult();
        q = session.createQuery ("select max(id) from RolesUsuarios");
        Integer rolIdnew = (Integer) q.uniqueResult();
        System.out.println("NUEVO ROL-USER AGREGADO OK!!" + (usuarioUltimo.getId()+1));
        RolesUsuarios rolesUsuarios=new RolesUsuarios(rolIdnew+1, nuevoUsuario, rol, fechaC);
        session.save(rolesUsuarios);
        
        
        session.getTransaction().commit();
        return true;
        
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
}
    
        public Usuarios findUser(String matricula) {
    Usuarios usuarios = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Usuarios where matricula='"+matricula+"'");
        usuarios = (Usuarios) q.uniqueResult();
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    return usuarios;
}
        
    public String getRolDeUsuario(String matriculaUsuario) {
    int role=0;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("select id from RolesUsuarios as ru where ru.usuarios.matricula='"+matriculaUsuario+"'");
        role = (Integer) q.uniqueResult();
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (Integer.toString(role));
}
    
    
   public String getNombrePermisoUsuario(String nombre_rol) {
    String role="";
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("select nombre from Permisos where id=(select permisos.id from RolesPermisos as jo  where \n" +
"jo.roles.id=(select id from Roles where nombre='"+nombre_rol+"'))");
        role = (String) q.uniqueResult();
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    return role;
}
    
       public List<Roles> getRoles() {
    List<Roles> roles=null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Roles");
        roles = (List<Roles>) q.list();
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    return roles;
}
        
    
}
