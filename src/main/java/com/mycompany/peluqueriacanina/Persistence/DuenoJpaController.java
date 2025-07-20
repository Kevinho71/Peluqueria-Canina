/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.peluqueriacanina.Persistence;

import com.mycompany.peluqueriacanina.Logic.Dueno;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.mycompany.peluqueriacanina.Logic.Mascota;
import com.mycompany.peluqueriacanina.Persistence.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DuenoJpaController implements Serializable {

    public DuenoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public DuenoJpaController(){
        emf = Persistence.createEntityManagerFactory("peluqueriaJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dueno dueno) {
        if (dueno.getMascotas() == null) {
            dueno.setMascotas(new ArrayList<Mascota>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<Mascota> attachedMascotas = new ArrayList<Mascota>();
            for (Mascota mascotasMascotaToAttach : dueno.getMascotas()) {
                mascotasMascotaToAttach = em.getReference(mascotasMascotaToAttach.getClass(), mascotasMascotaToAttach.getNumCliente());
                attachedMascotas.add(mascotasMascotaToAttach);
            }
            dueno.setMascotas(attachedMascotas);
            em.persist(dueno);
            for (Mascota mascotasMascota : dueno.getMascotas()) {
                Dueno oldDuenoOfMascotasMascota = mascotasMascota.getDueno();
                mascotasMascota.setDueno(dueno);
                mascotasMascota = em.merge(mascotasMascota);
                if (oldDuenoOfMascotasMascota != null) {
                    oldDuenoOfMascotasMascota.getMascotas().remove(mascotasMascota);
                    oldDuenoOfMascotasMascota = em.merge(oldDuenoOfMascotasMascota);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dueno dueno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dueno persistentDueno = em.find(Dueno.class, dueno.getIdDueno());
            List<Mascota> mascotasOld = persistentDueno.getMascotas();
            List<Mascota> mascotasNew = dueno.getMascotas();
            List<Mascota> attachedMascotasNew = new ArrayList<Mascota>();
            for (Mascota mascotasNewMascotaToAttach : mascotasNew) {
                mascotasNewMascotaToAttach = em.getReference(mascotasNewMascotaToAttach.getClass(), mascotasNewMascotaToAttach.getNumCliente());
                attachedMascotasNew.add(mascotasNewMascotaToAttach);
            }
            mascotasNew = attachedMascotasNew;
            dueno.setMascotas(mascotasNew);
            dueno = em.merge(dueno);
            for (Mascota mascotasOldMascota : mascotasOld) {
                if (!mascotasNew.contains(mascotasOldMascota)) {
                    mascotasOldMascota.setDueno(null);
                    mascotasOldMascota = em.merge(mascotasOldMascota);
                }
            }
            for (Mascota mascotasNewMascota : mascotasNew) {
                if (!mascotasOld.contains(mascotasNewMascota)) {
                    Dueno oldDuenoOfMascotasNewMascota = mascotasNewMascota.getDueno();
                    mascotasNewMascota.setDueno(dueno);
                    mascotasNewMascota = em.merge(mascotasNewMascota);
                    if (oldDuenoOfMascotasNewMascota != null && !oldDuenoOfMascotasNewMascota.equals(dueno)) {
                        oldDuenoOfMascotasNewMascota.getMascotas().remove(mascotasNewMascota);
                        oldDuenoOfMascotasNewMascota = em.merge(oldDuenoOfMascotasNewMascota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = dueno.getIdDueno();
                if (findDueno(id) == null) {
                    throw new NonexistentEntityException("The dueno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dueno dueno;
            try {
                dueno = em.getReference(Dueno.class, id);
                dueno.getIdDueno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dueno with id " + id + " no longer exists.", enfe);
            }
            List<Mascota> mascotas = dueno.getMascotas();
            for (Mascota mascotasMascota : mascotas) {
                mascotasMascota.setDueno(null);
                mascotasMascota = em.merge(mascotasMascota);
            }
            em.remove(dueno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dueno> findDuenoEntities() {
        return findDuenoEntities(true, -1, -1);
    }

    public List<Dueno> findDuenoEntities(int maxResults, int firstResult) {
        return findDuenoEntities(false, maxResults, firstResult);
    }

    private List<Dueno> findDuenoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Dueno as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Dueno findDueno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dueno.class, id);
        } finally {
            em.close();
        }
    }

    public int getDuenoCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Dueno as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
