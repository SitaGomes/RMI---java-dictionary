import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class DictionaryClient {
    public static void main(String[] args) {
        try {
            Dictionary dic = (Dictionary) Naming.lookup("rmi://localhost/DictionaryService");
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("\nDicionario MuitoBomProMaxUltra v2024:");
                    System.out.println("1. Procurar significado de uma palavra");
                    System.out.println("2. Adicionar nova palavra ao dicionário");
                    System.out.println("3. Remover uma palavra do dicionário");
                    System.out.println("4. Listar todas as palavras do dicionário");
                    System.out.println("5. Sair");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            System.out.print("\nDigite a palavra: ");
                            String searchWord = scanner.nextLine();
                            System.out.println("Significado: " + dic.getDefinition(searchWord));
                            break;
                        case 2:
                            System.out.print("\nDigite a palavra: ");
                            String addWord = scanner.nextLine();
                            System.out.print("Digite o significado: ");
                            String definition = scanner.nextLine();
                            System.out.println(dic.addWord(addWord, definition));
                            break;
                        case 3:
                            System.out.print("\nDigite a palavra: ");
                            String removeWord = scanner.nextLine();
                            System.out.println("Significado: " + dic.getDefinition(removeWord));
                            System.out.print("Tem certeza que deseja remover? (s/n): ");
                            String confirm = scanner.nextLine();
                            System.out
                                    .println(confirm.equals("s") ? dic.removeWord(removeWord) : "Operação cancelada.");
                            break;
                        case 4:
                            try {
                                System.out.println("\nPalavras no dicionário:");
                                List<String> words = dic.listWords();
                                if (words.isEmpty()) {
                                    System.out.println("O dicionário está vazio.");
                                } else {
                                    for (String word : words) {
                                        System.out.println(word + " - " + dic.getDefinition(word));
                                    }
                                }
                            } catch (RemoteException e) {
                                System.err.println("Erro ao tentar listar palavras: " + e.getMessage());
                                e.printStackTrace();
                            }
                            break;
                        case 5:
                            System.out.println("Até a proxima... (espero que demore)");
                            DictionaryServant.saveDictionary(DictionaryServant.dictionary);
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente mané: " + e.toString());
            e.printStackTrace();
        }
    }
}
