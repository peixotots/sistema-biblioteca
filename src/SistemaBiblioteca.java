import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por gerenciar o sistema da biblioteca
// Contém listas de usuários, livros e empréstimos e metodos para manipular esses dados

public class SistemaBiblioteca implements Serializable {

    private static final long serialVersionUID = 1L;

    // Lista de usuários cadastrados no sistema
    private List<Usuario> usuarios;

    // Lista de livros cadastrados no sistema
    private List<Livro> livros;

    // Lista de empréstimos realizados no sistema
    private List<Emprestimo> emprestimos;

    // Construtor da classe
    // Inicializa as listas de usuários, livros e empréstimos
    public SistemaBiblioteca() {
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    // Adiciona um novo usuário à lista de usuários
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Adiciona um novo livro à lista de livros
    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    // Lista todos os usuários cadastrados no sistema
    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("\nNenhum usuário cadastrado.");
        } else {
            for (Usuario user : usuarios) {
                user.exibirDados();
            }
        }
    }

    // Lista todos os livros cadastrados no sistema
    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    // Lista livros com base em um filtro (título ou autor)
    public void listarLivros(String filtro) {
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado.");
        } else {
            boolean encontrou = false;
            for (Livro livro : livros) {
                if (livro.getTitulo().equalsIgnoreCase(filtro) || livro.getAutor().equalsIgnoreCase(filtro)) {
                    System.out.println(livro);
                    encontrou = true;
                }
            }
            if (!encontrou) {
                System.out.println("\nNenhum livro encontrado com o filtro: " + filtro);
            }
        }
    }

    // Lista livros com base em um tema específico
    public void listarLivrosPorTema(String tema) {
        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado.");
            return;
        }

        boolean encontrou = false;
        for (Livro livro : livros) {
            if (livro.getTema().equalsIgnoreCase(tema)) {
                System.out.println(livro);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("\nNenhum livro encontrado com o tema: " + tema);
        }
    }

    // Realiza o empréstimo de um livro para um usuário
    // Verifica se o livro está disponível e registra o empréstimo
    public void emprestarLivro(String matricula, String tituloLivro) {
        try {
            Usuario usuario = buscarUsuarioPorMatricula(matricula);
            Livro livro = buscarLivroPorTitulo(tituloLivro);

            if (livro.isReservado()) {
                throw new LivroReservadoException("\nLivro está reservado e não pode ser emprestado.");
            }

            if (livro.isEmprestado()) {
                throw new EmprestimoNaoPermitidoException("\nLivro já está emprestado.");
            }

            LocalDate dataEmprestimo = LocalDate.now();
            LocalDate dataDevolucao = dataEmprestimo.plusDays(7);

            livro.emprestar(usuario.getNome());

            Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucao);
            emprestimos.add(emprestimo);

            System.out.println("\nLivro emprestado com sucesso para " + usuario.getNome() + "!");
            System.out.println("Data de Empréstimo: " + emprestimo.getDataEmprestimo());
            System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());

        } catch (UsuarioNaoEncontradoException | LivroNaoEncontradoException | LivroReservadoException | EmprestimoNaoPermitidoException e) {
            System.err.println("\n" + e.getMessage());
        }
    }

    // Realiza a devolução de um livro
    // Atualiza o status do livro no sistema
    public void devolverLivro(String tituloLivro) {
        try {
            Livro livro = buscarLivroPorTitulo(tituloLivro);

            if (!livro.isEmprestado()) {
                System.out.println("\nLivro não está emprestado.");
                return;
            }

            livro.devolver();
        } catch (LivroNaoEncontradoException e) {
            System.err.println("\n" + e.getMessage());
        }
    }

    // Realiza a reserva de um livro para um usuário
    public void reservarLivro(String matricula, String tituloLivro) {
        try {
            Usuario usuario = buscarUsuarioPorMatricula(matricula);
            Livro livro = buscarLivroPorTitulo(tituloLivro);

            if (livro.isEmprestado()) {
                System.out.println("\nReserva não realizada. O livro encontra-se emprestado.");
                return;
            }

            if (livro.isReservado()) {
                System.out.println("\nReserva não realizada. O livro já está reservado.");
                return;
            }

            livro.reservar();
            System.out.println("\nLivro reservado com sucesso para " + usuario.getNome() + "!");
        } catch (UsuarioNaoEncontradoException | LivroNaoEncontradoException e) {
            System.err.println("\n" + e.getMessage());
        }
    }

    // Lista todos os empréstimos registrados no sistema
    public void listarEmprestimos() {
        boolean encontrouEmprestimos = false;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().isEmprestado()) {
                System.out.println("Usuário: " + emprestimo.getUsuario().getNome() +
                        ", Livro: " + emprestimo.getLivro().getTitulo() +
                        ", Data de Empréstimo: " + emprestimo.getDataEmprestimo() +
                        ", Data de Devolução: " + emprestimo.getDataDevolucao());
                encontrouEmprestimos = true;
            }
        }

        if (!encontrouEmprestimos) {
            System.out.println("\nNenhum empréstimo registrado.");
        }
    }

    // Exibe um relatório com informações sobre o sistema
    public void exibirRelatorioEstatistico() {
        System.out.println("\n--- RELATÓRIO DA BIBLIOTECA 📊 ---");
        System.out.println("Total de livros cadastrados: " + livros.size());
        System.out.println("Livros emprestados: " + livros.stream().filter(Livro::isEmprestado).count());
        System.out.println("Livros reservados: " + livros.stream().filter(Livro::isReservado).count());
        System.out.println("Usuários cadastrados: " + usuarios.size());
    }

    // Busca um usuário pelo número de matrícula
    // Lança uma exceção personalizada caso o usuário não seja encontrado
    private Usuario buscarUsuarioPorMatricula(String matricula) throws UsuarioNaoEncontradoException {
        for (Usuario user : usuarios) {
            if (user.getMatricula().equalsIgnoreCase(matricula)) {
                return user;
            }
        }
        throw new UsuarioNaoEncontradoException("\nUsuário com matrícula " + matricula + " não encontrado.");
    }

    // Busca um livro pelo título
    // Lança uma exceção personalizada caso o livro não seja encontrado
    private Livro buscarLivroPorTitulo(String titulo) throws LivroNaoEncontradoException {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        throw new LivroNaoEncontradoException("\nLivro com título \"" + titulo + "\" não encontrado.");
    }

    // Salva os dados do sistema em um arquivo
    public void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dados_biblioteca.ser"))) {
            oos.writeObject(this);
            System.out.println("\nDados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("\nErro ao salvar os dados: " + e.getMessage());
        }
    }

    // Carrega os dados do sistema a partir de um arquivo
    // Retorna uma nova instância do sistema caso o arquivo não seja encontrado ou esteja corrompido
    public static SistemaBiblioteca carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dados_biblioteca.ser"))) {
            return (SistemaBiblioteca) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Arquivo de dados não encontrado ou corrompido. Iniciando sistema novo...");
            return new SistemaBiblioteca();
        }
    }
}