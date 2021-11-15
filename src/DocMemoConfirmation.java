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

public class DocMemoConfirmation extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, patient, dob, confirmation;
    private Button patientChoice;

    public DocMemoConfirmation()
    {
        //get the doctor's last name as well as the current patient's name + DOB
        String doc_name = null;
        ResultSet rs = null;
        String p_name = null, p_dob = null;
        try {
            String getConnection = "SELECT Last_Name from Professional WHERE ID="
                    + HealthPortal.currUser + ";";
            //using the current user, get the doctor's last name
            rs = HealthPortal.statement.executeQuery(getConnection);  //execute the query
            rs.last();  //get the last row of the query
            if (rs.getRow() == 1) { //there should only be 1 row but checking
                doc_name = rs.getString("Last_Name"); //store the last name
            } else {    //otherwise, throw and exception.
                throw new FailedException("Cannot find User: " + HealthPortal.currUser);
            }
            String getpatient = "SELECT First_Name, Last_Name, DOB FROM Patient WHERE PatientID=" +
                    HealthPortal.currPatient;
            //get patient info
            rs = HealthPortal.statement.executeQuery(getpatient); //execute the query
            rs.last(); //get the last row
            if (rs.getRow() == 1) { //if the index of the row is 1, then store values
                p_name = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                p_dob = rs.getString("DOB");
            }
            else { //otherwise, throw exception
                throw new FailedException("Cannot find Patient: " + HealthPortal.currPatient);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        welcome = new Text("Welcome in, Doctor " + doc_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        patient = new Text("Patient: " + p_name);
        patient.setFont(Font.font("Times New Roman", 14));
        patient.setFill(Color.BLACK);

        dob = new Text("DOB: " + p_dob);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        confirmation = new Text("Your memos have been saved for the above patient.");
        confirmation.setFont(Font.font("Times New Roman", 14));
        confirmation.setFill(Color.BLACK);

        //buttons for user, summary leads to the new patient's summary with their
        //inputted information, and back takes them back to the new patient form
        //page incase they need to go back to a field
        patientChoice = new Button("Patient Choice Home");
        //when the user presses Go, they are sent to the patient summary page. Case 15
        ForwardButton forward = new ForwardButton(15);
        patientChoice.setOnAction(forward);

        //Vertical pane to put the patient's name and dob stacked
        VBox patientInfo = new VBox(3);
        patientInfo.getChildren().addAll(title, welcome, patient, dob);

        //Vertical pane to add the confirmation text and buttons
        VBox confMessage = new VBox(5);
        confMessage.getChildren().addAll(confirmation, patientChoice);
        VBox.setMargin(patientChoice, new Insets(0, 0, 0, 60));

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
} //end doc memo confirmation page