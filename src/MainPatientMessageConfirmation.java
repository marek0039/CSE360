import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MainPatientMessageConfirmation extends Application
{
	private class PatientMessageConfirmation extends StackPane
	{
		//create attributes for this screen
		private Color mainColor;
		private Text title, welcome, dob, confirmation;
		private Button back;
		
		private PatientMessageConfirmation()
		{
			//establish color Falu Red as done on home screen
			mainColor = Color.rgb(128,32,32);
			
			//title and its color/size/font
			title = new Text("SunDevil Pediatric Health Portal");
			title.setFont(Font.font("Plantagenet Cherokee", 23));
			title.setFill(mainColor);
			
			//black text labeling the name of the patient and dob of the patient
			//Note: these will need to be read in from the patient's log in info
			//text fields/areas so they will end up being parsed input rather than this dummy default text
			welcome = new Text("Patient: Adam Samler");
			welcome.setFont(Font.font("Times New Roman", 14));
			welcome.setFill(Color.BLACK);
			
			dob = new Text("DOB: 01/09/2007");
			dob.setFont(Font.font("Times New Roman", 14));
			dob.setFill(Color.BLACK);

			confirmation = new Text("\t\tYour message has been sent!\nSelect the button to go back to your portal home screen.");
			confirmation.setFont(Font.font("Times New Roman", 14));
			confirmation.setFill(Color.BLACK);
			
			//button for the user to go back to their home patient portal screen
			//with options to send messages, update info, or view summary
			back = new Button("Back");
			
			//vertical box to store title and welcome contents
			VBox titleBox = new VBox(3);
			titleBox.getChildren().addAll(title, welcome, dob);
			
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
	} //end class
	
	public void start(Stage stage) 
	{
		//create the root stack pane and add user choice screen object to it
		StackPane root = new StackPane();
	
		//patient message confirmation object to place and display on the root
		PatientMessageConfirmation pmc = new PatientMessageConfirmation();
		root.getChildren().add(pmc);
	
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
