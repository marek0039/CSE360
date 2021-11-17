import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NurseVitals extends StackPane {
    private Color mainColor;
    private Text title, welcome, dob, contactInfo, email;
    private Text phone, medHisTitle, medHis, pharmacy, insurance, doctorNotes, pName;
    private Text visit, height, weight, bloodPressure, bodyTemp, allergies, sendMessage;
    private TextField heightField, weightField, bloodPField, bodyTempField, allergyField;
    private DatePicker visitDate;
    private TextArea docNotes;
    private Button back, go, submit, patientSum;
    private Label errorLabel;
    private String dateOfVisit = null;
    private String pHeight = null;
    private String pWeight = null;
    private String pBloodPress = null;
    private String pBodyTemp = null;
    private String pAllergies = null;
    private String nurseNotes = null;
    public NurseVitals() {
        String nurseName = null;
        String patientFName = null;
        String patientLName = null;
        String patientDOB = null;
        String patientPhone = null;
        String patientEmail = null;
        String patientInsurance = null;
        String patientPharmacy = null;
        String medicalHistory = null;

        errorLabel = new Label();

        ResultSet rs = null;
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128, 32, 32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //as well as the doctor who is logged on currently
        //Note: these will need to be read in from patient list
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
                patientDOB = rs.getString("DOB");
                patientPhone = rs.getString("Phone_Number");
                patientEmail = rs.getString("Email");
                patientInsurance = rs.getString("Insurance");
                patientPharmacy = rs.getString("Pharmacy");
                medicalHistory = rs.getString("Medical_History");

            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

            welcome = new Text("Welcome in, Nurse " + nurseName);
            welcome.setFont(Font.font("Times New Roman", 14));
            welcome.setFill(Color.BLACK);

            pName = new Text("Patient: " + patientFName + " " + patientLName);
            pName.setFont(Font.font("Times New Roman", 14));
            pName.setFill(Color.BLACK);

            dob = new Text("DOB: " + patientDOB);
            dob.setFont(Font.font("Times New Roman", 14));
            dob.setFill(Color.BLACK);

            //labels the contact information
            contactInfo = new Text("Contact Information:");
            contactInfo.setFont(Font.font("Times New Roman", 14));
            contactInfo.setFill(Color.BLACK);

            //these will be parsed in from the patient's profile, Note: that
            //mailing address and insurance number are not included on summary
            //screen, but the rest of the information is
            phone = new Text("Phone: " + patientPhone);
            phone.setFont(Font.font("Times New Roman", 14));
            phone.setFill(Color.BLACK);

            email = new Text("Email: " + patientEmail);
            email.setFont(Font.font("Times New Roman", 14));
            email.setFill(Color.BLACK);

            insurance = new Text("Insurance: " + patientInsurance);
            insurance.setFont(Font.font("Times New Roman", 14));
            insurance.setFill(Color.BLACK);

            pharmacy = new Text("Pharmacy: " + patientPharmacy);
            pharmacy.setFont(Font.font("Times New Roman", 14));
            pharmacy.setFill(Color.BLACK);

            //labels previous medical history
            medHisTitle = new Text("Previous Medical History:");
            medHisTitle.setFont(Font.font("Times New Roman", 14));
            medHisTitle.setFill(Color.BLACK);

            medHis = new Text("-"+medicalHistory);
            medHis.setFont(Font.font("Times New Roman", 14));
            medHis.setFill(Color.BLACK);

            //these will need to be filled in to save as the patient's vitals/notes for
            //the day to display on the patient summary page
            visit = new Text("Date of Visit(Today):");
            visit.setFont(Font.font("Times New Roman", 14));
            visit.setFill(Color.BLACK);

            height = new Text("Height:");
            height.setFont(Font.font("Times New Roman", 14));
            height.setFill(Color.BLACK);

            weight = new Text("Weight:");
            weight.setFont(Font.font("Times New Roman", 14));
            weight.setFill(Color.BLACK);

            bloodPressure = new Text("Blood Pressure:");
            bloodPressure.setFont(Font.font("Times New Roman", 14));
            bloodPressure.setFill(Color.BLACK);

            bodyTemp = new Text("Body Temperature:");
            bodyTemp.setFont(Font.font("Times New Roman", 14));
            bodyTemp.setFill(Color.BLACK);

            allergies = new Text("Allergies:");
            allergies.setFont(Font.font("Times New Roman", 14));
            allergies.setFill(Color.BLACK);

            //doctor's/nurse's notes label
            doctorNotes = new Text("Doctor/Nurse's Notes:");
            doctorNotes.setFont(Font.font("Times New Roman", 14));
            doctorNotes.setFill(Color.BLACK);

            sendMessage = new Text("Send/View Messages:");
            sendMessage.setFont(Font.font("Times New Roman", 14));
            sendMessage.setFill(Color.BLACK);

            //textfields
            heightField = new TextField();

            weightField = new TextField();

            bloodPField = new TextField();

            bodyTempField = new TextField();

            allergyField = new TextField();

            //text area for the doctors/nurses notes
            docNotes = new TextArea();

            //date picker for the date of the visit selection
            visitDate = new DatePicker();

        //back button takes the user to the choose a patient page
            //go button takes user to send a message to the patient they are currently on
            //submit allows user to send in notes/medication added to the page via the textfields
            back = new Button("Back");
            ForwardButton handler = new ForwardButton(21);
            back.setOnAction(handler);

            go = new Button("Go");
            ForwardButton handler2 = new ForwardButton(27);
            go.setOnAction(handler2);

            submit = new Button("Submit");
            //ForwardButton handler1 = new ForwardButton(25);
            NurseVitalsSubmitButton handler1 = new NurseVitalsSubmitButton(25);
            submit.setOnAction(handler1);

            patientSum = new Button("Patient Summary");
            ForwardButton handler3 = new ForwardButton(26);
            patientSum.setOnAction(handler3);

            //vertical panes for each group of information on the page, to be placed
            //in column vertical panes and then in a horizontal pane for display purposes
            VBox nameBox = new VBox(2);
            nameBox.getChildren().addAll(pName, dob);

            VBox contactBox = new VBox(2);
            contactBox.getChildren().addAll(contactInfo, phone, email);

            VBox insBox = new VBox(2);
            insBox.getChildren().addAll(insurance, pharmacy);

            VBox prevMedBox = new VBox(2);
            prevMedBox.getChildren().addAll(medHisTitle, medHis);

            VBox dateBox = new VBox(2);
            dateBox.getChildren().addAll(visit, visitDate);

            VBox heightBox = new VBox(2);
            heightBox.getChildren().addAll(height, heightField);

            VBox weightBox = new VBox(2);
            weightBox.getChildren().addAll(weight, weightField);

            VBox bloodPBox = new VBox(2);
            bloodPBox.getChildren().addAll(bloodPressure, bloodPField);

            VBox bodyTempBox = new VBox(2);
            bodyTempBox.getChildren().addAll(bodyTemp, bodyTempField);

            VBox allergyBox = new VBox(2);
            allergyBox.getChildren().addAll(allergies, allergyField);

            //back button is in this box as it is displayed low on the screen and has some
            //insets for aesthetic
            VBox docBox = new VBox(5);
            docBox.getChildren().addAll(doctorNotes, docNotes, submit, patientSum);
            VBox.setMargin(submit, new Insets(10, 0, 0, 200));
            VBox.setMargin(patientSum, new Insets(10, 0, 0, 180));

            //vbox for send message label and buttons
            VBox messageBox = new VBox(2);
            messageBox.getChildren().addAll(sendMessage, go, back);
            VBox.setMargin(go, new Insets(0, 0, 0, 40));
            VBox.setMargin(back, new Insets(0, 0, 0, 37));

            //vertical panes for each column
            VBox column1 = new VBox(8);
            column1.getChildren().addAll(nameBox, contactBox, insBox, prevMedBox, messageBox);

            VBox column2 = new VBox(8);
            column2.getChildren().addAll(dateBox, heightBox, weightBox, bloodPBox, bodyTempBox, allergyBox);

            //vbox for title and doctor greeting
            VBox titleBox = new VBox(5);
            titleBox.getChildren().addAll(title, welcome, errorLabel);

            //horizontal pane to store columns
            HBox colBox = new HBox(8);
            colBox.getChildren().addAll(column1, column2, docBox);

            //border pane to put horizontal pane in the center, and the doctor's notes/button
            //on the bottom of the screen
            //border pane to center the grid pane contents
            BorderPane bp = new BorderPane();
            bp.setCenter(colBox);
            bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

            //add the title to the top left of this stack pane
            StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
            StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
            this.getChildren().add(titleBox);

            //add the border pane to this stack pane
            this.getChildren().add(bp);
        } //end constructor
    private class NurseVitalsSubmitButton extends ForwardButton {
        private int num_rows;

        private NurseVitalsSubmitButton(int caseInt) {
            super(caseInt);
        }

        @Override
        public void handle(ActionEvent event) {
            //visitDate.getAccessibleText().isEmpty() ||heightField.getText().isEmpty() || weightField.getText().isEmpty() || bloodPField.getText().isEmpty() || bodyTempField.getText().isEmpty() || allergyField.getText().isEmpty()
            ResultSet rs = null;
            if (visitDate.getValue() == null ||  weightField.getText().isEmpty() || bloodPField.getText().isEmpty() || bodyTempField.getText().isEmpty() || allergyField.getText().isEmpty())
            {
                errorLabel.setText("Please enter all necessary info");
                errorLabel.setTextFill(Color.RED);
            }
            else
            {
                try {
                    dateOfVisit = visitDate.getValue().toString();
                    pHeight = heightField.getText();
                    pWeight = weightField.getText();
                    pBloodPress = bloodPField.getText();
                    pBodyTemp = bodyTempField.getText();
                    pAllergies = allergyField.getText();
                    nurseNotes = docNotes.getText();
                    String sql = "INSERT INTO Visit VALUES("+ HealthPortal.currPatient + ",'" + dateOfVisit + "'," + pHeight + "," + pWeight + ",'" + pBloodPress + "'," + pBodyTemp + ",'" + pAllergies + "','" +nurseNotes +"','N/A');";
                    System.out.println(sql);
                    HealthPortal.statement.executeUpdate(sql);
                    super.handle(event);
                }
                catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }
            }
        }
    }
} //end nurse vitals class
