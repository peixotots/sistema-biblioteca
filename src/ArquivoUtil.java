import java.io.*;
import java.util.List;

public class ArquivoUtil {

    public static <T> void salvarDados(List<T> lista, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> carregarDados(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + nomeArquivo + " não encontrado. Criando nova lista.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados do arquivo " + nomeArquivo + ": " + e.getMessage());
        }
        return new java.util.ArrayList<>();
    }
}