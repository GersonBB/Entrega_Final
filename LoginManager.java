import java.util.HashMap;

public class LoginManager {
    private HashMap<String, String> userCredentials;
    private CSVDataManager dataManager;

    public LoginManager(CSVDataManager dataManager) {
        this.dataManager = dataManager;
        this.userCredentials = dataManager.readCredentials();
    }

    public boolean validateLogin(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    public boolean userExists(String username) {
        return userCredentials.containsKey(username);
    }

    public void registerUser(String username, String password) {
        // Agregar nuevas credenciales al mapa y guardar en el archivo CSV
        userCredentials.put(username, password);
        dataManager.writeCredentials(userCredentials);
    }
}