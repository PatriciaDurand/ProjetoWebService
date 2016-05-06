package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Funcionario;
import org.springframework.jdbc.core.RowMapper;

public class FuncionarioMapper implements RowMapper<Funcionario> {

    public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setCodigo(rs.getInt(1));
        funcionario.setNome(rs.getString(2));
        funcionario.setSalarioBase(rs.getInt(3));
        funcionario.setArea(rs.getInt(4));
        return funcionario;
    }

}
