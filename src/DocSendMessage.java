import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.sql.ResultSet;

public class DocSendMessage extends StackPane
{
    //attributes of this class
    private Color mainColor;
    private Text title, welcome, patient, dob, directions;
    private TextArea message;
    private Button send, back;
    private Label errLabel;

    public DocSendMessage()
    {
        ResultSet rs = null;

        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        // attributes from database to be defined
        String doctor_name = "";
        int patient_id = 0;
        String patient_name = "";
        String patient_dob = "";

        // get the name of current doctor using the system
        try {
            // the following string is an SQL query to get the name of the current doctor
            String doctorNameQuery = "SELECT Last_Name from Professional WHERE ID=" +
                    HealthPortal.currUser;
            // execute the query
            rs = HealthPortal.statement.executeQuery(doctorNameQuery);
            rs.last(); // jump to last row of the query
            if (rs.getRow() == 1) { // check to make sure 1 doctor was found
                doctor_name = "Doctor " + rs.getString("Last_Name");
            } else { // otherwise, throw an exception
                throw new DocSendMessage.FailedException("Cannot find doctor: " + HealthPortal.currUser);
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        // get the name and date of birth of the selected patient
        try {
            // the following string is an SQL query to get the name and dob of the selected patient
            String patientQuery = "SELECT First_Name, Last_Name, DOB from Patient WHERE PatientID=" +
                    HealthPortal.currPatient + ";";
            // execute the query
            rs = HealthPortal.statement.executeQuery(patientQuery);
            rs.last(); // jump to the last row of the query
            if (rs.getRow() == 1) { // check to make sure 1 patient was found
                patient_name = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                patient_dob = rs.getString("DOB");
            } else {
                throw new DocSendMessage.FailedException("Cannot find patient: " + HealthPortal.currPatient);
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        errLabel = new Label(""); // label which will display if an error is done by the user

        // Black text labeling the name of the patient and dob of the patient
        // as well as which doctor is currently logged on
        welcome = new Text("Welcome in " + doctor_name);
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
        //and they have selected a medical professional, or go back to the previous page
        send = new Button("Send");
        SendMessageButton handler1 = new SendMessageButton(20);
        send.setOnAction(handler1);

        back = new Button("Back");
        // forward event handler to send doctor back to the message portal screen
        ForwardButton handler2 = new ForwardButton(18);
        back.setOnAction(handler2);

        //vertical boxes to store the title and it's contents and the middle of the page contents
        VBox titleBox = new VBox(2);
        titleBox.getChildren().addAll(title, welcome, patient, dob, errLabel);

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

    // the following class is a custom exception to print error messages
    private static class FailedException extends Exception {
        private FailedException(String errorMessage) {
            super(errorMessage);
        } // end constructor
    } // end FailedException class

    // the following class is a button which handles sending messages
    private class SendMessageButton extends ForwardButton {
        // constructor
        private SendMessageButton(int caseInt) { super(caseInt); }; // call the ForwardButton constructor

        @Override // override the handle method from ForwardButton
        public void handle(ActionEvent event) {
            if (message.getText().trim().equals("") || message.getText() == null) {
                // display error message
                errLabel.setText("Please Enter a Message");
                errLabel.setTextFill(Color.RED);
            } else { // otherwise, update message table in SQL databases
                try {
                    // get the text from the message box
                    String text = message.getText().trim();
                    text = text.replace("'", "''"); // avoid SQL injection

                    // The following is an SQL query to update the message table
                    String update = "INSERT INTO Message VALUES(" +
                            HealthPortal.currUser + ", " + // Sender
                            HealthPortal.currPatient + ", " + // Recipient
                            "'" + java.time.LocalDate.now() + "', " + // Date
                            "'" + text + "');"; // Message
                    int result = HealthPortal.statement.executeUpdate(update);
                    if (result == 1) { // update was successful
                        super.handle(event);
                    } else {
                        throw new DocSendMessage.FailedException("INSERTING NEW MESSAGE FAILED!!!");
                    }
                } catch(Exception e) { // catch exception
                    System.err.print(e);
                }
            }
        } // end handle method
    } // end SendMessageButton class
} //end doc send message class