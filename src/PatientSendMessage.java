import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.sql.ResultSet;

public class PatientSendMessage extends StackPane
{
    //attributes of this class
    private Color mainColor;
    private Text title, welcome, dob, directions, select;
    private ComboBox medList;
    private TextArea message;
    private Button send, back;

    public PatientSendMessage()
    {
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        String patient_name = null;
        String patient_dob = null;
        try {
            // the following string is an SQL query to get the patient name of the current user
            String patientNameQuery = "SELECT First_Name, Last_Name, DOB from Patient WHERE ID=" +
                    HealthPortal.currUser + ";";
            // execute the query
            ResultSet rs = HealthPortal.statement.executeQuery(patientNameQuery);
            rs.last(); // jump to the last row of the query
            if (rs.getRow() == 1) { // check to make sure 1 patient was found
                patient_name = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                patient_dob = rs.getString("DOB");
            } else { // otherwise, throw an exception.
                throw new FailedException("Cannot find user: " + HealthPortal.currUser);
            }
        } catch (Exception e) {
            system.out.print(e);
        }
        welcome = new Text("Patient: " + patient_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        dob = new Text("DOB: " + patient_dob);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        directions = new Text("Please type your message below:");
        directions.setFont(Font.font("Times New Roman", 14));
        directions.setFill(Color.BLACK);

        select = new Text("(Required) Select a medical professional below to send your message to: ");
        select.setFont(Font.font("Times New Roman", 14));
        select.setFill(Color.BLACK);

        //text area to store patient message from when they hit the send button
        message = new TextArea();

        //buttons for the user to send their message IF they have put message content,
        //and they have selected a medical professional, or go back to the previous page
        send = new Button("Send");
        //forward event handler for the patient to go to message confirmation page
        //after they have sent the message, case 12
        ForwardButton handler1 = new ForwardButton(12);
        send.setOnAction(handler1);

        back = new Button("Back");
        //forward event handler for patient to back to the message portal screen, case 10
        ForwardButton handler2 = new ForwardButton(10);
        back.setOnAction(handler2);

        // get the list of medical professionals associated with the patient
        int doctor_id = 0;
        int nurse_id = 0;
        String doctor_name = null;
        String nurse_name = null;

        // get the Doctor's patient ID
        try {
            // the following string is an SQL query to get the patient's doctor's ID
            String patientNameQuery = "SELECT Doctor from Patient WHERE ID=" +
                    HealthPortal.currUser + ";";
            // execute the query
            ResultSet rs = HealthPortal.statement.executeQuery(patientNameQuery);
            rs.last(); // jump to the last row of the query
            if (rs.getRow() = 1) { // check to make sure 1 patient was found
                doctor_id = rs.getInt("Doctor");
            } else { // otherwise, throw an exception.
                throw new FailedException("Cannot find user: " + HealthPortal.currUser);
            }
        } catch (Exception e) {
            system.out.print(e);
        }

        // Get the doctor's last name and the nurse ID that is connected to them
        try {
            // the following string is an SQL query to get the patient's doctor and nurse
            String profNameQuery = "SELECT First_Name, Last_Name, Connection from Professionals " +
                    "WHERE ID=" + doctor_id + ";";
            // execute the query
            ResultSet rs = HealthPortal.statement.executeQuery(doctorNameQuery);
            rs.last(); // jump to the last row of the query
            if (rs.getRow() = 1) { // check to make sure 1 doctor was found
                doctor_name = "Doctor " + rs.getString("Last_Name");
                nurse_id = rs.getInt("Connection");
            } else { // otherwise, throw an exception.
                throw new FailedException("Cannot find doctor: " + doctor_id);
            }
        } catch (Exception e) {
            system.out.print(e);
        }

        // Get the nurse's last name
        try {
            // the following string is an SQL query to get the nurse's first and last name
            String nurseNameQuery = "SELECT First_Name, Last_Name from Professionals " +
                    "WHERE ID=" + nurse_id + ";";
            // execute the query
            ResultSet rs = HealthPortal.statement.executeQuery(nurseNameQuery);
            rs.last(); // jump to the last row of the query
            if (rs.getRow() = 1) { // check to make sure 1 nurse was found
                nurse_name = "Nurse " + rs.getString("Last_Name");
            } else { // otherwise, throw an exception.
                throw new FailedException("Cannot find nurse: " + nurse_id);
            }
        } catch (Exception e) {
            system.out.print(e);
        }

        // create combo box to display the patient's nurse and doctor.
        medList = new ComboBox();
        medList.getItems().addAll(doctor_name, nurse_name);

        // vertical boxes to store the title and it's contents and the middle of the page contents
        VBox titleBox = new VBox(2);
        titleBox.getChildren().addAll(title, welcome, dob);

        VBox messageBox = new VBox(8);
        messageBox.getChildren().addAll(directions, message, select, medList, send, back);
        //insets to center the buttons below the text/other text fields aesthetic
        VBox.setMargin(medList, new Insets(0,0,0,150));
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

    // custom exception to print error messages
    private static class FailedException extends Exception {
        private FailedException(String errorMessage) {
            super(errorMessage);
        } // end constructor
    } // end FailedException class

} //end class