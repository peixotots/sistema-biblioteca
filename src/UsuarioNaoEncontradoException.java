// Criação de classe de exceção personalizada

public class UsuarioNaoEncontradoException extends Exception {
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}