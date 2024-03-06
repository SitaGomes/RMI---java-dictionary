import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class DictionaryServant extends java.rmi.server.UnicastRemoteObject implements Dictionary {
    static ConcurrentHashMap<String, String> dictionary = new ConcurrentHashMap<>();
    private final static long serialVersionUID = 1L;

    private final String wordExistsError = "Palavra já existe.";
    private final String wordNotFoundError = "Palavra não encontrada.";

    private final String wordAddedSuccessfully = "Palavra adicionada com sucesso!";
    private final String wordRemovedSuccessfully = "Palavra removida com sucesso!";

    private final static String fileName = "dictionary.json";

    protected DictionaryServant() throws java.rmi.RemoteException {
        super();
        DictionaryServant.loadDictionary();
    }

    public synchronized String getDefinition(String word) throws java.rmi.RemoteException {
        return dictionary.getOrDefault(word, wordNotFoundError);
    }

    public synchronized String addWord(String word, String definition) throws java.rmi.RemoteException {
        if (dictionary.containsKey(word)) {
            return wordExistsError;
        } else {
            dictionary.put(word, definition);
            return wordAddedSuccessfully;
        }
    }

    public synchronized String removeWord(String word) throws java.rmi.RemoteException {
        if (dictionary.remove(word) == null) {
            return wordNotFoundError;
        } else {
            return wordRemovedSuccessfully;
        }
    }

    public synchronized List<String> listWords() throws java.rmi.RemoteException {
        List<String> wordsList = new ArrayList<>(dictionary.keySet());
        return wordsList;
    }

    public static void saveDictionary(ConcurrentHashMap<String, String> dictionary) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write("{\n");
            int entryCount = 0;
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                fileWriter.write("  \"" + entry.getKey() + "\": \"" + entry.getValue() + "\"");
                entryCount++;
                if (entryCount < dictionary.size()) {
                    fileWriter.write(",\n");
                }
            }
            fileWriter.write("\n}");
            System.out.println("Dictionary saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the dictionary.");
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while closing the file.");
                    e.printStackTrace();
                }
            }
        }
    }

    public static ConcurrentHashMap<String, String> loadDictionary() {
        ConcurrentHashMap<String, String> dictionary = new ConcurrentHashMap<>();
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().trim();
                if (data.equals("}"))
                    break;
                String[] parts = data.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim().replace("\"", "");
                    String value = parts[1].trim().replace("\"", "").replace(",", "");
                    dictionary.put(key, value);
                }
            }
            myReader.close();
            System.out.println("Dicionario carregado com sucesso! path:" + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o load, mas ta tranquilo ainda 🙃");
        }
        return dictionary;
    }
}
