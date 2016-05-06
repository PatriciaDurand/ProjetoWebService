package SpringMVC.model;

import com.sun.org.apache.bcel.internal.util.Objects;

public class Area {

    private Integer codigo;
    public String nome;

    public Area(String nome) {
        this.nome = nome;
    }

    public Area() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Area{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 33 * hash + Objects.hashCode(this.nome);
        return hash;
    }

}
