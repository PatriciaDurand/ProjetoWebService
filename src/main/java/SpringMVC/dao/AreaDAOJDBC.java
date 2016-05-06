package SpringMVC.dao;

import SpringMVC.model.Area;
import SpringMVC.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("AreaDAO")
@Transactional
public class AreaDAOJDBC implements AreaDao {

    @Autowired
    private FuncionarioDAO funcionarioDAO;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public String salvar(Area area) {
        String SQL = "INSERT INTO AREA (NOME) VALUES (?)";
        jdbcTemplateObject.update(SQL, area.getNome());
        System.out.println("Área " + area.getNome() + " salva com sucesso!!");
        return "Área " + area.getNome() + " salva com sucesso!!";
    }

    @Override
    public List<Area> listar() {
        String SQL = "SELECT * FROM AREA";
        List <Area> areas = jdbcTemplateObject.query(SQL, new AreaMapper());
        return areas;
    }

    @Override
    public Area buscarPorCodigo(int codigo){
        String SQL = "SELECT * FROM AREA WHERE ID = ?";
        Area area = jdbcTemplateObject.queryForObject(SQL, new Object[]{codigo}, new AreaMapper());
        return area;
    }

    @Override
    public String deletar(int codigo) throws Exception {
        String area = buscarPorCodigo(codigo).getNome();
        try {
            String SQL = "DELETE FROM AREA WHERE ID = ?";
            jdbcTemplateObject.update(SQL, codigo);
            System.out.println("Área " + area + " deletada com sucesso!!");
            return "Área " + area + " deletada com sucesso!!";
        } catch (Exception e){
            System.out.println("Não foi possível deletar a área " + area + " porque existe funcionários associados a ela.");
            throw new Exception("Não foi possível deletar a área " + area + " porque existe funcionários associados a ela.");
        }
    }

    @Override
    public String deletarCascata(int codigo) throws Exception {
        funcionarioDAO.setDataSource(dataSource);
        List<Funcionario> listaFuncionarios = funcionarioDAO.listarPorArea(codigo);
        String area = buscarPorCodigo(codigo).getNome();
        for (Funcionario funcionario : listaFuncionarios) {
            funcionarioDAO.deletar(funcionario.getCodigo());
        }
        String SQL = "DELETE FROM AREA WHERE ID = ?";
        jdbcTemplateObject.update(SQL, codigo);
        System.out.println("Área " + area + " deletada com sucesso!!");
        return "Área " + area + " deletada com sucesso!!";
    }

}
