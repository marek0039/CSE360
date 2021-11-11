import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UpdateInfoConfirmation extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, dob, confirmation;
    private Button summary, back;
    private int currUser;

    public UpdateInfoConfirmation(int user)
    {
        currUser = user;

        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //Note: these will need to be read in from the patient logging on's
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Welcome, Patient Adam Samler");
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        dob = new Text("DOB: 01/09/2007");
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        confirmation = new Text("You have updated your contact information successfully.\n\t\tClick below to view your summary.");
        confirmation.setFont(Font.font("Times New Roman", 14));
        confirmation.setFill(Color.BLACK);

        //buttons for user, summary leads to the new patient's summary with their
        //inputted information, and back takes them back to the new patient form
        //page incase they need to go back to a field
        summary = new Button("Summary");
        //forward event handler for taking patient to view summary
        //after theyve updated their contact information, case 12
        ForwardButton handler1 = new ForwardButton(12,currUser);
        summary.setOnAction(handler1);

        back = new Button("Patient Menu");
        //forward event handler for back button takes user to
        //update the existing patient portal home screen
        ForwardButton handler2 = new ForwardButton(11,currUser);
        back.setOnAction(handler2);

        //Vertical pane to put the patient's name and dob stacked
        VBox patientInfo = new VBox(3);
        patientInfo.getChildren().addAll(title, welcome, dob);

        //Vertical pane to add the confirmation text and buttons
        VBox confMessage = new VBox(5);
        confMessage.getChildren().addAll(confirmation, summary, back);
        VBox.setMargin(summary, new Insets(0, 0, 0, 110));
        VBox.setMargin(back, new Insets(0, 0, 0, 107));

        //Border pane to put the confirmation message pane in the center of the page
        BorderPane bp = new BorderPane();
        bp.setCenter(confMessage);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //put the title/welcome message vertical pane at the top left
        StackPane.setAlignment(patientInfo, Pos.TOP_LEFT);
        StackPane.setMargin(patientInfo, new Insets(10, 0, 0, 10));

        //add the title and border pane containing the
        //other elements to the stack plane/this user choice screen object
        this.getChildren().add(patientInfo);
        this.getChildren().add(bp);
    } //end constructor
} //end class