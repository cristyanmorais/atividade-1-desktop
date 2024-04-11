/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package top.dribles.projeto.dao;

import java.util.List;
import top.dribles.projeto.model.Produto;

/**
 *
 * @author crist
 */
public interface ProdutoDAO {
    Produto findById(Integer id); 
    List<Produto> findAll();
}
