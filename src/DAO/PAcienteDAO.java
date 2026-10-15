package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public boolean inserir(String nome, String cpf, String telefone, int usuarioId) {
        String sql = "INSERT INTO pacientes (nome, cpf, telefone, usuario_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setInt(4, usuarioId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir paciente: " + e.getMessage());
            return false;
        }
    }

    public void listar() {
        String sql = "SELECT id, nome, cpf, telefone FROM pacientes";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- LISTA DE PACIENTES ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | CPF: %s | Tel: %s\n",
                        rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM pacientes WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar paciente: " + e.getMessage());
            return false;
        }
    }
}