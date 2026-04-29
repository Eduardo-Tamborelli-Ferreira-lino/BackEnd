package org.example.model;

import java.time.LocalDate;

public class Reserva {
    private Integer id;
    private Integer cliente_id;
    private Integer filme_id;
    private LocalDate data_reserva;
    private LocalDate data_sessao;

    public Reserva() {}

    public Reserva(Integer id, Integer cliente_id, Integer filme_id, LocalDate data_reserva, LocalDate data_sessao) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.filme_id = filme_id;
        this.data_reserva = data_reserva;
        this.data_sessao = data_sessao;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getCliente_id() { return cliente_id; }
    public void setCliente_id(Integer cliente_id) { this.cliente_id = cliente_id; }
    public Integer getFilme_id() { return filme_id; }
    public void setFilme_id(Integer filme_id) { this.filme_id = filme_id; }
    public LocalDate getData_reserva() { return data_reserva; }
    public void setData_reserva(LocalDate data_reserva) { this.data_reserva = data_reserva; }
    public LocalDate getData_sessao() { return data_sessao; }
    public void setData_sessao(LocalDate data_sessao) { this.data_sessao = data_sessao; }
}
