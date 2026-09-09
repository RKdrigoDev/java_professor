import dao.ProfessorDAO;
import model.Professor;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Instanciamos a nossa ferramenta de banco de dados
        ProfessorDAO dao = new ProfessorDAO();

        // 1. TESTE DE INSERÇÃO
        Professor novoProf = new Professor();
        novoProf.setNome("Carlos Silva");
        novoProf.setAulasSemanais(20);
        novoProf.setValorHora(45.50);

        // Repare que NÃO passamos o ID nem o Salário aqui.
        // O seu DAO e a sua Entidade vão resolver isso sozinhos!
        dao.inserir(novoProf);
        System.out.println("Professor cadastrado com sucesso!");

        // 2. TESTE DE LISTAGEM
        System.out.println("\n--- Lista de Todos os Professores ---");
        List<Professor> lista = dao.listar();

        for (Professor p : lista) {
            System.out.println("ID: " + p.getIdProfessor() +
                    " | Nome: " + p.getNome() +
                    " | Salário: R$ " + p.getSalario());
        }

        // 3. TESTE DE BUSCA POR ID (Usando o jeito sem Optional)
        System.out.println("\n--- Buscando Professor pelo ID 1000 ---");
        Optional<Professor> caixa = dao.buscarPorId(1000);

        if (caixa.isPresent()) {
            Professor profEncontrado = caixa.get();
            System.out.println("Encontrado: " + profEncontrado.getNome());
        } else {
            System.out.println("Nenhum professor encontrado com esse ID.");
        }
    }
}

