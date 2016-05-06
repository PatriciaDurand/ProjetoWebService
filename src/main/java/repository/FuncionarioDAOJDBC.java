package repository;

import dao.FuncionarioDAO;
import model.Funcionario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("FuncionarioDAO")
@Transactional
public class FuncionarioDAOJDBC implements FuncionarioDAO {

    //http://www.tutorialspoint.com/spring/spring_jdbc_example.htm

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void salvar(Funcionario funcionario) {
        String SQL = "INSERT INTO FUNCIONARIO (NOME, SALARIOBASE, AREA) VALUES (?, ?, ?)";
        jdbcTemplateObject.update(SQL, funcionario.getNome(), funcionario.getSalarioBase(), funcionario.getArea());

        return;
    }

    @Override
    public List<Funcionario> listar() {
        String SQL = "SELECT * FROM FUNCIONARIO";
        List <Funcionario> funcionarios = jdbcTemplateObject.query(SQL, new FuncionarioMapper());
        return funcionarios;
    }

    @Override
    public List<Funcionario> listarPorArea(int codArea){
        String SQL = "SELECT * FROM FUNCIONARIO WHERE AREA = ? ";
        List <Funcionario> funcionarios = jdbcTemplateObject.query(SQL, new Object[]{codArea}, new FuncionarioMapper());
        return funcionarios;
    }

    @Override
    public Funcionario buscarPorCodigo(int codigo){
        String SQL = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
        Funcionario funcionario = jdbcTemplateObject.queryForObject(SQL, new Object[]{codigo}, new FuncionarioMapper());
        return funcionario;
    }

    @Override
    public void deletar(int codigo) {
        String SQL = "DELETE FROM FUNCIONARIO WHERE ID = ?";
        jdbcTemplateObject.update(SQL, codigo);
        System.out.println("Funcionario deletado com sucesso!!\n ID = " + codigo);
        return;
    }

}