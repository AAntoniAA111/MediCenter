package services;

import model.Consulta;
import model.StatusConsulta;

import java.util.ArrayList;
import java.util.List;

public class ConsultaService {

    // Lista que armazena as consultas
    private List<Consulta> consultas;

    // Construtor
    public ConsultaService() {
        consultas = new ArrayList<>();
    }

    // Agendar consulta
    public boolean agendarConsulta(Consulta consulta) {

        // Verifica se o médico já possui consulta nesse horário
        for (Consulta existente : consultas) {

            if (existente.getMedicoId() == consulta.getMedicoId()
                    && existente.getDataConsulta().equals(
                    consulta.getDataConsulta())
                    && existente.getHoraConsulta().equals(
                    consulta.getHoraConsulta())
                    && existente.getStatus() != StatusConsulta.Cancelada) {

                return false;
            }
        }

        consultas.add(consulta);
        return true;
    }

    // Buscar consulta pelo ID
    public Consulta buscarPorId(int id) {

        for (Consulta consulta : consultas) {
            if (consulta.getId() == id) {
                return consulta;
            }
        }

        return null;
    }

    // Listar todas as consultas
    public List<Consulta> listarConsultas() {
        return new ArrayList<>(consultas);
    }

    // Buscar consultas de um paciente
    public List<Consulta> buscarPorPaciente(int pacienteId) {

        List<Consulta> resultado = new ArrayList<>();

        for (Consulta consulta : consultas) {
            if (consulta.getPacienteId() == pacienteId) {
                resultado.add(consulta);
            }
        }

        return resultado;
    }

    // Buscar consultas de um médico
    public List<Consulta> buscarPorMedico(int medicoId) {

        List<Consulta> resultado = new ArrayList<>();

        for (Consulta consulta : consultas) {
            if (consulta.getMedicoId() == medicoId) {
                resultado.add(consulta);
            }
        }

        return resultado;
    }

    // Atualizar consulta
    public boolean atualizarConsulta(Consulta consultaAtualizada) {

        Consulta consulta = buscarPorId(consultaAtualizada.getId());

        if (consulta == null) {
            return false;
        }

        // Verifica conflitos com outras consultas
        for (Consulta existente : consultas) {

            if (existente.getId() != consulta.getId()
                    && existente.getMedicoId()
                    == consultaAtualizada.getMedicoId()
                    && existente.getDataConsulta().equals(
                    consultaAtualizada.getDataConsulta())
                    && existente.getHoraConsulta().equals(
                    consultaAtualizada.getHoraConsulta())
                    && existente.getStatus() != StatusConsulta.Cancelada) {

                return false;
            }
        }

        consulta.setPacienteId(consultaAtualizada.getPacienteId());
        consulta.setMedicoId(consultaAtualizada.getMedicoId());
        consulta.setConsultorioId(consultaAtualizada.getConsultorioId());
        consulta.setDataConsulta(consultaAtualizada.getDataConsulta());
        consulta.setHoraConsulta(consultaAtualizada.getHoraConsulta());
        consulta.setStatus(consultaAtualizada.getStatus());

        return true;
    }

    // Cancelar consulta
    public boolean cancelarConsulta(int id) {

        Consulta consulta = buscarPorId(id);

        if (consulta == null) {
            return false;
        }

        consulta.setStatus(StatusConsulta.Cancelada);
        return true;
    }
}