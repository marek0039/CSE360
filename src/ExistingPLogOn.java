import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ExistingPLogOn extends StackPane
{
    // Test 123
    //attributes of this class to be displayed on the pane
    private Color mainColor;
    private Text title, label, fName, lName, dob;
    public TextField fNameField, lNameField;
    public DatePicker dobPicker;
    private Button submit, back;
    private StackPane root;
    private int currUser;
    public Label errorLabel;

    public ExistingPLogOn(StackPane rootPane, int user)
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

        //black text labeling that this is the existing patient log on screen
        label = new Text("Existing Patient Log On");
        label.setFont(Font.font("Times New Roman", 14));
        label.setFill(Color.BLACK);

        //black text labeling the fields needed to log on
        fName = new Text("Patient First Name:");
        fName.setFont(Font.font("Times New Roman", 14));
        fName.setFill(Color.BLACK);

        lName = new Text("Patient Last Name:");
        lName.setFont(Font.font("Times New Roman", 14));
        lName.setFill(Color.BLACK);

        dob = new Text("Patient Date of Birth:");
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        //text fields and date picker for the patient to input information
        fNameField = new TextField();
        lNameField = new TextField();
        dobPicker = new DatePicker();

        //buttons to allow the user to submit and log in, or go back to the
        //previous page in case they did not mean to enter this one
        submit = new Button("Submit");
        //forward event handler for submit button, case 7, takes user to existing patient
        //portal with their information
        ForwardButton handler1 = new ForwardButton(7, root, currUser);
        submit.setOnAction(handler1);

        back = new Button("Back");
        //forward event handler for back button, case 4, takes user back to patient choice screen
        ForwardButton handler2 = new ForwardButton(4, root, currUser);
        back.setOnAction(handler2);

        //Vertical pane to put the title and existing patient label together
        VBox titleBox = new VBox(5);
        titleBox.getChildren().addAll(title, label, errorLabel);

        //Vertical pane to put the log on requirements in the center of the page
        VBox centerElements = new VBox(8);
        centerElements.getChildren().addAll(fName, fNameField, lName, lNameField, dob, dobPicker, submit, back);
        //insets to center the buttons below the text/other text fields aesthetic
        VBox.setMargin(submit, new Insets(0, 0, 0, 60));
        VBox.setMargin(back, new Insets(0, 0, 0, 65));

        //add the top text with its contents to the top left
        //of this stack pane
        StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
        StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
        this.getChildren().add(titleBox);

        //border pane to add the contents to the center of the stack pane
        BorderPane bp = new BorderPane();
        bp.setCenter(centerElements);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //add the border pane to this stack pane
        this.getChildren().add(bp);
    } //end constructor
} //end existing patient log on class
