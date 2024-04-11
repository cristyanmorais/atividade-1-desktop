/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package top.dribles.projeto;

import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.JFrame;
import top.dribles.projeto.dao.ClienteDAO;
import top.dribles.projeto.dao.ClienteDAOImpl;
import top.dribles.projeto.model.Cliente;
import top.dribles.projeto.util.EntityManagerUtil;
import top.dribles.projeto.view.tablemodel.panel.ListarCliente;

/**
 *
 * @author crist
 */
public class Projeto {

    public static void main(String[] args) {
//        // Obtendo EntityManager
//    EntityManager entityManager = EntityManagerUtil.getManager();
//    
//    // Instanciando ClienteDAO
//    ClienteDAO clienteDAO = new ClienteDAOImpl(entityManager);
//    
//    // Encontrando todos os clientes
//    List<Cliente> listCliente = clienteDAO.findAll();
//    
//    // Verificando se a lista de clientes não está vazia
//    if (!listCliente.isEmpty()) {
//        // Iterando sobre a lista de clientes e imprimindo suas informações
//        for (Cliente cliente : listCliente) {
//            System.out.println("ID: " + cliente.getId());
//            System.out.println("Nome: " + cliente.getNome());
//            // Adicione mais campos conforme necessário
//            System.out.println("---------------------");
//        }
//    } else {
//        System.out.println("Não há clientes cadastrados.");
//    }
//    
//    // Fechando EntityManagerFactory
//    EntityManagerUtil.closeEntityManagerFactory();
//
        // Criar uma instância do painel ListarCliente
        ListarCliente listarCliente = new ListarCliente();
        
        // Criar um JFrame para exibir o painel
        JFrame frame = new JFrame("Sua Aplicação");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adicionar o painel ao JFrame
        frame.getContentPane().add(listarCliente);
        
        // Configurar o tamanho e tornar visível
        frame.pack();
        frame.setVisible(true);
    }
}
