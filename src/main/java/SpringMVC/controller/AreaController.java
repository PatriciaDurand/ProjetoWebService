package SpringMVC.controller;

import SpringMVC.dao.AreaDao;
import SpringMVC.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AreaController {

    private String mensagem = "";

    @Autowired
    AreaDao areaDAO;

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/adicionaArea/{nome}", method=RequestMethod.GET)
    public String adicionaFuncionario(@PathVariable("nome") String nome) {
        if (!nome.equals("")) {
            areaDAO.setDataSource(dataSource);
            mensagem = areaDAO.salvar(nome);
        }
        return mensagem;
    }

    @RequestMapping(value = "/listaArea", method=RequestMethod.GET)
    public ResponseEntity<List<Area>> listarArea() {
        areaDAO.setDataSource(dataSource);
        return new ResponseEntity<List<Area>>(new ArrayList<>(areaDAO.listar()), HttpStatus.OK);
    }

    @RequestMapping(value = "/deletaArea/{codigo}", method=RequestMethod.GET)
    public String deletarArea (@PathVariable("codigo") int codigo) throws Exception {
        areaDAO.setDataSource(dataSource);
        try {
            mensagem = areaDAO.deletar(codigo);
        } catch (Exception e) {
            mensagem = e.getMessage();
        }
        return mensagem;
    }

    @RequestMapping(value = "/deletaAreaCascata/{codigo}", method=RequestMethod.GET)
    public String deletarAreaCascata(@PathVariable("codigo") int codigo) {
        areaDAO.setDataSource(dataSource);
        try {
            mensagem = areaDAO.deletarCascata(codigo);
        } catch (Exception e) {
            mensagem = e.getMessage();
        }
        return mensagem;
    }

}
