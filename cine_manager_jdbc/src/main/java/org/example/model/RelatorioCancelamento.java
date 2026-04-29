package org.example.model;

public class RelatorioCancelamento {
    private String nomeCliente;
    private String tituloFilme;
    private String motivo;
    private Double taxaMulta;

    public RelatorioCancelamento() {}

    public RelatorioCancelamento(String nomeCliente, String tituloFilme, String motivo, Double taxaMulta) {
        this.nomeCliente = nomeCliente;
        this.tituloFilme = tituloFilme;
        this.motivo = motivo;
        this.taxaMulta = taxaMulta;
    }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public String getTituloFilme() { return tituloFilme; }
    public void setTituloFilme(String tituloFilme) { this.tituloFilme = tituloFilme; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public Double getTaxaMulta() { return taxaMulta; }
    public void setTaxaMulta(Double taxaMulta) { this.taxaMulta = taxaMulta; }
}
