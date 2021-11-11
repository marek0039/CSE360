import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NewPatientForm extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, form, required, fName, lName, dob, email;
    public Text phone, medHis, pharmacy, mailAddress, insurance, insNum, finish, doctorChoice;
    public TextField fNameField, lNameField, emailField, pharmField, numField, insField, insNumField;
    public TextField mailField1, mailField2, mailField3, mailField4;
    public TextArea medHisField;
    public DatePicker dobPicker;
    private ComboBox doctorsList;
    private Button submit, back;
    private int currUser;
    public Label errorLabel;

    public NewPatientForm(int user)
    {
        root = rootPane;
        currUser = user;
        errorLabel = new Label();
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling that this is a new patient form,
        //and red text labeling what is required for the patient
        //to fill in
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

        dob = new Text("Patient Date of Birth:");
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

        doctorChoice = new Text("Choose a Doctor(Optional):");
        doctorChoice.setFont(Font.font("Times New Roman", 12));
        doctorChoice.setFill(Color.BLACK);

        //text fields for their corresponding items
        //the medical history, date of birth
        //and doctor choice are NOT text fields

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
        dobPicker = new DatePicker();
        dobPicker.setPromptText("*");
        //Note: this isn't working to set the prompt * color to red
        //for the date picker so another method must be tried later
        //dobPicker.setStyle("-fx-prompt-text-fill: red");

        //combo box for the list of doctors that the
        //patient can choose from if they want
        //Note: this is NOT a required field (the only optional one)
        doctorsList = new ComboBox();
        //NOTE we are going to have to figure out if we want to
        //take the doctor's names from the doctors who log in
        //or just have these default dummy doctors
        doctorsList.getItems().addAll("Doctor Johnson", "Doctor Peters", "Doctor Anderson");

        //buttons for submitting the form or going back to the previous page
        //if submit is pressed, the program should read in every field
        //given the required fields are all full, or if they press back
        //the program should go to the previous screen
        //these will be handled in the event handlers for these buttons
        submit = new Button("Submit");
        //forward event handler for submit button, case 5, takes user to confirmation page
        ForwardButton handler1 = new ForwardButton(5, currUser);
        submit.setOnAction(handler1);

        back = new Button("Back");
        //forward event handler for back button, case 4, takes user back to patient choice screen
        ForwardButton handler2 = new ForwardButton(4, currUser);
        back.setOnAction(handler2);

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

        VBox docBox = new VBox(2);
        docBox.getChildren().addAll(doctorChoice, doctorsList);

        //Vertical pane for the first column of content
        //I didn't use a Grid Pane due to sizing issues/uneven
        //number of elements in each column
        VBox column1 = new VBox(8);
        column1.getChildren().addAll(fNameBox, emailBox, pharmBox, insuranceBox, insNumBox);

        VBox column2 = new VBox(8);
        column2.getChildren().addAll(lNameBox, phoneBox, mailBox, finishBox);

        VBox column3 = new VBox(8);
        column3.getChildren().addAll(dobBox, medHisBox, docBox);

        //put this all in a Horizontal pane to mimick the grid structure
        HBox formBox = new HBox(5);
        formBox.getChildren().addAll(column1, column2, column3);

        //create a vertical pane for the title, new patient form text,
        //and required text
        VBox topText = new VBox(5);
        topText.getChildren().addAll(title, form, required, errorLabel);

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
}//end new patient form class
