// Criação de classe de exceção personalizada

public class LivroNaoEncontradoException extends Exception {
    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}