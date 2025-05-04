import java.io.Serializable;

public class Livro implements Serializable, Emprestavel, Reservavel, Persistente {

    private String titulo;
    private String autor;
    private int anoLancamento;
    private String tema;
    private boolean emprestado;
    private boolean reservado;

    public Livro(String titulo, String autor, int anoLancamento, String tema) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoLancamento = anoLancamento;
        this.tema = tema;
        this.emprestado = false;
        this.reservado = false;
    }

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

    @Override
    public void emprestar() {
        emprestar(null);
    }

    public void emprestar(String nomeUsuario) {
        if (!emprestado && !reservado) {
            emprestado = true;
            System.out.println(nomeUsuario != null
                    ? "\nLivro emprestado com sucesso para " + nomeUsuario + "!"
                    : "\nLivro emprestado com sucesso!");
        } else if (emprestado) {
            System.out.println("\nLivro já está emprestado.");
        } else {
            System.out.println("\nLivro está reservado e não pode ser emprestado.");
        }
    }

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

    @Override
    public void reservar() {
        if (!emprestado && !reservado) {
            reservado = true;
            System.out.println("\nLivro reservado com sucesso!");
        } else {
            System.out.println("\nReserva não realizada. O livro encontra-se emprestado.");
        }
    }

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