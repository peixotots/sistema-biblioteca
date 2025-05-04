// GRUPO 2
// Felipe Sergio Da Silva Freitas - 12221BSI223
// Italo Carlos de Paula Gomes - 12221BSI264
// Pedro Henrique Da Silva Freitas - 12411BSI377
// Tainá Souza Peixoto - 12311BSI316

// Classe principal que inicializa o sistema da biblioteca
// Responsável por exibir o menu de interação com o usuário
public class Main {
    public static void main(String[] args) {

        // Carrega os dados do arquivo .dat
        SistemaBiblioteca sistema = Utilidades.carregarDados("dados_biblioteca.dat");

        // Exibe os dados carregados (se necessário)
        sistema.listarUsuarios();
        sistema.listarLivros();

        // Instancia o objeto Menu para que ele seja carregado
        Menu menu = new Menu();
        menu.exibirMenu();

    }
}