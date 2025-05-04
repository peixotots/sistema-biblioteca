import java.io.*;

// Classe para salvar e carregar os dados do sistema de biblioteca
public class Utilidades {

    // Salva os dados do sistema em um arquivo
    // Recebe o objeto do sistema e o nome do arquivo como parâmetros
    public static void salvarDados(SistemaBiblioteca sistema, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            // Serializa o objeto do sistema e grava no arquivo
            oos.writeObject(sistema);
            System.out.println("Dados do sistema salvos com sucesso no arquivo: " + nomeArquivo);
        } catch (IOException e) {
            // Trata erros de entrada/saída durante o processo de gravação
            System.err.println("Erro ao salvar os dados no arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }

    // Carrega os dados do sistema a partir de um arquivo
    // Retorna o objeto do sistema carregado ou um novo sistema caso ocorra algum erro
    public static SistemaBiblioteca carregarDados(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            // Desserializa o objeto do sistema a partir do arquivo
            return (SistemaBiblioteca) ois.readObject();
        } catch (FileNotFoundException e) {
            // Trata o caso em que o arquivo não é encontrado
            System.out.println("Arquivo " + nomeArquivo + " não encontrado. Iniciando novo sistema\n.");
        } catch (IOException | ClassNotFoundException e) {
            // Trata erros de entrada/saída ou problemas de classe durante a leitura
            System.err.println("Erro ao carregar os dados do arquivo " + nomeArquivo + ": " + e.getMessage());
        }
        // Retorna um novo sistema vazio caso ocorra algum erro
        return new SistemaBiblioteca();
    }
}