import java.util.Scanner;

public class Menu {

    private SistemaBiblioteca sistema;
    private Scanner scanner;

    public Menu() {
        sistema = new SistemaBiblioteca();
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int op;

        System.out.print("\t====================================\n");
        System.out.print("\t   BEM-VINDO À BIBLIOTECA VIRTUAL\n");
        System.out.print("\t====================================\n");
        System.out.print("\t       Sistema de Gerenciamento\n");

        do {
            System.out.println("\n### MENU ###");
            System.out.println("1. Cadastrar usuário (Aluno ou Professor) 👩‍🎓");
            System.out.println("2. Cadastrar livro 📖");
            System.out.println("3. Listar usuários cadastrados 📋");
            System.out.println("4. Listar todos os livros cadastrados 📋");
            System.out.println("5. Listar livros por filtro (título ou autor) 🔍");
            System.out.println("6. Emprestar livro 📚");
            System.out.println("7. Devolver livro 🔁");
            System.out.println("8. Reservar Livro 📌");
            System.out.println("9. Listar empréstimos 📋");
            //    System.out.println("10. Salvar dados em um arquivo 💾 ");
            System.out.println("0. Sair do sistema ❌");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarLivro();
                case 3 -> sistema.listarUsuarios();
                case 4 -> sistema.listarLivros();
                case 5 -> listarLivrosPorFiltro();
                case 6 -> emprestarLivro();
                case 7 -> devolverLivro();
                case 8 -> reservarLivro();
                case 9 -> sistema.listarEmprestimos();
                //case 10 ->
                case 0 -> System.out.println("\nEncerrando o sistema... Até a próxima! 👋");
                default -> System.err.println("\nOpção inválida! Por favor, digite uma opção válida. ⚠️\n");
            }
        } while (op != 0);
    }

    private void cadastrarUsuario() {
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
    }

    private void cadastrarLivro() {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor do livro: ");
        String autor = scanner.nextLine();

        sistema.adicionarLivro(new Livro(titulo, autor));
    }

    private void listarLivrosPorFiltro() {
        System.out.print("Digite o título ou autor para filtrar os livros: ");
        String filtro = scanner.nextLine();
        sistema.listarLivros(filtro);
    }

    private void emprestarLivro() {
        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine();
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();

        sistema.emprestarLivro(matricula, titulo);
    }

    private void devolverLivro() {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();

        sistema.devolverLivro(titulo);
    }

    private void reservarLivro() {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();

        sistema.reservarLivro(titulo);
    }
}