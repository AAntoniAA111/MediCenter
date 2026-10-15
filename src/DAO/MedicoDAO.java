package DAO;

import model.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    public boolean inserir(Medico medico) {
        String sql = "INSERT INTO medicos (crm, nome, especialidade, telefone, login, senha) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, medico.getCrm());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setString(4, medico.getTelefone());
            stmt.setString(5, medico.getLogin());
            stmt.setString(6, medico.getSenha());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        medico.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir médico: " + e.getMessage());
        }
        return false;
    }

    public Medico buscarPorId(int id) {
        String sql = "SELECT * FROM medicos WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return MapeadorUtil.mapearMedico(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar médico por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Medico> listar() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM medicos";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                medicos.add(MapeadorUtil.mapearMedico(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar médicos: " + e.getMessage());
        }
        return medicos;
    }

    public boolean atualizar(Medico medico) {
        String sql = "UPDATE medicos SET crm = ?, nome = ?, especialidade = ?, telefone = ?, login = ?, senha = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getCrm());
            stmt.setString(2, medico.getNome());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setString(4, medico.getTelefone());
            stmt.setString(5, medico.getLogin());
            stmt.setString(6, medico.getSenha());
            stmt.setInt(7, medico.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar médico: " + e.getMessage());
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM medicos WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar médico: " + e.getMessage());
            return false;
        }
    }
}