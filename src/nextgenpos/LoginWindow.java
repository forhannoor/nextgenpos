// Class responsible for user authentication. 

package nextgenpos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.swing.*;

public class LoginWindow extends JFrame implements ActionListener{
	private JTextField username;
	private JPasswordField password;
	private JButton login;
	
	public LoginWindow(){
		// window layout
		setLayout(new GridLayout(1, 2));
	
		// left panel containing logo
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(1, 1));
		
		// right panel containing login components
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(5, 1, 0, 5));
		
		// add logo to left panel
		ImageIcon i = new ImageIcon("asset/images/user.png");
		JLabel logo = new JLabel(i);
		left.add(logo);
		
		// initialize login components
		username = new JTextField();
		password = new JPasswordField();
		login = new JButton("Login");
		login.addActionListener(this);
		
		// add login components to right panel
		right.add(new JLabel("Username"));
		right.add(username);
		right.add(new JLabel("Password"));
		right.add(password);
		right.add(login);
		
		add(left);
		add(right);
		
		setSize(500, 200);
		setTitle("Login Window");
		setVisible(true);
		setLocation(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == login){ // login button action
			String u = username.getText();
			String p = String.valueOf(password.getPassword());
			
			if(u.length() > 0 && p.length() > 0){ // input for both fields are present
				p = encryptPassword(p);
				UserDatabase ub = new UserDatabase("users");
				User found = ub.getWhere("username", u);
				
				if(found != null){ // record exists for username
					if(found.getPassword().equals(p)){ // password matches
						JOptionPane.showMessageDialog(null, "Login Successful!");
					}
					
					else{ // password doesn't match
						JOptionPane.showMessageDialog(null, "Invalid credential! Please try again.");
						password.setText("");
					}
				}
				
				else{ // record doesn't exist
					JOptionPane.showMessageDialog(null, "Invalid credential. Please try again.");
					username.setText("");
					password.setText("");
				}
			}
			
			else{
				JOptionPane.showMessageDialog(null, "Both username and password are required!");
			}
		}
	}
	
	// hash password using SHA-1 algorithm
	private String encryptPassword(String password){
		try{
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		    crypt.reset();
		    crypt.update(password.getBytes("UTF-8"));
		    return new BigInteger(1, crypt.digest()).toString(16);
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}