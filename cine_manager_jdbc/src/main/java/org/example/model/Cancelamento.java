package org.example.model;

public class Cancelamento {
    private Integer id;
    private Integer reserva_id;
    private String motivo;
    private Double taxa_multa;

    public Cancelamento() {}

    public Cancelamento(Integer id, Integer reserva_id, String motivo, Double taxa_multa) {
        this.id = id;
        this.reserva_id = reserva_id;
        this.motivo = motivo;
        this.taxa_multa = taxa_multa;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getReserva_id() { return reserva_id; }
    public void setReserva_id(Integer reserva_id) { this.reserva_id = reserva_id; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public Double getTaxa_multa() { return taxa_multa; }
    public void setTaxa_multa(Double taxa_multa) { this.taxa_multa = taxa_multa; }
}
