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

public class DocMessageConfirmation extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, patient, dob, confirmation;
    private Button back;

    public DocMessageConfirmation()
    {
        ResultSet rs = null;
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        // get the name and date of birth of the selected patient
        String patient_name = "";
        String patient_dob = "";
        try {
            // the following string is an SQL query
            String query = "SELECT First_Name, Last_Name, DOB from Patient WHERE PatientID= " +
                    HealthPortal.currPatient + ";";
            // execute query
            rs = HealthPortal.statement.executeQuery(query);
            rs.last();
            if (rs.getRow() == 1) { // check to make sure 1 patient was found
                patient_name = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                patient_dob = rs.getString("DOB");
            } else { // throw exception
                throw new DocMessageConfirmation.FailedException("Cannot find patient: " + HealthPortal.currPatient);
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        // get the name of the doctor logged on currently
        String doctor_name = "";
        try {
            // the following string is an SQL query to get the patient name of the current user
            String doctorNameQuery = "SELECT Last_Name from Professional WHERE ID = " +
                    HealthPortal.currUser + ";";
            // execute the query
            rs = HealthPortal.statement.executeQuery(doctorNameQuery);
            rs.last();
            if (rs.getRow() == 1) {
                doctor_name = "Doctor " + rs.getString("Last_Name");
            } else {
                throw new DocMessageConfirmation.FailedException("Cannot find user: " + HealthPortal.currUser);
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        // black text labeling the name of the patient and dob of the patient
        // and doctor that is logged on currently
        welcome = new Text("Welcome in, " + doctor_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        patient = new Text("Patient: " + patient_name);
        patient.setFont(Font.font("Times New Roman", 14));
        patient.setFill(Color.BLACK);

        dob = new Text("DOB: " + patient_dob);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        confirmation = new Text("\t\tYour message has been sent!\nSelect the button to go back to your portal home screen.");
        confirmation.setFont(Font.font("Times New Roman", 14));
        confirmation.setFill(Color.BLACK);

        // button for the user to go back to their home screen where they are logged
        // on as a doctor and can select a patient from the patient list
        back = new Button("Back");
        ForwardButton handler = new ForwardButton(16);
        back.setOnAction(handler);

        //vertical box to store title and welcome contents
        VBox titleBox = new VBox(3);
        titleBox.getChildren().addAll(title, welcome, patient, dob);

        //vertical box to store confirmation message and back button
        VBox confMessage = new VBox(5);
        confMessage.getChildren().addAll(confirmation, back);
        VBox.setMargin(back, new Insets(10, 0, 0, 130));

        //border pane to put confirmation message in the center of the page
        BorderPane bp = new BorderPane();
        bp.setCenter(confMessage);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //put the title/welcome message vertical pane at the top left
        StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
        StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));

        //add the title and border pane containing the
        //other elements to the stack plane
        this.getChildren().add(titleBox);
        this.getChildren().add(bp);
    } //end constructor

    // the following class is a custom exception to print error messages
    private static class FailedException extends Exception {
        private FailedException(String errorMessage) {
            super(errorMessage);
        } // end constructor
    } // end FailedException class
} //end doc message confirmation class