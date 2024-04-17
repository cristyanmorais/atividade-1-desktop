/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import top.dribles.projeto.model.ItemVenda;

/**
 *
 * @author crist
 */
public class ItemVendaDAOImpl implements ItemVendaDAO {
    private EntityManager entityManager;
    
    public ItemVendaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void insert(ItemVenda itemVenda) {
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(itemVenda);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
