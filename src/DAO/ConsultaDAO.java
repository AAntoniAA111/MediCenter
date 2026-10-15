package DAO;

import model.Consulta;
import model.StatusConsulta;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public boolean inserir(Consulta consulta) {
        String sql = "INSERT INTO consultas (data_consulta, hora_consulta, status, paciente_id, medico_id, consultorio_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, Date.valueOf(consulta.getDataConsulta()));
            stmt.setTime(2, Time.valueOf(consulta.getHoraConsulta()));
            stmt.setString(3, consulta.getStatus().name());
            stmt.setInt(4, consulta.getPacienteId());
            stmt.setInt(5, consulta.getMedicoId());
            stmt.setInt(6, consulta.getConsultorioId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        consulta.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao agendar consulta: " + e.getMessage());
        }
        return false;
    }

    public Consulta buscarPorId(int id) {
        String sql = "SELECT * FROM consultas WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearConsulta(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consulta por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Consulta> listar() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                consultas.add(mapearConsulta(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas: " + e.getMessage());
        }
        return consultas;
    }

    public List<Consulta> buscarPorMedico(int medicoId) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas WHERE medico_id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, medicoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    consultas.add(mapearConsulta(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consultas por médico: " + e.getMessage());
        }
        return consultas;
    }

    public List<Consulta> buscarPorPaciente(int pacienteId) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas WHERE paciente_id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pacienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    consultas.add(mapearConsulta(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consultas por paciente: " + e.getMessage());
        }
        return consultas;
    }

    public boolean atualizar(Consulta consulta) {
        String sql = "UPDATE consultas SET data_consulta = ?, hora_consulta = ?, status = ?, paciente_id = ?, medico_id = ?, consultorio_id = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(consulta.getDataConsulta()));
            stmt.setTime(2, Time.valueOf(consulta.getHoraConsulta()));
            stmt.setString(3, consulta.getStatus().name());
            stmt.setInt(4, consulta.getPacienteId());
            stmt.setInt(5, consulta.getMedicoId());
            stmt.setInt(6, consulta.getConsultorioId());
            stmt.setInt(7, consulta.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar consulta: " + e.getMessage());
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM consultas WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar consulta: " + e.getMessage());
            return false;
        }
    }

    private Consulta mapearConsulta(ResultSet rs) throws SQLException {
        Consulta c = new Consulta();
        c.setId(rs.getInt("id"));
        c.setDataConsulta(rs.getDate("data_consulta").toLocalDate());
        c.setHoraConsulta(rs.getTime("hora_consulta").toLocalTime());
        c.setStatus(StatusConsulta.valueOf(rs.getString("status")));
        c.setPacienteId(rs.getInt("paciente_id"));
        c.setMedicoId(rs.getInt("medico_id"));
        c.setConsultorioId(rs.getInt("consultorio_id"));
        return c;
    }
}