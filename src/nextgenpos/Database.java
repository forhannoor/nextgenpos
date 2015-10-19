package nextgenpos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    protected String user;
    protected String password;
    protected Connection con;
    protected Statement st;
    protected ResultSet rs;
    protected String url;
    protected PreparedStatement ps;
    
    public Database(String u, String p, String database){
        user = u;
        password = p;
        con = null;
        st = null;
        rs = null;
        url = "jdbc:mysql://localhost/" + database;
        ps = null;
    }
    
    public Database(){
        user = "root";
        password = "";
        con = null;
        st = null;
        rs = null;
        url = "jdbc:mysql://localhost/nextgenpos";
        ps = null;
    }

	// establish connection
    public void setConnection(){
        try {
            this.con = DriverManager.getConnection(url, user, password);
            this.st = this.con.createStatement();
        } catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }
        
    // terminate connection
    public void closeConnection(){
        try {
            if (this.rs != null) {
                this.rs.close();
            }
            if (this.st != null) {
                this.st.close();
            }
            if (this.con != null) {
                this.con.close();
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}