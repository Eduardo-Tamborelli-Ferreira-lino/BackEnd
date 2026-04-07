package Maven.Model;

import java.time.LocalDate;

public class OrdemServico {

    private int id;

    private String descricao;

    private LocalDate dataEntrega;

    private String status;  //ABERTO, EM_ANDAMENTO, CONCLUIDO

    private Veiculos veiculo;

    private Veiculos macanico;

    public OrdemServico(int id, String descricao, LocalDate dataEntrega, String status, Veiculos veiculo,
            Veiculos macanico) {
        this.id = id;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.veiculo = veiculo;
        this.macanico = macanico;
    }

    public OrdemServico(String descricao, LocalDate dataEntrega, String status, Veiculos veiculo, Veiculos macanico) {
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.veiculo = veiculo;
        this.macanico = macanico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Veiculos getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculos veiculo) {
        this.veiculo = veiculo;
    }

    public Veiculos getMacanico() {
        return macanico;
    }

    public void setMacanico(Veiculos macanico) {
        this.macanico = macanico;
    }

    
}
