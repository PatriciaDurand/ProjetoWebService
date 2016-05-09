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

    private String erro = "";

    @Autowired
    AreaDao areaDAO;

    @Autowired
    DataSource dataSource;

    //    @RequestMapping(value = "/adicionaArea", method = RequestMethod.POST)
    @RequestMapping(value = "/adicionaArea")
    public String adicionaFuncionario(Area area) {
//        if (!area.getNome().equals("")) {
//            areaDAO.setDataSource(dataSource);
//            areaDAO.salvar(area);
//            erro = "";
//        }
//        return "redirect:/cadastroArea";
        return "OK";
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
            erro = areaDAO.deletar(codigo);
        } catch (Exception e) {
            erro = e.getMessage();
        }
        return erro;
    }

    @RequestMapping(value = "/deletaAreaCascata/{codigo}", method=RequestMethod.GET)
    public String deletarAreaCascata(@PathVariable("codigo") int codigo) {
        areaDAO.setDataSource(dataSource);
        try {
            erro = areaDAO.deletarCascata(codigo);
        } catch (Exception e) {
            erro = e.getMessage();
        }
        return erro;
    }

}
