package display;

import dao.ProfessorDAO;
import model.Professor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Display {
    Scanner sc= new Scanner(System.in);
    ProfessorDAO DAO= new ProfessorDAO();
    public void cadastrarProfessor(){
        Professor novoProfrssor= new Professor();
        String nome;
        int aulas;
        double valor;
        System.out.println("qual é o nome do professor?");
        nome=sc.nextLine();
        System.out.println("quantidade de aulas?");
        aulas=sc.nextInt();
        sc.nextLine();
        System.out.println("qual o valor por hora?");
        valor=sc.nextDouble();
        sc.nextLine();
        novoProfrssor.setNome(nome);
        novoProfrssor.setAulasSemanais(aulas);
        novoProfrssor.setValorHora(valor);
        DAO.inserir(novoProfrssor);
        System.out.println("Professor cadastrado!");

    }
    public void listarProfessores(){
        List<Professor> lista = DAO.listar();

        for (Professor p : lista) {
            System.out.println("ID: " + p.getIdProfessor() +
                    " | Nome: " + p.getNome() +
                    " | Salário: R$ " + p.getSalario());
        }
    }
    public void buscarProfessor(){
        int id;
        System.out.println("digite o ID do professor");
        id= sc.nextInt();
        sc.nextLine();
        Optional<Professor> caixa = DAO.buscarPorId(id);
        if (caixa.isPresent()) {
            Professor profEncontrado = caixa.get();
            System.out.println("Encontrado: " + profEncontrado.getNome());
        } else {
            System.out.println("Nenhum professor encontrado com esse ID.");
        }

    }
    public void deletarProfessor() {
        System.out.println("Digite o ID do professor a deletar:");
        int id = sc.nextInt();
        sc.nextLine();

        Optional<Professor> caixa = DAO.buscarPorId(id);
        if (caixa.isEmpty()) {
            System.out.println("Nenhum professor encontrado com esse ID.");
            return;
        }

        Professor p = caixa.get();
        DAO.deletar(p);
        System.out.println("Professor " + p.getNome() + " deletado com sucesso.");
    }
}
