package services;

import model.Funcionario;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioService {

    //Lista que armazena os funcionarios cadastrados
    private List<Funcionario> funcionarios;

    //Construtor
    public FuncionarioService(){
        funcionarios = new ArrayList();
    }

    //Cadastrar funcionario
    public boolean cadastarFuncionario(Funcionario funcionario) {

        //Verifica se o cpf ja esta cadastrado
        if (buscarPorCpf(funcionario.getCpf()) != null) {
            return true;
        }
        funcionarios.add(funcionario);
        return true;
    }

        //Buscar o funcionario pelo cpf
        public Funcionario buscarPorCpf(String cpf){
            for (Funcionario funcionario : funcionarios){
                if(funcionario.getCpf().equals(cpf)){
                    return funcionario;
                }
            }

            return null;
        }

        //Buscar funcionario pelo ID
        public Funcionario buscarPorId(int id){
            for(Funcionario funcionario : funcionarios){
                if(funcionario.getId() == id){
                    return funcionario;
                }
            }
            return null;
        }

        //Atualizar funcionario
    public boolean atualizarFuncionario(Funcionario funcionarioAtualizado){
        Funcionario funcionario = buscarPorId(funcionarioAtualizado.getId());

        if(funcionario == null){
            return false;
        }

        //Verifica se outro funcionario ja utiliza o cpf informado
        Funcionario funcionarioComMesmoCpf = buscarPorCpf(funcionarioAtualizado.getCpf());

        if(funcionarioComMesmoCpf != null && funcionarioComMesmoCpf.getId() != funcionario.getId()){
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

    //Listar todos os funcionarios
    public List<Funcionario> listarFuncionarios(){
        return new ArrayList<>(funcionarios);
    }

    //Remover funcionario pelo ID
    public boolean removerFuncionario(int id){
        Funcionario funcionario = buscarPorId(id);
        if(funcionario == null){
            return false;
        }

        funcionarios.remove(funcionario);
        return true;
    }
    }
