package edu.epn.web.b2022.g3.construccion.modelo;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MascotaDAO {
    
    private static EntityManager em = Persistence.createEntityManagerFactory("mysql_local")
            .createEntityManager();
    private static MascotaDAO instance;

    private MascotaDAO() {
    }

    
    
    public static MascotaDAO get(){
        if(instance==null) instance = new MascotaDAO();
        return instance;
    }
    
    public Mascota create(Mascota mascota){
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(mascota);
            transaction.commit();
            return mascota;
        }catch(Exception e){
            transaction.rollback();
            return null;
        }
    }
    
    public Mascota get(Integer key){
        return em.find(Mascota.class, key);
    }
    
    public Collection<Mascota> getAll(){
        return em.createQuery("SELECT m FROM Mascota m").getResultList();
    }
    
    public boolean update(Mascota object){
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.merge(object);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }
    }
    
    public boolean delete(Mascota mascota){
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.remove(mascota);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }
    }
}
