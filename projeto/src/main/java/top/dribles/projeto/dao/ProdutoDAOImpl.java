/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import top.dribles.projeto.model.Produto;

/**
 *
 * @author crist
 */
public class ProdutoDAOImpl implements ProdutoDAO {
    private EntityManager entityManager;
    
    public ProdutoDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public Produto findById(Integer id) {
        return entityManager.find(Produto.class, id);
    }
    
    @Override
    public List<Produto> findAll() {
        return entityManager.createQuery("SELECT p FROM Produto p",
                Produto.class).getResultList();
    }
}
