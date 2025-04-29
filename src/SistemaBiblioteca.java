import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaBiblioteca {

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
}
