package services;

import model.Prontuario;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioService {

    private List<Prontuario> prontuarios;

    public ProntuarioService() {
        this.prontuarios = new ArrayList<>();
    }

    public boolean registrarProntuario(Prontuario prontuario) {
        if (prontuario == null) {
            return false;
        }
        prontuarios.add(prontuario);
        return true;
    }

    public Prontuario buscarPorId(int id) {
        for (Prontuario p : prontuarios) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Prontuario> buscarPorPaciente(int pacienteId) {
        List<Prontuario> resultado = new ArrayList<>();
        for (Prontuario p : prontuarios) {
            if (p.getPacienteId() == pacienteId) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Prontuario> buscarPorMedico(int medicoId) {
        List<Prontuario> resultado = new ArrayList<>();
        for (Prontuario p : prontuarios) {
            if (p.getMedicoId() == medicoId) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Prontuario> listarTodos() {
        return new ArrayList<>(prontuarios);
    }
}