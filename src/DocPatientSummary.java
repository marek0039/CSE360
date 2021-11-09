import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DocPatientSummary extends StackPane
{
    private Color mainColor;
    private Text title, welcome, dob, contactInfo, email;
    private Text phone, medHisTitle, medHis, pharmacy, insurance, doctorNotes, pName;
    private Text date1, date2, height, weight, bloodPressure, bodyTemp, allergies, prescription;
    private Text height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2;
    private Text sendMessage, newMeds;
    private TextField notesField, presField;
    private Button back, go , submit;
    private StackPane root;
    private int curruser;

    public DocPatientSummary(StackPane root1, int user)
    {
        root = root1;
        curruser = user;
        //create attributes for this screen

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
        welcome = new Text("Welcome in, Doctor Sparky");
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

        //these are dummy dates for fake previous visits
        //Note: we need to decide how we will keep track of
        //visit dates
        date1 = new Text("Visit Date: 08/15/2020");
        date1.setFont(Font.font("Times New Roman", 14));
        date1.setFill(Color.BLACK);

        date2 = new Text("Visit Date: 03/04/2021");
        date2.setFont(Font.font("Times New Roman", 14));
        date2.setFill(Color.BLACK);

        //these are nurse's categories of what they take at each appointment
        //there are 2 of each because this screen displays 2 dummy previous visits
        //these will all be parsed in, ex. Height: + [string that holds height] from nurse's input
        height = new Text("Height: 5 feet 7 inches");
        height.setFont(Font.font("Times New Roman", 14));
        height.setFill(Color.BLACK);

        height2 = new Text("Height: 5 feet 9 inches, grew 2 inches");
        height2.setFont(Font.font("Times New Roman", 14));
        height2.setFill(Color.BLACK);

        weight = new Text("Weight: 120 lbs");
        weight.setFont(Font.font("Times New Roman", 14));
        weight.setFill(Color.BLACK);

        weight2 = new Text("Weight: 122 lbs, gained 2 lbs");
        weight2.setFont(Font.font("Times New Roman", 14));
        weight2.setFill(Color.BLACK);

        bloodPressure = new Text("Blood Pressure: 120/80mmHg");
        bloodPressure.setFont(Font.font("Times New Roman", 14));
        bloodPressure.setFill(Color.BLACK);

        bloodPressure2 = new Text("Blood Pressure: 120/80mmHg");
        bloodPressure2.setFont(Font.font("Times New Roman", 14));
        bloodPressure2.setFill(Color.BLACK);

        bodyTemp = new Text("Body Temperature: 96.8 degrees");
        bodyTemp.setFont(Font.font("Times New Roman", 14));
        bodyTemp.setFill(Color.BLACK);

        bodyTemp2 = new Text("Body Temperature: 96.8 degrees");
        bodyTemp2.setFont(Font.font("Times New Roman", 14));
        bodyTemp2.setFill(Color.BLACK);

        allergies = new Text("Allergies: Peanuts");
        allergies.setFont(Font.font("Times New Roman", 14));
        allergies.setFill(Color.BLACK);

        allergies2 = new Text("Allergies: Peanuts");
        allergies2.setFont(Font.font("Times New Roman", 14));
        allergies2.setFill(Color.BLACK);

        prescription = new Text("Prescription: Epinephrine 'EpiPen'");
        prescription.setFont(Font.font("Times New Roman", 14));
        prescription.setFill(Color.BLACK);

        prescription2 = new Text("Prescription: Epinephrine 'EpiPen'");
        prescription2.setFont(Font.font("Times New Roman", 14));
        prescription2.setFill(Color.BLACK);

        //doctor's/nurse's notes label
        doctorNotes = new Text("Doctor/Nurse's Notes:");
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

        //put a lot of space between the dates so they
        //align with their corresponding height/weight etc.
        VBox visitBox = new VBox(100);
        visitBox.getChildren().addAll(date1, date2);

        VBox visit1Box = new VBox(2);
        visit1Box.getChildren().addAll(height, weight, bloodPressure, bodyTemp, allergies, prescription);

        VBox visit2Box = new VBox(2);
        visit2Box.getChildren().addAll(height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2);

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

        //vbox for title and doctor greeting
        VBox titleBox = new VBox(2);
        titleBox.getChildren().addAll(title, welcome);

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
} //end class