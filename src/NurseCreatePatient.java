import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;

public class NurseCreatePatient extends StackPane
{
    private Color mainColor;
    private Text title, welcome, form, required, fName, lName, dob, email;
    private Text phone, medHis, pharmacy, mailAddress, insurance, insNum, finish;
    private TextField fNameField, lNameField, emailField, pharmField, numField, insField, insNumField;
    private TextField mailField1, mailField2, mailField3, mailField4;
    private TextArea medHisField;
    private TextField dobPicker;
    private Button submit, back;
    private Label errLabel;
    private int docID;

    public NurseCreatePatient()
    {
        //step 1, get nurse's last name
        ResultSet rs = null;
        String nurse_name = null;
        try {
            String getConnection = "SELECT Last_Name, Connection from Professional WHERE ID="
                    + HealthPortal.currUser + ";";
            //using the current user, get the nurse's last name
            rs = HealthPortal.statement.executeQuery(getConnection);  //execute the query
            rs.last();  //get the last row of the query
            if (rs.getRow() == 1) { //there should only be 1 row but checking
                nurse_name = rs.getString("Last_Name"); //store the last name
                docID = rs.getInt("Connection"); //also getting connected DoctorID for adding the patient.
            } else {    //otherwise, throw and exception.
                throw new FailedException("Cannot find User: " + HealthPortal.currUser);
            }
        } catch (Exception e) {
            System.err.print(e);
        }

        errLabel = new Label();
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling that this is a new patient form,
        //and red text labeling what is required for the patient
        //to fill in
        //nurse welcome which will be read in when the nurse logs in
        welcome = new Text("Welcome in, Nurse " + nurse_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        form = new Text("New Patient Form");
        form.setFont(Font.font("Times New Roman", 14));
        form.setFill(Color.BLACK);

        form = new Text("New Patient Form");
        form.setFont(Font.font("Times New Roman", 14));
        form.setFill(Color.BLACK);

        required = new Text("*Required for submission");
        required.setFont(Font.font("Times New Roman", 12));
        required.setFill(Color.RED);

        //all the different texts labeling their various text field/area
        //for the user to fill out the specified information
        fName = new Text("Patient First Name:");
        fName.setFont(Font.font("Times New Roman", 12));
        fName.setFill(Color.BLACK);

        lName = new Text("Patient Last Name:");
        lName.setFont(Font.font("Times New Roman", 12));
        lName.setFill(Color.BLACK);

        dob = new Text("Patient Date of Birth: (Use Format: YYYY-MM-DD)");
        dob.setFont(Font.font("Times New Roman", 12));
        dob.setFill(Color.BLACK);

        email = new Text("Preferred Email Address:");
        email.setFont(Font.font("Times New Roman", 12));
        email.setFill(Color.BLACK);

        phone = new Text("Preferred Phone Number:");
        phone.setFont(Font.font("Times New Roman", 12));
        phone.setFill(Color.BLACK);

        medHis = new Text("(Relevant)Previous Medical History:");
        medHis.setFont(Font.font("Times New Roman", 12));
        medHis.setFill(Color.BLACK);

        pharmacy = new Text("Preferred Pharmacy:");
        pharmacy.setFont(Font.font("Times New Roman", 12));
        pharmacy.setFill(Color.BLACK);

        mailAddress = new Text("Preferred Mailing Address:");
        mailAddress.setFont(Font.font("Times New Roman", 12));
        mailAddress.setFill(Color.BLACK);

        insurance = new Text("Insurance Company:");
        insurance.setFont(Font.font("Times New Roman", 12));
        insurance.setFill(Color.BLACK);

        insNum = new Text("Insurance Number:");
        insNum.setFont(Font.font("Times New Roman", 12));
        insNum.setFill(Color.BLACK);

        finish = new Text("All finished? Press submit below:");
        finish.setFont(Font.font("Times New Roman", 12));
        finish.setFill(Color.BLACK);

        //text fields for their corresponding items
        //the medical history is NOT a text field

        //Note: I added the prompt text for the mailing address
        //and the * to all required fields as specified, the * is
        //made red by the CSS inside of the setStyle method
        fNameField = new TextField();
        fNameField.setPromptText("*");
        fNameField.setStyle("-fx-prompt-text-fill: red");

        lNameField = new TextField();
        lNameField.setPromptText("*");
        lNameField.setStyle("-fx-prompt-text-fill: red");

        emailField = new TextField();
        emailField.setPromptText("*");
        emailField.setStyle("-fx-prompt-text-fill: red");

        pharmField = new TextField();
        pharmField.setPromptText("*");
        pharmField.setStyle("-fx-prompt-text-fill: red");

        numField = new TextField();
        numField.setPromptText("*");
        numField.setStyle("-fx-prompt-text-fill: red");

        insField = new TextField();
        insField.setPromptText("*");
        insField.setStyle("-fx-prompt-text-fill: red");

        insNumField = new TextField();
        insNumField.setPromptText("*");
        insNumField.setStyle("-fx-prompt-text-fill: red");

        mailField1 = new TextField();
        mailField1.setPromptText("* Line 1");
        mailField1.setStyle("-fx-prompt-text-fill: red");

        mailField2 = new TextField();
        mailField2.setPromptText("Line 2");

        mailField3 = new TextField();
        mailField3.setPromptText("* City, State");
        mailField3.setStyle("-fx-prompt-text-fill: red");

        mailField4 = new TextField();
        mailField4.setPromptText("* Zip Code");
        mailField4.setStyle("-fx-prompt-text-fill: red");

        //text area for the previous
        //medical history because it needs a larger space
        //rather than just a small text field
        medHisField = new TextArea();
        medHisField.setPromptText("*");
        medHisField.setStyle("-fx-prompt-text-fill: red");

        //date picker object in order for the patient
        //to pick their date of birth most effectively
        dobPicker = new TextField();
        dobPicker.setPromptText("*");
        //Note: this isn't working to set the prompt * color to red
        //for the date picker so another method must be tried later
        //dobPicker.setStyle("-fx-prompt-text-fill: red");

        //buttons for submitting the form or going back to the previous page
        //if submit is pressed, the program should read in every field
        //given the required fields are all full, or if they press back
        //the program should go to the previous screen
        //these will be handled in the event handlers for these buttons
        submit = new Button("Submit");
        back = new Button("Back");
        //when user presses Submit, they read all the boxes filled in and attempts to submit the new patient. Case 23
        CreatePatientNurseButton memo_submit = new CreatePatientNurseButton(23);
        submit.setOnAction(memo_submit);
        //when the user presses Back, they are sent to patient select menu, Case 21
        ForwardButton forward = new ForwardButton(21);
        back.setOnAction(forward);

        //vertical panes to attach the text element to it's corresponding
        //method of user input so textfield/area etc.
        VBox fNameBox = new VBox(2);
        fNameBox.getChildren().addAll(fName, fNameField);

        VBox lNameBox = new VBox(2);
        lNameBox.getChildren().addAll(lName, lNameField);

        VBox dobBox = new VBox(2);
        dobBox.getChildren().addAll(dob, dobPicker);

        VBox emailBox = new VBox(2);
        emailBox.getChildren().addAll(email, emailField);

        VBox phoneBox = new VBox(2);
        phoneBox.getChildren().addAll(phone, numField);

        VBox medHisBox = new VBox(2);
        medHisBox.getChildren().addAll(medHis, medHisField);

        VBox pharmBox = new VBox(2);
        pharmBox.getChildren().addAll(pharmacy, pharmField);

        VBox mailBox = new VBox(2);
        mailBox.getChildren().addAll(mailAddress, mailField1, mailField2, mailField3, mailField4);

        VBox insuranceBox = new VBox(2);
        insuranceBox.getChildren().addAll(insurance, insField);

        VBox insNumBox = new VBox(2);
        insNumBox.getChildren().addAll(insNum, insNumField);

        VBox finishBox = new VBox(2);
        finishBox.getChildren().addAll(finish, submit, back);
        //insets to center the buttons below the text/other text fields aesthetic
        VBox.setMargin(submit, new Insets(0, 0, 0, 40));
        VBox.setMargin(back, new Insets(0, 0, 0, 45));

        //Vertical pane for the first column of content
        //I didn't use a Grid Pane due to sizing issues/uneven
        //number of elements in each column
        VBox column1 = new VBox(8);
        column1.getChildren().addAll(fNameBox, emailBox, pharmBox, insuranceBox, insNumBox);

        VBox column2 = new VBox(8);
        column2.getChildren().addAll(lNameBox, phoneBox, mailBox, finishBox);

        VBox column3 = new VBox(8);
        column3.getChildren().addAll(dobBox, medHisBox);

        //put this all in a Horizontal pane to mimick the grid structure
        HBox formBox = new HBox(5);
        formBox.getChildren().addAll(column1, column2, column3);

        //create a vertical pane for the title, new patient form text,
        //and required text
        VBox topText = new VBox(5);
        topText.getChildren().addAll(title, welcome, form, required, errLabel);

        //add the top text with its contents to the top left
        //of this stack pane
        StackPane.setAlignment(topText, Pos.TOP_LEFT);
        StackPane.setMargin(topText, new Insets(10, 0, 0, 10));
        this.getChildren().add(topText);

        //border pane to center the grid pane contents
        BorderPane bp = new BorderPane();
        bp.setCenter(formBox);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);


        //add the border pane to this stack pane
        this.getChildren().add(bp);
    } //end constructor

