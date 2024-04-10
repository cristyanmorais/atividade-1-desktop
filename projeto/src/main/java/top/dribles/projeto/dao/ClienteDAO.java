/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package top.dribles.projeto.dao;

import java.util.List;
import top.dribles.projeto.model.Cliente;

/**
 *
 * @author crist
 */
public interface ClienteDAO {
    Cliente findById(Integer id); 
    List<Cliente> findAll();
}
