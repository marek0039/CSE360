import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.Locale;

public class MainExistingPatientLogOn extends Application
{
	private class ExistingPLogOn extends StackPane
	{
		//attributes of this class to be displayed on the pane
		private Color mainColor;
		private Text title, label, fName, lName, dob;
		private TextField fNameField, lNameField;
		private DatePicker dobPicker;
		private Button submit, back;
		
		private ExistingPLogOn() 
		{
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
			back = new Button("Back");
			
			//Vertical pane to put the title and existing patient label together
			VBox titleBox = new VBox(5);
			titleBox.getChildren().addAll(title, label);
			
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
	
	public void start(Stage stage) 
	{
		//create the root stack pane and add user choice screen object to it
		StackPane root = new StackPane();
	
		//existing patient log on object to place and display on the root
		ExistingPLogOn eplo = new ExistingPLogOn();
		root.getChildren().add(eplo);
	
		//set the scene/stage and put the default size as 700 x 500 with root displayed
	    Scene scene = new Scene(root, 700, 500);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void main(String[] args)
    {
		//locale is for the date picker it's set to today in the U.S.
		Locale.setDefault(Locale.US);
        launch(args);
    }
}
