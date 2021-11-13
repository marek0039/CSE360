import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class ForwardButton implements EventHandler<ActionEvent>
{
    //int to store which case of forwarding we're on based
    //on what button is being pushed on what screen
    private int cI;
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
	private MedProfLoginScreen mpls;
	private DocSelectPatient docSelPatient;
	private DocPatientSummary docSummary;
	private DocMemoConfirmation docMemoConf;
	private DocMessagePortal docMesPortal;
	private DocSendMessage docSendMessage;
	private DocMessageConfirmation docMessageConf;
	private NurseSelectPatient nurseSelect;
    private NurseCreatePatient nurseCreatePatient;
    private NurseCreatePatientConfirmation nurseCreateConf;
    private NurseVitals nurseVitals;
    private NurseVitalsConfirmation nurseVitalsConf;
    private NursePatientSummary nurseSummary;
    private NurseMessagePortal nurseMesPortal;
    private NurseSendMessage nurseSendMessage;
    private NurseMessageConfirmation nurseMessageConf;

    public ForwardButton(int caseInt)
    {

        cI = caseInt;
    }

    public void setcI(int cI) {
        this.cI = cI;
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
                HealthPortal.root.getChildren().clear();
                userChoice = new UserChoiceScreen();
                //add the user choice pane to the root because that is the next screen
                HealthPortal.root.getChildren().add(userChoice);
                break;
            case 2: //next button on user choice screen
                    //if they chose patient add the patient choice screen
                    HealthPortal.root.getChildren().clear();
                    ps = new PatientScreen();
                    HealthPortal.root.getChildren().add(ps);
                //CONDITION FOR IF USER DOESNT SELECT ANYTHING.. ERROR HANDLING

                break;
            case 3: //submit button on new patient form
                //takes patient to new patient confirmation page
                HealthPortal.root.getChildren().clear();
                newPConf = new NewPatientConfirmation();
                HealthPortal.root.getChildren().add(newPConf);

                //NEED TO CONFIRM THAT USER ENTERED EVERYTHING REQUIRED.. ERROR HANDLING

                break;
            case 4: //back button on new patient confirmation screen takes you back
                //to the new patient form
                HealthPortal.root.getChildren().clear();
                newPForm = new NewPatientForm();
                HealthPortal.root.getChildren().add(newPForm);
                break;
            case 5: //submit button on existing patient log on screen OR patient menu button on
                //all from the existing patient portal page, they will be taken back there
                //new patient confirmation screen both go to existing patient portal screen
                HealthPortal.root.getChildren().clear();
                epp = new ExistingPatientPortal();
                HealthPortal.root.getChildren().add(epp);

                //NEED TO CONFIRM USER ENTERED ALL LOG IN INFORMATION & EXISTS IN
                //SYSTEM IN CASE OF EXISTING PATIENT LOG ON.. ERROR HANDLING

                break;
            case 6: //back button on existing patient portal screen takes you back
                //to existing patient log on because the patient has already
                //been created in the database and thus they should be able to log in
                //even if they just filled out the new patient form
                HealthPortal.root.getChildren().clear();
                eplo = new ExistingPLogOn();
                HealthPortal.root.getChildren().add(eplo);
                break;
            case 7: //user selects go from existing patient portal screen to
                //update their contact information OR user selects back on update contact info confirmation
                HealthPortal.root.getChildren().clear();
                pUpdateInfo = new PatientUpdateInfo();
                HealthPortal.root.getChildren().add(pUpdateInfo);
                break;
            case 8: //user selects submit after updating their contact information
                //they should go to the update contact info confirmation screen
                HealthPortal.root.getChildren().clear();
                updateInfoConf = new UpdateInfoConfirmation();
                HealthPortal.root.getChildren().add(updateInfoConf);
                break;
            case 9: //user selects go from existing patient portal screen to
                //view their previous visit summary OR user selects view summary
                //from update contact info confirmation page OR
                HealthPortal.root.getChildren().clear();
                pSummary = new PatientSummary();
                HealthPortal.root.getChildren().add(pSummary);
                break;
            case 10: //user selects go from existing patient portal screen to
                //send a message (takes them to their message portal screen) OR
                //user selects back from send message screen
                HealthPortal.root.getChildren().clear();
                pMesPortal = new PatientMessagePortal();
                HealthPortal.root.getChildren().add(pMesPortal);
                break;
            case 11: //user selects to go to the screen to actually type out
                //and send a message from message portal screen
                HealthPortal.root.getChildren().clear();
                pSendMes = new PatientSendMessage();
                HealthPortal.root.getChildren().add(pSendMes);
                break;
            case 12: //user selects to send a message, and move forward to message
                //confirmation page

                //WE NEED TO MAKE SURE THEY'VE SELECTED A RECIPIENT AND PUT MESSAGE CONTENT
                //IN THE FIELD BEFORE THIS HAPPENS

                HealthPortal.root.getChildren().clear();
                pMesConf = new PatientMessageConfirmation();
                HealthPortal.root.getChildren().add(pMesConf);
                break;
            case 13: //medical professional has logged in they go to doctor screen
                //if they're a doctor OR nurse screen if theyre a nurse

                //NEED TO DETERMINE WHETHER MED PROF IS A DOCTOR OR NURSE
                //VIA DATABASE DATA BEFORE WRITING CODE TO GO TO NEXT PAGE

                break;
            case 14: //doctor selects logout/back from patient choice page
                //if they chose med prof add the med prof log on screen
                //it takes them back to med prof login screen
                HealthPortal.root.getChildren().clear();
                mpls = new MedProfLoginScreen();
                HealthPortal.root.getChildren().add(mpls);
                break;
            case 15://doctor selects patient from list and hits go
                //to be taken to their patient page

                //NEED TO CREATE CONDITIONS BY PULLING FROM DATABASE
                //I.E. WHICH PATIENT WAS SELECTED BEFORE SENDING THE GO BUTTON ANYWHERE
                //for now its on the default example screen

                HealthPortal.root.getChildren().clear();
                docSummary = new DocPatientSummary();
                HealthPortal.root.getChildren().add(docSummary);
                break;
            case 16:
                HealthPortal.root.getChildren().clear();
                docSelPatient = new DocSelectPatient();
                HealthPortal.root.getChildren().add(docSelPatient);
                break;
            case 17:
                HealthPortal.root.getChildren().clear();
                docMemoConf = new DocMemoConfirmation();
                HealthPortal.root.getChildren().add(docMemoConf);
                break;
            case 18:
                HealthPortal.root.getChildren().clear();
                docMesPortal = new DocMessagePortal();
                HealthPortal.root.getChildren().add(docMesPortal);
                break;
            case 19:
                HealthPortal.root.getChildren().clear();
                docSendMessage = new DocSendMessage();
                HealthPortal.root.getChildren().add(docSendMessage);
                break;
            case 20:
                HealthPortal.root.getChildren().clear();
                docMessageConf = new DocMessageConfirmation();
                HealthPortal.root.getChildren().add(docMessageConf);
                break;
            case 21:
                HealthPortal.root.getChildren().clear();
                nurseSelect = new NurseSelectPatient();
                HealthPortal.root.getChildren().add(nurseSelect);
                break;
            case 22:
                HealthPortal.root.getChildren().clear();
                nurseCreatePatient = new NurseCreatePatient();
                HealthPortal.root.getChildren().add(nurseCreatePatient);
                break;
            case 23:
                HealthPortal.root.getChildren().clear();
                nurseCreateConf = new NurseCreatePatientConfirmation();
                HealthPortal.root.getChildren().add(nurseCreateConf);
                break;
            case 24:
                HealthPortal.root.getChildren().clear();
                nurseVitals = new NurseVitals();
                HealthPortal.root.getChildren().add(nurseVitals);
                break;
            case 25:
                HealthPortal.root.getChildren().clear();
                nurseVitalsConf = new NurseVitalsConfirmation();
                HealthPortal.root.getChildren().add(nurseVitalsConf);
                break;
            case 26:
                HealthPortal.root.getChildren().clear();
                nurseSummary = new NursePatientSummary();
                HealthPortal.root.getChildren().add(nurseSummary);
                break;
            case 27:
                HealthPortal.root.getChildren().clear();
                nurseMesPortal = new NurseMessagePortal();
                HealthPortal.root.getChildren().add(nurseMesPortal);
                break;
            case 28:
                HealthPortal.root.getChildren().clear();
                nurseSendMessage = new NurseSendMessage();
                HealthPortal.root.getChildren().add(nurseSendMessage);
                break;
            case 29:
                HealthPortal.root.getChildren().clear();
                nurseMessageConf = new NurseMessageConfirmation();
                HealthPortal.root.getChildren().add(nurseMessageConf);
                break;
        } //end switch
    } //end handle
} //end forward class
