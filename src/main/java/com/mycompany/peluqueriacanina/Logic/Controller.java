/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.peluqueriacanina.Logic;

import com.mycompany.peluqueriacanina.Persistence.PersistenceController;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Controller {
    PersistenceController persis = new PersistenceController();

    public void guardar(String nombreMasco, String raza, String color, String observaciones, 
            String nombreDueno, String celDueno, String dirDueno, String alergico, String especial) {
       
        Dueno duenio = new Dueno();
        Mascota masco =new Mascota();
        
        duenio.setNombre(nombreDueno);
        duenio.setCelDueno(celDueno);
        duenio.setDireccion(dirDueno);
        
        masco.setNombreMascota(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setAlergico(alergico);
        masco.setAtencionEspecial(especial);
        masco.setObservaciones(observaciones);
        masco.setDueno(duenio);
        
        
        persis.guardar(duenio, masco);
    }

    public List<Mascota> cargarDatos() {
        return persis.traerDatos();
    }

    public void borrarMascota(int num_cliente) {
        persis.borrarDatos(num_cliente);
    }

    public Mascota traerMascota(int num_cliente) {
        return persis.traerMascota(num_cliente);
    }

    public void editarMascota(Mascota masco, String nombreMasco, String raza, String color,
            String observaciones, String nombreDueno, String celDueno, String dirDueno, String alergico, String especial) {
            masco.setNombreMascota(nombreMasco);
            masco.setRaza(raza);
            masco.setColor(color);
            masco.setObservaciones(observaciones);
            masco.setAlergico(alergico);
            masco.setAtencionEspecial(especial);
            
            persis.modificarMascota(masco);
            
            Dueno dueno = this.buscarDuenio(masco.getDueno().getIdDueno());
            dueno.setCelDueno(celDueno);
            dueno.setNombre(nombreDueno);
            dueno.setDireccion(dirDueno);
            
            this.editarDueno(dueno);
            
    }

    private Dueno buscarDuenio(int idDueno) {
          return persis.traerDueno(idDueno);
    }

    private void editarDueno(Dueno dueno) {
        persis.modificarDueno(dueno);
        }
    
    
    
    
    
    
}
