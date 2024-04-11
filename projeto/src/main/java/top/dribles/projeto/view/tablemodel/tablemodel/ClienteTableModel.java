/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.view.tablemodel.tablemodel;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import top.dribles.projeto.model.Cliente;

/**
 *
 * @author crist
 */
public class ClienteTableModel extends DefaultTableModel {
    
    public ClienteTableModel() {
        this.addColumn("Nome");
        this.addColumn("Telefone");
        this.addColumn("Cpf");
    }
    
    public ClienteTableModel(List<Cliente> listCliente) {
        this();
        
        for(Cliente cliente : listCliente) {
            this.addRow(new String[] {
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getCpf() });
        }
    }
    
    public Cliente getSelectedCliente(JTable table,
            List<Cliente> clientes) {
        return null;
    }
}
