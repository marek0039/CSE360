import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;	
import javafx.event.EventHandler;
import javafx.geometry.*;
import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class HealthPortal extends Application
{
	//main root pane for the whole gui
	StackPane root = new StackPane();
	
	//home screen and user choice panes displayed to all


	int currUser = 0;

	//need to keep track of the current user that is logged in
	
	public void start(Stage stage) 
	{
	    //create the root stack pane and add home screen object to it
		HomeScreen home = new HomeScreen(root, currUser);
		root.getChildren().add(home);
	
		//set the scene/stage and put the default size as 900 x 500 with root displayed
		Scene scene = new Scene(root, 900, 500);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void main(String[] args)
    {
		Locale.setDefault(Locale.US);
        launch(args);
    }
			
			private class NewPatientConfirmation extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, dob, confirmation;
				private Button patientMenu, back;
				
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
					patientMenu = new Button("Patient Menu");
					//forward event handler for patient menu button, case 7, takes user to existing patient
					//portal with their information
					Forward handler1 = new Forward(7);
					patientMenu.setOnAction(handler1);
					
					back = new Button("Back");
					//back button forward event handler, case 6, go back to new patient form screen
					Forward handler2 = new Forward(6);
					back.setOnAction(handler2);
					
					//Vertical pane to put the patient's name and dob stacked
					VBox patientInfo = new VBox(3);
					patientInfo.getChildren().addAll(title, welcome, dob);
					
					//Vertical pane to add the confirmation text and buttons
					VBox confMessage = new VBox(5);
					confMessage.getChildren().addAll(confirmation, patientMenu, back);
					VBox.setMargin(patientMenu, new Insets(0, 0, 0, 60));
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
					//forward event handler for first go button, case 9, takes user to
					//update their contact info/personal info screen
					Forward handler1 = new Forward(9);
					go1.setOnAction(handler1);
					
					go2 = new Button("Go");
					//forward event handler for second go button, case 12, takes user to
					//view previous visit summary screen 
					Forward handler2 = new Forward(12);
					go2.setOnAction(handler2);
					
					go3 = new Button("Go");
					//forward event handler for third go button, case 13, takes user to
					//message portal screen
					Forward handler3 = new Forward(13);
					go3.setOnAction(handler3);
					
					
					back = new Button("Back");
					//forward event handler for back button, case 8, takes user back
					//to existing patient log on screen 
					Forward handler4 = new Forward(8);
					back.setOnAction(handler4);
					
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
			
			private class PatientUpdateInfo extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, dob, directions, email;
				private Text phone, medHis, pharmacy, mailAddress, insurance, insNum, finish;
				private TextField emailField, pharmField, numField, insField, insNumField;
				private TextField mailField1, mailField2, mailField3, mailField4;
				private TextArea medHisField;
				private Button submit, back;
						
				private PatientUpdateInfo()
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
					
					//directions highlighting that if the existing patient does not 
					//change a field and leaves it blank when they submit, 
					//the existing information will be kept
					directions = new Text("Update your information, if no change is made to a particular area, your current information will remain.");
					directions.setFont(Font.font("Times New Roman", 14));
					directions.setFill(Color.BLACK);
					
					//the text indicating the fields the user can change
					email = new Text("Preferred Email Address:");
					email.setFont(Font.font("Times New Roman", 12));
					email.setFill(Color.BLACK);
					
					phone = new Text("Preferred Phone Number:");
					phone.setFont(Font.font("Times New Roman", 12));
					phone.setFill(Color.BLACK);
					
					medHis = new Text("(Relevant)Previous Medical History:");
					medHis.setFont(Font.font("Times New Roman", 12));
					medHis.setFill(Color.BLACK);
					
					pharmacy = new Text("Preferred Pharmacy:");
					pharmacy.setFont(Font.font("Times New Roman", 12));
					pharmacy.setFill(Color.BLACK);
					
					mailAddress = new Text("Preferred Mailing Address:");
					mailAddress.setFont(Font.font("Times New Roman", 12));
					mailAddress.setFill(Color.BLACK);
					
					insurance = new Text("Insurance Company:");
					insurance.setFont(Font.font("Times New Roman", 12));
					insurance.setFill(Color.BLACK);
					
					insNum = new Text("Insurance Number:");
					insNum.setFont(Font.font("Times New Roman", 12));
					insNum.setFill(Color.BLACK);
					
					finish = new Text("All finished? Press submit below:");
					finish.setFont(Font.font("Times New Roman", 12));
					finish.setFill(Color.BLACK);
					
					//text fields for the areas which just require a text field
					//the previous medical history uses a text area
					emailField = new TextField();
					
					pharmField = new TextField();
					
					numField = new TextField();
					
					insField = new TextField();
					insNumField = new TextField();
					
					mailField1 = new TextField();
					mailField1.setPromptText("Line 1");
					
					mailField2 = new TextField();
					mailField2.setPromptText("Line 2");
					
					mailField3 = new TextField();
					mailField3.setPromptText("City, State");
					
					mailField4 = new TextField();
					mailField4.setPromptText("Zip Code");
					
					//text area for the previous
					//medical history because it needs a larger space
					//rather than just a small text field
					medHisField = new TextArea();
					
					//buttons that allow the user to submit the form, Note:
					//the user does NOT have to have all fields filled out on this form
					//for submit to be utilized, this will be taken care of in event handler
					//back button takes user back to previous page
					submit = new Button("Submit");
					//forward event handler for submit button after patient had updated
					//their personal information, they will be taken to confirmation page, case 10
					Forward handler1 = new Forward(10);
					submit.setOnAction(handler1);
					
					back = new Button("Patient Menu");
					//forward event handler for the back button takes user back to existing
					//patient portal screen, case 11
					Forward handler2 = new Forward(11);
					back.setOnAction(handler2);
					
					//Vertical panes to store each field with its title
					VBox emailBox = new VBox(2);
					emailBox.getChildren().addAll(email, emailField);
					
					VBox phoneBox = new VBox(2);
					phoneBox.getChildren().addAll(phone, numField);
					
					VBox medHisBox = new VBox(2);
					medHisBox.getChildren().addAll(medHis, medHisField);
					
					VBox pharmBox = new VBox(2);
					pharmBox.getChildren().addAll(pharmacy, pharmField);
					
					VBox mailBox = new VBox(2);
					mailBox.getChildren().addAll(mailAddress, mailField1, mailField2, mailField3, mailField4);
					
					VBox insBox = new VBox(2);
					insBox.getChildren().addAll(insurance, insField);
					
					VBox insNumBox = new VBox(2);
					insNumBox.getChildren().addAll(insNum, insNumField);
					
					VBox finishBox = new VBox(2);
					finishBox.getChildren().addAll(finish, submit, back);
					//insets to center the buttons below the text/other text fields aesthetic
					VBox.setMargin(submit, new Insets(0, 0, 0, 40));
					VBox.setMargin(back, new Insets(0, 0, 0, 37));
					
					//Vertical panes to store the various columns of information
					//mimicks a grid pane but is more aesthetically pleasing
					VBox column1 = new VBox(8);
					column1.getChildren().addAll(emailBox, pharmBox, insBox, insNumBox);
					
					VBox column2 = new VBox(8);
					column2.getChildren().addAll(phoneBox, mailBox, finishBox);
					
					//add these to an HBox, note that medical history doesn't have a VBox since
					//it is by itself in a column already
					HBox contents = new HBox(5);
					contents.getChildren().addAll(column1, column2, medHisBox);
					
					//Vertical pane for the top of the screen for the title and other texts
					VBox titleBox = new VBox(3);
					titleBox.getChildren().addAll(title, welcome, dob, directions);
					
					//Border pane to put the contents pane in the center of the page
					BorderPane bp = new BorderPane();
					bp.setCenter(contents);
					bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
					
					//add the title and its accompanying texts to the top
					//left of this stack pane
					StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
					StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
					this.getChildren().add(titleBox);
					
					//add the border pane to this stack pane
					this.getChildren().add(bp);	
				} //end constructor
			} //end class
			
			private class UpdateInfoConfirmation extends StackPane
			{
				//create attributes for this screen
						private Color mainColor;
						private Text title, welcome, dob, confirmation;
						private Button summary, back;
						
				private UpdateInfoConfirmation()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//Note: these will need to be read in from the patient logging on's
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome, Patient Adam Samler");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);

					confirmation = new Text("You have updated your contact information successfully.\n\t\tClick below to view your summary.");
					confirmation.setFont(Font.font("Times New Roman", 14));
					confirmation.setFill(Color.BLACK);
					
					//buttons for user, summary leads to the new patient's summary with their
					//inputted information, and back takes them back to the new patient form 
					//page incase they need to go back to a field
					summary = new Button("Summary");
					//forward event handler for taking patient to view summary
					//after theyve updated their contact information, case 12
					Forward handler1 = new Forward(12);
					summary.setOnAction(handler1);
					
					back = new Button("Patient Menu");
					//forward event handler for back button takes user to
					//update the existing patient portal home screen
					Forward handler2 = new Forward(11);
					back.setOnAction(handler2);
					
					//Vertical pane to put the patient's name and dob stacked
					VBox patientInfo = new VBox(3);
					patientInfo.getChildren().addAll(title, welcome, dob);
					
					//Vertical pane to add the confirmation text and buttons
					VBox confMessage = new VBox(5);
					confMessage.getChildren().addAll(confirmation, summary, back);
					VBox.setMargin(summary, new Insets(0, 0, 0, 110));
					VBox.setMargin(back, new Insets(0, 0, 0, 107));
					
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
				} //end constructor
			} //end class
			
			private class PatientSummary extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, dob, contactInfo, email;
				private Text phone, medHisTitle, medHis, pharmacy, insurance, doctorNotes, notes;
				private Text date1, date2, height, weight, bloodPressure, bodyTemp, allergies, prescription;
				private Text height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2;
				private Button back;
				
				private PatientSummary()
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
					
					//labels the contact information
					contactInfo = new Text("Contact Information:");
					contactInfo.setFont(Font.font("Times New Roman", 14));
					contactInfo.setFill(Color.BLACK);
					
					//these will be parsed in from the patient's profile, Note: that
					//mailing address and insurance number are not included on summary
					//screen, but the rest of the information is
					phone = new Text("Phone: 123-456-7890");
					phone.setFont(Font.font("Times New Roman", 14));
					phone.setFill(Color.BLACK);
					
					email = new Text("Email: asamler@yahoo.com");
					email.setFont(Font.font("Times New Roman", 14));
					email.setFill(Color.BLACK);
					
					insurance = new Text("Insurance: Aetna");
					insurance.setFont(Font.font("Times New Roman", 14));
					insurance.setFill(Color.BLACK);
					
					pharmacy = new Text("Pharmacy: CVS #602");
					pharmacy.setFont(Font.font("Times New Roman", 14));
					pharmacy.setFill(Color.BLACK);
					
					//labels previous medical history
					medHisTitle = new Text("Previous Medical History:");
					medHisTitle.setFont(Font.font("Times New Roman", 14));
					medHisTitle.setFill(Color.BLACK);
					
					medHis = new Text("-Surgery, right foot, Jan. 2012\n-HepC Vaccine, Oct. 2020");
					medHis.setFont(Font.font("Times New Roman", 14));
					medHis.setFill(Color.BLACK);
					
					//these are dummy dates for fake previous visits
					//Note: we need to decide how we will keep track of
					//visit dates
					date1 = new Text("Visit Date: 08/15/2020");
					date1.setFont(Font.font("Times New Roman", 14));
					date1.setFill(Color.BLACK);
					
					date2 = new Text("Visit Date: 03/04/2021");
					date2.setFont(Font.font("Times New Roman", 14));
					date2.setFill(Color.BLACK);
					
					//these are nurse's categories of what they take at each appointment
					//there are 2 of each because this screen displays 2 dummy previous visits
					//these will all be parsed in, ex. Height: + [string that holds height] from nurse's input
					height = new Text("Height: 5 feet 7 inches");
					height.setFont(Font.font("Times New Roman", 14));
					height.setFill(Color.BLACK);
					
					height2 = new Text("Height: 5 feet 9 inches, grew 2 inches");
					height2.setFont(Font.font("Times New Roman", 14));
					height2.setFill(Color.BLACK);
				
					weight = new Text("Weight: 120 lbs");
					weight.setFont(Font.font("Times New Roman", 14));
					weight.setFill(Color.BLACK);
					
					weight2 = new Text("Weight: 122 lbs, gained 2 lbs");
					weight2.setFont(Font.font("Times New Roman", 14));
					weight2.setFill(Color.BLACK);
					
					bloodPressure = new Text("Blood Pressure: 120/80mmHg");
					bloodPressure.setFont(Font.font("Times New Roman", 14));
					bloodPressure.setFill(Color.BLACK);
					
					bloodPressure2 = new Text("Blood Pressure: 120/80mmHg");
					bloodPressure2.setFont(Font.font("Times New Roman", 14));
					bloodPressure2.setFill(Color.BLACK);
					
					bodyTemp = new Text("Body Temperature: 96.8 degrees");
					bodyTemp.setFont(Font.font("Times New Roman", 14));
					bodyTemp.setFill(Color.BLACK);
					
					bodyTemp2 = new Text("Body Temperature: 96.8 degrees");
					bodyTemp2.setFont(Font.font("Times New Roman", 14));
					bodyTemp2.setFill(Color.BLACK);
					
					allergies = new Text("Allergies: Peanuts");
					allergies.setFont(Font.font("Times New Roman", 14));
					allergies.setFill(Color.BLACK);
			
					allergies2 = new Text("Allergies: Peanuts");
					allergies2.setFont(Font.font("Times New Roman", 14));
					allergies2.setFill(Color.BLACK);
					
					prescription = new Text("Prescription: Epinephrine 'EpiPen'");
					prescription.setFont(Font.font("Times New Roman", 14));
					prescription.setFill(Color.BLACK);
					
					prescription2 = new Text("Prescription: Epinephrine 'EpiPen'");
					prescription2.setFont(Font.font("Times New Roman", 14));
					prescription2.setFill(Color.BLACK);
					
					//doctor's notes will be parsed in as well, this is the label for them
					doctorNotes = new Text("Doctor/Nurse's Notes:");
					doctorNotes.setFont(Font.font("Times New Roman", 14));
					doctorNotes.setFill(Color.BLACK); 
					
					//these will be taken from the doctor's notes input as a string
					notes = new Text("-03/04 - slight pain in knee, likely baseball, tylenol referral");
					notes.setFont(Font.font("Times New Roman", 14));
					notes.setFill(Color.BLACK);
					
					//back button takes the patient back to their portal screen from viewing their summary
					back = new Button("Patient Menu");
					//forward event handler for the back button takes user back to existing
					//patient portal screen, case 11
					Forward handler = new Forward(11);
					back.setOnAction(handler);
					
					//vertical panes for each group of information on the page, to be placed
					//in column vertical panes and then in a horizontal pane for display purposes
					VBox nameBox = new VBox(2);
					nameBox.getChildren().addAll(welcome, dob);
					
					VBox contactBox = new VBox(2);
					contactBox.getChildren().addAll(contactInfo, phone, email);
					
					VBox insBox = new VBox(2);
					insBox.getChildren().addAll(insurance, pharmacy);
					
					VBox prevMedBox = new VBox(2);
					prevMedBox.getChildren().addAll(medHisTitle, medHis);
					
					//put a lot of space between the dates so they 
					//align with their corresponding height/weight etc.
					VBox visitBox = new VBox(100);
					visitBox.getChildren().addAll(date1, date2);
					
					VBox visit1Box = new VBox(2);
					visit1Box.getChildren().addAll(height, weight, bloodPressure, bodyTemp, allergies, prescription);

					VBox visit2Box = new VBox(2);
					visit2Box.getChildren().addAll(height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2);
					
					//back button is in this box as it is displayed low on the screen and has some
					//insets for aesthetic
					VBox docBox = new VBox(2);
					docBox.getChildren().addAll(doctorNotes, notes, back);
					VBox.setMargin(back, new Insets(20,0,0,200));
					
					//vertical panes for each column
					VBox column1 = new VBox(8);
					column1.getChildren().addAll(nameBox, contactBox, insBox, prevMedBox);
					
					VBox column2 = new VBox(8);
					column2.getChildren().addAll(visitBox);
					
					VBox column3 = new VBox(16);
					column3.getChildren().addAll(visit1Box, visit2Box);
					
					//horizontal pane to store columns
					HBox colBox = new HBox(8);
					colBox.getChildren().addAll(column1, column2, column3);
					
					//border pane to put horizontal pane in the center, and the doctor's notes/button
					//on the bottom of the screen
					
					//border pane to center the grid pane contents
					BorderPane bp = new BorderPane();
					bp.setCenter(colBox);
					bp.setBottom(docBox);
					bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
					
					//add the title to the top left of this stack pane
					StackPane.setAlignment(title, Pos.TOP_LEFT);
					StackPane.setMargin(title, new Insets(10, 0, 0, 10));
					this.getChildren().add(title);
					
					//add the border pane to this stack pane
					this.getChildren().add(bp);
				} //end constructor
			} //end class
			
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
					//forward event handler for the patient to go to the message screen
					//where they can actually type out and send their message
					Forward handler1 = new Forward(14);
					sendMessage.setOnAction(handler1);
					
					back = new Button("Patient Menu");
					//forward event handler for the back button takes user back to existing
					//patient portal screen, case 11
					Forward handler2 = new Forward(11);
					back.setOnAction(handler2);
					
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
					VBox.setMargin(back, new Insets(0, 0, 0, 40));
					
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
					//forward event handler for the patient to go to message confirmation page
					//after theyve sent the message
					Forward handler1 = new Forward(15);
					send.setOnAction(handler1);
					
					back = new Button("Back");
					//forward event handler for patient to back to the message portal screen, case 13
					Forward handler2 = new Forward(13);
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
					back = new Button("Patient Menu");
					//forward event handler for the back button takes user back to existing
					//patient portal screen, case 11
					Forward handler = new Forward(11);
					back.setOnAction(handler);
					
					//vertical box to store title and welcome contents
					VBox titleBox = new VBox(3);
					titleBox.getChildren().addAll(title, welcome, dob);
					
					//vertical box to store confirmation message and back button
					VBox confMessage = new VBox(5);
					confMessage.getChildren().addAll(confirmation, back);
					VBox.setMargin(back, new Insets(10, 0, 0, 110));
					
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
			
			private class DocSelectPatient extends StackPane
			{
				//attributes of this class to be displayed on the pane
				private Color mainColor;
				private Text title, welcome, select, patient;
				private ComboBox patientList;
				private Button go, logout;
				
				private DocSelectPatient() 
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling who is logged in, we need to figure out
					//how we want to pull this from the database
					welcome = new Text("Welcome in, Doctor Sparky");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					//black text labeling the fields needed to log on
					select = new Text("Select a patient from the list below:");
					select.setFont(Font.font("Times New Roman", 14));
					select.setFill(Color.BLACK);
					
					patient = new Text("Patient:");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					//combobox with list of patients
					//needs to be added from the database
					//dummy list as filler right now
					patientList = new ComboBox();
					patientList.getItems().addAll("Adam Samler", "Jane Doe", "Johnny Appleseed");	
					
					//buttons to allow the user to go to the patient's page or log out (back to med prof
					//log in screen)
					go = new Button("Go");
					//TO BE HANDLED W DATABASE DATA
					//forward handler for going to default dummy patient summary page
					Forward handler1 = new Forward(18);
					go.setOnAction(handler1);
					
					logout = new Button("Log Out");
					//forward handler for taking user back to medical professional log
					//in page, case 17
					Forward handler2 = new Forward(17);
					logout.setOnAction(handler2);
					
					//Vertical pane to put the title and existing patient label together
					VBox titleBox = new VBox(5);
					titleBox.getChildren().addAll(title, welcome, select);
					
					//Vertical pane to put the log on requirements in the center of the page
					VBox centerElements = new VBox(8);
					centerElements.getChildren().addAll(patient, patientList, go, logout);
					//insets to center the buttons below the text/other text fields aesthetic
					VBox.setMargin(go, new Insets(0, 0, 0, 60));
					VBox.setMargin(logout, new Insets(0, 0, 0, 45));
					
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
		} //end doc choose patient class
			
			private class DocPatientSummary extends StackPane
			{
				private Color mainColor;
				private Text title, welcome, dob, contactInfo, email;
				private Text phone, medHisTitle, medHis, pharmacy, insurance, doctorNotes, pName;
				private Text date1, date2, height, weight, bloodPressure, bodyTemp, allergies, prescription;
				private Text height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2;
				private Text sendMessage, newMeds;
				private TextField notesField, presField;
				private Button back, go , submit;
				
				private DocPatientSummary()
				{
					//create attributes for this screen

						//establish color Falu Red as done on home screen
						mainColor = Color.rgb(128,32,32);
						
						//title and its color/size/font
						title = new Text("SunDevil Pediatric Health Portal");
						title.setFont(Font.font("Plantagenet Cherokee", 23));
						title.setFill(mainColor);
						
						//black text labeling the name of the patient and dob of the patient
						//as well as the doctor who is logged on currently
						//Note: these will need to be read in from patient list
						//text fields/areas so they will end up being parsed input rather than this dummy default text
						welcome = new Text("Welcome in, Doctor Sparky");
						welcome.setFont(Font.font("Times New Roman", 14));
						welcome.setFill(Color.BLACK);
						
						pName = new Text("Patient: Adam Samler");
						pName.setFont(Font.font("Times New Roman", 14));
						pName.setFill(Color.BLACK);
						
						dob = new Text("DOB: 01/09/2007");
						dob.setFont(Font.font("Times New Roman", 14));
						dob.setFill(Color.BLACK);
						
						//labels the contact information
						contactInfo = new Text("Contact Information:");
						contactInfo.setFont(Font.font("Times New Roman", 14));
						contactInfo.setFill(Color.BLACK);
						
						//these will be parsed in from the patient's profile, Note: that
						//mailing address and insurance number are not included on summary
						//screen, but the rest of the information is
						phone = new Text("Phone: 123-456-7890");
						phone.setFont(Font.font("Times New Roman", 14));
						phone.setFill(Color.BLACK);
						
						email = new Text("Email: asamler@yahoo.com");
						email.setFont(Font.font("Times New Roman", 14));
						email.setFill(Color.BLACK);
						
						insurance = new Text("Insurance: Aetna");
						insurance.setFont(Font.font("Times New Roman", 14));
						insurance.setFill(Color.BLACK);
						
						pharmacy = new Text("Pharmacy: CVS #602");
						pharmacy.setFont(Font.font("Times New Roman", 14));
						pharmacy.setFill(Color.BLACK);
						
						//labels previous medical history
						medHisTitle = new Text("Previous Medical History:");
						medHisTitle.setFont(Font.font("Times New Roman", 14));
						medHisTitle.setFill(Color.BLACK);
						
						medHis = new Text("-Surgery, right foot, Jan. 2012\n-HepC Vaccine, Oct. 2020");
						medHis.setFont(Font.font("Times New Roman", 14));
						medHis.setFill(Color.BLACK);
						
						//these are dummy dates for fake previous visits
						//Note: we need to decide how we will keep track of
						//visit dates
						date1 = new Text("Visit Date: 08/15/2020");
						date1.setFont(Font.font("Times New Roman", 14));
						date1.setFill(Color.BLACK);
						
						date2 = new Text("Visit Date: 03/04/2021");
						date2.setFont(Font.font("Times New Roman", 14));
						date2.setFill(Color.BLACK);
						
						//these are nurse's categories of what they take at each appointment
						//there are 2 of each because this screen displays 2 dummy previous visits
						//these will all be parsed in, ex. Height: + [string that holds height] from nurse's input
						height = new Text("Height: 5 feet 7 inches");
						height.setFont(Font.font("Times New Roman", 14));
						height.setFill(Color.BLACK);
						
						height2 = new Text("Height: 5 feet 9 inches, grew 2 inches");
						height2.setFont(Font.font("Times New Roman", 14));
						height2.setFill(Color.BLACK);
					
						weight = new Text("Weight: 120 lbs");
						weight.setFont(Font.font("Times New Roman", 14));
						weight.setFill(Color.BLACK);
						
						weight2 = new Text("Weight: 122 lbs, gained 2 lbs");
						weight2.setFont(Font.font("Times New Roman", 14));
						weight2.setFill(Color.BLACK);
						
						bloodPressure = new Text("Blood Pressure: 120/80mmHg");
						bloodPressure.setFont(Font.font("Times New Roman", 14));
						bloodPressure.setFill(Color.BLACK);
						
						bloodPressure2 = new Text("Blood Pressure: 120/80mmHg");
						bloodPressure2.setFont(Font.font("Times New Roman", 14));
						bloodPressure2.setFill(Color.BLACK);
						
						bodyTemp = new Text("Body Temperature: 96.8 degrees");
						bodyTemp.setFont(Font.font("Times New Roman", 14));
						bodyTemp.setFill(Color.BLACK);
						
						bodyTemp2 = new Text("Body Temperature: 96.8 degrees");
						bodyTemp2.setFont(Font.font("Times New Roman", 14));
						bodyTemp2.setFill(Color.BLACK);
						
						allergies = new Text("Allergies: Peanuts");
						allergies.setFont(Font.font("Times New Roman", 14));
						allergies.setFill(Color.BLACK);
				
						allergies2 = new Text("Allergies: Peanuts");
						allergies2.setFont(Font.font("Times New Roman", 14));
						allergies2.setFill(Color.BLACK);
						
						prescription = new Text("Prescription: Epinephrine 'EpiPen'");
						prescription.setFont(Font.font("Times New Roman", 14));
						prescription.setFill(Color.BLACK);
						
						prescription2 = new Text("Prescription: Epinephrine 'EpiPen'");
						prescription2.setFont(Font.font("Times New Roman", 14));
						prescription2.setFill(Color.BLACK);
						
						//doctor's/nurse's notes label
						doctorNotes = new Text("Doctor/Nurse's Notes:");
						doctorNotes.setFont(Font.font("Times New Roman", 14));
						doctorNotes.setFill(Color.BLACK); 
					
						newMeds = new Text("Prescribed Medication:");
						newMeds.setFont(Font.font("Times New Roman", 14));
						newMeds.setFill(Color.BLACK); 
						
						sendMessage = new Text("Send/View Messages:");
						sendMessage.setFont(Font.font("Times New Roman", 14));
						sendMessage.setFill(Color.BLACK); 
						
						//textfields
						notesField = new TextField();
						presField = new TextField();
						
						//back button takes the user to the choose a patient page
						//go button takes user to send a message to the patient they are currently on
						//submit allows user to send in notes/medication added to the page via the textfields
						back = new Button("Back");
						go = new Button("Go");
						submit = new Button("Submit");
						
						//vertical panes for each group of information on the page, to be placed
						//in column vertical panes and then in a horizontal pane for display purposes
						VBox nameBox = new VBox(2);
						nameBox.getChildren().addAll(pName, dob);
						
						VBox contactBox = new VBox(2);
						contactBox.getChildren().addAll(contactInfo, phone, email);
						
						VBox insBox = new VBox(2);
						insBox.getChildren().addAll(insurance, pharmacy);
						
						VBox prevMedBox = new VBox(2);
						prevMedBox.getChildren().addAll(medHisTitle, medHis);
						
						//put a lot of space between the dates so they 
						//align with their corresponding height/weight etc.
						VBox visitBox = new VBox(100);
						visitBox.getChildren().addAll(date1, date2);
						
						VBox visit1Box = new VBox(2);
						visit1Box.getChildren().addAll(height, weight, bloodPressure, bodyTemp, allergies, prescription);

						VBox visit2Box = new VBox(2);
						visit2Box.getChildren().addAll(height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2);
						
						//back button is in this box as it is displayed low on the screen and has some
						//insets for aesthetic
						VBox docBox = new VBox(5);
						docBox.getChildren().addAll(doctorNotes, notesField, newMeds, presField, submit);
						VBox.setMargin(submit, new Insets(10,0,0,200));
						
						//vertical panes for each column
						VBox column1 = new VBox(8);
						column1.getChildren().addAll(nameBox, contactBox, insBox, prevMedBox);
						
						VBox column2 = new VBox(8);
						column2.getChildren().addAll(visitBox);
						
						VBox column3 = new VBox(16);
						column3.getChildren().addAll(visit1Box, visit2Box);
						
						//vbox for title and doctor greeting
						VBox titleBox = new VBox(2);
						titleBox.getChildren().addAll(title, welcome);
						
						//vbox for send message label and buttons
						VBox messageBox = new VBox(2);
						messageBox.getChildren().addAll(sendMessage, go, back);
						VBox.setMargin(go, new Insets(0,0,0,40));
						VBox.setMargin(back, new Insets(0,0,0,37));
						
						//horizontal pane to store columns
						HBox colBox = new HBox(8);
						colBox.getChildren().addAll(column1, column2, column3);
						
						//horizontal pane to put the doctors notes/ button options at the bottom of the screen 
						HBox bottomBox = new HBox(40);
						bottomBox.getChildren().addAll(messageBox, docBox);
						
						//border pane to put horizontal pane in the center, and the doctor's notes/button
						//on the bottom of the screen
						//border pane to center the grid pane contents
						BorderPane bp = new BorderPane();
						bp.setCenter(colBox);
						bp.setBottom(bottomBox);
						bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
						
						//add the title to the top left of this stack pane
						StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
						StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
						this.getChildren().add(titleBox);
						
						//add the border pane to this stack pane
						this.getChildren().add(bp);
					} //end constructor
				} //end class
			
			private class DocMemoConfirmation extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, patient, dob, confirmation;
				private Button patientChoice;
				
				private DocMemoConfirmation()
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
					welcome = new Text("Welcome in, Doctor Sparky");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Patient: Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);

					confirmation = new Text("Your memos have been saved for the above patient.");
					confirmation.setFont(Font.font("Times New Roman", 14));
					confirmation.setFill(Color.BLACK);
					
					//buttons for user, summary leads to the new patient's summary with their
					//inputted information, and back takes them back to the new patient form 
					//page incase they need to go back to a field
					patientChoice = new Button("Patient Choice Home");
					
					//Vertical pane to put the patient's name and dob stacked
					VBox patientInfo = new VBox(3);
					patientInfo.getChildren().addAll(title, welcome, patient, dob);
					
					//Vertical pane to add the confirmation text and buttons
					VBox confMessage = new VBox(5);
					confMessage.getChildren().addAll(confirmation, patientChoice);
					VBox.setMargin(patientChoice, new Insets(0, 0, 0, 60));
					
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
				} //end constructor
			} //end doc memo confirmation page
			
			private class DocMessagePortal extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, patient, dob, prevMessages, message1, message2;
				private Button sendMessage, back;
				
				private DocMessagePortal()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//as well as which doctor is logged on currently
					//Note: these will need to be read in from the patient list the doctor chose from
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Doctor Sparky");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Patient: Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
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
					message1 = new Text("Hi Adam,\n\nThanks for visiting me today, please message back if you have any questions for me.\n\nHave a great day!");
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
					Text[] message = new Text[] {message2, message1};
					
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
					titleBox.getChildren().addAll(title, welcome, patient, dob);
					
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
			} //end doctor message portal class
			
			private class DocSendMessage extends StackPane
			{
				//attributes of this class
				private Color mainColor;
				private Text title, welcome, patient, dob, directions;
				private TextArea message;
				private Button send, back;
						
				private DocSendMessage()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//as well as which doctor is currently logged on 
					//Note: these will need to be read in from the patient list the doctor chose from
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Doctor Sparky");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Patient: Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);
					
					directions = new Text("Please type your message below:");
					directions.setFont(Font.font("Times New Roman", 14));
					directions.setFill(Color.BLACK);
					
					//text area to store patient message from when they hit the send button
					message = new TextArea();
					
					//buttons for the user to send their message IF they have put message content, 
					//and they have selected a medical professional, or go back to the previous page
					send = new Button("Send");
					back = new Button("Back");
					
					//vertical boxes to store the title and it's contents and the middle of the page contents
					VBox titleBox = new VBox(2);
					titleBox.getChildren().addAll(title, welcome, patient, dob);
					
					VBox messageBox = new VBox(8);
					messageBox.getChildren().addAll(directions, message, send, back);
					//insets to center the buttons below the text/other text fields aesthetic
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
			} //end doc send message class
			
			private class DocMessageConfirmation extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, patient, dob, confirmation;
				private Button back;
				
				private DocMessageConfirmation()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//and doctor that is logged on currently
					//Note: these will need to be read in from the patient list the doctor chose from
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Doctor Sparky");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Patient: Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);

					confirmation = new Text("\t\tYour message has been sent!\nSelect the button to go back to your portal home screen.");
					confirmation.setFont(Font.font("Times New Roman", 14));
					confirmation.setFill(Color.BLACK);
					
					//button for the user to go back to their home screen where they are logged
					//on as a doctor and can select a patient from the patient list
					back = new Button("Back");
					
					//vertical box to store title and welcome contents
					VBox titleBox = new VBox(3);
					titleBox.getChildren().addAll(title, welcome, patient, dob);
					
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
			} //end doc message confirmation class
			
			private class NurseSelectPatient extends StackPane
			{
				//attributes of this class to be displayed on the pane
				private Color mainColor;
				private Text title, welcome, select, patient, create;
				private ComboBox patientList;
				private Button go, createButton, logout;
						
				private NurseSelectPatient()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling who is logged in, we need to figure out
					//how we want to pull this from the database
					welcome = new Text("Welcome in, Nurse Jackson");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					//black text labeling the fields needed to log on
					select = new Text("Select a patient from the list below, or press create to make a new patient:");
					select.setFont(Font.font("Times New Roman", 14));
					select.setFill(Color.BLACK);
					
					patient = new Text("Patient:");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					create = new Text("Create New Patient:");
					create.setFont(Font.font("Times New Roman", 14));
					create.setFill(Color.BLACK);
					
					//combobox with list of patients
					//needs to be added from the database
					//dummy list as filler right now
					patientList = new ComboBox();
					patientList.getItems().addAll("Adam Samler", "Jane Doe", "Johnny Appleseed");
					
					//buttons to allow the user to go to the patient's page or log out (back to med prof
					//log in screen) as well as create button to take user to create a new patient
					go = new Button("Go");
					createButton = new Button("Create");
					logout = new Button("Log Out");
					
					//Vertical pane to put the title and existing patient label together
					VBox titleBox = new VBox(5);
					titleBox.getChildren().addAll(title, welcome, select);
					
					//Vertical pane to put the log on requirements in the center of the page
					VBox centerElements = new VBox(8);
					centerElements.getChildren().addAll(patient, patientList, go, create, createButton, logout);
					//insets to center the buttons below the text/other text fields aesthetic
					VBox.setMargin(go, new Insets(0, 0, 0, 60));
					VBox.setMargin(createButton, new Insets(0, 0, 0, 50));
					VBox.setMargin(logout, new Insets(0, 0, 0, 45));
					
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
			} //end nurse select patient class
			
			private class NurseCreatePatient extends StackPane
			{
				private Color mainColor;
				private Text title, welcome, form, required, fName, lName, dob, email;
				private Text phone, medHis, pharmacy, mailAddress, insurance, insNum, finish;
				private TextField fNameField, lNameField, emailField, pharmField, numField, insField, insNumField;
				private TextField mailField1, mailField2, mailField3, mailField4;
				private TextArea medHisField;
				private DatePicker dobPicker;
				private Button submit, back;
			
				private NurseCreatePatient()
				{
				//establish color Falu Red as done on home screen
				mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling that this is a new patient form,
					//and red text labeling what is required for the patient
					//to fill in
					//nurse welcome which will be read in when the nurse logs in
					welcome = new Text("Welcome in, Nurse Jackson");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					form = new Text("New Patient Form");
					form.setFont(Font.font("Times New Roman", 14));
					form.setFill(Color.BLACK);
					
					form = new Text("New Patient Form");
					form.setFont(Font.font("Times New Roman", 14));
					form.setFill(Color.BLACK);
					
					required = new Text("*Required for submission");
					required.setFont(Font.font("Times New Roman", 12));
					required.setFill(Color.RED);
					
					//all the different texts labeling their various text field/area
					//for the user to fill out the specified information
					fName = new Text("Patient First Name:");
					fName.setFont(Font.font("Times New Roman", 12));
					fName.setFill(Color.BLACK);
					
					lName = new Text("Patient Last Name:");
					lName.setFont(Font.font("Times New Roman", 12));
					lName.setFill(Color.BLACK);
					
					dob = new Text("Patient Date of Birth:");
					dob.setFont(Font.font("Times New Roman", 12));
					dob.setFill(Color.BLACK);
					
					email = new Text("Preferred Email Address:");
					email.setFont(Font.font("Times New Roman", 12));
					email.setFill(Color.BLACK);
					
					phone = new Text("Preferred Phone Number:");
					phone.setFont(Font.font("Times New Roman", 12));
					phone.setFill(Color.BLACK);
					
					medHis = new Text("(Relevant)Previous Medical History:");
					medHis.setFont(Font.font("Times New Roman", 12));
					medHis.setFill(Color.BLACK);
					
					pharmacy = new Text("Preferred Pharmacy:");
					pharmacy.setFont(Font.font("Times New Roman", 12));
					pharmacy.setFill(Color.BLACK);
					
					mailAddress = new Text("Preferred Mailing Address:");
					mailAddress.setFont(Font.font("Times New Roman", 12));
					mailAddress.setFill(Color.BLACK);
					
					insurance = new Text("Insurance Company:");
					insurance.setFont(Font.font("Times New Roman", 12));
					insurance.setFill(Color.BLACK);
					
					insNum = new Text("Insurance Number:");
					insNum.setFont(Font.font("Times New Roman", 12));
					insNum.setFill(Color.BLACK);
					
					finish = new Text("All finished? Press submit below:");
					finish.setFont(Font.font("Times New Roman", 12));
					finish.setFill(Color.BLACK);
					
					//text fields for their corresponding items
					//the medical history, date of birth
					//are NOT text fields
					
					//Note: I added the prompt text for the mailing address
					//and the * to all required fields as specified, the * is
					//made red by the CSS inside of the setStyle method
					fNameField = new TextField();
					fNameField.setPromptText("*");
					fNameField.setStyle("-fx-prompt-text-fill: red");
					
					lNameField = new TextField();
					lNameField.setPromptText("*");
					lNameField.setStyle("-fx-prompt-text-fill: red");
					
					emailField = new TextField();
					emailField.setPromptText("*");
					emailField.setStyle("-fx-prompt-text-fill: red");
					
					pharmField = new TextField();
					pharmField.setPromptText("*");
					pharmField.setStyle("-fx-prompt-text-fill: red");
					
					numField = new TextField();
					numField.setPromptText("*");
					numField.setStyle("-fx-prompt-text-fill: red");
					
					insField = new TextField();
					insField.setPromptText("*");
					insField.setStyle("-fx-prompt-text-fill: red");
					
					insNumField = new TextField();
					insNumField.setPromptText("*");
					insNumField.setStyle("-fx-prompt-text-fill: red");
					
					mailField1 = new TextField();
					mailField1.setPromptText("* Line 1");
					mailField1.setStyle("-fx-prompt-text-fill: red");
					
					mailField2 = new TextField();
					mailField2.setPromptText("Line 2");
					
					mailField3 = new TextField();
					mailField3.setPromptText("* City, State");
					mailField3.setStyle("-fx-prompt-text-fill: red");
					
					mailField4 = new TextField();
					mailField4.setPromptText("* Zip Code");
					mailField4.setStyle("-fx-prompt-text-fill: red");
					
					//text area for the previous
					//medical history because it needs a larger space
					//rather than just a small text field
					medHisField = new TextArea();
					medHisField.setPromptText("*");
					medHisField.setStyle("-fx-prompt-text-fill: red");
					
					//date picker object in order for the patient
					//to pick their date of birth most effectively
					dobPicker = new DatePicker();
					dobPicker.setPromptText("*");
					//Note: this isn't working to set the prompt * color to red
					//for the date picker so another method must be tried later
					//dobPicker.setStyle("-fx-prompt-text-fill: red");
					
					//buttons for submitting the form or going back to the previous page
					//if submit is pressed, the program should read in every field
					//given the required fields are all full, or if they press back
					//the program should go to the previous screen
					//these will be handled in the event handlers for these buttons
					submit = new Button("Submit");
					back = new Button("Back");
					
					//vertical panes to attach the text element to it's corresponding
					//method of user input so textfield/area etc.
					VBox fNameBox = new VBox(2);
					fNameBox.getChildren().addAll(fName, fNameField);
					
					VBox lNameBox = new VBox(2);
					lNameBox.getChildren().addAll(lName, lNameField);
					
					VBox dobBox = new VBox(2);
					dobBox.getChildren().addAll(dob, dobPicker);
					
					VBox emailBox = new VBox(2);
					emailBox.getChildren().addAll(email, emailField);
					
					VBox phoneBox = new VBox(2);
					phoneBox.getChildren().addAll(phone, numField);
					
					VBox medHisBox = new VBox(2);
					medHisBox.getChildren().addAll(medHis, medHisField);
					
					VBox pharmBox = new VBox(2);
					pharmBox.getChildren().addAll(pharmacy, pharmField);
					
					VBox mailBox = new VBox(2);
					mailBox.getChildren().addAll(mailAddress, mailField1, mailField2, mailField3, mailField4);
					
					VBox insuranceBox = new VBox(2);
					insuranceBox.getChildren().addAll(insurance, insField);
					
					VBox insNumBox = new VBox(2);
					insNumBox.getChildren().addAll(insNum, insNumField);
					
					VBox finishBox = new VBox(2);
					finishBox.getChildren().addAll(finish, submit, back);
					//insets to center the buttons below the text/other text fields aesthetic
					VBox.setMargin(submit, new Insets(0, 0, 0, 40));
					VBox.setMargin(back, new Insets(0, 0, 0, 45));
					
					//Vertical pane for the first column of content
					//I didn't use a Grid Pane due to sizing issues/uneven
					//number of elements in each column
					VBox column1 = new VBox(8);
					column1.getChildren().addAll(fNameBox, emailBox, pharmBox, insuranceBox, insNumBox);
					
					VBox column2 = new VBox(8);
					column2.getChildren().addAll(lNameBox, phoneBox, mailBox, finishBox);
					
					VBox column3 = new VBox(8);
					column3.getChildren().addAll(dobBox, medHisBox);
					
					//put this all in a Horizontal pane to mimick the grid structure
					HBox formBox = new HBox(5);
					formBox.getChildren().addAll(column1, column2, column3);
					
					//create a vertical pane for the title, new patient form text,
					//and required text
					VBox topText = new VBox(5);
					topText.getChildren().addAll(title, welcome, form, required);
					
					//add the top text with its contents to the top left
					//of this stack pane
					StackPane.setAlignment(topText, Pos.TOP_LEFT);
					StackPane.setMargin(topText, new Insets(10, 0, 0, 10));
					this.getChildren().add(topText);
					
					//border pane to center the grid pane contents
					BorderPane bp = new BorderPane();
					bp.setCenter(formBox);
					bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
					
					
					//add the border pane to this stack pane
					this.getChildren().add(bp);
				} //end constructor
			} //end nurse create patient class
			
			private class NurseCreatePatientConfirmation extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, patient, dob, confirmation;
				private Button patientSummary, back;
						
				private NurseCreatePatientConfirmation()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the nurse who is logged on 
					//as well as the new patient's name and dob that they have just created
					//to be read in
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Nurse Jackson");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Welcome in, Patient Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);

					confirmation = new Text("You have created a new patient as listed above.");
					confirmation.setFont(Font.font("Times New Roman", 14));
					confirmation.setFill(Color.BLACK);
					
					//buttons for user, summary leads to the new patient's summary with their
					//inputted information, and back takes them to the nurse patient selection page
					patientSummary = new Button("Patient Summary");
					back = new Button("Back Home");
					
					//Vertical pane to put the patient's name and dob stacked
					VBox patientInfo = new VBox(3);
					patientInfo.getChildren().addAll(title, welcome, patient, dob);
					
					//Vertical pane to add the confirmation text and buttons
					VBox confMessage = new VBox(5);
					confMessage.getChildren().addAll(confirmation, patientSummary, back);
					VBox.setMargin(patientSummary, new Insets(0, 0, 0, 60));
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
				} //end constructor
			} //end nurse create patient confirmation class
			
			private class NurseVitals extends StackPane
			{
				private Color mainColor;
				private Text title, welcome, dob, contactInfo, email;
				private Text phone, medHisTitle, medHis, pharmacy, insurance, doctorNotes, pName;
				private Text visit, height, weight, bloodPressure, bodyTemp, allergies, sendMessage;
				private TextField heightField, weightField, bloodPField, bodyTempField, allergyField;
				private DatePicker visitDate;
				private TextArea docNotes;
				private Button back, go, submit;
				
				private NurseVitals()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//as well as the doctor who is logged on currently
					//Note: these will need to be read in from patient list
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Nurse Jackson");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					pName = new Text("Patient: Adam Samler");
					pName.setFont(Font.font("Times New Roman", 14));
					pName.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);
					
					//labels the contact information
					contactInfo = new Text("Contact Information:");
					contactInfo.setFont(Font.font("Times New Roman", 14));
					contactInfo.setFill(Color.BLACK);
					
					//these will be parsed in from the patient's profile, Note: that
					//mailing address and insurance number are not included on summary
					//screen, but the rest of the information is
					phone = new Text("Phone: 123-456-7890");
					phone.setFont(Font.font("Times New Roman", 14));
					phone.setFill(Color.BLACK);
					
					email = new Text("Email: asamler@yahoo.com");
					email.setFont(Font.font("Times New Roman", 14));
					email.setFill(Color.BLACK);
					
					insurance = new Text("Insurance: Aetna");
					insurance.setFont(Font.font("Times New Roman", 14));
					insurance.setFill(Color.BLACK);
					
					pharmacy = new Text("Pharmacy: CVS #602");
					pharmacy.setFont(Font.font("Times New Roman", 14));
					pharmacy.setFill(Color.BLACK);
					
					//labels previous medical history
					medHisTitle = new Text("Previous Medical History:");
					medHisTitle.setFont(Font.font("Times New Roman", 14));
					medHisTitle.setFill(Color.BLACK);
					
					medHis = new Text("-Surgery, right foot, Jan. 2012\n-HepC Vaccine, Oct. 2020");
					medHis.setFont(Font.font("Times New Roman", 14));
					medHis.setFill(Color.BLACK);
					
					//these will need to be filled in to save as the patient's vitals/notes for
					//the day to display on the patient summary page
					visit = new Text("Date of Visit(Today):");
					visit.setFont(Font.font("Times New Roman", 14));
					visit.setFill(Color.BLACK);
					
					height = new Text("Height:");
					height.setFont(Font.font("Times New Roman", 14));
					height.setFill(Color.BLACK);
				
					weight = new Text("Weight:");
					weight.setFont(Font.font("Times New Roman", 14));
					weight.setFill(Color.BLACK);
					
					bloodPressure = new Text("Blood Pressure:");
					bloodPressure.setFont(Font.font("Times New Roman", 14));
					bloodPressure.setFill(Color.BLACK);
					
					bodyTemp = new Text("Body Temperature:");
					bodyTemp.setFont(Font.font("Times New Roman", 14));
					bodyTemp.setFill(Color.BLACK);
					
					allergies = new Text("Allergies:");
					allergies.setFont(Font.font("Times New Roman", 14));
					allergies.setFill(Color.BLACK);
					
					//doctor's/nurse's notes label
					doctorNotes = new Text("Doctor/Nurse's Notes:");
					doctorNotes.setFont(Font.font("Times New Roman", 14));
					doctorNotes.setFill(Color.BLACK); 
					
					sendMessage = new Text("Send/View Messages:");
					sendMessage.setFont(Font.font("Times New Roman", 14));
					sendMessage.setFill(Color.BLACK); 
					
					//textfields
					heightField = new TextField();
					weightField = new TextField();
					bloodPField = new TextField();
					bodyTempField = new TextField();
					allergyField = new TextField();
					
					//text area for the doctors/nurses notes
					docNotes = new TextArea();
					
					//date picker for the date of the visit selection
					visitDate = new DatePicker();
					
					//back button takes the user to the choose a patient page
					//go button takes user to send a message to the patient they are currently on
					//submit allows user to send in notes/medication added to the page via the textfields
					back = new Button("Back");
					go = new Button("Go");
					submit = new Button("Submit");
					
					//vertical panes for each group of information on the page, to be placed
					//in column vertical panes and then in a horizontal pane for display purposes
					VBox nameBox = new VBox(2);
					nameBox.getChildren().addAll(pName, dob);
					
					VBox contactBox = new VBox(2);
					contactBox.getChildren().addAll(contactInfo, phone, email);
					
					VBox insBox = new VBox(2);
					insBox.getChildren().addAll(insurance, pharmacy);
					
					VBox prevMedBox = new VBox(2);
					prevMedBox.getChildren().addAll(medHisTitle, medHis);
					
					VBox dateBox = new VBox(2);
					dateBox.getChildren().addAll(visit, visitDate);
					
					VBox heightBox = new VBox(2);
					heightBox.getChildren().addAll(height, heightField);
					
					VBox weightBox = new VBox(2);
					weightBox.getChildren().addAll(weight, weightField);
					
					VBox bloodPBox = new VBox(2);
					bloodPBox.getChildren().addAll(bloodPressure, bloodPField);
					
					VBox bodyTempBox = new VBox(2);
					bodyTempBox.getChildren().addAll(bodyTemp, bodyTempField);
					
					VBox allergyBox = new VBox(2);
					allergyBox.getChildren().addAll(allergies, allergyField);
			
					//back button is in this box as it is displayed low on the screen and has some
					//insets for aesthetic
					VBox docBox = new VBox(5);
					docBox.getChildren().addAll(doctorNotes, docNotes, submit);
					VBox.setMargin(submit, new Insets(10,0,0,200));
					
					//vbox for send message label and buttons
					VBox messageBox = new VBox(2);
					messageBox.getChildren().addAll(sendMessage, go, back);
					VBox.setMargin(go, new Insets(0,0,0,40));
					VBox.setMargin(back, new Insets(0,0,0,37));
					
					//vertical panes for each column
					VBox column1 = new VBox(8);
					column1.getChildren().addAll(nameBox, contactBox, insBox, prevMedBox, messageBox);
					
					VBox column2 = new VBox(8);
					column2.getChildren().addAll(dateBox, heightBox, weightBox, bloodPBox, bodyTempBox, allergyBox);
					
					//vbox for title and doctor greeting
					VBox titleBox = new VBox(2);
					titleBox.getChildren().addAll(title, welcome);
					
					//horizontal pane to store columns
					HBox colBox = new HBox(8);
					colBox.getChildren().addAll(column1, column2, docBox);
					
					//border pane to put horizontal pane in the center, and the doctor's notes/button
					//on the bottom of the screen
					//border pane to center the grid pane contents
					BorderPane bp = new BorderPane();
					bp.setCenter(colBox);
					bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
					
					//add the title to the top left of this stack pane
					StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
					StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
					this.getChildren().add(titleBox);
					
					//add the border pane to this stack pane
					this.getChildren().add(bp);
				} //end constructor
			} //end nurse vitals class
			
			private class NurseVitalsConfirmation extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, patient, dob, confirmation;
				private Button patientSummary, back;
				
				private NurseVitalsConfirmation()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//and the nurse who is currently logged in
					//Note: these will need to be read in from the previous New patient form
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Nurse Jackson");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Patient: Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);

					confirmation = new Text("Your updates have been saved for the above patient.");
					confirmation.setFont(Font.font("Times New Roman", 14));
					confirmation.setFill(Color.BLACK);
					
					//buttons for user, patient summary leads the user to the patient summary screen
					//of the patient they have currently selected, go back leads
					//the user to the previous screen of entering nurse vitals
					patientSummary = new Button("Patient Summary");
					back = new Button("Back");
					
					//Vertical pane to put the patient's name and dob stacked
					VBox patientInfo = new VBox(3);
					patientInfo.getChildren().addAll(title, welcome, patient, dob);
					
					//Vertical pane to add the confirmation text and buttons
					VBox confMessage = new VBox(5);
					confMessage.getChildren().addAll(confirmation, patientSummary, back);
					VBox.setMargin(patientSummary, new Insets(0, 0, 0, 60));
					VBox.setMargin(back, new Insets(0, 0, 0, 90));
					
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
				} //end constructor
			} //end nurse vitals confirmation class
		
			private class NursePatientSummary extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, patient, dob, contactInfo, email, sendMessage;
				private Text phone, medHisTitle, medHis, pharmacy, insurance, doctorNotes, notes;
			    private Text date1, date2, height, weight, bloodPressure, bodyTemp, allergies, prescription;
				private Text height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2;
				private Button back, go;
				
				private NursePatientSummary()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//as well as the nurse currently logged on
					//Note: these will need to be read in from the patient list the nurse chose from
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Nurse Jackson");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Patient: Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);
					
					//labels the contact information
					contactInfo = new Text("Contact Information:");
					contactInfo.setFont(Font.font("Times New Roman", 14));
					contactInfo.setFill(Color.BLACK);
					
					//these will be parsed in from the patient's profile, Note: that
					//mailing address and insurance number are not included on summary
					//screen, but the rest of the information is
					phone = new Text("Phone: 123-456-7890");
					phone.setFont(Font.font("Times New Roman", 14));
					phone.setFill(Color.BLACK);
					
					email = new Text("Email: asamler@yahoo.com");
					email.setFont(Font.font("Times New Roman", 14));
					email.setFill(Color.BLACK);
					
					insurance = new Text("Insurance: Aetna");
					insurance.setFont(Font.font("Times New Roman", 14));
					insurance.setFill(Color.BLACK);
					
					pharmacy = new Text("Pharmacy: CVS #602");
					pharmacy.setFont(Font.font("Times New Roman", 14));
					pharmacy.setFill(Color.BLACK);
					
					//labels previous medical history
					medHisTitle = new Text("Previous Medical History:");
					medHisTitle.setFont(Font.font("Times New Roman", 14));
					medHisTitle.setFill(Color.BLACK);
					
					medHis = new Text("-Surgery, right foot, Jan. 2012\n-HepC Vaccine, Oct. 2020");
					medHis.setFont(Font.font("Times New Roman", 14));
					medHis.setFill(Color.BLACK);
					
					//these are dummy dates for fake previous visits
					//Note: we need to decide how we will keep track of
					//visit dates
					date1 = new Text("Visit Date: 08/15/2020");
					date1.setFont(Font.font("Times New Roman", 14));
					date1.setFill(Color.BLACK);
					
					date2 = new Text("Visit Date: 03/04/2021");
					date2.setFont(Font.font("Times New Roman", 14));
					date2.setFill(Color.BLACK);
					
					//these are nurse's categories of what they take at each appointment
					//there are 2 of each because this screen displays 2 dummy previous visits
					//these will all be parsed in, ex. Height: + [string that holds height] from nurse's input
					height = new Text("Height: 5 feet 7 inches");
					height.setFont(Font.font("Times New Roman", 14));
					height.setFill(Color.BLACK);
					
					height2 = new Text("Height: 5 feet 9 inches, grew 2 inches");
					height2.setFont(Font.font("Times New Roman", 14));
					height2.setFill(Color.BLACK);
				
					weight = new Text("Weight: 120 lbs");
					weight.setFont(Font.font("Times New Roman", 14));
					weight.setFill(Color.BLACK);
					
					weight2 = new Text("Weight: 122 lbs, gained 2 lbs");
					weight2.setFont(Font.font("Times New Roman", 14));
					weight2.setFill(Color.BLACK);
					
					bloodPressure = new Text("Blood Pressure: 120/80mmHg");
					bloodPressure.setFont(Font.font("Times New Roman", 14));
					bloodPressure.setFill(Color.BLACK);
					
					bloodPressure2 = new Text("Blood Pressure: 120/80mmHg");
					bloodPressure2.setFont(Font.font("Times New Roman", 14));
					bloodPressure2.setFill(Color.BLACK);
					
					bodyTemp = new Text("Body Temperature: 96.8 degrees");
					bodyTemp.setFont(Font.font("Times New Roman", 14));
					bodyTemp.setFill(Color.BLACK);
					
					bodyTemp2 = new Text("Body Temperature: 96.8 degrees");
					bodyTemp2.setFont(Font.font("Times New Roman", 14));
					bodyTemp2.setFill(Color.BLACK);
					
					allergies = new Text("Allergies: Peanuts");
					allergies.setFont(Font.font("Times New Roman", 14));
					allergies.setFill(Color.BLACK);
			
					allergies2 = new Text("Allergies: Peanuts");
					allergies2.setFont(Font.font("Times New Roman", 14));
					allergies2.setFill(Color.BLACK);
					
					prescription = new Text("Prescription: Epinephrine 'EpiPen'");
					prescription.setFont(Font.font("Times New Roman", 14));
					prescription.setFill(Color.BLACK);
					
					prescription2 = new Text("Prescription: Epinephrine 'EpiPen'");
					prescription2.setFont(Font.font("Times New Roman", 14));
					prescription2.setFill(Color.BLACK);
					
					//doctor's notes will be parsed in as well, this is the label for them
					doctorNotes = new Text("Doctor/Nurse's Notes:");
					doctorNotes.setFont(Font.font("Times New Roman", 14));
					doctorNotes.setFill(Color.BLACK); 
					
					//these will be taken from the doctor's notes input as a string
					notes = new Text("-03/04 - slight pain in knee, likely baseball, tylenol referral");
					notes.setFont(Font.font("Times New Roman", 14));
					notes.setFill(Color.BLACK);
					
					//text labeling the option for the nurse currently logged on to send
					//a message to the patient whose summary they are viewing
					sendMessage = new Text("Send/View Messages");
					sendMessage.setFont(Font.font("Times New Roman", 14));
					sendMessage.setFill(Color.BLACK);
					
					//back button takes the user to the previous screen
					back = new Button("Back");
					//go button takes the user to their message portal with this patient to view/send messages
					go = new Button("Go");
					
					//vertical panes for each group of information on the page, to be placed
					//in column vertical panes and then in a horizontal pane for display purposes
					VBox nameBox = new VBox(2);
					nameBox.getChildren().addAll(patient, dob);
					
					VBox contactBox = new VBox(2);
					contactBox.getChildren().addAll(contactInfo, phone, email);
					
					VBox insBox = new VBox(2);
					insBox.getChildren().addAll(insurance, pharmacy);
					
					VBox prevMedBox = new VBox(2);
					prevMedBox.getChildren().addAll(medHisTitle, medHis);
					
					//put a lot of space between the dates so they 
					//align with their corresponding height/weight etc.
					VBox visitBox = new VBox(100);
					visitBox.getChildren().addAll(date1, date2);
					
					VBox visit1Box = new VBox(2);
					visit1Box.getChildren().addAll(height, weight, bloodPressure, bodyTemp, allergies, prescription);

					VBox visit2Box = new VBox(2);
					visit2Box.getChildren().addAll(height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2);
					
					VBox messageBox = new VBox(2);
					messageBox.getChildren().addAll(sendMessage, go, back);
					VBox.setMargin(sendMessage, new Insets(30,0,0,180));
					VBox.setMargin(back, new Insets(0,0,0,210));
					VBox.setMargin(go, new Insets(0,0,0,215));
					
					//back button is in this box as it is displayed low on the screen and has some
					//insets for aesthetic
					VBox docBox = new VBox(2);
					docBox.getChildren().addAll(doctorNotes, notes);
					
					VBox bottomBox = new VBox(2);
					bottomBox.getChildren().addAll(docBox, messageBox);
			
					//vertical panes for each column
					VBox column1 = new VBox(8);
					column1.getChildren().addAll(nameBox, contactBox, insBox, prevMedBox);
					
					VBox column2 = new VBox(8);
					column2.getChildren().addAll(visitBox);
					
					VBox column3 = new VBox(16);
					column3.getChildren().addAll(visit1Box, visit2Box);
					
					//horizontal pane to store columns
					HBox colBox = new HBox(8);
					colBox.getChildren().addAll(column1, column2, column3);
					
					//vertical pane for the title
					VBox titleBox = new VBox(2);
					titleBox.getChildren().addAll(title, welcome);
					
					//border pane to put horizontal pane in the center, and the doctor's notes/button
					//on the bottom of the screen
					
					//border pane to center the grid pane contents
					BorderPane bp = new BorderPane();
					bp.setCenter(colBox);
					bp.setBottom(bottomBox);
					bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
					
					//add the title to the top left of this stack pane
					StackPane.setAlignment(titleBox, Pos.TOP_LEFT);
					StackPane.setMargin(titleBox, new Insets(10, 0, 0, 10));
					this.getChildren().add(titleBox);
					
					//add the border pane to this stack pane
					this.getChildren().add(bp);
				} //end constructor
			} //end nurse patient summary class
			
			private class NurseMessagePortal extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, patient, dob, prevMessages, message1, message2;
				private Button sendMessage, back;
						
				private NurseMessagePortal()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//as well as which nurse is logged on currently
					//Note: these will need to be read in from the patient chosen by the nurse
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Nurse Jackson");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Patient: Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
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
					message1 = new Text("Hi Adam,\n\nI wanted to check in and see how you're doing today.\nLet me know if you need an appointment with Dr. Sparky!");
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
					titleBox.getChildren().addAll(title, welcome, patient, dob);
					
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
			} //end nurse message portal class
			
			private class NurseSendMessage extends StackPane
			{
				//attributes of this class
				private Color mainColor;
				private Text title, welcome, patient, dob, directions;
				private TextArea message;
				private Button send, back;
			
				private NurseSendMessage()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//as well as which nurse is currently logged on 
					//Note: these will need to be read in from the patient list the nurse chose from
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Nurse Jackson");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Patient: Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);
					
					directions = new Text("Please type your message below:");
					directions.setFont(Font.font("Times New Roman", 14));
					directions.setFill(Color.BLACK);
					
					//text area to store patient message from when they hit the send button
					message = new TextArea();
					
					//buttons for the user to send their message IF they have put message content, 
					//or go back to the previous page
					send = new Button("Send");
					back = new Button("Back");
					
					//vertical boxes to store the title and it's contents and the middle of the page contents
					VBox titleBox = new VBox(2);
					titleBox.getChildren().addAll(title, welcome, patient, dob);
					
					VBox messageBox = new VBox(8);
					messageBox.getChildren().addAll(directions, message, send, back);
					//insets to center the buttons below the text/other text fields aesthetic
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
		} //end nurse send message class
			
			private class NurseMessageConfirmation extends StackPane
			{
				//create attributes for this screen
				private Color mainColor;
				private Text title, welcome, patient, dob, confirmation;
				private Button back;
				
				private NurseMessageConfirmation()
				{
					//establish color Falu Red as done on home screen
					mainColor = Color.rgb(128,32,32);
					
					//title and its color/size/font
					title = new Text("SunDevil Pediatric Health Portal");
					title.setFont(Font.font("Plantagenet Cherokee", 23));
					title.setFill(mainColor);
					
					//black text labeling the name of the patient and dob of the patient
					//and nurse that is logged on currently
					//Note: these will need to be read in from the patient list the nurse chose from
					//text fields/areas so they will end up being parsed input rather than this dummy default text
					welcome = new Text("Welcome in, Nurse Jackson");
					welcome.setFont(Font.font("Times New Roman", 14));
					welcome.setFill(Color.BLACK);
					
					patient = new Text("Patient: Adam Samler");
					patient.setFont(Font.font("Times New Roman", 14));
					patient.setFill(Color.BLACK);
					
					dob = new Text("DOB: 01/09/2007");
					dob.setFont(Font.font("Times New Roman", 14));
					dob.setFill(Color.BLACK);

					confirmation = new Text("\t\tYour message has been sent!\nSelect the button to go back to your portal home screen.");
					confirmation.setFont(Font.font("Times New Roman", 14));
					confirmation.setFill(Color.BLACK);
					
					//button for the user to go back to their home screen where they are logged
					//on as a doctor and can select a patient from the patient list
					back = new Button("Back");
					
					//vertical box to store title and welcome contents
					VBox titleBox = new VBox(3);
					titleBox.getChildren().addAll(title, welcome, patient, dob);
					
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
			} //end nurse message confirmation class
			
			
} //end health portal class
