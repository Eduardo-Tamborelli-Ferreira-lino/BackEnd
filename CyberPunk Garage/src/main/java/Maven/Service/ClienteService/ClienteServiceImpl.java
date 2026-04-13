package Maven.Service.ClienteService;

import java.sql.SQLException;
import java.util.ArrayList;

import Maven.Model.Cliente;
import Maven.Repository.ClienteRepository.ClienteRepositoryImpl;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepositoryImpl clienteRepository;

    public ClienteServiceImpl() {
        this.clienteRepository = new ClienteRepositoryImpl();
    }

    public ClienteServiceImpl(ClienteRepositoryImpl clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente save(Cliente cliente) throws SQLException {

        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new NullPointerException("Não pode ser inserido valores nulos ou vazios.");
        }

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo;
    }

    @Override
    public Cliente updateCliente(Cliente cliente) throws SQLException {

        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new NullPointerException("Não pode ser inserido valores nulos ou vazios.");
        }

        Cliente clienteAlterado = clienteRepository.updateCliente(cliente);
        return clienteAlterado;
    }

    @Override
    public ArrayList<Cliente> findAll() throws SQLException {

        ArrayList<Cliente> clientes = new ArrayList<>();

        clientes = clienteRepository.findAll();

        return clientes;
    }

    @Override
    public Cliente findById(int chosenId) throws SQLException {

        ArrayList<Cliente> clientes = new ArrayList<>();

        clientes = clienteRepository.findAll();

        for (Cliente cliente : clientes) {

            if (cliente.getId() == chosenId) {
                Cliente findClient = clienteRepository.findById(chosenId);

                return findClient;
            }

        }

        throw new RuntimeException("The id chosed, don't exist in the data base");

    }

    @Override
    public boolean delete(int chosenId, String keyWord) throws SQLException {

        if ("Ana Beatriz".equalsIgnoreCase(keyWord)) {
            ArrayList<Cliente> clientes = new ArrayList<>();

            clientes = clienteRepository.findAll();

            for (Cliente cliente : clientes) {

                if (cliente.getId() == chosenId) {
                    clienteRepository.delete(chosenId);

                    return true;
                }

            }

            throw new RuntimeException("The id chosed, don't exist in the data base");
        }

        System.out.println("Key word incorrect.");

        return false;
    }

}
