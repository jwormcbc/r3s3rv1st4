/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.login;

import com.reservaciones.controladores.ControladorLogin;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;  
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;  
import org.primefaces.event.NodeCollapseEvent;  
import org.primefaces.event.NodeExpandEvent;  
import org.primefaces.event.NodeSelectEvent;  
import org.primefaces.event.NodeUnselectEvent;  
import org.primefaces.model.DefaultTreeNode;  
import org.primefaces.model.TreeNode;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DellXps15
 */
@ManagedBean
@SessionScoped
public class TreeBean implements Serializable {  
 private enum Modulos {
 P777, P775
}
    
    private boolean ninguno;
    private TreeNode root;  
    private TreeNode selectedNode;
    TreeNode node0 ;
    TreeNode node1 ;
    TreeNode node2 ;  
    TreeNode node3 ;
    String dlg;
  
    public TreeBean(){}
    
    public TreeNode getTreeBean() {
        root = new DefaultTreeNode("Root", null);
        ControladorLogin controlador=new ControladorLogin();
        LoginBean loginBean = (LoginBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        String permisos=controlador.GetPermisos(loginBean.getUsername());
        String value="P"+permisos; 
        Modulos modulo = Modulos.valueOf(value);
        
        switch(modulo){
            case P777:
                         node0 = new DefaultTreeNode("Altas", root);  
                         node1 = new DefaultTreeNode("Consultas", root);  
                         node2 = new DefaultTreeNode("Actualizaciones", root);  
                         node3 = new DefaultTreeNode("Bajas", root);
                break;
            case P775:  
                         node0 = new DefaultTreeNode("Consultas", root);
                         node1 = new DefaultTreeNode("Actualizaciones", root);  
                break;
            default:
                if(ninguno){
                TreeNode nodeEmpty = new DefaultTreeNode("vacio", root);
                }
                break;
        
        }
        
   
        
    return root;    
    }  
    
    public TreeNode getTreeBeanSystema() {
        root = new DefaultTreeNode("Root", null);
        ControladorLogin controlador=new ControladorLogin();
        LoginBean loginBean = (LoginBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
        String permisos=controlador.GetPermisos(loginBean.getUsername());
        String value="P"+permisos; 
        Modulos modulo = Modulos.valueOf(value);
        
        switch(modulo){
            case P777:
                         node0 = new DefaultTreeNode("configuracion", root);  
                         node1 = new DefaultTreeNode("ayuda", root);  
                         node2 = new DefaultTreeNode("contacto", root);
                break;
            case P775:   
                         node1 = new DefaultTreeNode("ayuda", root);  
                         node2 = new DefaultTreeNode("contacto", root); 
                break;
            default:
                if(ninguno){
                TreeNode nodeEmpty = new DefaultTreeNode("vacio", root);
                }
                break;
        
        }
        
   
        
    return root;    
    }  
  
    /**
     * 
     * @return regresa el evento javascript que abrira el dialog form que se pida segun el nodo. 
     */
    public String getDLG(){
         return "dlg_altas.show();";//nunca mandado llamar por problemas de dinamismo, en su lugar se usa el requestcontext Execute en onnodeselect
    }
    
    public TreeNode getRoot() {  
        return root;  
    }  
  
    public TreeNode getSelectedNode() {  
        return selectedNode;  
    }  
  
    public void setSelectedNode(TreeNode selectedNode) {  
        this.selectedNode = selectedNode;  
    }  
  
    public void onNodeExpand(NodeExpandEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());  
         System.out.println("===>>>       " + selectedNode);
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
  
    public void onNodeCollapse(NodeCollapseEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());  
         System.out.println("===>>>       " + selectedNode);
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
  
    public void onNodeSelect(NodeSelectEvent event) {  
        String nodo=""+selectedNode;
        if(nodo!=null || nodo.length()>0){
                    if(nodo.equals("Altas")){
                       RequestContext.getCurrentInstance().execute("dlg_altas.show();");
                    }else if(nodo.equals("Consultas")){
                       RequestContext.getCurrentInstance().execute("dlg_consultas.show();");
                    }else if(nodo.equals("Actualizaciones")){
                       RequestContext.getCurrentInstance().execute("dlg_actualizacion.show();");
                    }else if(nodo.equals("Bajas")){
                       RequestContext.getCurrentInstance().execute("dlg_bajas.show();");
                    }else if(nodo.equals("Bajas")){
                       RequestContext.getCurrentInstance().execute("dlg_bajas.show();");
                    }
                    
         }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccionado " + selectedNode, event.getTreeNode().toString());
         System.out.println("select===>>>       " + selectedNode);
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
     
  
    public void onNodeUnselect(NodeUnselectEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());  
         System.out.println("===>>>       " + selectedNode);
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
}  