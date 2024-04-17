/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top.dribles.projeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author crist
 */

@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cliente_id;
    private double valor_total;
    private double desconto_total;
    private int qtd_total_itens;

    public Venda() {
    }

    public Venda(int id, int cliente_id, double valor_total, double desconto_total, int qtd_total_itens) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.valor_total = valor_total;
        this.desconto_total = desconto_total;
        this.qtd_total_itens = qtd_total_itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public double getDesconto_total() {
        return desconto_total;
    }

    public void setDesconto_total(double desconto_total) {
        this.desconto_total = desconto_total;
    }

    public int getQtd_total_itens() {
        return qtd_total_itens;
    }

    public void setQtd_total_itens(int qtd_total_itens) {
        this.qtd_total_itens = qtd_total_itens;
    }
}
