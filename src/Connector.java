import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connector {
    private Statement statement;

    public Statement getStatement() {
        return statement;
    }

    public Connector() {
        try {
            String ssh_host = "70.162.165.24";  //ip of the sql server
            String ssh_user = "marek";          //user on the server (this is like the desktop user, don't change)
            String db_host = "localhost";       //this is the database host. It is local on the ssh machine
            int client_port = 4321;             //port on your computer which will hold the ssh connection.
            int db_port = 3306;                 //port on server which will holds the database
            String db_user = "root";            //username for the database
            String db_pswd = "root";            //password for the database
            String db = "cse360";               //the database name itself

            JSch jsch = new JSch();             //class which will do ssh
            jsch.addIdentity("~/.ssh/id_rsa");   //your private key file to be used for authentication
            jsch.setKnownHosts("~/.ssh/known_hosts");   //a second file used for authentication

            Session session = jsch.getSession(ssh_user, ssh_host);  //start the ssh session
            session.connect();  //connect
            int assigned_port=session.setPortForwardingL(client_port, db_host, db_port); //set the port on your machine
            System.out.println("localhost:"+assigned_port+" -> "+db_host+":"+db_port);  //for debugging purposes

            String jdbc_driver = "com.mysql.cj.jdbc.Driver"; //driver to use for sql connection
            String url = "jdbc:mysql://" + db_host + ":" + client_port + "/"; //the sql url to use
            System.setProperty(jdbc_driver,""); //set the driver

            Class.forName(jdbc_driver);
            Connection connection = DriverManager.getConnection(url+db, db_user, db_pswd); //connect to sql db

            statement = connection.createStatement(); //create a statement which will execute an sql cmd.
        } catch(Exception e) {  //catch exceptions
            System.err.print(e);
        }
    }
}
