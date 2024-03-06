import java.util.List;

public interface Dictionary extends java.rmi.Remote {
    String getDefinition(String word) throws java.rmi.RemoteException;

    String addWord(String word, String definition) throws java.rmi.RemoteException;

    String removeWord(String word) throws java.rmi.RemoteException;

    List<String> listWords() throws java.rmi.RemoteException;
}
