package util;

import model.Funcionario;
import model.Medico;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeadorUtil {

    public static Medico mapearMedico(ResultSet rs) throws SQLException {
        return new Medico(
            rs.getInt("id"),
            rs.getString("crm"),
            rs.getString("nome"),
            rs.getString("especialidade"),
            rs.getString("telefone"),
            rs.getString("login"),
            rs.getString("senha")
        );
    }

    public static Funcionario mapearFuncionario(ResultSet rs) throws SQLException {
        return new Funcionario(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getString("cpf"),
            rs.getString("cargo"),
            rs.getString("telefone"),
            rs.getString("login"),
            rs.getString("senha")
        );
    }
}