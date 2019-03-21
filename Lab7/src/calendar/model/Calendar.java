package calendar.model;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Kal Calendar Implementation
 * @author Brandon Calabrese
 */
public class Calendar extends Observable {
    /**
     * How many days in this month
     */
    private final int monthSize;
    /**
     * Associates days of the month with appointments
     */
    private List<List<Appointment>> appointments;

    /**
     * Stores filename for file being used
     */
    public static String fName;

    /**
     * Create a month calendar of the given size.
     * @param monthSize size of a single month
     */
    public Calendar(int monthSize){
        this.monthSize = monthSize;
        appointments = new ArrayList<>();
        for (int counter = 0; counter < monthSize + 1; counter++){
            appointments.add(new ArrayList<Appointment>());
        }
    }

    /**
     * Add an appointment.
     * @param appt appointment to be added
     */
    public void add(Appointment appt){
        appointmentsOn(appt.getDate()).add(appt);
        dateChanged(appt.getDate());
    }

    /**
     * Add a new appointment.
     * @param date the date of the new Appointment
     * @param time the time of the new Appointment
     * @param what the topic of the new Appointment
     */
    public void add(int date, Time time, String what){
        Appointment apptt;
        apptt = new Appointment(date,time,what);

        for (int counter = 0; counter < appointmentsOn(date).size(); counter++){
              if (apptt.compareTo(appointmentsOn(date).get(counter)) == 0){
                    return;
               }
        }
        appointmentsOn(date).add(apptt);
        dateChanged(date);
    }

    /**
     * What appointments are in place for a certain date?
     * @param date The date being tested
     * @return list of all appointments for a given date
     */
    public List<Appointment> appointmentsOn(int date){
        appointments.get(date).sort(Appointment::compareTo);
        return appointments.get(date);
    }

    /**
     * The appointment list for same date in the calendar has changed.
     * @param date The date being modified
     */
    protected void dateChanged(int date){
        setChanged();
        notifyObservers(date);
    }

    /**
     * Create a month calendar and initialize it with the data from a file.
     * @param fileName file to be read
     */
    public static Calendar fromFile(String fileName) throws IOException{
    fName = fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            ArrayList<String> lines = new ArrayList<>();
            String currentLine = "";
            while (currentLine != null){
                currentLine = reader.readLine();
                if (currentLine != null) lines.add(currentLine);
            }

            Calendar newCal = new Calendar(Integer.parseInt(lines.get(0)));
            lines.remove(lines.get(0));
            for (String s : lines){
                Appointment newApp = Appointment.fromString(s);
                newCal.appointmentsOn(newApp.getDate()).add(newApp);
                newCal.dateChanged(newApp.getDate());
            }

            return newCal;
        }
        catch(FileNotFoundException nfe){
            throw new IOException(nfe);
        }
    }

    /**
     * How many days in this calendar's month?
     * @return The size of the month
     */
    public int numDays(){
        return monthSize;
    }

    /**
     * Get rid of an existing appointment.
     * @param appt Appointment to be removed
     */
    public void remove(Appointment appt){
        for (Appointment a : appointmentsOn(appt.getDate())){
            if (appt.compareTo(a) == 0){
                appointmentsOn(appt.getDate()).remove(a);
                break;
            }
        }
        dateChanged(appt.getDate());
    }

    /**
     * Save all appointments in the file from which appointments
     * were originally read when this calendar was created.
     */
    public void toFile() throws IOException{
        if ( fName == null ) {
            throw new IOException( "Calendar not loaded from a file." );
       }
       try ( PrintWriter calFile = new PrintWriter( this.fName ) ) {
           calFile.println(monthSize);
           for (List<Appointment> day : appointments){
               for (Appointment a : day){
                   calFile.println(a.csvFormat());
               }
           }
        }
    }


}
