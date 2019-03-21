package calendar.model;

import java.io.IOException;
/**
 * Kal Appointment implementation
 * @author Brandon Calabrese
 */
public class Appointment implements Comparable<Appointment>{

    /**
     * Data that defines the date and time of an appointment,
     * as well as what the appointment is
     */
    public int date;
    public Time time;
    public String text;

    /**
     * Create a new appointment.
     * @param date new date
     * @param time new time
     * @param what new what
     */
    public Appointment(int date, Time time, String what){
        this.date = date;
        this.time = time;
        this.text = what;
    }
    /**
     * Compare this appointment to another one.
     * @param other object being compared to
    */
    public int compareTo(Appointment other){
        Appointment test = (Appointment)other;

        if (date < test.getDate()) return -1;
        if (date > test.getDate()) return 1;

        if (time.compareTo(other.getTime()) < 0) return -1;

        if (time.compareTo(other.getTime()) == 0) return 0;

        return 1;
    }

    /**
     * Create a string suitable for storing in a calendar file.
     * @return formatted string representation of appointment
     */
    public String csvFormat(){
        return date + "," + time.toString() + "," + text;
    }

    /**
     * Are the two appointments equal?
     * @param other object being compared to
     */
    @Override
    public boolean equals(Object other){
        Appointment test = (Appointment)other;
        if (compareTo(test) == 0){
            return true;
        }
        return false;
    }

    /**
     * Create an appointment from a text description.
     * @param inputLine text to read from
     */
    public static Appointment fromString(String inputLine){
        String[] word = inputLine.split(",");
        int newDate  = Integer.parseInt(word[0]);
        Time newTime = Time.fromString(word[1]);
        String newText = word[2];
        return new Appointment(newDate,newTime,newText);
    }

    /**
     * What is the date of the appointment?
     * @return date
     */
    public int getDate(){
        return date;
    }

    /**
     * What is this appointment for?
     * @return text
     */
    public String getText(){
        return text;
    }

    /**
     * What is the time of the appointment?
     * @return time
     */
    public Time getTime(){
        return time;
    }

    /**
     * Create a user-friendly description of this appointment.
     */
    public String toString() {
        return "on " + date + " at " + time + " -- " + text;
    }


}
