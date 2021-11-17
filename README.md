# CSE360
CSE360 Project for Fall 2021 <br />
<br />
Class: Tuesday 9:00AM <br />
Group #: 22 <br />
Members: Azrin Averitte, Jacqueline Dworaczyk, Marek Niemyjski, Sydney Wilcox, Zaid Usmani <br />
<br />
This is our group project for CSE360 for the Fall 2021 semester. What we have done is made a patient portal named "SunDevil Pediatric Health Portal". <br />
What our program does is it acts as a portal for a pediatric facility. <br />
It allows patients of the facility to be able to sign themselves up and putting in their contact information before their first visit. <br />
Exisitng patients are also able to login using their first and last name as well as their date of birth. <br />
Once they successfully login, then a patient can view a summary of the current contact information as well as their past visit information. <br />
They can also update their contact information if it is incorrect. <br />
The final thing that patients can do is see their previous message history and are able to send messages to either their doctor or nurse. <br />
<br />
Additionally nurses and doctors are also able to use the portal. <br />
Both patients and doctors will sign in using their username and password. <br />
Once they are signed in then both are prompted to select their current patient. There is a dropdown list that is displayed and they can choose a patient. <br />
Nurses also have an additional functionality where they can create <br />
a new patient in the case someone came to the facility without signing up before and it is their first time. <br />
Once a nurse selects a patient, then they are taken to the patient vitals screen in order to put in vitals for this patient for their visit. <br />
On this page the nurse can also select to view their message history and send messages to this patient and also to view the currently selected patient's summary. <br />
<br />
When a doctor selects their patient they are taken to the selected patient's summary with a field they are to fill in, a memo and a prescription for this visit. <br />
Doctors similarly can also go to their message history with this patient and send a message to them if they wish. <br />
<br />
And that is the summary of our project. <br />
<br />
<br />
GETTING STARTED: <br />
In order to run our programs there are a couple things you need to do first. <br />
We used an sql server to store all our information and our program uses that to access everything. <br />
Before you run the program you are going to need access to this server. <br />
We used SSH to first connect to physical hardware where the sql server is located so we need to set that up. <br />
In order to make the SSH connection we used RSA SSH keys so you need to set that up. <br />
Here are the following steps to setting up an rsa key to be used by ssh: <br />
<br />
1. Go to your .ssh directory (typically a hidden directory under your user directory) <br />
```console
$ cd .ssh
```
<br />
2. Check to see if you have an id_rsa and id_rsa.pub file <br />
```console
$ ls
```
<br />
3. If you do not have these files run this command to generate a key. <br />
```console
$ ssh-keygen -t rsa
```
When prompted set the name of the file as "id_rsa" and do not set a password (just hit enter when prompted). <br />
<br />
4. After generating the key or if you already have the key, we need to set the key to the correct format <br />
```console
 $ ssh-keygen -p -f id_rsa -m PEM -P "" -N ""
```
<br />
5. Now, all we need to do to connect to the server is to add your public key to the authorized keys on the server. <br />
In order to do this, PLEASE EMAIL your id_rsa.pub to marek at "mniemyjs@asu.edu". <br />
Once marek receive your public key he will add it to the authorized keys on the server. <br />
<br />
6. Once marek has email you back saying he has added your public key, run this command to make sure that it worked. <br />
```console
$ ssh marek@70.162.165.24
```
if you successfully connect just run this command <br />
```console
$ exit
```
<br />
7. One last step in order to finish setting up the ssh connection. <br />
Our program checks your known_hosts file to see if you are authorized to connect so go back to your .ssh directory and run this <br />
```console
$ ssh-keyscan -H -t rsa 70.162.165.24 >> known_hosts
```
This formats known_hosts so that the script works. <br />
<br />
Once you do all that then the final step you need to do is to make sure that you have a JDK version which supports JavaFX and also <br />
the two JAR files located in the Libraries directory are added to your project sturcutre so that you can use their imports. <br />
<br />
<br />
USEFUL INFORMATION FOR TESTING PURPOSES: <br />
Here are screenshots of each of our tables our database has <br />
Patient Table: <br />
![alt text](https://github.com/marek0039/CSE360/blob/main/Images/Patient_Table.png?raw=true)

Professional Table:
![alt text](https://github.com/marek0039/CSE360/blob/main/Images/Professional_Table.png?raw=true) <br /> <br /> <br />

Visit Table:
![alt text](https://github.com/marek0039/CSE360/blob/main/Images/Visit_Table.png?raw=true) <br /> <br /> <br />

Message Table:
![alt text](https://github.com/marek0039/CSE360/blob/main/Images/Message_Table.png?raw=true) <br /> <br /> <br />

