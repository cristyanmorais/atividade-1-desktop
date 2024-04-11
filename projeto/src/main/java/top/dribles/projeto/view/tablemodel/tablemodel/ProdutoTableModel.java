/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.view.tablemodel.tablemodel;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import top.dribles.projeto.model.Produto;

/**
 *
 * @author crist
 */
public class ProdutoTableModel extends DefaultTableModel {
    
    public ProdutoTableModel() {
        this.addColumn("Descricao");
    }
    
    public ProdutoTableModel(List<Produto> listProduto) {
        this();
        
        for(Produto produto : listProduto) {
            this.addRow(new String[] {
                produto.getDescricao() });
        }
    }
    
    public Produto getSelectedProduto(JTable table,
            List<Produto> produtos) {
        return null;
    }
}
