package Maven.Model;

public class Veiculos {

    
    private int id;

    private String modelo;

    private String placa;

    private Cliente cliente;

    public Veiculos(int id, String modelo, String placa, Cliente cliente) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.cliente = cliente;
    }

    public Veiculos(String modelo, String placa, Cliente cliente) {
        this.modelo = modelo;
        this.placa = placa;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
