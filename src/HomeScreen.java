import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HomeScreen extends StackPane
{
    //elements present on the home screen
    //color for the text, text elements
    //for the title and log in directions
    //and a button to press start and head to the next screen
    private Color mainColor;
    private Text title, log;
    private Button start;
    private StackPane root;

    //home screen constructor
    public HomeScreen(StackPane rootPane)
    {
        //set the color with it's rgb value
        //color name is "Falu Red" or #802020 in hex
        mainColor = Color.rgb(128,32,32);

        //vertical pane to store the elements on the
        //home screen, set some padding to put the
        //elements in the center of the screen
        VBox homePane = new VBox();
        homePane.setPadding(new Insets(200,100,100,200));

        //image for the background of the screen
        //need to figure out if this needs to be
        //posted online as that would make it accessible
        //to anyone running the code
        //Image img = new Image("");
        //ImageView imgView = new ImageView(img);

        //title of the portal and it's font/size/color
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //directions for logging on and their font/size/color
        log = new Text("To log on, press start.");
        log.setFont(Font.font("Plantagenet Cherokee", 14));
        log.setFill(mainColor);

        //start button to take user to next screen
        //when pressed
        start = new Button("Start");
        //Button handler object to take care of the event that
        //the user presses the start button
        ForwardButton handler = new ForwardButton(1, root);
        start.setOnAction(handler);

        //add the title to the vertical pane
        homePane.getChildren().add(title);

        //add the directions to the vertical pane
        //with some padding to space it from the
        //title and button since it is the middle element
        homePane.getChildren().add(log);
        VBox.setMargin(log, new Insets(10, 0, 10, 100));

        //add start button to the vertical pane
        //with some padding as described above
        homePane.getChildren().add(start);
        VBox.setMargin(start, new Insets(0, 0, 0, 150));

        //add the vertical pane with it's elements
        //to this home screen object, since it extends
        //stack pane, the photo should be added before
        //so that it is in the background and the
        //vertical pane is in front and visible to the user
        this.getChildren().add(homePane);

    }//end home screen constructor
}//end home screen class
