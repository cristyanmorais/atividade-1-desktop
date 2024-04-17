/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package top.dribles.projeto.view.tablemodel.panel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import top.dribles.projeto.dao.ClienteDAO;
import top.dribles.projeto.dao.ClienteDAOImpl;
import top.dribles.projeto.dao.ItemVendaDAO;
import top.dribles.projeto.dao.ItemVendaDAOImpl;
import top.dribles.projeto.dao.ProdutoDAO;
import top.dribles.projeto.dao.ProdutoDAOImpl;
import top.dribles.projeto.dao.VendaDAO;
import top.dribles.projeto.dao.VendaDAOImpl;
import top.dribles.projeto.model.Cliente;
import top.dribles.projeto.model.Produto;
import top.dribles.projeto.model.ItemVenda;
import top.dribles.projeto.model.ItemVendaId;
import top.dribles.projeto.model.Venda;
import top.dribles.projeto.util.EntityManagerUtil;
import top.dribles.projeto.view.tablemodel.tablemodel.VendaTableModel;

/**
 *
 * @author crist
 */
public class CadastroVenda extends javax.swing.JPanel {
    
    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;
    private ItemVendaDAO itemVendaDAO;
    private VendaDAO vendaDAO;
    private List<ItemVenda> itensVenda;
    private VendaTableModel vendaTableModel;
    private double valorTotalVenda = 0;
    private int qtdTotalVenda = 0;
    double descTotalDouble;

    /**
     * Creates new form CadastroVenda
     */
    public CadastroVenda() {
        initComponents();
        clienteDAO = new ClienteDAOImpl(EntityManagerUtil.getManager());
        produtoDAO = new ProdutoDAOImpl(EntityManagerUtil.getManager());
        itemVendaDAO = new ItemVendaDAOImpl(EntityManagerUtil.getManager());
        vendaDAO = new VendaDAOImpl(EntityManagerUtil.getManager());
        itensVenda = new ArrayList<>();
        vendaTableModel = new VendaTableModel(itensVenda);
        fetchClientes();
        fetchProdutos();
        produtosTable.setModel(vendaTableModel);
    }
    
    private void fetchClientes() {
        List<Cliente> clientes = clienteDAO.findAll();
        for (Cliente cliente : clientes) {
            clienteCB.addItem(cliente.getNome()); // Adicione aqui o nome do cliente ao combobox
        }
    }
    
    private void fetchProdutos() {
        List<Produto> produtos = produtoDAO.findAll();
        for (Produto produto : produtos) {
            produtoCB.addItem(produto.getDescricao());
        }
    }
    
    private void adicionarProduto() {
        String descUnitario;
        
        if(descUnitarioTF.getText().isEmpty()) {
            descUnitario = "0";
        } else {
            descUnitario = descUnitarioTF.getText();
        }
        
        String qtd = qtdTF.getText();
        String vlrUnitario = vlrUnitarioTF.getText();
        int indiceProdutoSelecionado = produtoCB.getSelectedIndex();
        
        if (qtd.isEmpty() || vlrUnitario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double descUnitarioDouble = Double.parseDouble(descUnitario);
            int qtdInt = Integer.parseInt(qtd);
            double vlrUnitarioDouble = Double.parseDouble(vlrUnitario);
            double vlrTotalDouble = (vlrUnitarioDouble - ((descUnitarioDouble / 100) * vlrUnitarioDouble)) * qtdInt;
            
            if (produtoCB.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione um produto.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Produto produtoSelecionado = produtoDAO.findAll().get(indiceProdutoSelecionado - 1);
            int idProdutoSelecionado = produtoSelecionado.getId();
            String descricao = produtoSelecionado.getDescricao();
            
            ItemVenda itemVenda = new ItemVenda();
            ItemVendaId itemVendaId = new ItemVendaId();
            itemVendaId.setProdutoId(idProdutoSelecionado);
            
            itemVenda.setId(itemVendaId);
            itemVenda.setDesconto_un(descUnitarioDouble);
            itemVenda.setQtd(qtdInt);
            itemVenda.setVlr_unitario(vlrUnitarioDouble);
            itemVenda.setVlr_total(vlrTotalDouble);
            
            itemVenda.setDescricao(descricao);
            
            itensVenda.add(itemVenda);
            
            valorTotalVenda += vlrTotalDouble;
            qtdTotalVenda += qtdInt;
            
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Os campos 'Quantidade', 'Valor Unitário' e 'Desc. Unitário' devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
//        System.out.println("Itens de Venda:");
//        for (ItemVenda item : itensVenda) {
//            System.out.println("Produto ID: " + item.getProduto_id() + ", Desconto Unitário: " + item.getDesconto_un() + ", Quantidade: " + item.getQtd() + ", Valor Unitário: " + item.getVlr_unitario());
//        }
        
        vlrTotalTF.setText(String.valueOf(valorTotalVenda));
        
        vendaTableModel.setItensVenda(itensVenda);
        
        produtoCB.setSelectedIndex(0);
        qtdTF.setText("");
        vlrUnitarioTF.setText("");
        descUnitarioTF.setText("");
    }
    
    private void handleConfirmar() {
        
        if (clienteCB.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (itensVenda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, adicione um produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (descTotalTF.getText().isEmpty()) {
            descTotalDouble = 0;
        } else {
            descTotalDouble = Double.parseDouble(descTotalTF.getText());
        }
        
        valorTotalVenda = (valorTotalVenda - ((descTotalDouble / 100) * valorTotalVenda));
        
        Venda venda = new Venda();
        venda.setCliente_id(clienteCB.getSelectedIndex());
        venda.setValor_total(valorTotalVenda);
        venda.setDesconto_total(descTotalDouble);
        venda.setQtd_total_itens(qtdTotalVenda);
        
//        System.out.println("Venda: " + venda.getCliente_id() + " - " + venda.getValor_total() + " - " + venda.getDesconto_total() + " - " + venda.getQtd_total_itens());
    
        int vendaId = vendaDAO.insert(venda);
        
        for (ItemVenda item : itensVenda) {
            ItemVendaId id = item.getId();
            id.setVendaId(vendaId);
            item.setId(id);
            
            itemVendaDAO.insert(item);
        }
        
        //---------------------------  Clear  ----------------------------------
        clienteCB.setSelectedIndex(0);
        descTotalTF.setText("");
        itensVenda.clear();
        vendaTableModel.setItensVenda(itensVenda);
        valorTotalVenda = 0;
        vlrTotalTF.setText(String.valueOf(valorTotalVenda));
        qtdTotalVenda = 0;
    }
    
    private void abrirListarClientePanel() {
        // Cria uma instância do JPanel "ListarCliente"
        ListarCliente listarClientePanel = new ListarCliente();

        // Cria um JFrame para exibir o JPanel "ListarCliente"
        JFrame frame = new JFrame("Listar Clientes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(listarClientePanel);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void abrirListarProdutoPanel() {
        // Cria uma instância do JPanel "ListarCliente"
        ListarProduto listarProdutoPanel = new ListarProduto();

        // Cria um JFrame para exibir o JPanel "ListarCliente"
        JFrame frame = new JFrame("Listar Produtos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(listarProdutoPanel);
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        clienteCB = new javax.swing.JComboBox<>();
        produtoCB = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        qtdTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        vlrUnitarioTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        descUnitarioTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        descTotalTF = new javax.swing.JTextField();
        vlrTotalTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        confirmaBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        produtosTable = new javax.swing.JTable();
        clienteBtn = new javax.swing.JButton();
        produtoBtn = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Cadastro de Venda");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Cliente");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Produto");

        clienteCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        clienteCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteCBActionPerformed(evt);
            }
        });

        produtoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        produtoCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtoCBActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Quantidade");

        qtdTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtdTFActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Valor Unitário");

        vlrUnitarioTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vlrUnitarioTFActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Desc. Unitário");

        descUnitarioTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descUnitarioTFActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Desc. Total");

        descTotalTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descTotalTFActionPerformed(evt);
            }
        });

