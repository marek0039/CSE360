import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MainExistingPatientPortal extends Application
{
	private class ExistingPatientPortal extends StackPane
	{
		//attributes of this class to be displayed on the pane
		private Color mainColor;
		private Text title, welcome, dob, update, view, message;
		private Button go1, go2, go3, back;
		
		private ExistingPatientPortal()
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
			go2 = new Button("Go");
			go3 = new Button("Go");
			back = new Button("Back");
			
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
	
	public void start(Stage stage) 
	{
		//create the root stack pane and add user choice screen object to it
		StackPane root = new StackPane();
	
		//existing patient portal object to place and display on the root
		ExistingPatientPortal epp = new ExistingPatientPortal();
		root.getChildren().add(epp);
	
		//set the scene/stage and put the default size as 700 x 500 with root displayed
	    Scene scene = new Scene(root, 700, 500);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void main(String[] args)
    {
        launch(args);
    }
}
