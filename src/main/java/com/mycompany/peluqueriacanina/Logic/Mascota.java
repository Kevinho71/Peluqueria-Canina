/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.peluqueriacanina.Logic;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.io.Serializable;

@Entity
public class Mascota implements Serializable {
    
    @Id
    @SequenceGenerator(name = "mascota_seq", sequenceName= "mascota_seq", allocationSize= 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mascota_seq")
    private int numCliente;
    @Basic
    private String nombreMascota;
    private String raza;
    private String color;
    private String alergico;
    private String atencionEspecial;
    private String observaciones;
    @ManyToOne
    private Dueno dueno;
   public Mascota(){
   
   }
   
   public Mascota(int numCliente, String nombreMascota, String raza, String color, String alergico
           , String atencionEspecial, String observaciones, Dueno dueno){
       
       this.numCliente = numCliente;
       this.nombreMascota= nombreMascota;
       this.raza= raza;
       this.color = color;
       this.alergico = alergico;
       this.atencionEspecial = atencionEspecial;
       this.observaciones = observaciones;
       this.dueno = dueno;
    }

    /**
     * @return the numCliente
     */
    public int getNumCliente() {
        return numCliente;
    }

    /**
     * @param numCliente the numCliente to set
     */
    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    /**
     * @return the nombreMascota
     */
    public String getNombreMascota() {
        return nombreMascota;
    }

    /**
     * @param nombreMascota the nombreMascota to set
     */
    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    /**
     * @return the raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     * @param raza the raza to set
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the alergico
     */
    public String getAlergico() {
        return alergico;
    }

    /**
     * @param alergico the alergico to set
     */
    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    /**
     * @return the atencionEspecial
     */
    public String getAtencionEspecial() {
        return atencionEspecial;
    }

    /**
     * @param atencionEspecial the atencionEspecial to set
     */
    public void setAtencionEspecial(String atencionEspecial) {
        this.atencionEspecial = atencionEspecial;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the dueno
     */
    public Dueno getDueno() {
        return dueno;
    }

    /**
     * @param dueno the dueno to set
     */
    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }
   
    
}