        vlrTotalTF.setEditable(false);
        vlrTotalTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vlrTotalTFActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Valor Total");

        cancelBtn.setText("Cancelar");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        addBtn.setText("Adicionar");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        confirmaBtn.setText("Confirmar");
        confirmaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmaBtnActionPerformed(evt);
            }
        });

        produtosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "ID"
            }
        ));
        jScrollPane2.setViewportView(produtosTable);

        clienteBtn.setText("Clientes");
        clienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteBtnActionPerformed(evt);
            }
        });

        produtoBtn.setText("Produtos");
        produtoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(vlrTotalTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(descTotalTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addComponent(jLabel2)
                                    .addGap(195, 195, 195)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(clienteCB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(47, 47, 47)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(produtoBtn)
                                                        .addComponent(clienteBtn))))
                                            .addGap(66, 66, 66)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(cancelBtn)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(confirmaBtn))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(vlrUnitarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(qtdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(produtoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(descUnitarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(64, 64, 64))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(addBtn)
                                            .addGap(116, 116, 116)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(clienteCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(produtoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(qtdTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(vlrUnitarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(descUnitarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(clienteBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(produtoBtn))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(descTotalTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addBtn)
                        .addGap(19, 19, 19)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vlrTotalTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn)
                    .addComponent(confirmaBtn))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clienteCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clienteCBActionPerformed

    private void produtoCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtoCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_produtoCBActionPerformed

    private void qtdTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtdTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtdTFActionPerformed

    private void vlrUnitarioTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vlrUnitarioTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vlrUnitarioTFActionPerformed

    private void descUnitarioTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descUnitarioTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descUnitarioTFActionPerformed

    private void descTotalTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descTotalTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descTotalTFActionPerformed

    private void vlrTotalTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vlrTotalTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vlrTotalTFActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        adicionarProduto();
    }//GEN-LAST:event_addBtnActionPerformed

    private void confirmaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmaBtnActionPerformed
        handleConfirmar();
    }//GEN-LAST:event_confirmaBtnActionPerformed

    private void clienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteBtnActionPerformed
        abrirListarClientePanel();
    }//GEN-LAST:event_clienteBtnActionPerformed

    private void produtoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtoBtnActionPerformed
        abrirListarProdutoPanel();
    }//GEN-LAST:event_produtoBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton clienteBtn;
    private javax.swing.JComboBox<String> clienteCB;
    private javax.swing.JButton confirmaBtn;
    private javax.swing.JTextField descTotalTF;
    private javax.swing.JTextField descUnitarioTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JButton produtoBtn;
    private javax.swing.JComboBox<String> produtoCB;
    private javax.swing.JTable produtosTable;
    private javax.swing.JTextField qtdTF;
    private javax.swing.JTextField vlrTotalTF;
    private javax.swing.JTextField vlrUnitarioTF;
    // End of variables declaration//GEN-END:variables
}
