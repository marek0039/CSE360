import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DocMessagePortal extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, patient, dob, prevMessages, message1, message2;
    private Button sendMessage, back;

    public DocMessagePortal()
    {
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //SQL to grab current patient's name the doctor is on
        ResultSet rs = null;
        try
        {
            String sql = "select First_Name, Last_Name, DOB from Patient where PatientID =" + HealthPortal.currPatient + ";";
            rs = HealthPortal.statement.executeQuery(sql);
            rs.last();
            if (rs.getRow() == 1)
            {
                pFirstName = rs.getString("First_Name");
                pLastName = rs.getString("Last_Name");
                dateOfBirth = rs.getString("DOB");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        //sql query to grab the name and last name of the nurse currently logged in
        ResultSet rs3 = null;
        try
        {
            String sql = "select First_Name, Last_Name, from Professional where ID =" + HealthPortal.currUser + ";";
            rs3 = HealthPortal.statement.executeQuery(sql);
            rs3.last();
            if (rs3.getRow() == 1)
            {
                docFirstName = rs3.getString("First_Name");
                docLastName = rs3.getString("Last_Name");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
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
        ForwardButton handler2 = new ForwardButton(15);
        back.setOnAction(handler2);

        //SQL for the messages to be displayed on this users screen
        String[] results = new String[6];
        ResultSet rs2 = null;
        try
        {
            String sql2 = "SELECT Sender, Text, Date From Message WHERE Recipient =" + HealthPortal.currUser + "And Date IN (SELECT t1.Date FROM Message t1 left join Message t2 on t1.Date <= t2.Date group by t1.Date having count(distinct t2.Date)<=2)";
            rs2 = HealthPortal.statement.executeQuery(sql2);
            int i = 0;
            if (rs2.first())
            {
                while(rs2.next())
                {
                    results[i] = rs2.getString("Sender");
                    results[i+1] = rs2.getString("Text");
                    results[i+2] = rs2.getString("Date");
                    i = i+3;
                }

                sender1 = results[0];
                text1 = results[1];
                date1 = results[2];
                sender2 = results[3];
                text2 = results[4];
                date2 = results[5];
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

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
