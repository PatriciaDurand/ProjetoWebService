package service;

import model.Area;

import java.util.List;

public interface AreaService {

    String salvar(Area area);

    List<Area> listar();

    String deletar(int codigo) throws Exception;

    String deletarCascata(int codigo);

    Area buscarPorCodigo(int codigo);

}
