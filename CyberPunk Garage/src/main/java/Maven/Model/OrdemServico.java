package Maven.Model;

import java.time.LocalDate;

public class OrdemServico {

    private int id;

    private String descricao;

    private LocalDate dataEntrega;

    private String status;  //ABERTO, EM_ANDAMENTO, CONCLUIDO

    private int veiculoId;

    private int mecanicoId;

    public OrdemServico(int id, String descricao, LocalDate dataEntrega, String status, int veiculo,
            int macanico) {
        this.id = id;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.veiculoId = veiculo;
        this.mecanicoId = macanico;
    }

    public OrdemServico(String descricao, LocalDate dataEntrega, String status, int veiculo, int macanico) {
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.veiculoId = veiculo;
        this.mecanicoId = macanico;
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

    public int getVeiculo() {
        return veiculoId;
    }

    public void setVeiculo(int veiculoId) {
        this.veiculoId = veiculoId;
    }

    public int getMecanico() {
        return mecanicoId;
    }

    public void setMecanico(int macanicoId) {
        this.mecanicoId = macanicoId;
    }

    
}
