import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DictionaryServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // RMI port 1099
            DictionaryServant service = new DictionaryServant();
            Naming.rebind("rmi://localhost/DictionaryService", service);
            System.out.println("Server do dicionário pronto...");
        } catch (Exception e) {
            System.err.println("Erro mané: " + e.toString());
            e.printStackTrace();
        }
    }
}
