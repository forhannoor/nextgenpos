package nextgenpos;

import javax.swing.*;

public class LoginWindow extends JFrame {
	private JTextField username;
	private JPasswordField password;
	private JButton login;
	
	public LoginWindow(){
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new LoginWindow();
	}
}