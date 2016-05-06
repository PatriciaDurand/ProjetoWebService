package SpringMVC.dao;

import SpringMVC.model.Funcionario;

import javax.sql.DataSource;
import java.util.List;

public interface FuncionarioDAO {

    String salvar(Funcionario funcionario);

    List<Funcionario> listar();

    List<Funcionario> listarPorArea(int codArea);

    String deletar(int ccodigo) throws Exception;

    Funcionario buscarPorCodigo(int codigo);

    void setDataSource(DataSource dataSource);

}