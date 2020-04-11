// Handles database operations for sales.

package nextgenpos;

import java.sql.SQLException;

public class SaleDatabase extends Database{
private String table;
	
	public SaleDatabase(String table){
		this.table = table;
	}
	
	public SaleDatabase(String u, String p, String database, String table){
		super(u, p, database);
		this.table = table;
	}
	
	public void insert(Sale s){
		setConnection();
		
		try{
			ps = con.prepareStatement("INSERT INTO " + table + " (description, amount) VALUES (?, ?)");
			ps.setString(1, s.getDescription());
			ps.setString(2, s.getAmount() + "");
			ps.executeUpdate();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		} finally{
			closeConnection();
		}
	}
}