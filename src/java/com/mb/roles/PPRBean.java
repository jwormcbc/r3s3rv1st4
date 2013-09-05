/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mb.roles;

import com.reservaciones.controladores.ControladorUsuarios;
import com.reservaciones.mapeos.Roles;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;  
import java.util.HashMap;  
import java.util.Map;  
  
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;

/**
 *
 * @author DellXps15
 */
@ManagedBean
@RequestScoped
public class PPRBean implements Serializable {  
  
    private String city;  
  
    private String suburb;  
      
    private Map<String,String> cities = new HashMap<String, String>();  
  
    private Map<String,Map<String,String>> suburbsData = new HashMap<String, Map<String,String>>();  
      
    private Map<String,String> suburbs = new HashMap<String, String>(); 
    
    
    
    
    
        private ControladorUsuarios cu= new ControladorUsuarios();
        public PPRBean() {  
        Map<String, Roles> rolesHM=new HashMap<String, Roles>();
        rolesHM=cu.getRoles();
        for(int i=0;i<rolesHM.size();i++){
        cities.put(rolesHM.get(""+i).getNombre(), rolesHM.get(""+i).getNombre());
        }
          
        
        
        
        
        Map<String,String> suburbsIstanbul = new HashMap<String, String>();  
        suburbsIstanbul.put("Kadikoy", "Kadikoy");  
        suburbsIstanbul.put("Levent", "Levent");  
        suburbsIstanbul.put("Cengelkoy", "Cengelkoy");  
          
        Map<String,String> suburbsAnkara = new HashMap<String, String>();  
        suburbsAnkara.put("Kecioren", "Kecioren");  
        suburbsAnkara.put("Cankaya", "Cankaya");  
        suburbsAnkara.put("Yenimahalle", "Yenimahalle");  
          
        Map<String,String> suburbsIzmir = new HashMap<String, String>();  
        suburbsIzmir.put("Cesme", "Cesme");  
        suburbsIzmir.put("Gumuldur", "Gumuldur");  
        suburbsIzmir.put("Foca", "Foca");  
          
        suburbsData.put("Istanbul", suburbsIstanbul);  
        suburbsData.put("Ankara", suburbsAnkara);  
        suburbsData.put("Izmir", suburbsIzmir);  
    }  
      
    public String getCity() {  
        return city;  
    }  
  
    public void setCity(String city) {  
        this.city = city;  
    }  
  
    public String getSuburb() {  
        return suburb;  
    }  
  
    public void setSuburb(String suburb) {  
        this.suburb = suburb;  
    }  
  
    public Map<String, String> getCities() {  
        return cities;  
    }  
  
    public void setCities(Map<String, String> cities) {  
        this.cities = cities;  
    }  
      
    public Map<String, Map<String, String>> getSuburbsData() {  
        return suburbsData;  
    }  
  
    public void setSuburbsData(Map<String, Map<String, String>> suburbsData) {  
        this.suburbsData = suburbsData;  
    }  
      
    public Map<String, String> getSuburbs() {  
        return suburbs;  
    }  
  
    public void setSuburbs(Map<String, String> suburbs) {  
        this.suburbs = suburbs;  
    }  
      
    public void handleCityChange() {  
        
        Map<String,String>  mUtil=new HashMap<String, String>();
        suburbsData.clear();
        mUtil.put(cu.getPermisoNombreByNombreRol(city), city+"mas");
        suburbsData.put("perms",mUtil);
        
        if(city !=null && !city.equals(""))
            suburbs = suburbsData.get("perms");  
        else  
            suburbs = new HashMap<String, String>();  
    
    
    
    }  
  
    public void displayLocation() {  
        FacesMessage msg = new FacesMessage("Selected", "City:" + city + ", Suburb: " + suburb);  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}  
  