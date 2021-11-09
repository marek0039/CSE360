import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UserChoiceScreen extends StackPane
{
    //elements present on the user choice screen
    //color for the text, text elements
    //for the title and question and directions
    //and a button to press next and head to the next screen
    private Color mainColor;
    private Text title, question, select;
    public ComboBox menu;
    private Button next;
    private StackPane root;
    private int currUser;

    //user choice screen constructor
    public UserChoiceScreen(StackPane rootPane, int user)
    {
        root = rootPane;
        currUser = user;

        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //create borderpane and virtual/horizontal panes
        //to hold elements
        BorderPane bp = new BorderPane();
        VBox middleText = new VBox();
        HBox attributes = new HBox();

        //title of the portal and its font/color/size
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //question and selection text with their font/size
        //and color black
        question = new Text("Are you a patient or a medical professional?");
        question.setFont(Font.font("Times New Roman", 14));
        question.setFill(Color.BLACK);

        select = new Text("Please select one choice below:");
        select.setFont(Font.font("Times New Roman", 14));
        select.setFill(Color.BLACK);

        //combobox object for the menu which will contain
        //options 'Patient' and 'Medical Professional'
        menu = new ComboBox();
        menu.getItems().addAll("Patient", "Medical Professional");

        //next button to move to the next screen
        next = new Button("Next");
        //add forward method to handle where this button takes the user
        //this is case 2 in the switch statement for forward
        ForwardButton handler = new ForwardButton(2, root, currUser);
        next.setOnAction(handler);

        //add combobox and button to the horizontal pane to be side
        //by side underneath text
        attributes.getChildren().add(menu);
        attributes.getChildren().add(next);

        //add both text and horizontal pane containing combobox
        //and button to the vertical pane to vertically display
        //these attributes
        middleText.getChildren().add(question);
        middleText.getChildren().add(select);
        middleText.getChildren().add(attributes);

        //put the vertical pane in the center of the borderpane
        //so that it is displayed in the middle of the screen
        //set the max size so it does not move and is centered
        bp.setCenter(middleText);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //put the title at the top left of the user choice screen
        //which is a stack pane, and set some margins so it isn't
        //crammed into the corner
        this.setAlignment(title, Pos.TOP_LEFT);
        this.setMargin(title, new Insets(10, 0, 0, 10));

        //add the title and border pane containing the
        //other elements to the stack plane/this user choice screen object
        this.getChildren().add(title);
        this.getChildren().add(bp);

    }//end user choice screen constructor
}//end user choice screen class
