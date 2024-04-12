/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.model;

/**
 *
 * @author crist
 */
public class ItemVenda {
    private int produto_id;
    private int venda_id;
    private String descricao;
    private int qtd;
    private double vlr_unitario;
    private double vlr_total;
    private double desconto_un;

    public ItemVenda() {
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(int venda_id) {
        this.venda_id = venda_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getVlr_unitario() {
        return vlr_unitario;
    }

    public void setVlr_unitario(double vlr_unitario) {
        this.vlr_unitario = vlr_unitario;
    }

    public double getVlr_total() {
        return vlr_total;
    }

    public void setVlr_total(double vlr_total) {
        this.vlr_total = vlr_total;
    }

    public double getDesconto_un() {
        return desconto_un;
    }

    public void setDesconto_un(double desconto_un) {
        this.desconto_un = desconto_un;
    }
}
