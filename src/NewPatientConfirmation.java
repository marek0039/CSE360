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

public class NewPatientConfirmation extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, dob, confirmation;
    private Button patientMenu, back;

    public NewPatientConfirmation()
    {
        ResultSet rs = null;
        String p_name = null, p_dob = null;
        try {
            String getpatient = "SELECT First_Name, Last_Name, DOB FROM Patient WHERE PatientID=" +
                    HealthPortal.currUser;
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
        //Note: these will need to be read in from the previous New patient form
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Welcome, Patient: " + p_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        dob = new Text("DOB: " + p_dob);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        confirmation = new Text("You have created your new patient account.\nClick below to view your summary.");
        confirmation.setFont(Font.font("Times New Roman", 14));
        confirmation.setFill(Color.BLACK);

        //buttons for user, summary leads to the new patient's summary with their
        //inputted information, and back takes them back to the new patient form
        //page incase they need to go back to a field
        patientMenu = new Button("Patient Menu");
        //forward event handler for patient menu button, case 5, takes user to existing patient
        //portal with their information
        ForwardButton handler1 = new ForwardButton(5);
        patientMenu.setOnAction(handler1);

        back = new Button("Back");
        //back button forward event handler, case 2, go back to new patient select screen
        ForwardButton handler2 = new ForwardButton(2);
        back.setOnAction(handler2);

        //Vertical pane to put the patient's name and dob stacked
        VBox patientInfo = new VBox(3);
        patientInfo.getChildren().addAll(title, welcome, dob);

        //Vertical pane to add the confirmation text and buttons
        VBox confMessage = new VBox(5);
        confMessage.getChildren().addAll(confirmation, patientMenu, back);
        VBox.setMargin(patientMenu, new Insets(0, 0, 0, 60));
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
    }
    private static class FailedException extends Exception {
        private FailedException(String errorMessage) {
            super(errorMessage);
        } // end constructor
    } // end FailedException class

}

