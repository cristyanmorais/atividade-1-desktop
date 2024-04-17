/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package top.dribles.projeto;

import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.JFrame;
import top.dribles.projeto.dao.ClienteDAO;
import top.dribles.projeto.dao.ClienteDAOImpl;
import top.dribles.projeto.dao.ItemVendaDAO;
import top.dribles.projeto.dao.ItemVendaDAOImpl;
import top.dribles.projeto.model.Cliente;
import top.dribles.projeto.model.ItemVenda;
import top.dribles.projeto.model.ItemVendaId;
import top.dribles.projeto.util.EntityManagerUtil;
import top.dribles.projeto.view.tablemodel.panel.CadastrarVenda;
import top.dribles.projeto.view.tablemodel.panel.CadastroVenda;
import top.dribles.projeto.view.tablemodel.panel.ListarCliente;
import top.dribles.projeto.view.tablemodel.panel.ListarProduto;

/**
 *
 * @author crist
 */
public class Projeto {

    public static void main(String[] args) {
        // Criar uma instância do painel ListarCliente
        CadastroVenda cadastroVenda = new CadastroVenda();
        
        // Criar um JFrame para exibir o painel
        JFrame frame = new JFrame("Sua Aplicação");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // Adicionar o painel ao JFrame
        frame.getContentPane().add(cadastroVenda);
        
        // Configurar o tamanho e tornar visível
        frame.pack();
        frame.setVisible(true);

//        // Obter o EntityManager
//        EntityManager entityManager = EntityManagerUtil.getManager();
//
//        // Criar uma instância do DAO com o EntityManager
//        ItemVendaDAO itemVendaDAO = new ItemVendaDAOImpl(entityManager);
//        
//        ItemVendaId itemId = new ItemVendaId();
//        ItemVenda novoIV = new ItemVenda();
//        
//        novoIV.setDescricao("teste-insert");
//        novoIV.setQtd(1);
//        novoIV.setVlr_unitario(1);
//        novoIV.setVlr_total(1);
//        novoIV.setDesconto_un(1);
//        
//        itemId.setProdutoId(1); // Substitua 1 pelo ID do produto desejado
//        itemId.setVendaId(1); 
//        novoIV.setId(itemId);
//
//        // Inserir o novo cliente no banco de dados
//        itemVendaDAO.insert(novoIV);
//
//        // Fechar o EntityManager
//        EntityManagerUtil.closeEntityManagerFactory();
    }
}
