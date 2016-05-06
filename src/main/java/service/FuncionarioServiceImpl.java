package service;

import model.Funcionario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

    @Override
    public void salvar(Funcionario funcionario) {

    }

    @Override
    public List<Funcionario> listar() {
        return null;
    }

    @Override
    public List<Funcionario> listarPorArea(int codArea) {
        return null;
    }

    @Override
    public void deletar(int ccodigo) {

    }

    @Override
    public Funcionario buscarPorCodigo(int codigo) {
        return null;
    }

}
