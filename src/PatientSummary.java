
import com.mysql.cj.protocol.Resultset;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PatientSummary extends StackPane {
    //create attributes for this screen
    private Color mainColor;
    private Text title, welcome, dob, contactInfo, email;
    private Text phone, medHisTitle, medHis, pharmacy, insurance, insuranceNum, address, doctorNotes, notes;
    private Text date1, date2, height, weight, bloodPressure, bodyTemp, allergies, prescription;
    private Text height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2, memo, memo2;
    private Button back;

    public PatientSummary() {
        ResultSet rs = null;
        String p_name = null, p_email = null, p_phone = null, p_address = null, p_pharmacy = null, p_insurance = null,
                p_insurance_num = null, p_dob = null, p_med = null;

        String getpatient = "SELECT * FROM Patient WHERE PatientID=" + HealthPortal.currUser;
        //get patient info
        try {
            rs = HealthPortal.statement.executeQuery(getpatient); //execute the query
            rs.last();
            if (rs.getRow() == 1) {
                //store every column in their respective variable
                p_name = rs.getString("First_Name") + " " + rs.getString("Last_Name");
                p_email = rs.getString("Email");
                p_phone = rs.getString("Phone_Number");
                p_address = rs.getString("Address");
                p_pharmacy = rs.getString("Pharmacy");
                p_insurance = rs.getString("Insurance");
                p_insurance_num = rs.getString("Insurance_Number");
                p_dob = rs.getString("DOB");
                p_med = rs.getString("Medical_History");
            } else {
                System.out.println("Cannot field Patient: " + HealthPortal.currPatient);
                System.out.println(getpatient);
                //throw new FailedException("Cannot find Patient: " + HealthPortal.currPatient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }


        //establish color Falu Red as done on home screen
        mainColor = Color.rgb(128, 32, 32);

        //title and its color/size/font


        title = new Text("SunDevil Pediatric Health Portal");
        title.setFont(Font.font("Plantagenet Cherokee", 23));
        title.setFill(mainColor);

        //black text labeling the name of the patient and dob of the patient
        //Note: these will need to be read in from the existing patient log in
        //text fields/areas so they will end up being parsed input rather than this dummy default text
        welcome = new Text("Patient:" + p_name);
        welcome.setFont(Font.font("Times New Roman", 14));
        welcome.setFill(Color.BLACK);

        dob = new Text("DOB: " + p_dob);
        dob.setFont(Font.font("Times New Roman", 14));
        dob.setFill(Color.BLACK);

        //labels the contact information
        contactInfo = new Text("Contact Information:");
        contactInfo.setFont(Font.font("Times New Roman", 14));
        contactInfo.setFill(Color.BLACK);

        //these will be parsed in from the patient's profile, Note: that
        //mailing address and insurance number are not included on summary
        //screen, but the rest of the information is
        phone = new Text("Phone: " + p_phone);
        phone.setFont(Font.font("Times New Roman", 14));
        phone.setFill(Color.BLACK);

        email = new Text("Email: " + p_email);
        email.setFont(Font.font("Times New Roman", 14));
        email.setFill(Color.BLACK);

        insurance = new Text("Insurance: " + p_insurance);
        insurance.setFont(Font.font("Times New Roman", 14));
        insurance.setFill(Color.BLACK);

        address = new Text("Address: " + p_address);
        address.setFont(Font.font("Times New Roman", 14));
        address.setFill(Color.BLACK);

        insuranceNum = new Text("Insurance Number: " + p_insurance_num);
        insuranceNum.setFont(Font.font("Times New Roman", 14));
        insuranceNum.setFill(Color.BLACK);

        pharmacy = new Text("Pharmacy: " + p_pharmacy);
        pharmacy.setFont(Font.font("Times New Roman", 14));
        pharmacy.setFill(Color.BLACK);

        //labels previous medical history
        medHisTitle = new Text("Previous Medical History");
        medHisTitle.setFont(Font.font("Times New Roman", 14));
        medHisTitle.setFill(Color.BLACK);

        medHis = new Text(p_med);
        medHis.setFont(Font.font("Times New Roman", 14));
        medHis.setFill(Color.BLACK);

        //these are dummy dates for fake previous visits
        //Note: we need to decide how we will keep track of
        //visit dates



        String[] results = new String[16];

        ResultSet rs2 = null;
        try
        {
            System.out.println("curPatient: "+ HealthPortal.currUser );
            String get_visits = "SELECT * FROM Visit WHERE ID=" + HealthPortal.currUser + ";"; //query to execute
            rs = HealthPortal.statement.executeQuery(get_visits);
            System.out.println(get_visits);
            //rs = HealthPortal.statement.executeQuery(get_visits); //executing query
            int i;  //index to iterate through array.
            rs.last();
            if (!(rs.first())) //check if we got some rows.
            {

                for ( i = 0; i < results.length; i++) {
                    results[i] = "N/A";
                }

            }else if (rs.isFirst() && rs.isLast())//iterate through result
            {
                //add each column value into the array
                results[0] = rs.getString("Date");
                results[1] = rs.getString("Height");
                results[2] = rs.getString("Weight");
                results[3] = rs.getString("Pressure");
                results[4] = rs.getString("Temp");
                results[5] = rs.getString("Allergies");
                results[6] = rs.getString("Prescription");
                results[7] = rs.getString("Memo");
                for (i = 8; i < results.length; i++) {
                    results[i] = "N/A";
                }
            }else {
                i = 0; //otherwise, go back to the beginning
                if (rs.first()) {   //go back to first.
                    while (rs.getRow() < 3)//iterate through result
                    {
                        //add each column value into the array
                        results[i] = rs.getString("Date");
                        results[i + 1] = rs.getString("Height");
                        results[i + 2] = rs.getString("Weight");
                        results[i + 3] = rs.getString("Pressure");
                        results[i + 4] = rs.getString("Temp");
                        results[i + 5] = rs.getString("Allergies");
                        results[i + 6] = rs.getString("Prescription");
                        results[i + 7] = rs.getString("Memo");
                        i = i + 8;
                        rs2.next();
                    }
                }
            }
                } catch(SQLException e){
                    System.out.println(e);
                    e.printStackTrace();
                }



        //these are nurse's categories of what they take at each appointment
        //there are 2 of each because this screen displays 2 dummy previous visits
        //these will all be parsed in, ex. Height: + [string that holds height] from nurse's input
        //visit dates
        date1 = new Text("Visit Date: " + results[0]);
        date1.setFont(Font.font("Times New Roman", 14));
        date1.setFill(Color.BLACK);

        date2 = new Text("Visit Date:" + results[8]);
        date2.setFont(Font.font("Times New Roman", 14));
        date2.setFill(Color.BLACK);

        height = new Text("Height: " + results[1]);
        height.setFont(Font.font("Times New Roman", 14));
        height.setFill(Color.BLACK);

        height2 = new Text("Height: "+ results[9] + "inches");
        height2.setFont(Font.font("Times New Roman", 14));
        height2.setFill(Color.BLACK);

        weight = new Text("Weight: " + results[2]+ "lbs");
        weight.setFont(Font.font("Times New Roman", 14));
        weight.setFill(Color.BLACK);

        weight2 = new Text("Weight: " + results[10] + "lbs");
        weight2.setFont(Font.font("Times New Roman", 14));
        weight2.setFill(Color.BLACK);

        bloodPressure = new Text("Blood Pressure: " + results[3] + "mmHg");
        bloodPressure.setFont(Font.font("Times New Roman", 14));
        bloodPressure.setFill(Color.BLACK);

        bloodPressure2 = new Text("Blood Pressure: "+ results[11] + "degrees");
        bloodPressure2.setFont(Font.font("Times New Roman", 14));
        bloodPressure2.setFill(Color.BLACK);

        bodyTemp = new Text("Body Temperature: " + results[4] + "mmHg");
        bodyTemp.setFont(Font.font("Times New Roman", 14));
        bodyTemp.setFill(Color.BLACK);

        bodyTemp2 = new Text("Body Temperature: " +  results[12] + "mmHg");
        bodyTemp2.setFont(Font.font("Times New Roman", 14));
        bodyTemp2.setFill(Color.BLACK);

        allergies = new Text("Allergies: " + results[5]);
        allergies.setFont(Font.font("Times New Roman", 14));
        allergies.setFill(Color.BLACK);

        allergies2 = new Text("Allergies: " + results[13]);
        allergies2.setFont(Font.font("Times New Roman", 14));
        allergies2.setFill(Color.BLACK);

        prescription = new Text("Prescription: "+ results[6]);
        prescription.setFont(Font.font("Times New Roman", 14));
        prescription.setFill(Color.BLACK);

        prescription2 = new Text("Prescription: " + results[14]);
        prescription2.setFont(Font.font("Times New Roman", 14));
        prescription2.setFill(Color.BLACK);

        //doctor's notes will be parsed in as well, this is the label for them
        doctorNotes = new Text("\nDoctor/Nurse's Notes:");
        doctorNotes.setFont(Font.font("Times New Roman", 14));
        doctorNotes.setFill(Color.BLACK);

        //these will be taken from the doctor's notes input as a string
        memo = new Text("Memo: " + results[7]);
        memo.setFont(Font.font("Times New Roman", 14));
        memo.setFill(Color.BLACK);

        memo2 = new Text("Memo: " + results[15]);
        memo2.setFont(Font.font("Times New Roman", 14));
        memo2.setFill(Color.BLACK);

        notes = new Text("Memo: " + results[7]);
        notes.setFont(Font.font("Times New Roman", 14));
        notes.setFill(Color.BLACK);

        notes = new Text("Memo: " + results[15]);
        notes.setFont(Font.font("Times New Roman", 14));
        notes.setFill(Color.BLACK);

        /*notes = new Text("-03/04 - slight pain in knee, likely baseball, tylenol referral");
        notes.setFont(Font.font("Times New Roman", 14));
        notes.setFill(Color.BLACK); */

        //back button takes the patient back to their portal screen from viewing their summary
        back = new Button("Patient Menu");

        //forward event handler for the back button takes user back to existing
        //patient portal screen, case 5
        ForwardButton handler = new ForwardButton(5);
        back.setOnAction(handler);

        //vertical panes for each group of information on the page, to be placed
        //in column vertical panes and then in a horizontal pane for display purposes
        VBox nameBox = new VBox(2);
        nameBox.getChildren().

                addAll(welcome, dob);

        VBox contactBox = new VBox(2);
        contactBox.getChildren().

                addAll(contactInfo, phone, email, address);

        VBox insBox = new VBox(2);
        insBox.getChildren().

                addAll(insurance, insuranceNum, pharmacy);

        VBox prevMedBox = new VBox(2);
        prevMedBox.getChildren().

                addAll(medHisTitle, medHis);

        //put a lot of space between the dates so they
        //align with their corresponding height/weight etc.
        VBox visitBox = new VBox(100);
        visitBox.getChildren().

                addAll(date1, date2);

        VBox visit1Box = new VBox(2);
        visit1Box.getChildren().

                addAll(height, weight, bloodPressure, bodyTemp, allergies, prescription);

        VBox visit2Box = new VBox(2);
        visit2Box.getChildren().

                addAll(height2, weight2, bloodPressure2, bodyTemp2, allergies2, prescription2);

        //back button is in this box as it is displayed low on the screen and has some
        //insets for aesthetic
        VBox docBox = new VBox(2);
        docBox.getChildren().

                addAll(doctorNotes, notes, back);
        VBox.setMargin(back, new

                Insets(20, 0, 0, 200));

        //vertical panes for each column
        VBox column1 = new VBox(8);
        column1.getChildren().

                addAll(nameBox, contactBox, insBox, prevMedBox);

        VBox column2 = new VBox(8);
        column2.getChildren().

                addAll(visitBox);

        VBox column3 = new VBox(16);
        column3.getChildren().

                addAll(visit1Box, visit2Box);

        //horizontal pane to store columns
        HBox colBox = new HBox(8);
        colBox.getChildren().

                addAll(column1, column2, column3);

        //border pane to put horizontal pane in the center, and the doctor's notes/button
        //on the bottom of the screen

        //border pane to center the grid pane contents
        BorderPane bp = new BorderPane();
        bp.setCenter(colBox);
        bp.setBottom(docBox);
        bp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        //add the title to the top left of this stack pane
        StackPane.setAlignment(title, Pos.TOP_LEFT);
        StackPane.setMargin(title, new

                Insets(10, 0, 0, 10));
        this.

                getChildren().

                add(title);

        //add the border pane to this stack pane
        this.

                getChildren().

                add(bp);
    } //end constructor
    /*public void handle(ActionEvent event) {
        String [] results = new String[6];
        resultSet rs = 2;
        String sql2 = "SELECT Patient Contact Information Insurance Previous Medical History Visit Date Height Weight Blood Pressure Body Temp Allergies Presccription Visit Date Height Weight Blood Pressure Body Temp Allergies Prescription"
        int i = 0;
        if (rs2.first() == true) {
            while(rs2.next()) {
                result[i] = rs2.getString(columnLabel:"Patient");
                result[i + 1] = rs2.getString(columnLabl:"Contact Information");
                result[i + 2] = rs2.getString(columnLabl:"Insurance");
                result[i + 3] = rs2.getString(columnLabel:"Previous Medical History");
                result[i + 4] = rs2.getString(columnLabl:"Visit Date");
                result[i + 5] = rs2.getString(columnLabel:"Weight");
                result[i + 6] = rs2.getString(columnLabl:"Blood Pressure");
                result[i + 7] = rs2.getString(columnLabel:"Body Temp");
                result[i + 8] = rs2.getString(columnLabel:"Allergies");
                result[i + 9] = rs2.getString(columnLabel:"Prescription");
                i = i + 10;
            } */

}


//} //end class

