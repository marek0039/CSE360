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

public class ExistingPatientPortal extends StackPane
{
    //attributes of this class to be displayed on the pane
    private Color mainColor;
    private Text title, welcome, dob, update, view, message;
    private Button go1, go2, go3, back;

    public ExistingPatientPortal()
    {
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //Note: these will need to be read in from the previous New patient form
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Welcome, Patient Adam Samler");
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        dob = new Text("DOB: 01/09/2007");
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        //options for the user to select which screen they want to go to next
        update = new Text("Update Personal Information:");
        update.setFont(Font.font("Times New Roman", 14));
        update.setFill(Color.BLACK);

        view = new Text("View Previous Visit Summaries:");
        view.setFont(Font.font("Times New Roman", 14));
        view.setFill(Color.BLACK);

        message = new Text("Send Message to Medical Professional:");
        message.setFont(Font.font("Times New Roman", 14));
        message.setFill(Color.BLACK);

        //buttons to go to each various screen
        //go1 will go to updating their personal information
        //go2 will go to viewing their previous summaries
        //go3 will go to sending a message to a medical prof.
        //the back button will take them back to the previous screen
        go1 = new Button("Go");
        //forward event handler for first go button, case 7, takes user to
        //update their contact info/personal info screen
        ForwardButton handler1 = new ForwardButton(7);
        go1.setOnAction(handler1);

        go2 = new Button("Go");
        //forward event handler for second go button, case 9, takes user to
        //view previous visit summary screen
        ForwardButton handler2 = new ForwardButton(9);
        go2.setOnAction(handler2);

        go3 = new Button("Go");
        //forward event handler for third go button, case 10, takes user to
        //message portal screen
        ForwardButton handler3 = new ForwardButton(10);
        go3.setOnAction(handler3);


        back = new Button("Back");
        //forward event handler for back button, case 7, takes user back
        //to existing patient log on screen
        ForwardButton handler4 = new ForwardButton(7);
        back.setOnAction(handler4);

        //vertical pane to store all the options and buttons in a stack
        VBox content = new VBox(8);
        content.getChildren().addAll(welcome, dob, update, go1, view, go2, message, go3, back);
        //give dob space on the bottom to separate it from options content
        VBox.setMargin(dob, new Insets(0, 0, 15, 0));

        //insets object to apply same insets to all the go buttons
        Insets i = new Insets(0, 0, 0, 70);
        VBox.setMargin(go1, i);
        VBox.setMargin(go2, i);
        VBox.setMargin(go3, i);
        //give back button some space on the top/center it
        VBox.setMargin(back, new Insets(15, 0, 0, 65));

        //add the title to the top left of this stack pane
        StackPane.setAlignment(title, Pos.TOP_LEFT);
        StackPane.setMargin(title, new Insets(10, 0, 0, 10));
        this.getChildren().add(title);

        //border pane to add the contents to the center of the stack pane
        BorderPane bp = new BorderPane();
        bp.setCenter(content);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //add the border pane to this stack pane
        this.getChildren().add(bp);
    } //end constructor
} //end class