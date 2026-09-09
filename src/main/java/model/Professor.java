package model;

public class Professor {
    private int idProfessor;
    private String nome;
    private int aulasSemanais;
    private double valorHora;
    private double salario;

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAulasSemanais() {
        return aulasSemanais;
    }

    public void setAulasSemanais(int aulasSemanais) {
        this.aulasSemanais = aulasSemanais;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    public double calcularSalarioBase(){
        return getValorHora()*getAulasSemanais()*4.5;
    }
    public double calcularDSR(){
        return calcularSalarioBase()/6;
    }
    public double calcularHoraAtividade(){
        return ((calcularSalarioBase()+calcularDSR())*5)/100;
    }
    public double calcularSalarioTotal(){
        return this.salario=calcularSalarioBase()+calcularDSR()+calcularHoraAtividade();
    }
    public String definirRegime(){
        if (getAulasSemanais()<=12){
            return "horista";
        } else if (getAulasSemanais()>=13&&getAulasSemanais()<=39) {
            return "parcial";
        }
        else {
            return "integral";
        }
    }

}
