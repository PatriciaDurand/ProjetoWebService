package service;

import model.Funcionario;

import java.util.List;

public interface FuncionarioService {

    void salvar(Funcionario funcionario);

    List<Funcionario> listar();

    List<Funcionario> listarPorArea(int codArea);

    void deletar(int ccodigo);

    Funcionario buscarPorCodigo(int codigo);

}
