// Implementação concreta da classe abstrata Usuario
// Herda da classe abstrata Usuario e implementa a interface Persistente
// Classe que representa um aluno no sistema

public class Aluno extends Usuario implements Persistente {

    private String curso;
    private int anoDeIngresso;

    // Construtor da classe concreta
    // Inicializa os atributos com os valores fornecidos
    public Aluno(String nome, String matricula, String email, String curso, int anoDeIngresso) {
        super(nome, matricula, email);
        this.curso = curso;
        this.anoDeIngresso = anoDeIngresso;
    }

    // Metodos de acesso aos atributos da classe (encapsulamento)
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getAnoDeIngresso() {
        return anoDeIngresso;
    }

    public void setAnoDeIngresso(int anoDeIngresso) {
        this.anoDeIngresso = anoDeIngresso;
    }

    // Sobrescrita do metodo abstrato exibirDados da classe Usuario
    // A implementação desse metodo é obrigatória, uma vez que este é um metodo abstrato da superclasse (também abstrata)
    @Override
    public void exibirDados() {
        System.out.println("Aluno: " + getNome() + ", Matrícula: " + getMatricula() +
                ", Email: " + getEmail() + ", Curso: " + curso + ", Ano de Ingresso: " + anoDeIngresso);
    }
}