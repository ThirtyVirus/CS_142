package calendar.view_controller.fx;

import calendar.model.Appointment;
import calendar.model.Calendar;
import calendar.model.Time;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.VerticalDirection;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

import java.io.IOException;
import java.util.List;

/**
 * Kal GUI Implementation
 * @author Brandon Calabrese
 */
public class KalGUI extends Application {
    public static String readFile = null;

    /**
     * Displays main window
     * @param primaryStage a new stage to add to
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Kal - Brandon Calabrese");
        Calendar c;
        //if no argument
        if (readFile == null){
            c = new Calendar(28);
        }
        else{
            c = Calendar.fromFile(readFile);
        }

        //define GridPane
        GridPane g = new GridPane();

        int x = 0;
        int y = 0;
        for (int counter = 1; counter < c.numDays() + 1; counter++){
            Button b = new Button(Integer.toString(counter));
            int date = counter;
            b.setOnAction(event -> new specificDate(c,date));
            b.setPrefWidth(75);
            b.setPrefHeight(75);
            g.add(b,x,y);
            x++;
            if (x > 6){x = 0; y++;}
        }
        Button saveButton = new Button("Save");
        saveButton.setPrefWidth(75);
        saveButton.setPrefHeight(75);
        saveButton.setStyle("-fx-border-color:green;");
        saveButton.setOnAction(event -> {
            try {
                c.toFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        g.add(saveButton,4,y+1);

        Button updateButton = new Button("Update");
        updateButton.setPrefWidth(75);
        updateButton.setPrefHeight(75);
        updateButton.setOnAction(event -> updateButtons(g,c));
        updateButton.setStyle("-fx-border-color:green;");
        g.add(updateButton,2,y+1);

        g.add(new Label("Press Update\nAfter Making\n    Changes"),1,y+1);
        g.add(new Label(" Red Border\nMeans there\nIs At Least 1\nAppointment"),5,y+1);

        updateButtons(g,c);
        primaryStage.setScene(new Scene(g));
        primaryStage.show();
    }

    /**
     * Calls start
     */
    public static void main(String[] args){
        if (args.length > 0){
            readFile = args[0];
        }

        Application.launch(args);
    }

    /**
     * Updates data representing when a date has an appointment
     */
    public void updateButtons(GridPane g, Calendar c){
        for (int counter = 0; counter < c.numDays(); counter++){
            if (c.appointmentsOn(counter + 1).size() > 0){
                g.getChildren().get(counter).setStyle("-fx-border-color:red;");
            }
            else{
                g.getChildren().get(counter).setStyle("");
            }
        }
    }

    /**
     * Class for second set of windows
     * Representation of a single day
     */
    class specificDate extends Stage{
        GridPane g;

        /**
         * Constructor, displays all Appointments as well as option to add or remove
         */
        public specificDate(Calendar c, int date){
            this.setTitle("Modify - " + date);
            this.setMinHeight(300);

            //define GridPane
            g = new GridPane();

            Label l = new Label("Appointments");
            l.setFont(Font.font("Arial",18));

            Label ll = new Label("Click an\nAppointment\nto delete it");
            ll.setFont(Font.font("Arial",18));

            Button addButton = new Button("Add");
            addButton.setOnAction(event -> addApp(c,date));
            addButton.setPrefWidth(75);
            addButton.setPrefHeight(75);

            g.add(l,0,0);
            g.add(new Label("              "),1,0);
            g.add(ll,2,0);
            g.add(addButton,2,1);

            for (int counter = 0; counter < c.appointmentsOn(date).size(); counter++){
                Button b = new Button(c.appointmentsOn(date).get(counter).toString());
                int newDate = counter;
                b.setOnAction(event -> removeApp(c,c.appointmentsOn(date).get(newDate)));

                g.add(b,0,counter + 1);
            }

            this.setScene(new Scene(g));
            this.show();
        }

        /**
         * Removes Appointment
         * @param c Calendar to modify
         * @param a Appointment to remove
         */
        public void removeApp(Calendar c, Appointment a){
            c.remove(a);
            this.close();
        }

        /**
         * Adds Appointment, calls new window
         * @param c Calendar to modify
         * @param date Date to add to
         */
        public void addApp(Calendar c, int date){
            new addAppointment(c,date);

            this.close();
        }

        public void update(){

        }
    }

    /**
     * Add appointment screen
     */
    class addAppointment extends Stage{

        /**
         * Constructor, gives user options for new appointment
         * @param c Calendar to modify
         * @param date Date to modify
         */
        public addAppointment(Calendar c, int date){
            this.setTitle("Add - " + date);
            this.setMinHeight(300);

            //define GridPane
            GridPane g = new GridPane();

            Label l = new Label("Time");
            l.setFont(Font.font("Arial",18));
            Label ll = new Label("Description");
            ll.setFont(Font.font("Arial",18));

            g.add(l,0,0);
            g.add(new Label("          "),1,0);
            g.add(ll,2,0);

            TextField ti = new TextField();
            g.add(ti,1,1);

            TextField desc = new TextField();
            g.add(desc,2,1);

            Button addButton = new Button("Add");
            addButton.setOnAction(event -> onButtonClick(c,date,ti,desc));
            addButton.setPrefWidth(75);
            addButton.setPrefHeight(75);
            g.add(addButton,2,3);

            this.setScene(new Scene(g));
            this.show();
        }

        /**
         * Removes Appointment
         * @param c Calendar to modify
         * @param date date for new appointment
         * @param ti time textbox
         * @param desc appointment description
         */
        public void onButtonClick(Calendar c, int date, TextField ti, TextField desc){
            if (ti.getText() == "" || desc.getText() == "") return;
            c.add(new Appointment(date,Time.fromString(ti.getText()),desc.getText()));
            this.close();
        }
    }

}