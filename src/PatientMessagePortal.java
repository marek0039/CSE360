import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientMessagePortal extends StackPane
{
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, dob, prevMessages, message1, message2;
    private Button sendMessage, back;
    private String pFirstName, pLastName, dateOfBirth;
    private String sender1, text1, date1, sender2, text2, date2;

    public PatientMessagePortal()
    {
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //SQL queries for grabbing the name and date of birth of the current user
        //logged in to display on the screen
        ResultSet rs = null;
        try
        {
            String sql = "select First_Name, Last_Name, DOB from Patient where PatientID =" + HealthPortal.currUser;
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

        //black text labeling the name of the patient and dob of the patient
        //Note: these will need to be read in from the existing patient log in
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Patient: " + pFirstName + " " + pLastName);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        dob = new Text("DOB: " + dateOfBirth);
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
        //where they can actually type out and send their message, case 11
        ForwardButton handler1 = new ForwardButton(11);
        sendMessage.setOnAction(handler1);

        back = new Button("Patient Menu");
        //forward event handler for the back button takes user back to existing
        //patient portal screen, case 5
        ForwardButton handler2 = new ForwardButton(5);
        back.setOnAction(handler2);

        //SQL for the messages to be displayed on this users screen
        String[] results = new String[6];
        //the 2 result set objects for the next two queries
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        //is doc integers to eventually store whether the sender of the message is a doctor or not
        //to give the proper title to display on the screen
        int isDoc = -1;
        int isDoc2 = -1;

        try
        {
            //Query to grab the sender, text, and date from the message table where the current
            //patient logged in is the recipient of that message
            String sql2 = "Select Sender,Text,Date From Message Where Recipient=" + HealthPortal.currUser;
            rs2 = HealthPortal.statement.executeQuery(sql2);
            rs2.last(); //set the result to the last row
            //if the current patient has NO messages, the following will be displayed in the two message panes:
                if(!(rs2.first()))
                {
                   sender1 = "N/A";
                   text1 = "\n\nYou have no messages.\n\n\n";
                   date1 = "N/A";
                   sender2 = "N/A";
                   text2 = "\n\nYou have no messages.\n\n\n";
                   date2 = "N/A";
                }
                //if the current patient has ONE message, the message content/sender/date will be stored
                //to be displayed and the other message will have N/A with no content
                else if(rs2.isFirst() && rs2.isLast())
                {
                    sender1 = rs2.getString("Sender");
                    text1 = rs2.getString("Text");
                    date1 = rs2.getString("Date");
                    sender2 = "N/A";
                    text2 = "\n\nYou have no messages.\n\n\n";
                    date2 = "N/A";

                    //since the user has ONE message, get the medical professional's last name
                    //who sent the message by pulling from the professional table where the ID matches
                    //the sender, and pull if they are a doctor or not as well
                    int senderId = Integer.parseInt(sender1);
                    try
                    {
                        String sql3 = "Select Last_Name, IsDoctor From Professional Where ID=" + senderId;
                        rs3 = HealthPortal.statement.executeQuery(sql3);

                        rs3.last();
                        if (rs3.getRow() == 1)
                        {
                            sender1 = rs3.getString("Last_Name");
                            isDoc = rs3.getInt("IsDoctor");
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }

                    //if they are a doctor, place Doctor in front of their last name, Nurse otherwise
                    if(isDoc == 1)
                    {
                        String doc = "Doctor ";
                        sender1 = doc + sender1;
                    }
                    else
                    {
                        String nur = "Nurse ";
                        sender1 = nur + sender1;
                    }
                }
                //Finally if the patient has 2+ messages, display 2 messages by traversing the result set
                //and taking the senders, message contents, and dates
                else
                {
                    int i = 0;
                    if (rs2.first())
                    {
                        while (rs2.next())
                        {
                            results[i] = rs2.getString("Sender");
                            results[i + 1] = rs2.getString("Text");
                            results[i + 2] = rs2.getString("Date");
                            i = i + 3;
                        }
                        sender1 = results[0];
                        text1 = results[1];
                        date1 = results[2];
                        sender2 = results[3];
                        text2 = results[4];
                        date2 = results[5];
                    }

                    //repeat the steps from the previous if statement to select the doctor or nurse's last
                    //name who sent these messages, NOTE: they can be different so this must happen twice
                    int senderId1 = Integer.parseInt(sender1);
                    int senderId2 = Integer.parseInt(sender2);

                    try
                    {
                        String sql3 = "Select Last_Name, IsDoctor From Professional Where ID=" + senderId1;
                        rs3 = HealthPortal.statement.executeQuery(sql3);

                        rs3.last();
                        if (rs3.getRow() == 1)
                        {
                            sender1 = rs3.getString("Last_Name");
                            isDoc = rs3.getInt("IsDoctor");
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }

                    //again, if they are a doctor or a nurse put the proper title before their last name for
                    //both queries
                    if(isDoc == 1)
                    {
                        String doc = "Doctor ";
                        sender1 = doc + sender1;
                    }
                    else
                    {
                        String nur = "Nurse ";
                        sender1 = nur + sender1;
                    }

                    try
                    {
                        String sql3 = "Select Last_Name, IsDoctor From Professional Where ID=" + senderId2;
                        rs3 = HealthPortal.statement.executeQuery(sql3);

                        rs3.last();
                        if (rs3.getRow() == 1)
                        {
                            sender2 = rs3.getString("Last_Name");
                            isDoc2 = rs3.getInt("IsDoctor");
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }

                    if(isDoc2 == 1)
                    {
                        String doc = "Doctor ";
                        sender2 = doc + sender2;
                    }
                    else
                    {
                        String nur = "Nurse ";
                        sender2 = nur + sender2;
                    }
                }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        //text objects for the messages
        message1 = new Text("\n\n" + text1 + "\n\n\n");
        message1.setFont(Font.font("Times New Roman", 10));
        message1.setFill(Color.BLACK);

        message2 = new Text("\n\n" + text2 + "\n\n\n");
        message2.setFont(Font.font("Times New Roman", 10));
        message2.setFill(Color.BLACK);

        //titled pane to store the previous messages
        //the string array has the message titles and messages, for the titles we will have to figure out if we
        //want to parse these in any way or just say 'message 1' 'message 2' etc.
        //as defaults that don't change, for now it's the same as our mockup
        String[] messageTitles = new String[] {date1 + " " + sender1, date2 + " " + sender2};
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
