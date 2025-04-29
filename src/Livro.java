import java.io.Serializable;

public class Livro implements Serializable, Emprestavel, Reservavel {

    private String titulo;
    private String autor;
    private boolean emprestado;
    private boolean reservado;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
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

    public boolean isEmprestado() {
        return emprestado;
    }

    public boolean isReservado() {
        return reservado;
    }

    @Override
    public void emprestar() {
        emprestar(null); // Chama o metodo com parâmetro, passando null
    }

    public void emprestar(String nomeUsuario) {
        if (!emprestado) {
            emprestado = true;
            if (nomeUsuario != null) {
                System.out.println("\nLivro emprestado com sucesso para " + nomeUsuario + "!");
            } else {
                System.out.println("\nLivro emprestado com sucesso!");
            }
        } else {
            System.out.println("\nLivro já está emprestado.");
        }
    }

//    public void emprestar(LocalDate prazo) {
//        if (!emprestado) {
//            emprestado = true;
//            System.out.println("Item emprestado até " + prazo + "!");
//        } else {
//            System.out.println("Item já está emprestado.");
//        }
//    }

    @Override
    public void devolver() {
        if (emprestado) {
            emprestado = false;
            reservado = false; // Remove a reserva ao devolver o livro
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
        return "Título: " + titulo + ", Autor: " + autor +
                ", Emprestado: " + (emprestado ? "Sim" : "Não") +
                ", Reservado: " + (reservado ? "Sim" : "Não");
    }

}
