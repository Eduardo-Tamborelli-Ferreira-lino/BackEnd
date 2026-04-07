package Maven.Model;

public class Veiculos {

    
    private int id;

    private String modelo;

    private String placa;

    private int clienteId;

    public Veiculos(int id, String modelo, String placa, int cliente) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.clienteId = cliente;
    }

    public Veiculos(String modelo, String placa, int cliente) {
        this.modelo = modelo;
        this.placa = placa;
        this.clienteId = cliente;
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

    public int getCliente() {
        return clienteId;
    }

    public void setCliente(int clienteId) {
        this.clienteId = clienteId;
    }

}
