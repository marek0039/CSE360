import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MainPatientSendMessage extends Application
{
	private class PatientSendMessage extends StackPane
	{
		//attributes of this class
		private Color mainColor;
		private Text title, welcome, dob, directions, select;
		private ComboBox medList;
		private TextArea message;
		private Button send, back;
		
		private PatientSendMessage()
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
			back = new Button("Back");
			
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
	
	public void start(Stage stage) 
	{
		//create the root stack pane and add user choice screen object to it
		StackPane root = new StackPane();
	
		//patient send message object to place and display on the root
		PatientSendMessage psm = new PatientSendMessage();
		root.getChildren().add(psm);
	
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
