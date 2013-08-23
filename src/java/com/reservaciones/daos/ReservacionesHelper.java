/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservaciones.daos;

import com.reservaciones.mapeos.HibernateUtil;
import com.reservaciones.mapeos.reserv.Historico;
import com.reservaciones.mapeos.reserv.Motivo;
import com.reservaciones.mapeos.reserv.ObjetoReservable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author DellXps15
 */
public class ReservacionesHelper {
     Session session=null;
   
    
    public ReservacionesHelper(){
   this.session=HibernateUtil.getSessionFactory().getCurrentSession();
    }  
    
  public List<ObjetoReservable> getObjReservables() {
    List<ObjetoReservable> objetosReservables=null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from ObjetoReservable");
        objetosReservables = (List<ObjetoReservable>) q.list();
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    return objetosReservables;
}
   
   public int getObjReservableIDByName(String nombre) {
    int objetosReservableId=0;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("select id from ObjetoReservable where nombre='"+nombre+"'");
        objetosReservableId = (Integer) q.uniqueResult();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return objetosReservableId;
}
  
  public boolean altaReserva(int id_ObjRes,int id_Motivo,Date desde,Date hasta) {
        
    
    try {
        Session session=this.session.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        
        Query q=session.createQuery("from ObjetoReservable where id='"+id_ObjRes+"'");
        ObjetoReservable objRes=(ObjetoReservable)q.uniqueResult();
        q=session.createQuery("from Motivo where id='"+id_Motivo+"'");
        Motivo motivo=(Motivo)q.uniqueResult();
        
        q = session.createQuery (" from Historico where id=(select max(id) from Historico) ");
        Historico historicoUltimo = (Historico) q.uniqueResult();
        System.out.println("historico OK!!" + (historicoUltimo.getId()+1));
        Historico nuevoHistorico=new Historico(historicoUltimo.getId()+1, objRes,motivo,desde,hasta);
        session.save(nuevoHistorico);
        
        session.getTransaction().commit();
        return true;
        
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  
    public List<Historico> getObjHistorico(int id) {
    List<Historico> historico=null;
    try {
        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery ("from Historico as objeto where objeto.objetoReservable.id="+id);
        historico = (List<Historico>) q.list();
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    return historico;
}
  
  
}
