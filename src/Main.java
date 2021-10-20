import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;	
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application
{	
	//Class for the Home Screen GUI, this is the only background photo that is different
	//as it has the cartoon in the bottom corner
		private class HomeScreen extends StackPane
		{
			//elements present on the home screen
			//color for the text, text elements
			//for the title and log in directions
			//and a button to press start and head to the next screen
			private Color mainColor;
			private Text title, log;
			private Button start;
			
			//home screen constructor
			private HomeScreen()
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
				//ButtonHandler handler = new ButtonHandler();
				//start.setOnAction(handler);
				
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
			
//			 private class ButtonHandler implements EventHandler<ActionEvent> 
//		   	 {
//		   	    //Override the abstract method handle()
//		   	    public void handle(ActionEvent event)
//		        {
//		   	    	THIS WILL BE THE BUTTON HANDLER DEFINITION FOR THE START BUTTON
//		        }
//		   	 }
		}//end home screen class
		
	public void start(Stage stage) 
	{
	    //create the root stack pane and add home screen object to it
		StackPane root = new StackPane();
	
		HomeScreen home = new HomeScreen();
		root.getChildren().add(home);
	
		//set the scene/stage and put the default size as 700 x 500 with root displayed
	    Scene scene = new Scene(root, 700, 500);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void main(String[] args)
    {
        launch(args);
    }
}//end public main class