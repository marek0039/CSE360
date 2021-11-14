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
    private int caseInt;

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
        MedicalProfLoginButton handler1 = new MedicalProfLoginButton(caseInt);
        login.setOnAction(handler1);
        back = new Button("Back");
        //back button forward event handler, case 1, go back to user choice screen
        ForwardButton handler = new ForwardButton(1);
        back.setOnAction(handler);

        //Vertical pane to put the title and existing patient label together
        VBox titleBox = new VBox(5);
        titleBox.getChildren().addAll(title, label, errorLabel);

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

    private class MedicalProfLoginButton extends ForwardButton
    {
        private int num_rows;
        private MedicalProfLoginButton(int caseInt)
        {
            super(caseInt);
        }

        @Override
        public void handle(ActionEvent event)
        {
            ResultSet rs = null;

            if(uNameField.getText().isEmpty() || passField.getText().isEmpty())
            {
                errorLabel.setText("Please enter all necessary login info");
                errorLabel.setTextFill(Color.RED);
            }

            else
            {
                try {
                    String username = uNameField.getText();
                    String password = passField.getText();
                    int isDoc = -1;

                    String sql = "SELECT ID, IsDoctor FROM Professional WHERE username='"+ username + "' and password='"+ password+ "';";
                    rs = HealthPortal.statement.executeQuery(sql);

                    rs.last();
                    if(rs.getRow() == 1)
                    {
                        int id = rs.getInt("ID");
                        HealthPortal.currUser = id;
                        int isDoc1 = rs.getInt("IsDoctor");

                        if(isDoc1 == 1)
                        {
                            super.setcI(16);
                        }
                        else
                        {
                            super.setcI(21);
                        }
                        super.handle(event);
                    }

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
        }
    }
} //end med prof log on class