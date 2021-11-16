import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NurseVitalsConfirmation extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, patient, dob, confirmation;
    private Button patientSummary, back;

    public NurseVitalsConfirmation()
    {
        ResultSet rs = null;
        String nurseName = null;
        String patientFName = null;
        String patientLName = null;
        String pDateOfBirth = null;
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //and the nurse who is currently logged in
        //Note: these will need to be read in from the previous New patient form
        //text fields/areas so they will end up being parsed input rather than this dummy default text

        try {
            String sql = "SELECT Last_Name FROM Professional WHERE ID='" + HealthPortal.currUser + "';";
            String sql1 = "SELECT * FROM Patient WHERE PatientID='" + HealthPortal.currPatient + "';";
            rs = HealthPortal.statement.executeQuery(sql);

            rs.last();  //get the last row of the query
            if (rs.getRow() == 1) { //there should only be 1 row but checking
                nurseName = rs.getString("Last_Name"); //store the last name
            }

            rs = HealthPortal.statement.executeQuery(sql1); //execute the query
            rs.last();
            if (rs.getRow() == 1) {
                patientFName = rs.getString("First_Name");
                patientLName = rs.getString("Last_Name");
                pDateOfBirth = rs.getString("DOB");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        welcome = new Text("Welcome in, Nurse " + nurseName);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        patient = new Text("Patient: " + patientFName + " " + patientLName);
        patient.setFont(Font.font("Times New Roman", 14));
        patient.setFill(Color.BLACK);

        dob = new Text("DOB: " + pDateOfBirth);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        confirmation = new Text("Your updates have been saved for the above patient.");
        confirmation.setFont(Font.font("Times New Roman", 14));
        confirmation.setFill(Color.BLACK);

        //buttons for user, patient summary leads the user to the patient summary screen
        //of the patient they have currently selected, go back leads
        //the user to the previous screen of entering nurse vitals
        patientSummary = new Button("Patient Summary");
        ForwardButton handler = new ForwardButton(26);
        patientSummary.setOnAction(handler);

        back = new Button("Back");
        ForwardButton handler1 = new ForwardButton(21);
        back.setOnAction(handler1);

        //Vertical pane to put the patient's name and dob stacked
        VBox patientInfo = new VBox(3);
        patientInfo.getChildren().addAll(title, welcome, patient, dob);

        //Vertical pane to add the confirmation text and buttons
        VBox confMessage = new VBox(5);
        confMessage.getChildren().addAll(confirmation, patientSummary, back);
        VBox.setMargin(patientSummary, new Insets(0, 0, 0, 60));
        VBox.setMargin(back, new Insets(0, 0, 0, 90));

        //Border pane to put the confirmation message pane in the center of the page
        BorderPane bp = new BorderPane();
        bp.setCenter(confMessage);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //put the title/welcome message vertical pane at the top left
        StackPane.setAlignment(patientInfo, Pos.TOP_LEFT);
        StackPane.setMargin(patientInfo, new Insets(10, 0, 0, 10));

        //add the title and border pane containing the
        //other elements to the stack plane/this user choice screen object
        this.getChildren().add(patientInfo);
        this.getChildren().add(bp);
    } //end constructor
} //end nurse vitals confirmation class