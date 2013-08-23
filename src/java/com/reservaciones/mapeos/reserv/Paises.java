package com.reservaciones.mapeos.reserv;
// Generated 22/08/2013 12:51:15 AM by Hibernate Tools 3.2.1.GA



/**
 * Paises generated by hbm2java
 */
public class Paises  implements java.io.Serializable {


     private short id;
     private String iso2;
     private String iso3;
     private short prefijo;
     private String nombre;
     private String continente;
     private String subcontinente;
     private String isoMoneda;
     private String nombreMoneda;

    public Paises() {
    }

	
    public Paises(short id, String iso2, String iso3, short prefijo, String nombre) {
        this.id = id;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.prefijo = prefijo;
        this.nombre = nombre;
    }
    public Paises(short id, String iso2, String iso3, short prefijo, String nombre, String continente, String subcontinente, String isoMoneda, String nombreMoneda) {
       this.id = id;
       this.iso2 = iso2;
       this.iso3 = iso3;
       this.prefijo = prefijo;
       this.nombre = nombre;
       this.continente = continente;
       this.subcontinente = subcontinente;
       this.isoMoneda = isoMoneda;
       this.nombreMoneda = nombreMoneda;
    }
   
    public short getId() {
        return this.id;
    }
    
    public void setId(short id) {
        this.id = id;
    }
    public String getIso2() {
        return this.iso2;
    }
    
    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }
    public String getIso3() {
        return this.iso3;
    }
    
    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }
    public short getPrefijo() {
        return this.prefijo;
    }
    
    public void setPrefijo(short prefijo) {
        this.prefijo = prefijo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContinente() {
        return this.continente;
    }
    
    public void setContinente(String continente) {
        this.continente = continente;
    }
    public String getSubcontinente() {
        return this.subcontinente;
    }
    
    public void setSubcontinente(String subcontinente) {
        this.subcontinente = subcontinente;
    }
    public String getIsoMoneda() {
        return this.isoMoneda;
    }
    
    public void setIsoMoneda(String isoMoneda) {
        this.isoMoneda = isoMoneda;
    }
    public String getNombreMoneda() {
        return this.nombreMoneda;
    }
    
    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }




}


