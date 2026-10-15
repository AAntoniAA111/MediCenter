package services;

import model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {

    private List<Funcionario> funcionarios;

    public FuncionarioService() {
        funcionarios = new ArrayList<>();
    }

    // Cadastrar funcionário (Correção da verificação de CPF existente)
    public boolean cadastrarFuncionario(Funcionario funcionario) {
        if (buscarPorCpf(funcionario.getCpf()) != null) {
            return false; // Retorna false se o CPF já estiver cadastrado
        }
        funcionarios.add(funcionario);
        return true;
    }

    public Funcionario buscarPorCpf(String cpf) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }
        return null;
    }

    public Funcionario buscarPorId(int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        return null;
    }

    public boolean atualizarFuncionario(Funcionario funcionarioAtualizado) {
        Funcionario funcionario = buscarPorId(funcionarioAtualizado.getId());
        if (funcionario == null) {
            return false;
        }

        Funcionario funcionarioComMesmoCpf = buscarPorCpf(funcionarioAtualizado.getCpf());
        if (funcionarioComMesmoCpf != null && funcionarioComMesmoCpf.getId() != funcionario.getId()) {
            return false;
        }

        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setCpf(funcionarioAtualizado.getCpf());
        funcionario.setCargo(funcionarioAtualizado.getCargo());
        funcionario.setTelefone(funcionarioAtualizado.getTelefone());
        funcionario.setLogin(funcionarioAtualizado.getLogin());
        funcionario.setSenha(funcionarioAtualizado.getSenha());

        return true;
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public boolean removerFuncionario(int id) {
        Funcionario funcionario = buscarPorId(id);
        if (funcionario == null) {
            return false;
        }
        funcionarios.remove(funcionario);
        return true;
    }
}