/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.view.tablemodel.tablemodel;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import top.dribles.projeto.model.ItemVenda;

/**
 *
 * @author crist
 */
public class VendaTableModel extends DefaultTableModel {
    public VendaTableModel() {
        this.addColumn("Id");
    }
    
    public VendaTableModel(List<ItemVenda> listItemVenda) {
        this();
        
        for(ItemVenda itemVenda : listItemVenda) {
            this.addRow(new String[] {
                String.valueOf(itemVenda.getProduto_id()) });
        }
    }
    
    public ItemVenda getSelectedProduto(JTable table,
            List<ItemVenda> itensVenda) {
        return null;
    }
}
