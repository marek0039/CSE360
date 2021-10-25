import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MainPatientMessagePortal extends Application 
{
	private class PatientMessagePortal extends StackPane
	{
		//create attributes for this screen
		private Color mainColor;
		private Text title, welcome, dob, prevMessages, message1, message2;
		private Button sendMessage, back;
		
		private PatientMessagePortal()
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
			
			prevMessages = new Text("Previous Messages:");
			prevMessages.setFont(Font.font("Times New Roman", 14));
			prevMessages.setFill(Color.BLACK);
			
			//buttons for the user to send a message, takes them to the
			//screen where they can type out and send a message, back
			//button takes them back if they just wanted to read old messages
			sendMessage = new Button("Send Message");
			back = new Button("Back");
			
			//text objects for the messages
			message1 = new Text("Hi Adam, I wanted to check to see how your knee was feeling.\nHope taking some time off baseball helped, please let me know\nif you need to schedule a follow-up appointment with Dr. Sparky.");
			message1.setFont(Font.font("Times New Roman", 10));
			message1.setFill(Color.BLACK);
			
			message2 = new Text("Hope you are having a great summer Adam!");
			message2.setFont(Font.font("Times New Roman", 10));
			message2.setFill(Color.BLACK);
			
			//titled pane to store the previous messages
			//the string array has the message titles and messages, for the titles we will have to figure out if we 
			//want to parse these in in any way or just say 'message 1' 'message 2' etc. 
			//as defaults that don't change, for now it's the same as our mockup
			String[] messageTitles = new String[] {"03/04 Nurse Johnson", "08/15 Dr. Sparky"};
			Text[] message = new Text[] {message1, message2};
			
			//Note: the strings for the text objects of messages will be parsed from input so these are dummy messages
			TitledPane[] tp = new TitledPane[messageTitles.length];
			
			//for loop to load the messages as text nodes into the titled pane array
			//and their message titles (strings)
			for(int i = 0; i < messageTitles.length; i++)
			{
				tp[i] = new TitledPane(messageTitles[i], message[i]);
			}
			
			//accordion object to store the titled panes in a way
			//a user can collapse/open certain messages
			Accordion acc = new Accordion();
			//add the titled panes and set the expanded one
			//so the first one displayed as the message at index 0, or
			//the first message
			//Note: accordion displays as the size of the message so it's really small with
			//just 2 elements, I kept it like this in the case that someone has a large number
			//of previous messages
			acc.getPanes().addAll(tp);
			acc.setExpandedPane(tp[0]);
			
			//vertical panes to store the title/welcome text, the message label
			//and accordion, and the buttons
			VBox titleBox = new VBox(2);
			titleBox.getChildren().addAll(title, welcome, dob);
			
			VBox messageBox = new VBox(2);
			messageBox.getChildren().addAll(prevMessages, acc);
			
			VBox buttonBox = new VBox(2);
			buttonBox.getChildren().addAll(sendMessage, back);
			//insets to center the buttons below the text/other text fields aesthetic
			VBox.setMargin(sendMessage, new Insets(0, 0, 0, 40));
			VBox.setMargin(back, new Insets(0, 0, 0, 70));
			
			//horizontal pane to store the message and button contents
			HBox contents = new HBox(10);
			contents.getChildren().addAll(messageBox, buttonBox);
			
			//border pane to put the contents in the center of this 
			//stack pane
			BorderPane bp = new BorderPane();
			bp.setCenter(contents);
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
	
		//patient message portal object to place and display on the root
		PatientMessagePortal pmp = new PatientMessagePortal();
		root.getChildren().add(pmp);
	
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