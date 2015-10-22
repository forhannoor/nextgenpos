package nextgenpos;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDatabase extends Database{

	private String table;
	
	public ProductDatabase(String table){
		this.table = table;
	}
	
	public ProductDatabase(String u, String p, String database, String table){
		super(u, p, database);
		this.table = table;
	}
	
	public Product getWhere(String where, String value){
		Product u = null;
		
		try{
			setConnection();
			String query = "SELECT * FROM " + this.table;
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
			String query = "SELECT * FROM " + this.table;
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