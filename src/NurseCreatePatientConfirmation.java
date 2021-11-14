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

public class NurseCreatePatientConfirmation extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, patient, dob, confirmation;
    private Button patientVitals, back;

    public NurseCreatePatientConfirmation()
    {
        //step 1, get nurse's last name
        ResultSet rs = null;
        String nurse_name = null;
        String p_name = null, p_dob = null;
        try {
            String getConnection = "SELECT Last_Name from Professional WHERE ID="
                    + HealthPortal.currUser + ";";
            //using the current user, get the nurse's last name
            rs = HealthPortal.statement.executeQuery(getConnection);  //execute the query
            rs.last();  //get the last row of the query
            if (rs.getRow() == 1) { //there should only be 1 row but checking
                nurse_name = rs.getString("Last_Name"); //store the last name
            } else {    //otherwise, throw and exception.
                throw new FailedException("Cannot find User: " + HealthPortal.currUser);
            }
            String getpatient = "SELECT First_Name, Last_Name, DOB FROM Patient WHERE PatientID=" + HealthPortal.currPatient;
            //get patient info
            rs = HealthPortal.statement.executeQuery(getpatient); //execute the query
            rs.last();
            if (rs.getRow() == 1) {
                p_name = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                p_dob = rs.getString("DOB");
            }
            else {
                throw new FailedException("Cannot find Patient: " + HealthPortal.currPatient);
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

        //black text labeling the name of the nurse who is logged on
        //as well as the new patient's name and dob that they have just created
        //to be read in
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Welcome in, Nurse " + nurse_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        patient = new Text("Welcome in, Patient " + p_name);
        patient.setFont(Font.font("Times New Roman", 14));
        patient.setFill(Color.BLACK);

        dob = new Text("DOB: " + p_dob);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        confirmation = new Text("You have created a new patient as listed above.");
        confirmation.setFont(Font.font("Times New Roman", 14));
        confirmation.setFill(Color.BLACK);

        //buttons for user, summary leads to the new patient's summary with their
        //inputted information, and back takes them to the nurse patient selection page
        patientVitals = new Button("Add Vitals");
        back = new Button("Back Home");
        //when the user presses Add Vitals, they are sent to the newly created patient's add vitals page. Case 24
        ForwardButton forward1 = new ForwardButton(24);
        patientVitals.setOnAction(forward1);
        //when the user presses Back, they are sent back to the patient select screen. Case 21
        ForwardButton forward2 = new ForwardButton(21);
        back.setOnAction(forward2);

        //Vertical pane to put the patient's name and dob stacked
        VBox patientInfo = new VBox(3);
        patientInfo.getChildren().addAll(title, welcome, patient, dob);

        //Vertical pane to add the confirmation text and buttons
        VBox confMessage = new VBox(5);
        confMessage.getChildren().addAll(confirmation, patientVitals, back);
        VBox.setMargin(patientVitals, new Insets(0, 0, 0, 60));
        VBox.setMargin(back, new Insets(0, 0, 0, 75));

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
} //end nurse create patient confirmation class