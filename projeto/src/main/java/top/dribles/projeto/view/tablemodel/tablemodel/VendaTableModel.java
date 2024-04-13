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
        this.addColumn("Desconto");
        this.addColumn("Quantidade");
        this.addColumn("Valor");
    }
    
    public VendaTableModel(List<ItemVenda> listItemVenda) {
        this();
        addItensVenda(listItemVenda);
    }
    
    public void addItensVenda(List<ItemVenda> listItemVenda) {
        for (ItemVenda itemVenda : listItemVenda) {
            this.addRow(new Object[] {
                itemVenda.getProduto_id(),
                itemVenda.getDesconto_un(),
                itemVenda.getQtd(),
                itemVenda.getVlr_unitario()
            });
        }
    }
    
    public void setItensVenda(List<ItemVenda> listItemVenda) {
        this.setRowCount(0); // Limpa todas as linhas existentes na tabela
        addItensVenda(listItemVenda); // Adiciona os novos itens de venda à tabela
    }
    
    public ItemVenda getSelectedProduto(JTable table,
            List<ItemVenda> itensVenda) {
        return null;
    }
}