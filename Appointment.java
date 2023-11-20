public class Appointment {
    private String name;
    private String date;
    private String time;
    private String description;

    public Appointment(String name, String date, String time, String description) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Cita: " + name + ", Fecha: " + date + ", Hora: " + time + ", Descripción: " + description;
    }
}