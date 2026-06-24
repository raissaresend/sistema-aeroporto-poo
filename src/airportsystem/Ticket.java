/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airportsystem;

import java.time.LocalDateTime;

/**
 *
 * @author RAISSA
 */
public class Ticket {

    private long id;
    private double valor;
    private long vooId;
    private long passageiroId;
    private long codigo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Ticket() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getVooId() {
        return vooId;
    }

    public void setVooId(long vooId) {
        this.vooId = vooId;
    }

    public long getPassageiroId() {
        return passageiroId;
    }

    public void setPassageiroId(long passageiroId) {
        this.passageiroId = passageiroId;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Cód: " + codigo + " | Voo ID: " + vooId
                + " | Pass. ID: " + passageiroId + " | Valor: R$" + valor;
    }
}
