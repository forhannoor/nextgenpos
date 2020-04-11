// Handles database operations for users.

package nextgenpos;

import java.sql.SQLException;

public class UserDatabase extends Database{

	private String table;
	
	public UserDatabase(String table){
		this.table = table;
	}
	
	public UserDatabase(String u, String p, String database, String table){
		super(u, p, database);
		this.table = table;
	}
	
	public User getWhere(String where, String value){
		User u = null;
		setConnection();
		
		try{
			String query = "SELECT * " + this.table;
			query += " WHERE " + where + " = '" + value + "'";
			rs = st.executeQuery(query);
			
			if(rs.next()){
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		} finally{
			closeConnection();
		}
		
		return u;
	}
}