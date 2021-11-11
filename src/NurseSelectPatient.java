import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private StackPane root;
    private int currUser;
    private Statement statement;

    public NurseSelectPatient(StackPane rootPane, int user) throws SQLException {
        root = rootPane;
        currUser = user;
        String[] f_name_arr = new String[0];
        String[] l_name_arr = new String[0];
        try {
            Connector connect = new Connector();
            statement = connect.getStatement();
            String getConnection = "SELECT Connection from Professional WHERE ID=" + currUser;
            ResultSet rs = statement.executeQuery(getConnection);
            int doctorID = 0;
            if (rs.getRow() == 1) {
                rs.first();
                doctorID = rs.getInt("Connection");
            } else {
                throw new FailedException("Cannot find User: " + currUser);
            }
            List<String> patient_first_names = new ArrayList<String>();
            List<String> patient_last_names = new ArrayList<String>();
            if (doctorID != 0) {
                String getpatients = "SELECT First_Name, Last_Name FROM Patient WHERE Doctor=" + doctorID;
                rs = statement.executeQuery(getpatients);
                while(rs.next()) {
                    patient_first_names.add(rs.getString("First_Name"));
                    patient_last_names.add(rs.getString("Last_Name"));
                }
                f_name_arr = patient_first_names.toArray(new String[patient_first_names.size()]);
                l_name_arr = patient_last_names.toArray(new String[patient_last_names.size()]);
            }
            else {
                throw new FailedException("Did not Find Doctor.") ;
            }
        } catch (Exception e) {
            System.err.print(e);
        }
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling who is logged in, we need to figure out
        //how we want to pull this from the database
        welcome = new Text("Welcome in, Nurse Jackson");
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
        //dummy list as filler right now
        patientList = new ComboBox();
        String name;
        for(int i=0; i < f_name_arr.length; i++) {
            name = f_name_arr[i] + l_name_arr[i];
            patientList.getItems().add(name);
        }

        //buttons to allow the user to go to the patient's page or log out (back to med prof
        //log in screen) as well as create button to take user to create a new patient
        go = new Button("Go");
        createButton = new Button("Create");
        logout = new Button("Log Out");
        SelectPatientButton handler = new SelectPatientButton(27, root, currUser);
        go.setOnAction(handler);
        ForwardButton forward1 = new ForwardButton(25, root, currUser);
        createButton.setOnAction(forward1);
        ForwardButton forward2 = new ForwardButton(1, root, currUser);
        logout.setOnAction(forward2);


        //Vertical pane to put the title and existing patient label together
        VBox titleBox = new VBox(5);
        titleBox.getChildren().addAll(title, welcome, select);

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

    private class SelectPatientButton extends ForwardButton {

        private SelectPatientButton(int caseInt, StackPane pane, int user) {
            super(caseInt, pane, user);

        }

        @Override
        public void handle(ActionEvent event) {

            super.handle(event);
        }
    }

    private class FailedException extends Exception {
        private FailedException(String errorMessage) {
            super(errorMessage);
        }
    }
} //end nurse select patient class