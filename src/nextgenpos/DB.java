package nextgenpos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    private String user;
    private String password;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private String url;
    private PreparedStatement ps;
    
    public DB(String u, String p){
        user = u;
        password = p;
        con = null;
        st = null;
        rs = null;
        url = "jdbc:mysql://localhost/pos";
        ps = null;
    }
    
    public void setConnection(){
        try {
            this.con = DriverManager.getConnection(url, user, password);
        } catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }
    
    public String getUserPassword(String username){
        String result = null;
        
        try{
            this.setConnection();
            this.st = this.con.createStatement();
            String q = "SELECT password from POS.USERS where username = ";
            q +="'";
            q += username;
            q +="'";
            this.rs = this.st.executeQuery(q);

            if(this.rs.next()){
                result = this.rs.getString(1);
            }
        } catch(SQLException se){
            System.out.println(se.getMessage());
        } finally{
            this.closeConnection();
        }
        return result;
    }
    
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
            Logger lgr = Logger.getLogger(DB.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
    
    public void getProducts(){
        String result = null;
        Product x;
        
        try{
            this.setConnection();
            this.st = this.con.createStatement();
            String q = "SELECT * from POS.PRODUCTS";
            this.rs = this.st.executeQuery(q);
            
            while(this.rs.next()){
                x = new Product(this.rs.getString(1), this.rs.getString(2), this.rs.getString(3), Integer.parseInt(this.rs.getString(4)), Double.parseDouble(this.rs.getString(5)));
            }
        } catch(SQLException se){
            System.out.println(se.getMessage());
        } finally{
            this.closeConnection();
        }
    }
    
    public void saveSale(SaleConduct s){
        double amount = s.getTotal();
        
        try{
            this.setConnection();
            this.st = this.con.createStatement();
            String q = "INSERT INTO POS.SALES (amount) VALUES (";
            q += Double.toString(amount);
            q += ")";
            this.st.executeUpdate(q);
        } catch(SQLException se){
            System.out.println(se.getMessage());
        } finally{
            this.closeConnection();
        }
    }
    
    public void addProduct(Product p){
        try{
            this.setConnection();
            this.ps = this.con.prepareStatement("INSERT INTO POS.PRODUCTS (name, brand, quantity, price) VALUES (?, ?, ?, ?)");
            this.ps.setString(1, p.getName());
            this.ps.setString(2, p.getBrand());
            this.ps.setString(3, Integer.toString(p.getQuantity()));
            this.ps.setString(4, Double.toString(p.getPrice()));
            this.ps.executeUpdate();
        } catch(SQLException se){
            System.out.println(se.getMessage());
        } finally{
            this.closeConnection();
        }
    }
}