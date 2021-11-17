# CSE360
CSE360 Project for Fall 2021

Class: Tuesday 9:00AM
Group #: 22
Members: Azrin Averitte, Jacqueline Dworaczyk, Marek Niemyjski, Sydney Wilcox, Zaid Usmani

This is our group project for CSE360 for the Fall 2021 semester. What we have done is made a patient portal named "SunDevil Pediatric Health Portal".
What our program does is it acts as a portal for a pediatric facility. 
It allows patients of the facility to be able to sign themselves up and putting in their contact information before their first visit.
Exisitng patients are also able to login using their first and last name as well as their date of birth.
Once they successfully login, then a patient can view a summary of the current contact information as well as their past visit information.
They can also update their contact information if it is incorrect. 
The final thing that patients can do is see their previous message history and are able to send messages to either their doctor or nurse.

Additionally nurses and doctors are also able to use the portal.
Both patients and doctors will sign in using their username and password.
Once they are signed in then both are prompted to select their current patient. There is a dropdown list that is displayed and they can choose a patient.
Nurses also have an additional functionality where they can create 
a new patient in the case someone came to the facility without signing up before and it is their first time.
Once a nurse selects a patient, then they are taken to the patient vitals screen in order to put in vitals for this patient for their visit. 
On this page the nurse can also select to view their message history and send messages to this patient and also to view the currently selected patient's summary.

When a doctor selects their patient they are taken to the selected patient's summary with a field they are to fill in, a memo and a prescription for this visit.
Doctors similarly can also go to their message history with this patient and send a message to them if they wish.

And that is the summary of our project.

GETTING STARTED:
In order to run our programs there are a couple things you need to do first.
We used an sql server to store all our information and our program uses that to access everything. 
Before you run the program you are going to need access to this server. 
We used SSH to first connect to physical hardware where the sql server is located so we need to set that up.
In order to make the SSH connection we used RSA SSH keys so you need to set that up.
Here are the following steps to setting up an rsa key to be used by ssh:

1. Go to your .ssh directory (typically a hidden directory under your user directory)
bash cmd= $ cd .ssh

2. Check to see if you have an id_rsa and id_rsa.pub file
bash cmd= $ ls

3. If you do not have these files run this command to generate a key.
bash cmd = $ ssh-keygen -t rsa
When prompted set the name of the file as "id_rsa" and do not set a password (just hit enter when prompted).

4. After generating the key or if you already have the key, we need to set the key to the correct format
bash cmd = $ ssh-keygen -p -f id_rsa -m PEM -P "" -N ""

5. Now, all we need to do to connect to the server is to add your public key to the authorized keys on the server.
In order to do this, PLEASE EMAIL your id_rsa.pub to marek at "mniemyjs@asu.edu".
Once marek receive your public key he will add it to the authorized keys on the server.

6. Once marek has email you back saying he has added your public key, run this command to make sure that it worked.
bash cmd = $ ssh marek@70.162.165.24
if you successfully connect just run this command
bash cmd = $ exit

7. One last step in order to finish setting up the ssh connection. 
Our program checks your known_hosts file to see if you are authorized to connect so go back to your .ssh directory and run this
bash cmd= $ ssh-keyscan -H -t rsa 70.162.165.24 >> known_hosts
This formats known_hosts so that the script works.

Once you do all that then the final step you need to do is to make sure that you have a JDK version which supports JavaFX and also
the two JAR files located in the Libraries directory are added to your project sturcutre so that you can use their imports.


USEFUL INFORMATION FOR TESTING PURPOSES:
Here are screenshots of each of our tables our database has

Patient Table:
![alt text](https://github.com/marek0039/CSE360/blob/main/Images/Patient_Table.png?raw=true)



Professional Table:
![alt text](https://github.com/marek0039/CSE360/blob/main/Images/Professional_Table.png?raw=true)



Visit Table:
![alt text](https://github.com/marek0039/CSE360/blob/main/Images/Visit_Table.png?raw=true)



Message Table:
![alt text](https://github.com/marek0039/CSE360/blob/main/Images/Message_Table.png?raw=true)

