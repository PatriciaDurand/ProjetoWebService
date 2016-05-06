package SpringMVC.model;

import com.sun.org.apache.bcel.internal.util.Objects;

import java.io.Serializable;

public class Funcionario implements Serializable{

    private Integer codigo;
    public String nome;
    public double salarioBase;
    public Integer area;

    public Funcionario() {
    }

    public Funcionario(String nome, double salarioBase, Integer area) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.area = area;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public Integer getArea() {
        return area;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public double getSalarioLiquido() {
        double inss = salarioBase * 0.1;
        double ir = 0.0;
        if (salarioBase > 2000.0) {
            ir = (salarioBase - 2000.0) * 0.2;
        }
        return (salarioBase - inss - ir);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", salarioBase=" + salarioBase +
                ", area=" + area +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        if (!((Funcionario) obj).getNome().equals(this.nome) ||
                ((Funcionario) obj).getCodigo()!=this.getCodigo() ||
                ((Funcionario) obj).getSalarioBase()!=this.getSalarioBase() ||
                ((Funcionario) obj).getArea()!=this.getArea()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.nome);
        return hash;
    }

}