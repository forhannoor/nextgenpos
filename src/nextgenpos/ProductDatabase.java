// Handles database operations for products.

package nextgenpos;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDatabase extends Database{
	private final String TABLE = "Products";
	
	public Product getWhere(String where, String value){
		Product u = null;
		
		try{
			setConnection();
			String query = "SELECT * FROM " + this.TABLE;
			query += " WHERE " + where + " = '" + value + "'";
			rs = st.executeQuery(query);
			
			if(rs.next()){
				u = new Product(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6));
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		} finally{
			closeConnection();
		}
		
		return u;
	}
	
	public ArrayList<Product> getAll(){
		ArrayList<Product> records = new ArrayList<Product>();
		
		try {
			setConnection();
			String query = "SELECT * FROM " + this.TABLE;
			rs = st.executeQuery(query);
			
			while(rs.next()){
				Product u = new Product(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getDouble(6));
				records.add(u);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			closeConnection();
		}
		
		return records;
	}
}