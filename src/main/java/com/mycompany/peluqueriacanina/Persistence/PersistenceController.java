/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.peluqueriacanina.Persistence;

import com.mycompany.peluqueriacanina.Logic.Dueno;
import com.mycompany.peluqueriacanina.Logic.Mascota;
import com.mycompany.peluqueriacanina.Persistence.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PersistenceController {

    public DuenoJpaController dueJpa = new DuenoJpaController();
    public MascotaJpaController masJpa = new MascotaJpaController();

    public void guardar(Dueno duenio, Mascota masco) {
        dueJpa.create(duenio);
        masJpa.create(masco);
    }

    public List<Mascota> traerDatos() {

        return masJpa.findMascotaEntities();
    }

    public void borrarDatos(int num_cliente) {

        try {
            masJpa.destroy(num_cliente);
        } catch (NonexistentEntityException ex) {
            System.getLogger(PersistenceController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public Mascota traerMascota(int num_cliente) {
        return masJpa.findMascota(num_cliente);
    }

    public void modificarMascota(Mascota masco) {
        try {
            masJpa.edit(masco);
        } catch (Exception ex) {
            System.getLogger(PersistenceController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public Dueno traerDueno(int idDueno) {
        return dueJpa.findDueno(idDueno);
     }

    public void modificarDueno(Dueno dueno) {
        try {
            dueJpa.edit(dueno);
        } catch (Exception ex) {
            System.getLogger(PersistenceController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
