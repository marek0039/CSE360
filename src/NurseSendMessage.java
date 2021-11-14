import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.sql.ResultSet;

public class NurseSendMessage extends StackPane
{
    // attributes of this class
    private Color mainColor;
    private Text title, welcome, patient, dob, directions;
    private TextArea message;
    private Button send, back;

    public NurseSendMessage()
    {
        // establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        // title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        // attributes from database to be defined
        String nurse_name = null;
        int patient_id = 0;
        String patient_name = null;
        String patient_dob = null;

        // get the name of current nurse using the system
        try {
            // the following string is an SQL query to get the name of the current user/nurse
            String nurseNameQuery = "SELECT Last_Name, from Nurse WHERE ID=" +
                    HealthPortal.currUser + ";";
            // execute the query
            ResultSet rs = HealthPortal.statement.executeQuery(nurseNameQuery);
            rs.last(); // jump to the last row of the query
            if (rs.getRow() == 1) { // check to make sure 1 patient was found
                nurse_name = "Nurse " + rs.getString("Last_Name");
            } else { // otherwise, throw an exception.
                throw new PatientSendMessage.FailedException("Cannot find user: " + HealthPortal.currUser);
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        // Black text labeling the name of the patient and dob of the patient
        // as well as which nurse is currently logged on
        welcome = new Text("Welcome in, " + nurse_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        patient = new Text("Patient: " + patient_name);
        patient.setFont(Font.font("Times New Roman", 14));
        patient.setFill(Color.BLACK);

        dob = new Text("DOB: " + patient_dob);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        directions = new Text("Please type your message below:");
        directions.setFont(Font.font("Times New Roman", 14));
        directions.setFill(Color.BLACK);

        //text area to store patient message from when they hit the send button
        message = new TextArea();

        //buttons for the user to send their message IF they have put message content,
        //or go back to the previous page
        send = new Button("Send");
        back = new Button("Back");

        //vertical boxes to store the title and it's contents and the middle of the page contents
        VBox titleBox = new VBox(2);
        titleBox.getChildren().addAll(title, welcome, patient, dob);

        VBox messageBox = new VBox(8);
        messageBox.getChildren().addAll(directions, message, send, back);
        //insets to center the buttons below the text/other text fields aesthetic
        VBox.setMargin(send, new Insets(0, 0, 0, 200));
        VBox.setMargin(back, new Insets(0, 0, 0, 200));

        //border pane to the message contents in the center of the screen
        BorderPane bp = new BorderPane();
        bp.setCenter(messageBox);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //add the top text with its contents to the top left
        //of this stack pane
        StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
        StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
        this.getChildren().add(titleBox);

        //add the border pane to this stack pane
        this.getChildren().add(bp);
    } //end constructor
} //end nurse send message class