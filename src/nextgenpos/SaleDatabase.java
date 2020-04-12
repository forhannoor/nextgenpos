// Handles database operations for sales.

package nextgenpos;

import java.sql.SQLException;

public class SaleDatabase extends Database{
	private final String TABLE = "Sales";
	
	public void insert(Sale s){
		setConnection();
		
		try{
			ps = con.prepareStatement("INSERT INTO " + TABLE + " (description, amount) VALUES (?, ?)");
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