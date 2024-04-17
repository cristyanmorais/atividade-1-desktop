/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import top.dribles.projeto.model.Venda;

/**
 *
 * @author crist
 */
public class VendaDAOImpl implements VendaDAO {
    private EntityManager entityManager;
    
    public VendaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public int insert(Venda venda) {
        EntityTransaction transaction = null;
        int vendaId = -1;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(venda);
            transaction.commit();
            
            Query query = entityManager.createQuery("SELECT v.id FROM Venda v WHERE v = :venda");
            query.setParameter("venda", venda);
            vendaId = (int) query.getSingleResult();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
        
        return vendaId;
    }
}
