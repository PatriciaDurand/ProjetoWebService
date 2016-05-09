package SpringMVC.dao;

import SpringMVC.model.Area;

import javax.sql.DataSource;
import java.util.List;

public interface AreaDao {

    String salvar(String nome);

    List<Area> listar();

    String deletar(int codigo) throws Exception;

    String deletarCascata(int codigo) throws Exception;

    Area buscarPorCodigo(int codigo);

    void setDataSource(DataSource dataSource);

}
