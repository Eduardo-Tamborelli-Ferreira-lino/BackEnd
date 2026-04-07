package Maven.Model;

public class ItensPecas {

    private int id;

    private String nome_peca;

    private double preco_unitario;

    private int quantidade;

    private int ordemServicoId;

    public ItensPecas(int id, String nome_peca, double preco_unitario, int quantidade, int ordemServico) {
        this.id = id;
        this.nome_peca = nome_peca;
        this.preco_unitario = preco_unitario;
        this.quantidade = quantidade;
        this.ordemServicoId = ordemServico;
    }

    public ItensPecas(String nome_peca, double preco_unitario, int quantidade, int ordemServico) {
        this.nome_peca = nome_peca;
        this.preco_unitario = preco_unitario;
        this.quantidade = quantidade;
        this.ordemServicoId = ordemServico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_peca() {
        return nome_peca;
    }

    public void setNome_peca(String nome_peca) {
        this.nome_peca = nome_peca;
    }

    public double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getOrdemServico() {
        return ordemServicoId;
    }

    public void setOrdemServico(int ordemServico) {
        this.ordemServicoId = ordemServico;
    }

    
}
