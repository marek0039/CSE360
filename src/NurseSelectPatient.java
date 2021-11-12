import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NurseSelectPatient extends StackPane
{
    //attributes of this class to be displayed on the pane
    private Color mainColor;
    private Text title, welcome, select, patient, create;
    private ComboBox patientList;
    private Button go, createButton, logout;
    private Label errLabel;

    public NurseSelectPatient()
    {
        //Step 1. Using the current User logged in, get the list of their patients and also display their name
        String[] name_arr = new String[0];    //array which will store first and last name of the patients
        String nurse_name = null;   //will hold the last name of the nurse
        try {
            String getConnection = "SELECT Last_Name, Connection from Professional WHERE ID="
                    + HealthPortal.currUser + ";";
            //using the current user, get the doctor the nurse is connected to as well as their name.
            ResultSet rs = HealthPortal.statement.executeQuery(getConnection);  //execute the query
            int doctorID = 0;   //will hold the id of the doctor.
            rs.last();  //get the last row of the query
            if (rs.getRow() == 1) { //there should only be 1 row but checking
                nurse_name = rs.getString("Last_Name"); //store the last name
                doctorID = rs.getInt("Connection");     // and connected doctor.
            } else {    //otherwise, throw and exception.
                throw new FailedException("Cannot find User: " + HealthPortal.currUser);
            }
            List<String> patient_names = new ArrayList<String>(); //because the length is unknown first make a list
            if (doctorID != 0) {    //if the doctor id was updated, continue
                String getpatients = "SELECT First_Name, Last_Name FROM Patient WHERE Doctor=" + doctorID;
                //get patient info
                rs = HealthPortal.statement.executeQuery(getpatients); //execute the query
                while(rs.next()) {  //iterate through all the rows
                    patient_names.add(rs.getString("First_Name") + rs.getString("Last_Name"));
                    //add each patient name to the patient_names list
                }
                name_arr = patient_names.toArray(new String[patient_names.size()]);
                //once done iterating convert to array
            }
            else {
                throw new FailedException("Did not Find Doctor."); //throw an exception if doctor_id is still 0.
            }
        } catch (Exception e) {
            System.err.print(e);
        }

        errLabel = new Label(); //label which will display an error done by the user.
    private int currUser;

    public NurseSelectPatient(int user)
    {
        currUser = user;
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling who is logged in
        welcome = new Text("Welcome in, Nurse " + nurse_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        //black text labeling the fields needed to log on
        select = new Text("Select a patient from the list below, or press create to make a new patient:");
        select.setFont(Font.font("Times New Roman", 14));
        select.setFill(Color.BLACK);

        patient = new Text("Patient:");
        patient.setFont(Font.font("Times New Roman", 14));
        patient.setFill(Color.BLACK);

        create = new Text("Create New Patient:");
        create.setFont(Font.font("Times New Roman", 14));
        create.setFill(Color.BLACK);

        //combobox with list of patients
        //needs to be added from the database
        patientList = new ComboBox();
        for(int i=0; i < name_arr.length; i++) {    //iterate through the array...
            patientList.getItems().add(name_arr[i]);//and each name to the combo box.
        }

        //buttons to allow the user to go to the patient's page or log out (back to med prof
        //log in screen) as well as create button to take user to create a new patient
        go = new Button("Go");
        createButton = new Button("Create");
        logout = new Button("Log Out");
        //when user presses go, they read whatever patient the user selected and goes to their vitals page. Case 24
        SelectPatientButton handler = new SelectPatientButton(24);
        go.setOnAction(handler);
        //when the user presses create, they are sent to the new patient creation form but the nurse vers. Case 22
        ForwardButton forward1 = new ForwardButton(22);
        createButton.setOnAction(forward1);
        //when the user presses Log Out, they are sent back to the medical professional login screen. Case 14
        ForwardButton forward2 = new ForwardButton(14);
        logout.setOnAction(forward2);


        //Vertical pane to put the title and existing patient label together
        VBox titleBox = new VBox(5);
        titleBox.getChildren().addAll(title, welcome, select, errLabel);

        //Vertical pane to put the log on requirements in the center of the page
        VBox centerElements = new VBox(8);
        centerElements.getChildren().addAll(patient, patientList, go, create, createButton, logout);
        //insets to center the buttons below the text/other text fields aesthetic
        VBox.setMargin(go, new Insets(0, 0, 0, 60));
        VBox.setMargin(createButton, new Insets(0, 0, 0, 50));
        VBox.setMargin(logout, new Insets(0, 0, 0, 45));

        //add the top text with its contents to the top left
        //of this stack pane
        StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
        StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
        this.getChildren().add(titleBox);

        //border pane to add the contents to the center of the stack pane
        BorderPane bp = new BorderPane();
        bp.setCenter(centerElements);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //add the border pane to this stack pane
        this.getChildren().add(bp);
    } //end constructor

    private class SelectPatientButton extends ForwardButton {   //Button which handles reading what patient is selected

        private SelectPatientButton(int caseInt) {
            super(caseInt);
        }   //constructor to call the super constructor.

        @Override
        public void handle(ActionEvent event) { //override the handle method.
            if (patientList.getSelectionModel().isEmpty()) {    //make sure the nurse selected a patient
                errLabel.setText("Please Select a Patient");    //if it is empty update the error label.
                errLabel.setTextFill(Color.RED);
            }
            else {
                try {
                    String name = (String) patientList.getValue();  //get the value that the user selected.
                    String[] name_list = name.split(" ");   //split the name between first and last name.
                    System.out.println(name);   //print out for debugging purposes
                    String fname = name_list[0];    //store the first and last name
                    String lname = name_list[1];
                    String query = "SELECT PatientID FROM Patient WHERE First_Name='"
                            + fname + "' AND Last_Name='" + lname + "';";   //query to find the patient.
                    ResultSet rs = HealthPortal.statement.executeQuery(query);  //execute the query
                    rs.last();  //get the last row.
                    if(rs.getRow() == 0) {  //if the row index is 0, then the query failed.
                        throw new FailedException("SQL Query FAILED!!!");   //throw exception.
                    }
                    else {  //otherwise...
                        HealthPortal.currPatient = rs.getInt("PatientID");
                        //set current patient to this patient
                        super.handle(event);    //and now call forward button's handle.
                    }
                } catch(Exception e) {  //catch exception
                    System.err.print(e);
                }
            }
        }
    }

    private static class FailedException extends Exception {    //custom exception to print the error message.
        private FailedException(String errorMessage) {
            super(errorMessage);
        }
    }
} //end nurse select patient class