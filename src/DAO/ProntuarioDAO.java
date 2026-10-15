package DAO;

import model.Medico;
import model.Paciente;
import model.Prontuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProntuarioDAO {

    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();

    public boolean inserir(Prontuario prontuario) {
        String sql = "INSERT INTO prontuarios (paciente_id, medico_id, data, diagnostico, observacoes, prescricao) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, prontuario.getPaciente().getId());
            stmt.setInt(2, prontuario.getMedico().getId());
            stmt.setDate(3, Date.valueOf(prontuario.getData()));
            stmt.setString(4, prontuario.getDiagnostico());
            stmt.setString(5, prontuario.getObservacoes());
            stmt.setString(6, prontuario.getPrescricao());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        prontuario.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao registar prontuário: " + e.getMessage());
        }
        return false;
    }

    public Prontuario buscarPorId(int id) {
        String sql = "SELECT * FROM prontuarios WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearProntuario(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar prontuário por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Prontuario> buscarPorPacienteId(int pacienteId) {
        List<Prontuario> prontuarios = new ArrayList<>();
        String sql = "SELECT * FROM prontuarios WHERE paciente_id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pacienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    prontuarios.add(mapearProntuario(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar prontuários por paciente: " + e.getMessage());
        }
        return prontuarios;
    }

    public boolean atualizar(Prontuario prontuario) {
        String sql = "UPDATE prontuarios SET paciente_id = ?, medico_id = ?, data = ?, diagnostico = ?, observacoes = ?, prescricao = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prontuario.getPaciente().getId());
            stmt.setInt(2, prontuario.getMedico().getId());
            stmt.setDate(3, Date.valueOf(prontuario.getData()));
            stmt.setString(4, prontuario.getDiagnostico());
            stmt.setString(5, prontuario.getObservacoes());
            stmt.setString(6, prontuario.getPrescricao());
            stmt.setInt(7, prontuario.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar prontuário: " + e.getMessage());
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM prontuarios WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar prontuário: " + e.getMessage());
            return false;
        }
    }

    private Prontuario mapearProntuario(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int pacienteId = rs.getInt("paciente_id");
        int medicoId = rs.getInt("medico_id");
        LocalDate data = rs.getDate("data").toLocalDate();
        String diagnostico = rs.getString("diagnostico");
        String observacoes = rs.getString("observacoes");
        String prescricao = rs.getString("prescricao");

        // Assumindo que os DAOs de Paciente e Médico possuem o método buscarPorId
        Paciente paciente = new PacienteDAO().buscarPorId(pacienteId);
        Medico medico = medicoDAO.buscarPorId(medicoId);

        return new Prontuario(id, paciente, medico, data, diagnostico, observacoes, prescricao);
    }
}