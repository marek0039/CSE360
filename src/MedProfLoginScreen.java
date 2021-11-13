import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedProfLoginScreen extends StackPane
{
    //attributes of this class to be displayed on the pane
    private Color mainColor;
    private Text title, label, username, password;
    private TextField uNameField, passField;
    private Button login, back;
    private Label errorLabel;

    public MedProfLoginScreen()
    {
        errorLabel = new Label();
        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128,32,32);

        //title and its color/size/font
        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling that this is the med prof log on screen
        label = new Text("Medical Professional Login Screen");
        label.setFont(Font.font("Times New Roman", 14));
        label.setFill(Color.BLACK);

        //black text labeling the fields needed to log on
        username = new Text("Username:");
        username.setFont(Font.font("Times New Roman", 14));
        username.setFill(Color.BLACK);

        password = new Text("Password:");
        password.setFont(Font.font("Times New Roman", 14));
        password.setFill(Color.BLACK);

        //text fields for username and password input
        uNameField = new TextField();
        passField = new TextField();


        //buttons to allow the user to submit and log in, or go back to the
        //previous page in case they did not mean to enter this one
        login = new Button("Login");
        back = new Button("Back");
        //back button forward event handler, case 1, go back to user choice screen
        ForwardButton handler = new ForwardButton(1);
        back.setOnAction(handler);

        //Vertical pane to put the title and existing patient label together
        VBox titleBox = new VBox(5);
        titleBox.getChildren().addAll(title, label);

        //Vertical pane to put the log on requirements in the center of the page
        VBox centerElements = new VBox(8);
        centerElements.getChildren().addAll(username, uNameField, password, passField, login, back);
        //insets to center the buttons below the text/other text fields aesthetic
        VBox.setMargin(login, new Insets(0, 0, 0, 60));
        VBox.setMargin(back, new Insets(0, 0, 0, 60));

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

    private class MedicalProfessionalLoginButton extends ForwardButton
    {
        private int num_rows;
        private MedicalProfessionalLoginButton(int caseInt)
        {
            super(caseInt);
        }

        @Override
        public void handle(ActionEvent event)
        {
            //Format = YYYY-MM-DD

            ResultSet rs = null;
            //String[] delim = dobPicker.getText().split("-");

            if(uNameField.getText().isEmpty() || passField.getText().isEmpty())
            {
                errorLabel.setText("Please enter all necessary login info");
                errorLabel.setTextFill(Color.RED);
            }
            //delim[0].length() == 4 && delim[1].length() == 1 && delim[2].length() == 2
            else
            {
                try {
                    String username = uNameField.getText();
                    String password = passField.getText();
                    String sql = "SELECT ID FROM Professional WHERE username='"+ username + "' and password='"+ password+ "';";
                    rs = HealthPortal.statement.executeQuery(sql);

                    if(rs.next())
                    {
                        this.num_rows++;
                    }
                    if(rs.getRow() == 1) {
                        while (rs.next()) {
                            int id = rs.getInt("ID");
                            HealthPortal.currUser = id;
                        }
                        super.handle(event);
                    }

//                    rs.next();
//                    if (rs.getRow() == 1) {
//                        System.out.print("Error6\n");
//                        rs.first();
//                       int pFirstName = rs.getInt("PatientID");
//                        System.out.print(pFirstName + "\n");
//                        String pLastName = rs.getString("Last_Name");
//                        System.out.print(pLastName + "\n");
//                        String dob = rs.getString("DOB");
//                        System.out.print(dob + "\n");
//                        HealthPortal.currUser = rs.getInt("PatientID");
//                        super.handle(event);
//                    }
                    else
                    {
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
} //end med prof log on class
