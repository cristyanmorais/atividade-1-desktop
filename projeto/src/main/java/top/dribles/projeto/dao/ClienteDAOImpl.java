/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import top.dribles.projeto.model.Cliente;

/**
 *
 * @author crist
 */
public class ClienteDAOImpl implements ClienteDAO {
    private EntityManager entityManager;

    public ClienteDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public Cliente findById(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> findAll() {
        return entityManager.createQuery("SELECT c FROM Cliente c",
                Cliente.class).getResultList();
    }
}
