import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVDataManager {
    private String csvFilePath = "citas.csv";

    public List<String[]> readData() {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public HashMap<String, String> readCredentials() {
        HashMap<String, String> credentials = new HashMap<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Verificar que la línea tenga al menos dos elementos
                if (values.length >= 2) {
                    // Assuming the first column is the username and the second column is the password
                    credentials.put(values[0], values[1]);
                } else {
                    // Manejar el caso en que la línea no tenga suficientes elementos
                    System.err.println("Error: Datos insuficientes en la línea del archivo CSV.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return credentials;
    }
    
    public void writeData(List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (String[] values : data) {
                bw.write(String.join(",", values));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCredentials(Map<String, String> credentials) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            for (Map.Entry<String, String> entry : credentials.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}