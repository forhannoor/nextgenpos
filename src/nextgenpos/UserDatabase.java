package nextgenpos;

import java.sql.SQLException;

public class UserDatabase {

	private Database db;
	private String table;
	
	public UserDatabase(Database db, String table){
		this.db = db;
		this.table = table;
	}
	
	public User getWhere(String where, String value){
		User u = null;
		db.setConnection();
		
		try{
			String query = "SELECT id, username, password, lastLogin FROM " + this.table;
			query += " WHERE " + where + " = '" + value + "'";
			db.setRs(db.getSt().executeQuery(query));
			
			if(db.getRs().next()){
				u = new User(db.getRs().getInt(1), db.getRs().getString(2), db.getRs().getString(3), db.getRs().getString(4));
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		} finally{
			this.db.closeConnection();
		}
		
		return u;
	}
	
	public static void main(String[] args) {
		Database d = new Database("root", "", "nextgenpos");
		UserDatabase u = new UserDatabase(d, "users");
		System.out.println(u.getWhere("username", "forhan"));
	}
}