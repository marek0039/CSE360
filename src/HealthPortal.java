import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;	
import javafx.event.EventHandler;
import javafx.geometry.*;
import java.sql.Statement;
import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class HealthPortal extends Application
{
	//main root pane for the whole gui
	public static StackPane root = new StackPane();
	public static int currUser = 0;
	public static int currPatient = 0;
	public static Statement statement;
	
	//home screen and user choice panes displayed to all
	//need to keep track of the current user that is logged in
	
	public void start(Stage stage) 
	{
	    //create the root stack pane and add home screen object to it
		Connector connect = new Connector();
		statement = connect.getStatement();
		HomeScreen home = new HomeScreen();
		root.getChildren().add(home);
		//styling to add image to the background of the root with our dr.sparky character
		root.setStyle("-fx-background-image: url('drsparky.JPG'); " +
				"-fx-background-repeat: no-repeat;" + "-fx-background-size: 900px 550px;");
	
		//set the scene/stage and put the default size as 900 x 500 with root displayed
		Scene scene = new Scene(root, 900, 550);
	    stage.setScene(scene);
	    stage.show();
	}
	public static void main(String[] args)
    {
		Locale.setDefault(Locale.US);
        launch(args);
    }
} //end health portal class
