import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PatientSendMessage extends StackPane
{
    //attributes of this class
    private Color mainColor;
    private Text title, welcome, dob, directions, select;
    private ComboBox medList;
    private TextArea message;
    private Button send, back;

    public PatientSendMessage()
    {
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //Note: these will need to be read in from the existing patient log in
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Patient: Adam Samler");
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        dob = new Text("DOB: 01/09/2007");
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        directions = new Text("Please type your message below:");
        directions.setFont(Font.font("Times New Roman", 14));
        directions.setFill(Color.BLACK);

        select = new Text("(Required) Select a medical professional below to send your message to: ");
        select.setFont(Font.font("Times New Roman", 14));
        select.setFill(Color.BLACK);

        //text area to store patient message from when they hit the send button
        message = new TextArea();

        //buttons for the user to send their message IF they have put message content,
        //and they have selected a medical professional, or go back to the previous page
        send = new Button("Send");
        //forward event handler for the patient to go to message confirmation page
        //after they have sent the message, case 12
        ForwardButton handler1 = new ForwardButton(12);
        send.setOnAction(handler1);

        back = new Button("Back");
        //forward event handler for patient to back to the message portal screen, case 10
        ForwardButton handler2 = new ForwardButton(10);
        back.setOnAction(handler2);

        //combo box with a list of medical professionals to send the message to
        //again Note: we need to figure out if we want to pull these from doctors/nurses
        //in our data base or have automatic default ones and create a list so we
        //can add additional ones when knew medical professionals are created
        medList = new ComboBox();
        medList.getItems().addAll("Doctor Anderson", "Doctor Sparky", "Nurse Johnson", "Nurse Stevens");

        //vertical boxes to store the title and it's contents and the middle of the page contents
        VBox titleBox = new VBox(2);
        titleBox.getChildren().addAll(title, welcome, dob);

        VBox messageBox = new VBox(8);
        messageBox.getChildren().addAll(directions, message, select, medList, send, back);
        //insets to center the buttons below the text/other text fields aesthetic
        VBox.setMargin(medList, new Insets(0,0,0,150));
        VBox.setMargin(send, new Insets(0, 0, 0, 200));
        VBox.setMargin(back, new Insets(0, 0, 0, 200));

        //border pane to the message contents in the center of the screen
        BorderPane bp = new BorderPane();
        bp.setCenter(messageBox);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //add the top text with its contents to the top left
        //of this stack pane
        StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
        StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
        this.getChildren().add(titleBox);

        //add the border pane to this stack pane
        this.getChildren().add(bp);
    } //end constructor
} //end class