// Implementação concreta da classe abstrata Usuario
// Herda da classe abstrata Usuario e implementa a interface Persistente
// Classe que representa um professor no sistema

public class Professor extends Usuario implements Persistente {

    private String departamento;
    private int anoDeContratacao;

    // Construtor da classe concreta
    // Inicializa os atributos com os valores fornecidos
    public Professor(String nome, String matricula, String email, String departamento, int anoDeContratacao) {
        super(nome, matricula, email);
        this.departamento = departamento;
        this.anoDeContratacao = anoDeContratacao;
    }

    // Metodos de acesso aos atributos da classe (encapsulamento)
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getAnoDeContratacao() {
        return anoDeContratacao;
    }

    public void setAnoDeContratacao(int anoDeContratacao) {
        this.anoDeContratacao = anoDeContratacao;
    }

    // Sobrescrita do metodo abstrato exibirDados da classe Usuario
    // A implementação desse metodo é obrigatória, uma vez que este é um metodo abstrato da superclasse (também abstrata)
    @Override
    public void exibirDados() {
        System.out.println("Professor: " + getNome() + ", Matrícula: " + getMatricula() +
                ", Email: " + getEmail() + ", Departamento: " + departamento + ", Ano de Contratação: " + anoDeContratacao);
    }
}