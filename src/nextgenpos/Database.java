package nextgenpos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private String user;
    private String password;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String url;
    private PreparedStatement ps;
    
    public Database(String u, String p, String database){
        user = u;
        password = p;
        con = null;
        st = null;
        rs = null;
        url = "jdbc:mysql://localhost/" + database;
        ps = null;
    }
    
    public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
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