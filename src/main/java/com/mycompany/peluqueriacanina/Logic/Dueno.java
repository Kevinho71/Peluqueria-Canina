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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Dueno implements Serializable {
    @Id
    @SequenceGenerator(name = "dueno_seq",sequenceName = "dueno_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dueno_seq")
    private int idDueno;
    @Basic
    private String nombre;
    private String celDueno;
    private String direccion;   
    @OneToMany(mappedBy = "dueno")
    private List<Mascota> mascotas;
    
    public Dueno(){
    
    }
    
    public Dueno(int idDueno, String nombre, String celDueno, String direccion, List<Mascota> mascotas){
        this.idDueno = idDueno;
        this.nombre = nombre;
        this.celDueno = celDueno;
        this.direccion= direccion;
        this.mascotas = mascotas;
    }

    /**
     * @return the idDueno
     */
    public int getIdDueno() {
        return idDueno;
    }

    /**
     * @param idDueno the idDueno to set
     */
    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the celDueno
     */
    public String getCelDueno() {
        return celDueno;
    }

    /**
     * @param celDueno the celDueno to set
     */
    public void setCelDueno(String celDueno) {
        this.celDueno = celDueno;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the mascotas
     */
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    /**
     * @param mascotas the mascotas to set
     */
    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    
    
}
