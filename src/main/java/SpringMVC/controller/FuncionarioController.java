package SpringMVC.controller;

import SpringMVC.dao.AreaDao;
import SpringMVC.dao.FuncionarioDAO;
import SpringMVC.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FuncionarioController {

    private String mensagem = "";

    @Autowired
    private FuncionarioDAO funcionarioDAO;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/adicionaFuncionario/{nome}/{salarioBase}/{area}", method=RequestMethod.GET)
    public String adicionaFuncionario(@PathVariable("nome") String nome, @PathVariable("salarioBase") double salarioBase, @PathVariable("area") int area) {
        if (!nome.equals("") && salarioBase >= 0) {
            funcionarioDAO.setDataSource(dataSource);
            mensagem = funcionarioDAO.salvar(new Funcionario(nome, salarioBase, area));
        }
        return mensagem;
    }

    @RequestMapping(value = "/listaFuncionario", method=RequestMethod.GET)
    public ResponseEntity<List<Funcionario>> listarFuncionario(Model model) {
        funcionarioDAO.setDataSource(dataSource);
        return new ResponseEntity<List<Funcionario>>(new ArrayList<>(funcionarioDAO.listar()), HttpStatus.OK);
    }

    @RequestMapping(value = "/deletaFuncionario/{codigo}", method=RequestMethod.GET)
    public String deletarFuncionario (@PathVariable("codigo") int codigo) throws Exception {
        funcionarioDAO.setDataSource(dataSource);
        try {
            mensagem = funcionarioDAO.deletar(codigo);
        } catch (Exception e) {
            mensagem = e.getMessage();
        }
        return mensagem;
    }

}