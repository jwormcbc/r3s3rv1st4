/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservaciones.daos;

import com.reservaciones.mapeos.HibernateUtil;
import com.reservaciones.mapeos.PermisoAcciones;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.reservaciones.mapeos.Usuarios;

/**
 *
 * @author Julio
 */
public class LoginHelper {
    Session session=null;
   
    
    public LoginHelper(){
   this.session=HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List<Usuarios> findAllUser() {
    List<Usuarios> usuariosList = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Usuarios");
        usuariosList = (List<Usuarios>) q.list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return usuariosList;
}
    
    
    public PermisoAcciones findUserPermisos(String matricula) {
        PermisoAcciones usuarioPermisos = null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from PermisoAcciones where\n" +
" id=(select permisoAcciones.id from PermisosAcciones as obj1 where obj1.permisos.id=(select roles.id \n" +
"from RolesPermisos as obj2 where\n" +
"obj2.roles.id=(select id from Roles where\n" +
" id=(select roles.id from RolesUsuarios where \n" +
"usuario_id=(select id from Usuarios where matricula='"+matricula+"')))))");
        usuarioPermisos = (PermisoAcciones) q.uniqueResult();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return usuarioPermisos;
}
    
}
