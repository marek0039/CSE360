import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class ForwardButton implements EventHandler<ActionEvent>
{
    //int to store which case of forwarding we're on based
    //on what button is being pushed on what screen
    private int cI;
    private StackPane root;
    private int currUser;

    //patient guis
    private UserChoiceScreen userChoice;
    private PatientScreen ps;
	private NewPatientForm newPForm;
	private ExistingPLogOn eplo;
	private NewPatientConfirmation newPConf;
	private ExistingPatientPortal epp;
	private PatientUpdateInfo pUpdateInfo;
	private UpdateInfoConfirmation updateInfoConf;
	private PatientSummary pSummary;
	private PatientMessagePortal pMesPortal;
	private PatientSendMessage pSendMes;
	private PatientMessageConfirmation pMesConf;

    //medical professional guis
	MedProfLoginScreen mpls;
	DocSelectPatient docSelPatient;
	DocPatientSummary docSummary;
	DocMemoConfirmation docMemoConf;
	DocMessagePortal docMesPortal;
	DocSendMessage docSendMessage;
	DocMessageConfirmation docMessageConf;
	NurseSelectPatient nurseSelect;
	NurseCreatePatient nurseCreatePatient;
	NurseCreatePatientConfirmation nurseCreateConf;
	NurseVitals nurseVitals;
	NurseVitalsConfirmation nurseVitalsConf;
	NursePatientSummary nurseSummary;
	NurseMessagePortal nurseMesPortal;
	NurseSendMessage nurseSendMessage;
	NurseMessageConfirmation nurseMessageConf;

    public ForwardButton(int caseInt, StackPane pane, int user)
    {

        cI = caseInt;
        root = pane;
        currUser = user;
    }

    //Override the abstract method handle()
    public void handle(ActionEvent event)
    {
        switch(cI)
        {
            case 1: //start button on first page OR
                //back button on patient choice screen OR med prof log on screen
                //all of them take you back to the user choice screen
                //remove the previous pane from the root
                root.getChildren().clear();
                userChoice = new UserChoiceScreen(root, currUser);
                //add the user choice pane to the root because that is the next screen
                root.getChildren().add(userChoice);
                break;
            case 2: //next button on user choice screen
                //always remove the user choice screen first
                root.getChildren().clear();
                //check if the user chose patient or med professional
                //based on combobox input using selectUser class
                if(userChoice.menu.getValue() == "Patient")
                {
                    //if they chose patient add the patient choice screen
                    root.getChildren().add(ps);
                }
                else if(userChoice.menu.getValue() == "Medical Professional")
                {
                    //if they chose med prof add the med prof log on screen
                    root.getChildren().add(mpls);
                }

                //CONDITION FOR IF USER DOESNT SELECT ANYTHING.. ERROR HANDLING

                break;
            case 3: //Next button on patient choice screen
                //check if they chose new or existing patient
                //always remove previous pane first
                root.getChildren().clear();
                if(ps.three.isSelected())
                {
                    root.getChildren().add(newPForm);
                }
                else if(ps.four.isSelected())
                {
                    root.getChildren().add(eplo);
                }

                //CONDITION FOR IF USER DOESNT SELECT ANYTHING.. ERROR HANDLING

                break;
            case 4: //back button on new patient form screen OR existing patient log on screen
                //both go to patient choice screen
                root.getChildren().clear();
                root.getChildren().add(ps);
                break;
            case 5: //submit button on new patient form
                //takes patient to new patient confirmation page
                root.getChildren().clear();
                root.getChildren().add(newPConf);

                //NEED TO CONFIRM THAT USER ENTERED EVERYTHING REQUIRED.. ERROR HANDLING

                break;
            case 6: //back button on new patient confirmation screen takes you back
                //to the new patient form
                root.getChildren().clear();
                root.getChildren().add(newPForm);
                break;
            case 7: //submit button on existing patient log on screen OR patient menu button on
                //new patient confirmation screen both go to existing patient portal screen
                root.getChildren().clear();
                root.getChildren().add(epp);

                //NEED TO CONFIRM USER ENTERED ALL LOG IN INFORMATION & EXISTS IN
                //SYSTEM IN CASE OF EXISTING PATIENT LOG ON.. ERROR HANDLING

                break;
            case 8: //back button on existing patient portal screen takes you back
                //to existing patient log on because the patient has already
                //been created in the database and thus they should be able to log in
                //even if they just filled out the new patient form
                root.getChildren().clear();
                root.getChildren().add(eplo);
                break;
            case 9: //user selects go from existing patient portal screen to
                //update their contact information OR user selects back on update contact info confirmation
                root.getChildren().clear();
                root.getChildren().add(pUpdateInfo);
                break;
            case 10: //user selects submit after updating their contact information
                //they should go to the update contact info confirmation screen
                root.getChildren().clear();
                root.getChildren().add(updateInfoConf);
                break;
            case 11: //user selects back from update contact info OR visit summary OR send message
                //all from the existing patient portal page, they will be taken back there
                root.getChildren().clear();
                root.getChildren().add(epp);
                break;
            case 12: //user selects go from existing patient portal screen to
                //view their previous visit summary OR user selects view summary
                //from update contact info confirmation page OR
                root.getChildren().clear();
                root.getChildren().add(pSummary);
                break;
            case 13: //user selects go from existing patient portal screen to
                //send a message (takes them to their message portal screen) OR
                //user selects back from send message screen
                root.getChildren().clear();
                root.getChildren().add(pMesPortal);
                break;
            case 14: //user selects to go to the screen to actually type out
                //and send a message from message portal screen
                root.getChildren().clear();
                root.getChildren().add(pSendMes);
                break;
            case 15: //user selects to send a message, and move forward to message
                //confirmation page

                //WE NEED TO MAKE SURE THEY'VE SELECTED A RECIPIENT AND PUT MESSAGE CONTENT
                //IN THE FIELD BEFORE THIS HAPPENS

                root.getChildren().clear();
                root.getChildren().add(pMesConf);
                break;
            case 16: //medical professional has logged in they go to doctor screen
                //if they're a doctor OR nurse screen if theyre a nurse

                //NEED TO DETERMINE WHETHER MED PROF IS A DOCTOR OR NURSE
                //VIA DATABASE DATA BEFORE WRITING CODE TO GO TO NEXT PAGE

                break;
            case 17: //doctor selects logout/back from patient choice page
                //it takes them back to med prof login screen
                root.getChildren().clear();
                root.getChildren().add(mpls);
                break;
            case 18://doctor selects patient from list and hits go
                //to be taken to their patient page

                //NEED TO CREATE CONDITIONS BY PULLING FROM DATABASE
                //I.E. WHICH PATIENT WAS SELECTED BEFORE SENDING THE GO BUTTON ANYWHERE
                //for now its on the default example screen

                root.getChildren().clear();
                root.getChildren().add(docSummary);
                break;
        } //end switch
    } //end handle
} //end forward class
