
import display.Display;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Display d = new Display();
        int opcao;
        do{
            System.out.println("""
                    Bem vindo! Está agora no sitema de gerenciamento de professores
                    escolha as opções
                    1- cadastrarum novo professor
                    2- listar professores
                    3- buscar professor
                    4- deletar professor
                    5- finalizar""");
            opcao=sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1 -> d.cadastrarProfessor();
                case 2 -> d.listarProfessores();
                case 3 -> d.buscarProfessor();
                case 4 -> d.deletarProfessor();
                case 5 -> System.out.println("Sistema finalizado!");
            }
        }while (opcao!=5);
    }
}

