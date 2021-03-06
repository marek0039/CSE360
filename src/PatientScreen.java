import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.*;

public class PatientScreen extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, question1, question2;
    public RadioButton one, two, three, four;
    private Button next, back;
    private Label errorLabel;

    public PatientScreen()
    {
        errorLabel = new Label();
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //welcome text and it's color/size/font
        welcome = new Text("Welcome in! Please answer a few questions so we can better help you today.");
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        //questions and their fonts/sizes/colors to be placed
        //above the two sets of radio button options
        question1 = new Text("I am a...");
        question1.setFont(Font.font("Times New Roman", 14));
        question1.setFill(Color.BLACK);

        question2 = new Text("I am...");
        question2.setFont(Font.font("Times New Roman", 14));
        question2.setFill(Color.BLACK);

        //radio buttons for the user to select which type of patient/parent
        //or guardian they are
        one = new RadioButton("Patient");
        two = new RadioButton("Parent/Guardian of a patient");

        //put one and two in a group so only one can be selected at once
        ToggleGroup group1 = new ToggleGroup();
        one.setToggleGroup(group1);
        two.setToggleGroup(group1);

        three = new RadioButton("New patient (or parent/guardian of one)");
        four = new RadioButton("Existing patient (or parent/guardian of one)");

        //put three and four in a group so only one can be selected at once
        ToggleGroup group2 = new ToggleGroup();
        three.setToggleGroup(group2);
        four.setToggleGroup(group2);

        //buttons to go to the next screen, or go back to the previous screen
        next = new Button("Next");
        //next button forward event handler, set case to 3 but will get changed in PatientStatusButton
        //log on screen or new patient form screen
        PatientStatusButton handler1 = new PatientStatusButton(3);
        next.setOnAction(handler1);

        back = new Button("Back");
        //back button forward event handler, case 1, go back to user choice screen
        ForwardButton handler2 = new ForwardButton(1);
        back.setOnAction(handler2);

        //vertical pane to hold the various buttons and their questions
        VBox buttonContent = new VBox(10);
        buttonContent.getChildren().addAll(welcome, errorLabel, question1, one, two, question2, three, four, next, back);

        //some insets to better center the buttons/questions for aesthetic
        //insets object prevents having to type the same thing a million times
        Insets i = new Insets(0, 0, 0, 75);
        VBox.setMargin(question1, i);
        VBox.setMargin(question2, i);
        VBox.setMargin(one, i);
        VBox.setMargin(two, i);
        VBox.setMargin(three, i);
        VBox.setMargin(four, i);

        //insets for the buttons, they have a larger
        //left margin because they are so small
        //and need to be centered
        Insets j = new Insets(0, 0, 0, 175);
        VBox.setMargin(next, j);
        VBox.setMargin(back, j);

        //border pane to place the vertical pane in the center of,
        //to center it on top of the stack pane eventually
        BorderPane bp = new BorderPane();
        bp.setCenter(buttonContent);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //add the title to the top left of this stack pane with
        //some insets to prevent it being crammed in the top
        StackPane.setAlignment(title, Pos.TOP_LEFT);
        StackPane.setMargin(title, new Insets(10, 0, 0, 10));
        this.getChildren().add(title);

        //add the border pane containing all the attributes to this stack pane
        this.getChildren().add(bp);
    } //end constructor

    private class PatientStatusButton extends ForwardButton {

        public PatientStatusButton(int caseInt) {
            super(caseInt);
        }

        @Override
        public void handle(ActionEvent event) {
            if (three.isSelected()) {
                super.setcI(4);
            }
            if (four.isSelected()) {
                super.setcI(6);
            }
            super.handle(event);
        }
    }
} //end patient screen class
