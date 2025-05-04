import java.io.Serializable;

// Classe que representa um livro no sistema de biblioteca
// Implementa as interfaces Emprestavel, Reservavel e Serializable
// A implementação de Serializable permite que objetos da classe Livro sejam salvos em arquivos e carregados posteriormente
// A serialização converte o arquivo em um formato binário e desserialização reconstrói o arquivo a partir desse formato, possibilitando que sejam salvos em arquivos

public class Livro implements Serializable, Emprestavel, Reservavel {

    private String titulo;
    private String autor;
    private int anoLancamento;
    private String tema;
    private boolean emprestado;
    private boolean reservado;

    // Construtor da classe
    // Inicializa os atributos com os valores fornecidos
    public Livro(String titulo, String autor, int anoLancamento, String tema) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoLancamento = anoLancamento;
        this.tema = tema;
        this.emprestado = false;
        this.reservado = false;
    }

    // Metodos de encapsulamento
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public boolean isReservado() {
        return reservado;
    }

    // Sobrescrita e Sobrecarga de metodos
    // Sobrescrita do metodo emprestar() da interface Emprestavel
    @Override
    public void emprestar() {
        emprestar(null);
    }

    // Sobrecarga do metodo emprestar() ao incluir um novo parâmetro
    // Empresta o livro para um usuário, caso ele não esteja emprestado ou reservado
    public void emprestar(String nomeUsuario) {
        if (!emprestado && !reservado) {
            emprestado = true;
        } else if (emprestado) {
            System.out.println("Livro já está emprestado.");
        } else {
            System.out.println("Livro está reservado e não pode ser emprestado.");
        }
    }

    // Sobrescrita do metodo devolver() da interface Emprestavel
    // Devolve o livro e remove a reserva, caso exista
    @Override
    public void devolver() {
        if (emprestado) {
            emprestado = false;
            reservado = false;
            System.out.println("\nLivro devolvido com sucesso!");
        } else {
            System.out.println("\nLivro não estava emprestado.");
        }
    }

   // Sobrescrita do metodo reservar() da interface Reservavel
   // Reserva o livro, caso ele não esteja emprestado ou já reservado
    @Override
    public void reservar() {
        if (!emprestado && !reservado) {
            reservado = true;
        } else {
            System.out.println("\nReserva não realizada. O livro encontra-se emprestado.");
        }
    }

    // Sobrescrita do metodo toString do Java
    @Override
    public String toString() {
        return "Título: " + titulo +
                ", Autor: " + autor +
                ", Ano de Lançamento: " + anoLancamento +
                ", Tema: " + tema +
                ", Emprestado: " + (emprestado ? "Sim" : "Não") +
                ", Reservado: " + (reservado ? "Sim" : "Não");
    }
}