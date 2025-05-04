import java.io.Serializable;

// Classe abstrata que serve como base para diferentes tipos de usuários no sistema (molde para as subclasses)
// Implementa a interface Persistente para permitir a serialização
public abstract class Usuario implements Serializable {

    private String nome;
    private String matricula;
    private String email;

    // Construtor da classe abstrata
    // Inicializa os atributos com os valores fornecidos
    public Usuario(String nome, String matricula, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
    }

    // Metodos de acesso aos atributos da classe (encapsulamento)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Metodo abstrato que deve ser implementado pelas subclasses para exibir os dados do usuário
    public abstract void exibirDados();
}