import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class MainFrame extends JFrame {
    private LoginManager loginManager;
    private CSVDataManager dataManager;
    private JPanel currentPanel;
    private AppointmentManager appointmentManager;

    public MainFrame(LoginManager loginManager, CSVDataManager dataManager) {
        this.loginManager = loginManager;
        this.dataManager = dataManager;
        this.appointmentManager = new AppointmentManager();

        // Personalizar la apariencia de los botones
        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Button.foreground", Color.BLACK);

        setTitle("Tu Aplicación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        // Establecer un tamaño inicial más pequeño (ajústalo según tus necesidades)
        setSize(400, 300);

        // Mostrar el panel de inicio de sesión inicialmente
        showLoginPanel();
    }

    public void showMainOptions() {
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton verCitasButton = createStyledButton("Ver Citas");
        JButton agregarCitaButton = createStyledButton("Agregar Cita");
        JButton eliminarCitaButton = createStyledButton("Eliminar Cita");

        verCitasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAppointments();
            }
        });

        agregarCitaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAppointment();
            }
        });

        eliminarCitaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAppointmentsForDeletion();
            }
        });

        mainPanel.add(verCitasButton);
        mainPanel.add(agregarCitaButton);
        mainPanel.add(eliminarCitaButton);

        switchPanel(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        return button;
    }

    private void showAppointments() {
        List<Appointment> appointments = appointmentManager.getAppointments();
        String[] columnNames = {"Nombre", "Fecha", "Hora", "Descripción"};
        Object[][] data = new Object[appointments.size()][4];

        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            data[i] = new Object[]{appointment.getName(), appointment.getDate(), appointment.getTime(), appointment.getDescription()};
        }

        JTable table = new JTable(data, columnNames);
        JOptionPane.showMessageDialog(MainFrame.this, new JScrollPane(table));
    }

    private void addAppointment() {
        JPanel addAppointmentPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        addAppointmentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField descriptionField = new JTextField();

        addAppointmentPanel.add(new JLabel("Nombre:"));
        addAppointmentPanel.add(nameField);
        addAppointmentPanel.add(new JLabel("Fecha (DD/MM/YYYY):"));
        addAppointmentPanel.add(dateField);
        addAppointmentPanel.add(new JLabel("Hora:"));
        addAppointmentPanel.add(timeField);
        addAppointmentPanel.add(new JLabel("Descripción:"));
        addAppointmentPanel.add(descriptionField);

        int result = JOptionPane.showConfirmDialog(MainFrame.this, addAppointmentPanel,
                "Agregar Cita", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String date = dateField.getText();
            String time = timeField.getText();
            String description = descriptionField.getText();

            Appointment newAppointment = new Appointment(name, date, time, description);
            appointmentManager.addAppointment(newAppointment);

            JOptionPane.showMessageDialog(MainFrame.this, "Cita agregada exitosamente.");
        }
    }

    private void showAppointmentsForDeletion() {
        List<Appointment> appointments = appointmentManager.getAppointments();

        if (appointments.isEmpty()) {
            JOptionPane.showMessageDialog(MainFrame.this, "No tiene citas para eliminar.");
        } else {
            Object[] options = new Object[appointments.size()];
            for (int i = 0; i < appointments.size(); i++) {
                options[i] = appointments.get(i).toString();
            }

            Object selectedOption = JOptionPane.showInputDialog(MainFrame.this,
                    "Seleccione la cita a eliminar:", "Eliminar Cita", JOptionPane.QUESTION_MESSAGE, null, options,
                    options[0]);

            if (selectedOption != null) {
                Appointment selectedAppointment = appointments.get(Arrays.asList(options).indexOf(selectedOption));
                appointmentManager.removeAppointment(selectedAppointment);
                JOptionPane.showMessageDialog(MainFrame.this, "Cita eliminada exitosamente.");
            }
        }
    }

    private void switchPanel(JPanel newPanel) {
        getContentPane().removeAll();
        add(newPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showLoginPanel() {
        LoginPanel loginPanel = new LoginPanel(loginManager, this);
        switchPanel(loginPanel);
    }

    public static void main(String[] args) {
        CSVDataManager dataManager = new CSVDataManager();
        LoginManager loginManager = new LoginManager(dataManager);
        MainFrame mainFrame = new MainFrame(loginManager, dataManager);
        mainFrame.setVisible(true);
    }
}

// Otras clases y métodos
