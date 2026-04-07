package Maven.Model;

public class Mecanicos {


    private int id;

    private String nome;

    private String especialidade;

    private Double valorHora;

    public Mecanicos(int id, String nome, String especialidade, Double valorHora) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.valorHora = valorHora;
    }

    public Mecanicos(String nome, String especialidade, Double valorHora) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.valorHora = valorHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    

}