    private class CreatePatientNurseButton extends ForwardButton { //button which makes sure all fields are filled in.
        private CreatePatientNurseButton(int caseInt) {
            super(caseInt);
        }

        @Override
        public void handle(ActionEvent event) {
            //first check to see if none of the required fields are empty.
            if (fNameField.getText().isEmpty() || lNameField.getText().isEmpty() || emailField.getText().isEmpty() ||
                pharmField.getText().isEmpty() || numField.getText().isEmpty() || insField.getText().isEmpty() ||
                insNumField.getText().isEmpty() || mailField1.getText().isEmpty() || mailField3.getText().isEmpty() ||
                mailField4.getText().isEmpty() || dobPicker.getText().isEmpty()) {

                //if any are empty update the label for the user.
                errLabel.setText("Please enter all necessary info");
                errLabel.setTextFill(Color.RED);
            }
            else {
                String[] dob_split = dobPicker.getText().split("-"); //split the DOB string to check if it's valid
                if ((dob_split[0].length() == 4) && (dob_split[1].length() == 2) && (dob_split[2].length() == 2)) {
                    //if so, then store all the fields into variables
                    try {
                        String p_fname = fNameField.getText();
                        String p_lname = lNameField.getText();
                        String p_email = emailField.getText();
                        String p_phone = numField.getText();
                        String p_pharmacy = pharmField.getText();
                        String p_insurance = insField.getText();
                        String p_insurance_num = insNumField.getText();
                        String p_dob = dobPicker.getText();
                        String p_med = medHisField.getText();
                        String p_address;
                        // I have to check if mailField2 is empty or not because it affects address formatting.
                        if (mailField2.getText().isEmpty()) {
                            p_address = mailField1.getText() + "\n" + mailField3.getText() + " " + mailField4.getText();
                        } else {
                            p_address = mailField1.getText() + " " + mailField2.getText() + "\n" + mailField3.getText()
                                    + " " + mailField4.getText();
                        }
                        // now need to generate a new patientID
                        String patientInt = "12";
                        int rand = (int) (Math.random() * 9000) + 1000;
                        String p_id = patientInt + String.valueOf(rand);
                        int final_p_id = Integer.parseInt(p_id);
                        //now I can finally execute the query.
                        String ins = "INSERT INTO Patient VALUES ('" + p_fname + "', '" + p_lname + "', " + final_p_id +
                                ", '" + p_email + "', '" + p_phone + "', '" + p_address + "', '" + p_pharmacy + "', '" +
                                p_insurance + "', '" + p_insurance_num + "', '" + p_dob + "', '" + p_med + "', " +
                                docID + ");";
                        boolean result = HealthPortal.statement.execute(ins); //return a boolean if it inserted or not
                        if (result) {
                            //if it did then update current patient as well as called ForwardButton's handle
                            HealthPortal.currPatient = final_p_id;
                            super.handle(event);
                        }
                        else { //otherwise, throw an exception.
                            throw new FailedException("INSERTING NEW PATIENT FAILED!!!");
                        }
                    } catch (Exception e) {
                        System.err.print(e);
                    }
                }
                else {  //this is in the case that the DOB is not formatted correctly.
                    errLabel.setText("Please Enter in The Date of Birth in the Correct Format.");
                    errLabel.setTextFill(Color.RED);
                }
            }
        }
    }
} //end nurse create patient class