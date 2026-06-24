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
public class BoardingPass {

    private long id;
    private long ticketId;
    private long passageiroId;
    private long vooId;
    private long assentoId;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public BoardingPass() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public long getPassageiroId() {
        return passageiroId;
    }

    public void setPassageiroId(long passageiroId) {
        this.passageiroId = passageiroId;
    }

    public long getVooId() {
        return vooId;
    }

    public void setVooId(long vooId) {
        this.vooId = vooId;
    }

    public long getAssentoId() {
        return assentoId;
    }

    public void setAssentoId(long assentoId) {
        this.assentoId = assentoId;
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
        return "ID: " + id + " | Ticket ID: " + ticketId
                + " | Pass. ID: " + passageiroId + " | Voo ID: " + vooId
                + " | Assento ID: " + assentoId;
    }
}
