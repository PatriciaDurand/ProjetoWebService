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

    private String erro = "";

    @Autowired
    private FuncionarioDAO funcionarioDAO;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    DataSource dataSource;

//    @RequestMapping(value = "/adicionaFuncionario", method = RequestMethod.POST)
    @RequestMapping(value = "/adicionaFuncionario")
    public String adicionaFuncionario() {
//        if (!funcionario.getNome().equals("") && funcionario.getSalarioBase() >= 0) {
//            funcionarioDAO.setDataSource(dataSource);
//            funcionarioDAO.salvar(funcionario);
//        }
//        return "redirect:/cadastroFuncionario";
        return "OK";
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
            erro = funcionarioDAO.deletar(codigo);
        } catch (Exception e) {
            erro = e.getMessage();
        }
        return erro;
    }

}