/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

/**
 *
 * @author crist
 */
@Entity
public class ItemVenda {
    
    @EmbeddedId
    private ItemVendaId id;
    
    private String descricao;
    private int qtd;
    private double vlr_unitario;
    private double vlr_total;
    private double desconto_un;

    public ItemVenda() {
    }

    public ItemVendaId getId() {
        return id;
    }

    public void setId(ItemVendaId id) {
        this.id = id;
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
