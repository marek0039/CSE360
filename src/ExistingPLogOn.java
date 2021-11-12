import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExistingPLogOn extends StackPane
{
    // Test 123
    //attributes of this class to be displayed on the pane
    private Color mainColor;
    private Text title, label, fName, lName, dob;
    private TextField fNameField, lNameField, dobPicker;
    //private DatePicker dobPicker;
    private Button submit, back;
    private Label errorLabel;
    public TextField fNameField, lNameField;
    public DatePicker dobPicker;
    private Button submit, back;
    private StackPane root;
    private int currUser;
    public Label errorLabel;

    public ExistingPLogOn()
    {
        errorLabel = new Label();
        root = rootPane;
        currUser = user;
        errorLabel = new Label();
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

        dob = new Text("Patient Date of Birth (Use Format YYYY-MM-DD): ");
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        //text fields and date picker for the patient to input information
        fNameField = new TextField();
        lNameField = new TextField();
        dobPicker = new TextField();

        //buttons to allow the user to submit and log in, or go back to the
        //previous page in case they did not mean to enter this one
        submit = new Button("Submit");
        //forward event handler for submit button, case 5, takes user to existing patient
        //portal with their information
        PatientLoginButton handler1 = new PatientLoginButton(5);
        submit.setOnAction(handler1);

        back = new Button("Back");
        //forward event handler for back button, case 2, takes user back to patient choice screen
        ForwardButton handler2 = new ForwardButton(2);
        back.setOnAction(handler2);

        //Vertical pane to put the title and existing patient label together
        VBox titleBox = new VBox(5);
        titleBox.getChildren().addAll(title, label, errorLabel);

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

    private class PatientLoginButton extends ForwardButton
    {
        private PatientLoginButton(int caseInt)
        {
            super(caseInt);
        }

        @Override
        public void handle(ActionEvent event)
        {

            //Format = YYYY-MM-DD
            ResultSet rs;
            String[] delim = dobPicker.getText().split("-");

            if(fNameField.getText().isEmpty() || lNameField.getText().isEmpty() || dobPicker.getText().isEmpty())
            {
                errorLabel.setText("Please enter all necessary info");
                errorLabel.setTextFill(Color.RED);
            }

            else if(delim[0].length() == 4 && delim[1].length() == 1 && delim[2].length() == 2)
            {
                try {
                    String fName = fNameField.getText();
                    String lName = lNameField.getText();
                    String birthday = dob.getText();
                    String sql = "select First_Name, Last_Name, DOB, PatientID from Patient where First_Name = " + fName + ", Last_Name = " + lName + ", DOB = " + birthday;
                    rs = HealthPortal.statement.executeQuery(sql);
                    if (rs.getRow() == 1) {
                        rs.first();
                        String pFirstName = rs.getString("First_Name");
                        String pLastName = rs.getString("Last_Name");
                        String dob = rs.getString("DOB");
                        HealthPortal.currUser = rs.getInt("PatientID");
                        super.handle(event);
                    }
                    else {
                        errorLabel.setText("Enter Valid Login Info or go back");
                        errorLabel.setTextFill(Color.RED);
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //if(//info is right)

        }
    }
} //end existing patient log on class
