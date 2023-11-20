import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto CSVDataManager
        CSVDataManager dataManager = new CSVDataManager();

        // Crear un objeto LoginManager pasándole CSVDataManager
        LoginManager loginManager = new LoginManager(dataManager);

        // Crear un objeto de tu interfaz gráfica principal pasándole LoginManager y CSVDataManager
        MainFrame mainFrame = new MainFrame(loginManager, dataManager);
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
// clase principal 
