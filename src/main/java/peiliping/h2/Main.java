package peiliping.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class Main {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
		
        Server tcpServer = Server.createTcpServer(new String[] {
        		"-tcpAllowOthers","-tcpPort", "9081","-baseDir", "dev/logs/db1"}).start();
        Server webServer = Server.createWebServer(new String[] {"-webPort", "8082"}).start();
        boolean running=true;
        Class.forName("org.h2.Driver");
        Connection conn =DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stat = conn.createStatement();
        stat.execute("DROP TABLE BDB IF EXISTS");
        stat.execute("CREATE TABLE BDB(ID INT PRIMARY KEY, TIME VARCHAR)");
        try {
        	int i = 0 ;
            while (i<100000) {
                stat.execute("MERGE INTO BDB VALUES(1, NOW())");
                System.out.println(i++);
                Thread.sleep(500);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
        conn.close();
        tcpServer.stop();
        webServer.stop();
		
	}
}