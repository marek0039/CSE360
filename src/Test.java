import java.sql.*;  //used for sql stuff
import com.jcraft.jsch.*;    //used for ssh connection

public class Test {
    public static void main(String[] args) {
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

            String jdbc_driver = "com.mysql.jdbc.Driver"; //driver to use for sql connection
            String url = "jdbc:mysql://" + db_host + ":" + client_port + "/"; //the sql url to use
            System.setProperty(jdbc_driver,""); //set the driver

            Class.forName(jdbc_driver);
            Connection connection = DriverManager.getConnection(url+db, db_user, db_pswd); //connect to sql db

            Statement statement = connection.createStatement(); //create a statement which will execute an sql cmd.
            String sql = "SELECT * FROM test";  //sql command
            ResultSet rs = statement.executeQuery(sql); //execute the command
            while (rs.next()) { //iterate through the lines generated
                String name = rs.getString("Name"); //store each value...
                String test_this = rs.getString("This");
                int test_is = rs.getInt("Is");
                int test_a = rs.getInt("A");
                int test = rs.getInt("Test");
                System.out.println(name);       //and print them out.
                System.out.println(test_this);
                System.out.println(test_is);
                System.out.println(test_a);
                System.out.println(test);
            }
        } catch(Exception e) {  //catch exceptions
            System.err.print(e);
        }
    }
}
