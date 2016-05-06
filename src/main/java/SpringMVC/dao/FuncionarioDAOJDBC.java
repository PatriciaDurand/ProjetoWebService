package SpringMVC.dao;

import SpringMVC.model.Funcionario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository("FuncionarioDAO")
@Transactional
public class FuncionarioDAOJDBC implements FuncionarioDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public String salvar(Funcionario funcionario) {
        String SQL = "INSERT INTO FUNCIONARIO (NOME, SALARIOBASE, AREA) VALUES (?, ?, ?)";
        jdbcTemplateObject.update(SQL, funcionario.getNome(), funcionario.getSalarioBase(), funcionario.getArea());
        return "Funcionario salvo com sucesso!\n Nome = " + funcionario.getNome() +
                " Salário Base = " + funcionario.getSalarioBase() +
                " Área = " + funcionario.getArea();
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
    public String deletar(int codigo) throws Exception {
        String funcionario = buscarPorCodigo(codigo).getNome();
        try {
            String SQL = "DELETE FROM FUNCIONARIO WHERE ID = ?";
            jdbcTemplateObject.update(SQL, codigo);
            System.out.println("Funcionario " + funcionario + " deletado com sucesso!!");
            return "Funcionario " + funcionario + " deletado com sucesso!!";
        } catch (Exception e){
            System.out.println("Não foi possível deletar o funcionário " + funcionario + ".");
            throw new Exception("Não foi possível deletar o funcionárop " + funcionario + ".");
        }
    }

}