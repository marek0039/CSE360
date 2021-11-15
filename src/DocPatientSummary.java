import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocPatientSummary extends StackPane
{
    private Color mainColor;
    private Text title, welcome, dob, contactInfo, email;
    private Text phone, medHisTitle, medHis, pharmacy, insurance, insuranceNum, address, doctorNotes, pName;
    private Text date1, date2, height, weight, bloodPressure, bodyTemp, allergies, prescription;
    private Text height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2, memo, memo2;
    private Text sendMessage, newMeds;
    private TextField notesField, presField;
    private Button back, go , submit;
    private Label errLabel;

    public DocPatientSummary()
    {
        //step 1, get doctor last name as well as all the contact information of the patient.
        ResultSet rs = null;
        String doc_name = null;
        String p_name = null, p_email = null, p_phone = null, p_address = null, p_pharmacy = null, p_insurance = null,
                p_insurance_num = null, p_dob = null, p_med = null;
        try {
            String getConnection = "SELECT Last_Name from Professional WHERE ID="
                    + HealthPortal.currUser + ";";
            //using the current user, get the doctor's last name
            rs = HealthPortal.statement.executeQuery(getConnection);  //execute the query
            rs.last();  //get the last row of the query
            if (rs.getRow() == 1) { //there should only be 1 row but checking
                doc_name = rs.getString("Last_Name"); //store the last name
            } else {    //otherwise, throw an exception.
                throw new FailedException("Cannot find User: " + HealthPortal.currUser);
            }
            String getpatient = "SELECT * FROM Patient WHERE PatientID=" + HealthPortal.currPatient;
            //get patient info
            rs = HealthPortal.statement.executeQuery(getpatient); //execute the query
            rs.last();
            if (rs.getRow() == 1) {
                //store every column into their respective variable
                p_name = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                p_email = rs.getString("Email");
                p_phone = rs.getString("Phone_Number");
                p_address = rs.getString("Address");
                p_pharmacy = rs.getString("Pharmacy");
                p_insurance = rs.getString("Insurance");
                p_insurance_num = rs.getString("Insurance_Number");
                p_dob = rs.getString("DOB");
                p_med = rs.getString("Medical_History");
            }
            else {
                throw new FailedException("Cannot find Patient: " + HealthPortal.currPatient);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        errLabel = new Label(); //label which will display an error done by the user.
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //step 2, display all the contact information in text labels
        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //as well as the doctor who is logged on currently
        welcome = new Text("Welcome in, Doctor " + doc_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        pName = new Text("Patient: " + p_name);
        pName.setFont(Font.font("Times New Roman", 14));
        pName.setFill(Color.BLACK);

        dob = new Text("DOB: " + p_dob);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        //labels the contact information
        contactInfo = new Text("Contact Information:");
        contactInfo.setFont(Font.font("Times New Roman", 14));
        contactInfo.setFill(Color.BLACK);

        phone = new Text("Phone: " + p_phone);
        phone.setFont(Font.font("Times New Roman", 14));
        phone.setFill(Color.BLACK);

        email = new Text("Email: " + p_email);
        email.setFont(Font.font("Times New Roman", 14));
        email.setFill(Color.BLACK);

        insurance = new Text("Insurance: " + p_insurance);
        insurance.setFont(Font.font("Times New Roman", 14));
        insurance.setFill(Color.BLACK);

        address = new Text("Address: " + p_address);
        address.setFont(Font.font("Times New Roman", 14));
        address.setFill(Color.BLACK);

        insuranceNum = new Text("Insurance Number: " + p_insurance_num);
        insuranceNum.setFont(Font.font("Times New Roman", 14));
        insuranceNum.setFill(Color.BLACK);

        pharmacy = new Text("Pharmacy: " + p_pharmacy);
        pharmacy.setFont(Font.font("Times New Roman", 14));
        pharmacy.setFill(Color.BLACK);

        //labels previous medical history
        medHisTitle = new Text("Previous Medical History:");
        medHisTitle.setFont(Font.font("Times New Roman", 14));
        medHisTitle.setFill(Color.BLACK);

        medHis = new Text(p_med);
        medHis.setFont(Font.font("Times New Roman", 14));
        medHis.setFill(Color.BLACK);

        //step 3, parse the Visit table and get the two most recent visits of the patient
        String[] results = new String[16]; //array which will hold visit values
        try
        {
            String get_visits = "SELECT * FROM Visit WHERE ID=" + HealthPortal.currPatient +
                    "AND Date IN (SELECT t1.Date FROM Visit t1 LEFT JOIN Visit t2 ON t1.Date <= t2.Date " +
                    "GROUP BY t1.Date HAVING COUNT(DISTINCT t2.Date)<=2)"; //query to execute
            rs = HealthPortal.statement.executeQuery(get_visits); //executing query
            int i = 0;  //index to iterate through array.
            if (rs.first()) //check if we got some rows.
            {
                while(rs.next())//iterate through result
                {
                    //add each column value into the array
                    results[i] = rs.getString("Date");
                    results[i+1] = rs.getString("Height");
                    results[i+2] = rs.getString("Weight");
                    results[i+3] = rs.getString("Pressure");
                    results[i+4] = rs.getString("Temp");
                    results[i+5] = rs.getString("Allergies");
                    results[i+6] = rs.getString("Prescription");
                    results[i+7] = rs.getString("Memo");
                    i = i+8;
                }
            }
            else {  //if we aren't at first then throw exception.
                throw new FailedException("SQL QUERY FAILED!!!");
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

        //step 4, display visit information.
        //Shows information on the last two visits
        date1 = new Text("Visit Date: " + results[0]);
        date1.setFont(Font.font("Times New Roman", 14));
        date1.setFill(Color.BLACK);

        date2 = new Text("Visit Date: " + results[8]);
        date2.setFont(Font.font("Times New Roman", 14));
        date2.setFill(Color.BLACK);

        //these are nurse's categories of what they take at each appointment
        height = new Text("Height: " + results[1] + " inches");
        height.setFont(Font.font("Times New Roman", 14));
        height.setFill(Color.BLACK);

        height2 = new Text("Height: " + results[9] + " inches");
        height2.setFont(Font.font("Times New Roman", 14));
        height2.setFill(Color.BLACK);

        weight = new Text("Weight: " + results[2] + " lbs");
        weight.setFont(Font.font("Times New Roman", 14));
        weight.setFill(Color.BLACK);

        weight2 = new Text("Weight: " + results[10] + " lbs");
        weight2.setFont(Font.font("Times New Roman", 14));
        weight2.setFill(Color.BLACK);

        bloodPressure = new Text("Blood Pressure: " + results[3] + " mmHg");
        bloodPressure.setFont(Font.font("Times New Roman", 14));
        bloodPressure.setFill(Color.BLACK);

        bloodPressure2 = new Text("Blood Pressure: " + results[11] + " mmHg");
        bloodPressure2.setFont(Font.font("Times New Roman", 14));
        bloodPressure2.setFill(Color.BLACK);

        bodyTemp = new Text("Body Temperature: " + results[4] + " degrees");
        bodyTemp.setFont(Font.font("Times New Roman", 14));
        bodyTemp.setFill(Color.BLACK);

        bodyTemp2 = new Text("Body Temperature: " + results[12] + " degrees");
        bodyTemp2.setFont(Font.font("Times New Roman", 14));
        bodyTemp2.setFill(Color.BLACK);

        allergies = new Text("Allergies: " + results[5]);
        allergies.setFont(Font.font("Times New Roman", 14));
        allergies.setFill(Color.BLACK);

        allergies2 = new Text("Allergies: " + results[13]);
        allergies2.setFont(Font.font("Times New Roman", 14));
        allergies2.setFill(Color.BLACK);

        prescription = new Text("Prescription: " + results[6]);
        prescription.setFont(Font.font("Times New Roman", 14));
        prescription.setFill(Color.BLACK);

        prescription2 = new Text("Prescription: " + results[14]);
        prescription2.setFont(Font.font("Times New Roman", 14));
        prescription2.setFill(Color.BLACK);

        memo = new Text("Memo: " + results[7]);
        memo.setFont(Font.font("Times New Roman", 14));
        memo.setFill(Color.BLACK);

        memo2 = new Text("Memo: " + results[15]);
        memo2.setFont(Font.font("Times New Roman", 14));
        memo2.setFill(Color.BLACK);

        //doctor's/nurse's notes label
        doctorNotes = new Text("Doctor's Memo:");
        doctorNotes.setFont(Font.font("Times New Roman", 14));
        doctorNotes.setFill(Color.BLACK);

        newMeds = new Text("Prescribed Medication:");
        newMeds.setFont(Font.font("Times New Roman", 14));
        newMeds.setFill(Color.BLACK);

        sendMessage = new Text("Send/View Messages:");
        sendMessage.setFont(Font.font("Times New Roman", 14));
        sendMessage.setFill(Color.BLACK);

        //textfields
        notesField = new TextField();
        presField = new TextField();

        //back button takes the user to the choose a patient page
        //go button takes user to send a message to the patient they are currently on
        //submit allows user to send in notes/medication added to the page via the textfields
        back = new Button("Back");
        go = new Button("Go");
        submit = new Button("Submit");
        //when user presses Submit, they read whatever the notes the doctor typed and then confirms the submission.
        // Case 17
        MemoSubmissionButton memo_submit = new MemoSubmissionButton(17);
        submit.setOnAction(memo_submit);
        //when the user presses Go, they are sent to the message portal. Case 18
        ForwardButton forward1 = new ForwardButton(18);
        go.setOnAction(forward1);
        //when the user presses Back, they are sent back to the patient select screen. Case 16
        ForwardButton forward2 = new ForwardButton(16);
        back.setOnAction(forward2);


        //vertical panes for each group of information on the page, to be placed
        //in column vertical panes and then in a horizontal pane for display purposes
        VBox nameBox = new VBox(2);
        nameBox.getChildren().addAll(pName, dob);

        VBox contactBox = new VBox(2);
        contactBox.getChildren().addAll(contactInfo, phone, email, address);

        VBox insBox = new VBox(2);
        insBox.getChildren().addAll(insurance, insuranceNum, pharmacy);

        VBox prevMedBox = new VBox(2);
        prevMedBox.getChildren().addAll(medHisTitle, medHis);

        //put a lot of space between the dates so they
        //align with their corresponding height/weight etc.
        VBox visitBox = new VBox(100);
        visitBox.getChildren().addAll(date1, date2);

        VBox visit1Box = new VBox(2);
        visit1Box.getChildren().addAll(height, weight, bloodPressure, bodyTemp, allergies, prescription, memo);

        VBox visit2Box = new VBox(2);
        visit2Box.getChildren().addAll(height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2, memo2);

        //back button is in this box as it is displayed low on the screen and has some
        //insets for aesthetic
        VBox docBox = new VBox(5);
        docBox.getChildren().addAll(doctorNotes, notesField, newMeds, presField, submit);
        VBox.setMargin(submit, new Insets(10,0,0,200));

        //vertical panes for each column
        VBox column1 = new VBox(8);
        column1.getChildren().addAll(nameBox, contactBox, insBox, prevMedBox);

        VBox column2 = new VBox(8);
        column2.getChildren().addAll(visitBox);

        VBox column3 = new VBox(16);
        column3.getChildren().addAll(visit1Box, visit2Box);

        //vbox for title and doctor greeting as well as the error label.
        VBox titleBox = new VBox(2);
        titleBox.getChildren().addAll(title, welcome, errLabel);

        //vbox for send message label and buttons
        VBox messageBox = new VBox(2);
        messageBox.getChildren().addAll(sendMessage, go, back);
        VBox.setMargin(go, new Insets(0,0,0,40));
        VBox.setMargin(back, new Insets(0,0,0,37));

        //horizontal pane to store columns
        HBox colBox = new HBox(8);
        colBox.getChildren().addAll(column1, column2, column3);

        //horizontal pane to put the doctors notes/ button options at the bottom of the screen
        HBox bottomBox = new HBox(40);
        bottomBox.getChildren().addAll(messageBox, docBox);

        //border pane to put horizontal pane in the center, and the doctor's notes/button
        //on the bottom of the screen
        //border pane to center the grid pane contents
        BorderPane bp = new BorderPane();
        bp.setCenter(colBox);
        bp.setBottom(bottomBox);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //add the title to the top left of this stack pane
        StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
        StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
        this.getChildren().add(titleBox);

        //add the border pane to this stack pane
        this.getChildren().add(bp);
    } //end constructor

    private class MemoSubmissionButton extends ForwardButton {   //Button which handles reading what the doctor typed

        private MemoSubmissionButton(int caseInt) {
            super(caseInt);
        }   //constructor to call the super constructor.

        @Override
        public void handle(ActionEvent event) { //override the handle method.
            if(notesField.getText().isEmpty() || presField.getText().isEmpty()) { //first check if both are filled in
                errLabel.setText("Please Fill in Both Boxes. If There is no Prescription enter \"N/A\"");
                errLabel.setTextFill(Color.RED);    //display error to user
            }
            else {
                try {
                    String notes = notesField.getText(); //get the text in both of them
                    String pres = presField.getText();
                    System.out.println(notes);   //print out for debugging purposes
                    System.out.println(pres);   //^^^
                    String query = "UPDATE Visit SET Prescription='" + pres + "', Memo='" + notes + "' WHERE ID="
                            + HealthPortal.currPatient + " AND Prescription=NULL AND Memo=NULL";//query to get the visit
                    int result = HealthPortal.statement.executeUpdate(query);  //execute the update
                    if(result == 0) {  //if the number of rows updated is 0 then the update failed.
                        errLabel.setText("Your Nurse May Have Not Logged Vitals. Please Check with your nurse.");
                        errLabel.setTextFill(Color.RED);    //display this message to the user.
                    }
                    else {  //otherwise...
                        super.handle(event);    //call forward button's handle.
                    }
                } catch(Exception e) {  //catch exception
                    System.err.println(e);
                }
            }
        }
    }
} //end class