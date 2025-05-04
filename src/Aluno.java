public class Aluno extends Usuario implements Persistente {

    private String curso;
    private int anoDeIngresso;

    public Aluno(String nome, String matricula, String email, String curso, int anoDeIngresso) {
        super(nome, matricula, email);
        this.curso = curso;
        this.anoDeIngresso = anoDeIngresso;
    }

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

    @Override
    public void exibirDados() {
        System.out.println("Aluno: " + getNome() + ", Matrícula: " + getMatricula() +
                ", Email: " + getEmail() + ", Curso: " + curso + ", Ano de Ingresso: " + anoDeIngresso);
    }
}