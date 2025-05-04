// Criação de classe de exceção personalizada

public class EmprestimoNaoPermitidoException extends Exception {
    public EmprestimoNaoPermitidoException(String mensagem) {
        super(mensagem);
    }
}