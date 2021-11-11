import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NurseVitals extends StackPane
{
    private Color mainColor;
    private Text title, welcome, dob, contactInfo, email;
    private Text phone, medHisTitle, medHis, pharmacy, insurance, doctorNotes, pName;
    private Text visit, height, weight, bloodPressure, bodyTemp, allergies, sendMessage;
    private TextField heightField, weightField, bloodPField, bodyTempField, allergyField;
    private DatePicker visitDate;
    private TextArea docNotes;
    private Button back, go, submit;

    private StackPane root;
    private int currUser;

    public NurseVitals(StackPane rootPane, int currentUser)
    {
        // initialize parameters
        root = rootPane;
        currUser = currentUser;

        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //as well as the doctor who is logged on currently
        //Note: these will need to be read in from patient list
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Welcome in, Nurse Jackson");
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        pName = new Text("Patient: Adam Samler");
        pName.setFont(Font.font("Times New Roman", 14));
        pName.setFill(Color.BLACK);

        dob = new Text("DOB: 01/09/2007");
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        //labels the contact information
        contactInfo = new Text("Contact Information:");
        contactInfo.setFont(Font.font("Times New Roman", 14));
        contactInfo.setFill(Color.BLACK);

        //these will be parsed in from the patient's profile, Note: that
        //mailing address and insurance number are not included on summary
        //screen, but the rest of the information is
        phone = new Text("Phone: 123-456-7890");
        phone.setFont(Font.font("Times New Roman", 14));
        phone.setFill(Color.BLACK);

        email = new Text("Email: asamler@yahoo.com");
        email.setFont(Font.font("Times New Roman", 14));
        email.setFill(Color.BLACK);

        insurance = new Text("Insurance: Aetna");
        insurance.setFont(Font.font("Times New Roman", 14));
        insurance.setFill(Color.BLACK);

        pharmacy = new Text("Pharmacy: CVS #602");
        pharmacy.setFont(Font.font("Times New Roman", 14));
        pharmacy.setFill(Color.BLACK);

        //labels previous medical history
        medHisTitle = new Text("Previous Medical History:");
        medHisTitle.setFont(Font.font("Times New Roman", 14));
        medHisTitle.setFill(Color.BLACK);

        medHis = new Text("-Surgery, right foot, Jan. 2012\n-HepC Vaccine, Oct. 2020");
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
        go = new Button("Go");
        submit = new Button("Submit");

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
        docBox.getChildren().addAll(doctorNotes, docNotes, submit);
        VBox.setMargin(submit, new Insets(10,0,0,200));

        //vbox for send message label and buttons
        VBox messageBox = new VBox(2);
        messageBox.getChildren().addAll(sendMessage, go, back);
        VBox.setMargin(go, new Insets(0,0,0,40));
        VBox.setMargin(back, new Insets(0,0,0,37));

        //vertical panes for each column
        VBox column1 = new VBox(8);
        column1.getChildren().addAll(nameBox, contactBox, insBox, prevMedBox, messageBox);

        VBox column2 = new VBox(8);
        column2.getChildren().addAll(dateBox, heightBox, weightBox, bloodPBox, bodyTempBox, allergyBox);

        //vbox for title and doctor greeting
        VBox titleBox = new VBox(2);
        titleBox.getChildren().addAll(title, welcome);

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
} //end nurse vitals class