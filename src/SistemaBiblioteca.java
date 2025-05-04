import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaBiblioteca implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;

    public SistemaBiblioteca() {
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario user : usuarios) {
                user.exibirDados();
            }
        }
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    public void listarLivros(String filtro) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            boolean encontrou = false;
            for (Livro livro : livros) {
                if (livro.getTitulo().equalsIgnoreCase(filtro) || livro.getAutor().equalsIgnoreCase(filtro)) {
                    System.out.println(livro);
                    encontrou = true;
                }
            }
            if (!encontrou) {
                System.out.println("Nenhum livro encontrado com o filtro: " + filtro);
            }
        }
    }

    public void listarLivrosPorTema(String tema) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
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
            System.out.println("Nenhum livro encontrado com o tema: " + tema);
        }
    }

    public void emprestarLivro(String matricula, String tituloLivro) {
        Usuario usuario = buscarUsuarioPorMatricula(matricula);
        Livro livro = buscarLivroPorTitulo(tituloLivro);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        try {
            if (livro.isReservado()) {
                throw new LivroReservadoException("Livro está reservado e não pode ser emprestado.");
            }

            if (livro.isEmprestado()) {
                System.out.println("Livro já está emprestado.");
                return;
            }

            LocalDate dataEmprestimo = LocalDate.now();
            LocalDate dataDevolucao = dataEmprestimo.plusDays(7);

            livro.emprestar(usuario.getNome());

            Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucao);
            emprestimos.add(emprestimo);

            System.out.println("Livro emprestado com sucesso para " + usuario.getNome() + "!");
            System.out.println("Data de Empréstimo: " + emprestimo.getDataEmprestimo());
            System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());

        } catch (LivroReservadoException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    public void devolverLivro(String tituloLivro) {
        Livro livro = buscarLivroPorTitulo(tituloLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        if (!livro.isEmprestado()) {
            System.out.println("Livro não está emprestado.");
            return;
        }

        livro.devolver();
    }

    public void reservarLivro(String tituloLivro) {
        Livro livro = buscarLivroPorTitulo(tituloLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        livro.reservar();
    }

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
            System.out.println("Nenhum empréstimo registrado.");
        }
    }

    public void exibirRelatorioEstatistico() {
        System.out.println("\n--- RELATÓRIO DA BIBLIOTECA 📊 ---");
        System.out.println("Total de livros cadastrados: " + livros.size());
        System.out.println("Livros emprestados: " + livros.stream().filter(Livro::isEmprestado).count());
        System.out.println("Livros reservados: " + livros.stream().filter(Livro::isReservado).count());
        System.out.println("Usuários cadastrados: " + usuarios.size());
    }

    private Usuario buscarUsuarioPorMatricula(String matricula) {
        for (Usuario user : usuarios) {
            if (user.getMatricula().equalsIgnoreCase(matricula)) {
                return user;
            }
        }
        return null;
    }

    private Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dados_biblioteca.ser"))) {
            oos.writeObject(this);
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static SistemaBiblioteca carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dados_biblioteca.ser"))) {
            return (SistemaBiblioteca) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Arquivo de dados não encontrado ou corrompido. Iniciando sistema novo...");
            return new SistemaBiblioteca();
        }
    }
}