import java.text.SimpleDateFormat;
import java.util.Date;

public class Reminder {
    private String appointmentName;
    private Date dateTime;

    public Reminder(String appointmentName, Date dateTime) {
        this.appointmentName = appointmentName;
        this.dateTime = dateTime;
    }

    public String getAppointmentName() {
        return appointmentName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getFormattedDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(dateTime);
    }
}
