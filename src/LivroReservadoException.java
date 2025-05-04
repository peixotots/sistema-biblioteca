// Criação de classe de exceção personalizada

public class LivroReservadoException extends Exception {
    public LivroReservadoException(String mensagem) {
        super(mensagem);
    }
}