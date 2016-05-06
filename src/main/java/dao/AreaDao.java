package dao;

import model.Area;

import javax.sql.DataSource;
import java.util.List;

public interface AreaDao {

    String salvar(Area area);

    List<Area> listar();

    String deletar(int codigo) throws Exception;

    String deletarCascata(int codigo);

    Area buscarPorCodigo(int codigo);

    void setDataSource(DataSource dataSource);

}
