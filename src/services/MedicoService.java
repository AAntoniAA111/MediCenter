package services;
import model.Medico;

import java.util.ArrayList;
import java.util.List;


public class MedicoService {

    //Lista de quem armazena os medicos cadastrados
    private List<Medico> medicos;

    //Construtor
    public MedicoService(){
        medicos = new ArrayList<>();
    }

    //Cadatrar médico
    public boolean cadastrarMedico(Medico medico){

        //Verifica se o crm ja esta cadstrado
        if(buscarPorCrm(medico.getCrm()) != null) {
        return false;
          }
        medicos.add(medico);
        return true;
    }

        //BUscar medico pelo crm
    public Medico buscarPorCrm(String crm){
        for(Medico medico : medicos){
            if(medico.getCrm().equals(crm)){
                return medico;
            }
        }
        return null;
    }

    //Buscar medico pelo ID
    public Medico buscarPorId(int id){
        for(Medico medico : medicos){
            if(medico.getId() == id){
                return medico;
            }
        }
        return null;
    }

    //Atualizar medico
    public boolean atualizarMedico(Medico medicoAtualizado){
        Medico medico = buscarPorId(medicoAtualizado.getId());
        if(medico == null){
            return false;
        }
        medico.setCrm(medicoAtualizado.getCrm());
        medico.setNome(medicoAtualizado.getNome());
        medico.setEspecialidade(medicoAtualizado.getEspecialidade());
        medico.setTelefone(medicoAtualizado.getTelefone());
        medico.setLogin(medicoAtualizado.getLogin());
        medico.setSenha(medicoAtualizado.getSenha());

        return true;
    }

    //Listar todos os medicos
    public List<Medico> listarMedicos(){
        return medicos;
    }

    //Remover medico
    public boolean removerMedico(int id){
        Medico medico = buscarPorId(id);
        if(medico == null){
            return false;
        }
        medicos.remove(medico);
        return true;
    }
}


