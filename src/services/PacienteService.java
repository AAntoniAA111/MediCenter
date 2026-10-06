package services;
import model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    //Lista que armazena os pacientes cadastrados
    private List<Paciente> pacientes;

    //Construtor
    public PacienteService(){
        pacientes = new ArrayList<>();
    }

    //Cadastrar paciente
    public boolean cadastrarPaciente(Paciente paciente){
        //Verifica se o CPF já está cadastrado
        if(buscarPorCpf(paciente.getCpf()) != null){
            return false;
        }

        pacientes.add(paciente);
        return true;
    }

    //Buscar paciente pelo CPF
    public Paciente buscarPorCpf(String cpf){
        for(Paciente paciente : pacientes){
            if(paciente.getCpf().equals(cpf)){
                return paciente;
            }
        }
        return null;
    }

    //BUscar paciente pelo id
    public Paciente buscarPorId(int id){
        for (Paciente paciente : pacientes) {

            if (paciente.getId() == id) {
                return paciente;
            }
    }
        return null;
    }

    // Atualizar paciente
    public boolean atualizarPaciente(Paciente pacienteAtualizado) {

        Paciente paciente = buscarPorId(pacienteAtualizado.getId());

        if (paciente == null) {
            return false;
        }

        paciente.setNome(pacienteAtualizado.getNome());
        paciente.setCpf(pacienteAtualizado.getCpf());
        paciente.setDataNascimento(pacienteAtualizado.getDataNascimento());
        paciente.setEndereco(pacienteAtualizado.getEndereco());
        paciente.setTelefone(pacienteAtualizado.getTelefone());
        paciente.setLogin(pacienteAtualizado.getLogin());
        paciente.setSenha(pacienteAtualizado.getSenha());

        return true;
    }

    // Listar todos os pacientes
    public List<Paciente> listarPacientes() {
        return pacientes;
    }

    // Remover paciente
    public boolean removerPaciente(int id) {

        Paciente paciente = buscarPorId(id);

        if (paciente == null) {
            return false;
        }

        pacientes.remove(paciente);
        return true;
    }
}
