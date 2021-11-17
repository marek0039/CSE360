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

public class PatientMessageConfirmation extends StackPane
{
    // create attributes for this screen
    private Color mainColor;
    private Text title, welcome, dob, confirmation;
    private Button back;

    public PatientMessageConfirmation()
    {
        // establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        // title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        // get the name and date of birth of current patient using the system
        String patient_name = null;
        String patient_dob = null;
        try {
            // the following string is an SQL query to get the patient name of the current user
            String patientNameQuery = "SELECT First_Name, Last_Name, DOB from Patient WHERE PatientID=" +
                    HealthPortal.currUser + ";";
            // execute the query
            ResultSet rs = HealthPortal.statement.executeQuery(patientNameQuery);
            rs.last(); // jump to the last row of the query
            if (rs.getRow() == 1) { // check to make sure 1 patient was found
                patient_name = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                patient_dob = rs.getString("DOB");
            } else { // otherwise, throw an exception.
                throw new PatientMessageConfirmation.FailedException("Cannot find user: " + HealthPortal.currUser);
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        // Black text labeling the name of the patient and dob of the patient
        welcome = new Text("Patient: " + patient_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);
        dob = new Text("DOB: " + patient_dob);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        confirmation = new Text("\t\tYour message has been sent!\nSelect the button to go back to your portal home screen.");
        confirmation.setFont(Font.font("Times New Roman", 14));
        confirmation.setFill(Color.BLACK);

        // button for the user to go back to their home patient portal screen
        // with options to send messages, update info, or view summary
        back = new Button("Patient Menu");
        // forward event handler for the back button takes user back to existing
        // patient portal screen, case 5
        ForwardButton handler = new ForwardButton(5);
        back.setOnAction(handler);

        // vertical box to store title and welcome contents
        VBox titleBox = new VBox(3);
        titleBox.getChildren().addAll(title, welcome, dob);

        // vertical box to store confirmation message and back button
        VBox confMessage = new VBox(5);
        confMessage.getChildren().addAll(confirmation, back);
        VBox.setMargin(back, new Insets(10, 0, 0, 110));

        // border pane to put confirmation message in the center of the page
        BorderPane bp = new BorderPane();
        bp.setCenter(confMessage);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // put the title/welcome message vertical pane at the top left
        StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
        StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));

        // add the title and border pane containing the
        // other elements to the stack plane
        this.getChildren().add(titleBox);
        this.getChildren().add(bp);
    } // end constructor

    // the following class is a custom exception to print error messages
    private static class FailedException extends Exception {
        private FailedException(String errorMessage) {
            super(errorMessage);
        } // end constructor
    } // end FailedException class

} // end PatientMessageConfirmation class