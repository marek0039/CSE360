import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientUpdateInfo extends StackPane {
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, dob, directions, email;
    private Text phone, medHis, pharmacy, mailAddress, insurance, insNum, finish;
    private TextField emailField, pharmField, numField, insField, insNumField;
    private TextField mailField1, mailField2, mailField3, mailField4;
    private TextArea medHisField;
    private Button submit, back;

    public PatientUpdateInfo() {


        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128, 32, 32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //Note: these will need to be read in from the previous New patient form
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Welcome, Patient: ");
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        dob = new Text("DOB: ");
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        //directions highlighting that if the existing patient does not
        //change a field and leaves it blank when they submit,
        //the existing information will be kept
        directions = new Text("Update your information, if no change is made to a particular area, your current information will remain.");
        directions.setFont(Font.font("Times New Roman", 14));
        directions.setFill(Color.BLACK);

        //the text indicating the fields the user can change
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

        //text fields for the areas which just require a text field
        //the previous medical history uses a text area
        emailField = new TextField();

        numField = new TextField();

        pharmField = new TextField();

        numField = new TextField();

        insField = new TextField();
        insNumField = new TextField();

        mailField1 = new TextField();
        mailField1.setPromptText("Line 1");

        //text area for the previous
        //medical history because it needs a larger space
        //rather than just a small text field
        medHisField = new TextArea();

        //buttons that allow the user to submit the form, Note:
        //the user does NOT have to have all fields filled out on this form
        //for submit to be utilized, this will be taken care of in event handler
        //back button takes user back to previous page
        submit = new Button("Submit");
        //forward event handler for submit button after patient had updated
        //their personal information, they will be taken to confirmation page, case 8
        UpdateInfo handler1 = new UpdateInfo(8);
        submit.setOnAction(handler1);

        back = new Button("Patient Menu");
        //forward event handler for the back button takes user back to existing
        //patient portal screen, case 5
        ForwardButton handler2 = new ForwardButton(5);
        back.setOnAction(handler2);

        //Vertical panes to store each field with its title
        VBox emailBox = new VBox(2);
        emailBox.getChildren().addAll(email, emailField);

        VBox phoneBox = new VBox(2);
        phoneBox.getChildren().addAll(phone, numField);

        VBox medHisBox = new VBox(2);
        medHisBox.getChildren().addAll(medHis, medHisField);

        VBox pharmBox = new VBox(2);
        pharmBox.getChildren().addAll(pharmacy, pharmField);

        VBox mailBox = new VBox(2);
        mailBox.getChildren().addAll(mailAddress, mailField1);

        VBox insBox = new VBox(2);
        insBox.getChildren().addAll(insurance, insField);

        VBox insNumBox = new VBox(2);
        insNumBox.getChildren().addAll(insNum, insNumField);

        VBox finishBox = new VBox(2);
        finishBox.getChildren().addAll(finish, submit, back);
        //insets to center the buttons below the text/other text fields aesthetic
        VBox.setMargin(submit, new Insets(0, 0, 0, 40));
        VBox.setMargin(back, new Insets(0, 0, 0, 37));

        //Vertical panes to store the various columns of information
        //mimicks a grid pane but is more aesthetically pleasing
        VBox column1 = new VBox(8);
        column1.getChildren().addAll(emailBox, pharmBox, insBox, insNumBox);

        VBox column2 = new VBox(8);
        column2.getChildren().addAll(phoneBox, mailBox, finishBox);

        //add these to an HBox, note that medical history doesn't have a VBox since
        //it is by itself in a column already
        HBox contents = new HBox(5);
        contents.getChildren().addAll(column1, column2, medHisBox);

        //Vertical pane for the top of the screen for the title and other texts
        VBox titleBox = new VBox(3);
        titleBox.getChildren().addAll(title, welcome, dob, directions);

        //Border pane to put the contents pane in the center of the page
        BorderPane bp = new BorderPane();
        bp.setCenter(contents);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //add the title and its accompanying texts to the top
        //left of this stack pane
        StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
        StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
        this.getChildren().add(titleBox);
        //handle();
        //add the border pane to this stack pane
        this.getChildren().add(bp);
        System.out.println("Handle Method getting called");
        String getpatient = "SELECT * FROM Patient WHERE PatientID=" + HealthPortal.currUser;
        //get patient info
        try {
            ResultSet rs = null;
            String p_name = null, p_email = null, p_phone = null, p_address = null, p_pharmacy = null, p_insurance = null,
                    p_insurance_num = null, p_dob = null, p_med = null;


            rs = HealthPortal.statement.executeQuery(getpatient); //execute the query
            rs.next();
            if (rs.getRow() == 1) {
                //store every column in their respective variable
                p_name = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                p_email = rs.getString("Email");
                p_phone = rs.getString("Phone_Number");
                p_address = rs.getString("Address");
                p_pharmacy = rs.getString("Pharmacy");
                p_insurance = rs.getString("Insurance");
                p_insurance_num = rs.getString("Insurance_Number");
                p_dob = rs.getString("DOB");
                p_med = rs.getString("Medical_History");

                welcome.setText(welcome.getText() + p_name);
                dob.setText(dob.getText() + p_dob);

                emailField.setText(p_email);
                pharmField.setText(p_pharmacy);
                numField.setText(p_phone);
                insNumField.setText(p_insurance_num);
                insField.setText(p_insurance);
                mailField1.setText(p_address);
                medHisField.setText(p_med);

            } else {
                System.out.println("Cannot field Patient: " + HealthPortal.currPatient);
                System.out.println(getpatient);
                //throw new FailedException("Cannot find Patient: " + HealthPortal.currPatient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    } //end constructor
    private class UpdateInfo extends ForwardButton {
        private int num_rows;

        private UpdateInfo(int caseInt) {

            super(caseInt);
        }
        @Override

    public void handle(ActionEvent event) {
            System.out.println("Running Update Patient SQL");
        try {
            String sql2 = "UPDATE Patient SET Email ='" + emailField.getText() + "', Phone_Number ='" + numField.getText() + "',Address ='"
                    + mailField1.getText() + "', Pharmacy ='" + pharmField.getText() + "', Insurance='"
                    + insField.getText() + "', Insurance_Number='"
                    + insNumField.getText() + "', Medical_History ='" + medHisField.getText() + "'WHERE PatientID =" + HealthPortal.currUser;
            HealthPortal.statement.executeUpdate(sql2);
            super.setcI(8);
            super.handle(event);


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }




    }
}

//end class