import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.Locale;

public class MainNewPatientConfirmation extends Application
{
	private class NewPatientConfirmation extends StackPane
	{
		//create attributes for this screen
		private Color mainColor;
		private Text title, welcome, dob, confirmation;
		private Button summary, back;
		
		private NewPatientConfirmation()
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

			confirmation = new Text("You have created your new patient account.\nClick below to view your summary.");
			confirmation.setFont(Font.font("Times New Roman", 14));
			confirmation.setFill(Color.BLACK);
			
			//buttons for user, summary leads to the new patient's summary with their
			//inputted information, and back takes them back to the new patient form 
			//page incase they need to go back to a field
			summary = new Button("Summary");
			back = new Button("Back");
			
			//Vertical pane to put the patient's name and dob stacked
			VBox patientInfo = new VBox(3);
			patientInfo.getChildren().addAll(title, welcome, dob);
			
			//Vertical pane to add the confirmation text and buttons
			VBox confMessage = new VBox(5);
			confMessage.getChildren().addAll(confirmation, summary, back);
			VBox.setMargin(summary, new Insets(0, 0, 0, 60));
			VBox.setMargin(back, new Insets(0, 0, 0, 75));
			
			//Border pane to put the confirmation message pane in the center of the page
			BorderPane bp = new BorderPane();
			bp.setCenter(confMessage);
			bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
			
			//put the title/welcome message vertical pane at the top left
			StackPane.setAlignment(patientInfo, Pos.TOP_LEFT);
			StackPane.setMargin(patientInfo, new Insets(10, 0, 0, 10));
			
			//add the title and border pane containing the 
			//other elements to the stack plane/this user choice screen object
			this.getChildren().add(patientInfo);
			this.getChildren().add(bp);
		}
	}

	public void start(Stage stage) 
	{
		//create the root stack pane and add user choice screen object to it
		StackPane root = new StackPane();
	
		//new patient confirmation object to place and display on the root
		NewPatientConfirmation npc = new NewPatientConfirmation();
		root.getChildren().add(npc);
	
		//set the scene/stage and put the default size as 700 x 500 with root displayed
	    Scene scene = new Scene(root, 700, 500);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void main(String[] args)
    {
		Locale.setDefault(Locale.US);
        launch(args);
    }
}
