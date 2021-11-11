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

public class NurseMessageConfirmation extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, patient, dob, confirmation;
    private Button back;
    private int currUser;

    public NurseMessageConfirmation(int user)
    {
        currUser = user;

        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //and nurse that is logged on currently
        //Note: these will need to be read in from the patient list the nurse chose from
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Welcome in, Nurse Jackson");
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        patient = new Text("Patient: Adam Samler");
        patient.setFont(Font.font("Times New Roman", 14));
        patient.setFill(Color.BLACK);

        dob = new Text("DOB: 01/09/2007");
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        confirmation = new Text("\t\tYour message has been sent!\nSelect the button to go back to your portal home screen.");
        confirmation.setFont(Font.font("Times New Roman", 14));
        confirmation.setFill(Color.BLACK);

        //button for the user to go back to their home screen where they are logged
        //on as a doctor and can select a patient from the patient list
        back = new Button("Back");

        //vertical box to store title and welcome contents
        VBox titleBox = new VBox(3);
        titleBox.getChildren().addAll(title, welcome, patient, dob);

        //vertical box to store confirmation message and back button
        VBox confMessage = new VBox(5);
        confMessage.getChildren().addAll(confirmation, back);
        VBox.setMargin(back, new Insets(10, 0, 0, 130));

        //border pane to put confirmation message in the center of the page
        BorderPane bp = new BorderPane();
        bp.setCenter(confMessage);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //put the title/welcome message vertical pane at the top left
        StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
        StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));

        //add the title and border pane containing the
        //other elements to the stack plane
        this.getChildren().add(titleBox);
        this.getChildren().add(bp);
    } //end constructor
} //end nurse message confirmation class