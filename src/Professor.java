public class Professor extends Usuario implements Persistente {

    private String departamento;
    private int anoDeContratacao;

    public Professor(String nome, String matricula, String email, String departamento, int anoDeContratacao) {
        super(nome, matricula, email);
        this.departamento = departamento;
        this.anoDeContratacao = anoDeContratacao;
    }

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

    @Override
    public void exibirDados() {
        System.out.println("Professor: " + getNome() + ", Matrícula: " + getMatricula() +
                ", Email: " + getEmail() + ", Departamento: " + departamento + ", Ano de Contratação: " + anoDeContratacao);
    }
}