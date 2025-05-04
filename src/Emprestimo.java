// Classe que representa um empréstimo de livro realizado por um usuário
// Contém informações sobre o usuário, o livro, a data de empréstimo e a data de devolução

import java.io.Serializable;
import java.time.LocalDate;

public class Emprestimo implements Serializable {

    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    // Construtor da classe
    // Inicializa os atributos com os valores fornecidos
    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    // Metodos de acesso aos atributos da classe (encapsulamento)
    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    // Sobrescrita do metodo toString do Java
    // Retorna uma representação textual do objeto Emprestimo
    @Override
    public String toString() {
        return "Empréstimo [Usuário: " + usuario.getNome() +
                ", Livro: " + livro.getTitulo() +
                ", Data de Empréstimo: " + dataEmprestimo +
                ", Data de Devolução: " + dataDevolucao + "]";
    }
}