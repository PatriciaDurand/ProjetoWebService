package dao;

import model.Funcionario;

import javax.sql.DataSource;
import java.util.List;

public interface FuncionarioDAO {

    void salvar(Funcionario funcionario);

    List<Funcionario> listar();

    List<Funcionario> listarPorArea(int codArea);

    void deletar(int ccodigo);

    Funcionario buscarPorCodigo(int codigo);

    void setDataSource(DataSource dataSource);

}