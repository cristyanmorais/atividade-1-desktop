/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package top.dribles.projeto;

import jakarta.persistence.EntityManager;
import top.dribles.projeto.dao.ClienteDAO;
import top.dribles.projeto.dao.ClienteDAOImpl;
import top.dribles.projeto.model.Cliente;
import top.dribles.projeto.util.EntityManagerUtil;

/**
 *
 * @author crist
 */
public class Projeto {

    public static void main(String[] args) {
        // Obtendo EntityManager
        EntityManager entityManager = EntityManagerUtil.getManager();
        
        // Instanciando ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAOImpl(entityManager);
        
        // Encontrando cliente pelo ID
        Cliente cliente = clienteDAO.findById(1); // Substitua 1 pelo ID desejado
        
        // Verificando se o cliente foi encontrado
        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }
        
        // Fechando EntityManagerFactory
        EntityManagerUtil.closeEntityManagerFactory();
    }
}
