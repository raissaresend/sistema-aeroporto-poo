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
public class VooAssentos {

    private long id;
    private long vooId;
    private long codAssento;
    private long passageiroId;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public VooAssentos() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVooId() {
        return vooId;
    }

    public void setVooId(long vooId) {
        this.vooId = vooId;
    }

    public long getCodAssento() {
        return codAssento;
    }

    public void setCodAssento(long codAssento) {
        this.codAssento = codAssento;
    }

    public long getPassageiroId() {
        return passageiroId;
    }

    public void setPassageiroId(long passageiroId) {
        this.passageiroId = passageiroId;
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
        return "ID: " + id + " | Assento Cód: " + codAssento
                + " | Voo ID: " + vooId + " | Passageiro ID: " + passageiroId;
    }
}
