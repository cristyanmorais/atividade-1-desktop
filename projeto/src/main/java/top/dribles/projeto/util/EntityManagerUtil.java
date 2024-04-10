package top.dribles.projeto.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author crist
 */
public class EntityManagerUtil {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    private EntityManagerUtil() {}
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if(emf == null) {
            emf = Persistence
                    .createEntityManagerFactory("DesktopPU");
            System.out.println("conexão aberta!");
        }
        return emf;
    }
    
    public static void closeEntityManagerFactory() {
        if(emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("conexão fechada!");
        }
    }
    
    public static EntityManager getManager() {
        if (emf == null) {
            getEntityManagerFactory();
        }
        if(em == null || !em.isOpen()) {
            em = emf.createEntityManager();
            System.out.println("entity manager aberta!");
        }
        return em;
    }
}