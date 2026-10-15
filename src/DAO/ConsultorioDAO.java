package DAO;

import model.Consultorio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultorioDAO {

    public boolean inserir(Consultorio consultorio) {
        String sql = "INSERT INTO consultorios (nome, endereco, numero, complemento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, consultorio.getNome());
            stmt.setString(2, consultorio.getEndereco());
            stmt.setString(3, consultorio.getNumero());
            stmt.setString(4, consultorio.getComplemento());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        consultorio.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir consultório: " + e.getMessage());
        }
        return false;
    }

    public Consultorio buscarPorId(int id) {
        String sql = "SELECT * FROM consultorios WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Consultorio c = new Consultorio();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setNumero(rs.getString("numero"));
                    c.setComplemento(rs.getString("complemento"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consultório por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Consultorio> listar() {
        List<Consultorio> consultorios = new ArrayList<>();
        String sql = "SELECT * FROM consultorios";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Consultorio c = new Consultorio();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                c.setNumero(rs.getString("numero"));
                c.setComplemento(rs.getString("complemento"));
                consultorios.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultórios: " + e.getMessage());
        }
        return consultorios;
    }

    public boolean atualizar(Consultorio consultorio) {
        String sql = "UPDATE consultorios SET nome = ?, endereco = ?, numero = ?, complemento = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consultorio.getNome());
            stmt.setString(2, consultorio.getEndereco());
            stmt.setString(3, consultorio.getNumero());
            stmt.setString(4, consultorio.getComplemento());
            stmt.setInt(5, consultorio.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar consultório: " + e.getMessage());
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM consultorios WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar consultório: " + e.getMessage());
            return false;
        }
    }
}