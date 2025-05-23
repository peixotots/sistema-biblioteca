import java.util.InputMismatchException;
import java.util.Scanner;

// Classe responsável por gerenciar a interação com o usuário
// Exibe opções de menu e executa as ações selecionadas
public class Menu {

    // Composição de objetos
    private SistemaBiblioteca sistema;
    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
        sistema = SistemaBiblioteca.carregarDados();
    }

    // Menu principal de interação com o usuário
    public void exibirMenu() {
        int op;

        System.out.print("\t====================================\n");
        System.out.print("\t   BEM-VINDO À BIBLIOTECA VIRTUAL\n");
        System.out.print("\t====================================\n");
        System.out.print("\t      Sistema de Gerenciamento\n");

        do {
            System.out.println("\n### MENU ###");
            System.out.println("1. Cadastrar usuário (Aluno ou Professor) 👩‍🎓");
            System.out.println("2. Cadastrar livro 📖");
            System.out.println("3. Listar usuários cadastrados 📋");
            System.out.println("4. Listar todos os livros cadastrados 📋");
            System.out.println("5. Listar livros por filtro (título ou autor) 🔍");
            System.out.println("6. Listar livros por tema 🎯");
            System.out.println("7. Emprestar livro 📚");
            System.out.println("8. Devolver livro 🔁");
            System.out.println("9. Reservar Livro 📌");
            System.out.println("10. Listar empréstimos 📋");
            System.out.println("11. Exibir relatório da biblioteca 📊");
            System.out.println("12. Salvar dados em um arquivo 💾");
            System.out.println("0. Sair do sistema ❌");
            System.out.print("Escolha uma opção: ");

            try {
                op = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();
                op = -1;
            }

            switch (op) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarLivro();
                case 3 -> sistema.listarUsuarios();
                case 4 -> sistema.listarLivros();
                case 5 -> listarLivrosPorFiltro();
                case 6 -> listarLivrosPorTema();
                case 7 -> emprestarLivro();
                case 8 -> devolverLivro();
                case 9 -> reservarLivro();
                case 10 -> sistema.listarEmprestimos();
                case 11 -> sistema.exibirRelatorioEstatistico();
                case 12 -> sistema.salvarDados();
                case 0 -> {
                    sistema.salvarDados();
                    System.out.println("\nEncerrando o sistema... Até a próxima! 👋");
                }
                default -> System.err.println("Opção inválida! Por favor, digite uma opção válida. ⚠️\n");
            }

        } while (op != 0);
    }

    // Implementação dos metodos responsáveis por executar as ações selecionadas no menu principal

    // Solicita os dados do usuário e o cadastra no sistema
    private void cadastrarUsuario() {
        try {
            System.out.print("Escolha o tipo de usuário (1-Aluno, 2-Professor): ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Matrícula: ");
            String matricula = scanner.nextLine();
            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            if (tipo == 1) {
                System.out.print("Curso: ");
                String curso = scanner.nextLine();
                System.out.print("Ano de Ingresso: ");
                int anoDeIngresso = scanner.nextInt();
                scanner.nextLine();
                sistema.adicionarUsuario(new Aluno(nome, matricula, email, curso, anoDeIngresso));
            } else if (tipo == 2) {
                System.out.print("Departamento: ");
                String departamento = scanner.nextLine();
                System.out.print("Ano de Contratação: ");
                int anoDeContratacao = scanner.nextInt();
                scanner.nextLine();
                sistema.adicionarUsuario(new Professor(nome, matricula, email, departamento, anoDeContratacao));
            } else {
                System.out.println("Tipo de usuário inválido.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida! Use apenas números onde for solicitado.");
            scanner.nextLine();
        }
    }

    // Solicita os dados do livro e o cadastra no sistema
    private void cadastrarLivro() {
        try {
            System.out.print("Título do livro: ");
            String titulo = scanner.nextLine();

            System.out.print("Autor do livro: ");
            String autor = scanner.nextLine();

            System.out.print("Ano de lançamento: ");
            int ano = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Tema do livro: ");
            String tema = scanner.nextLine();

            sistema.adicionarLivro(new Livro(titulo, autor, ano, tema));

        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida! O ano de lançamento deve ser um número.");
            scanner.nextLine();
        }
    }

    // Solicita um título ou autor para filtrar os livros e exibe os resultados correspondentes
    private void listarLivrosPorFiltro() {
        System.out.print("Digite o título ou autor para filtrar os livros: ");
        String filtro = scanner.nextLine();
        sistema.listarLivros(filtro);
    }

    // Solicita um tema para filtrar os livros e exibe os resultados correspondentes
    private void listarLivrosPorTema() {
        System.out.print("Digite o tema para filtrar os livros: ");
        String tema = scanner.nextLine();
        sistema.listarLivrosPorTema(tema);
    }

    // Solicita a matrícula do usuário e o título do livro para realizar o empréstimo
    // Verifica se o livro pode ser emprestado e registra o empréstimo no sistema
    private void emprestarLivro() {
        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine();
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();

        try {
            sistema.emprestarLivro(matricula, titulo);
        } catch (Exception e) {
            System.err.println("\n" + e.getMessage());
        }
    }

    // Solicita o título do livro para realizar a devolução
    // Verifica se o livro está emprestado e atualiza o status no sistema
    private void devolverLivro() {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        sistema.devolverLivro(titulo);
    }

    // Solicita a matrícula do usuário e o título do livro para realizar a reserva
    // Verifica se o livro pode ser reservado e registra a reserva no sistema
    private void reservarLivro() {
        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine();
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        sistema.reservarLivro(matricula, titulo);
    }
}