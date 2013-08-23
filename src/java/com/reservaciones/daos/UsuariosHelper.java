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
        Session session=this.session.getSessionFactory().openSession();
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
        
        /*
         * Se ejecuta un mexicanaso: es un tipo RESTART conection, para actualizar datos de BD.  :)
         */
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.close();
        session=this.session.getSessionFactory().openSession();
        
        
        org.hibernate.Transaction tx = session.beginTransaction();
        session.flush();
        Query q = session.createQuery ("from Usuarios where matricula='"+matricula+"'");
        usuarios = (Usuarios) q.uniqueResult();
        
        System.out.println("helpeeeerrr-------->>>" + usuarios.getNombre());
        
        return usuarios;
    } catch (Exception e) {
      e.printStackTrace();
      return usuarios;
    }
}
        
    public String getRolDeUsuario(String matriculaUsuario) {
    String role="";
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("select nombre from Roles where id=(select roles.id from RolesUsuarios as ru where ru.usuarios.matricula='"+matriculaUsuario+"')");
        role = (String) q.uniqueResult();
        return role;
    } catch (Exception e) {
      e.printStackTrace();
      return role;
    }
    
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
       
 public boolean doActualizacionDeUsuario(String matricula,String nombre,String apellidop,String apellidom,String sexo,
                                 String fechaNacimiento,String pais,String estado,String puesto,String descripcion,String new_Rol) {
    
     
    try {
        
        Session session2=this.session.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session2.beginTransaction();
        System.out.println("UPDATE Usuarios SET nombre='"+nombre+"'"
                                                        +", apellido1='"+apellidop+"'"
                                                        +", apellido2='"+apellidom+"'"
                                                        +", sexo='"+sexo+"'"
                                                        +", fechanacimiento='"+fechaNacimiento+"'"
                                                        +", pais='"+pais+"'"
                                                        +", estado='"+estado+"'"
                                                        +", puesto='"+puesto+"'"
                                                        +", descripcion='"+descripcion+"'"
                                                        +" where matricula='"+matricula+"'");
        Query q = session2.createQuery ("UPDATE Usuarios SET nombre='"+nombre+"'"
                                                        +", apellido1='"+apellidop+"'"
                                                        +", apellido2='"+apellidom+"'"
                                                        +", sexo='"+sexo+"'"
                                                        +", fechanacimiento='"+fechaNacimiento+"'"
                                                        +", pais='"+pais+"'"
                                                        +", estado='"+estado+"'"
                                                        +", puesto='"+puesto+"'"
                                                        +", descripcion='"+descripcion+"'"
                                                        +" where matricula='"+matricula+"'");
        int result=q.executeUpdate();
        q = session2.createQuery ("UPDATE RolesUsuarios as obj SET obj.roles.id=(select id from Roles where nombre='"+new_Rol+"')"
                                                      +" where obj.usuarios.id=(select id from Usuarios where matricula='"+matricula+"')");
        
        result=q.executeUpdate();
        session2.getTransaction().commit();
        return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
   
}
 
 public boolean updateRolUser(String new_Rol,String usuarioMatricula ){
     try {
        Session session=this.session.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("UPDATE RolesUsuarios as obj SET obj.roles.id=(select id from Roles where nombre='"+new_Rol+"')"
                                                        +" where obj.usuarios.id=(select id from Usuarios where matricula='"+usuarioMatricula+"')");
        int result=q.executeUpdate();
        session.getTransaction().commit();
        return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } 
     
 }
 
 public String getIdByNombreRol(String nombre){
    Roles role=null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("select id from Roles where nombre='"+nombre+"'");
        role = (Roles) q.uniqueResult();
        
        if(role!=null)
        return Integer.toString(role.getId());
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
 }
 
  public String getIdByMatriculaUsurio(String matricula){
    Usuarios idUsuario=null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("select id from Usuarios where matricula='"+matricula+"'");
         idUsuario= (Usuarios) q.uniqueResult();
        
        if(idUsuario!=null)
        return Integer.toString(idUsuario.getId());
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
 }
    
  
  /*
   * Elimina usuario eliminndo registros que violen la integridad referencial.(  elimina por matricula en RolesUsuarios y Usuarios )
   */
public boolean eliminaUser(String matricula){

        try {
        Session session=this.session.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("delete  from RolesUsuarios where usuarios.id=(select id from Usuarios where matricula='"+matricula+"')");
        int result=q.executeUpdate();
        
        q = session.createQuery ("delete  from Usuarios where matricula='"+matricula+"'");
        result=q.executeUpdate();
        
        session.getTransaction().commit();
        return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } 
     
    
}     
    
}
