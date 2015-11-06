package nextgenpos;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Payment extends JFrame implements ActionListener, ItemListener{

	private JComboBox paymentMethods;
	
	private JTextField cardNo;
	private JTextField due;
	private JTextField paid;
	private JTextField change;
	
	private JButton process;
	
	public Payment(double dueAmount){
		setLayout(new GridLayout(6, 2));
		
		paymentMethods = new JComboBox(new String [] {"----", "CASH", "CARD"});
		paymentMethods.addItemListener(this);
		
		cardNo = new JTextField();
		cardNo.setEditable(false);
		due = new JTextField();
		dueAmount = Math.ceil(dueAmount);
		due.setText(dueAmount + "");
		due.setEditable(false);
		paid = new JTextField();
		paid.setEditable(false);
		change = new JTextField();
		change.setEditable(false);
		
		process = new JButton("Process");
		process.addActionListener(this);
		
		add(new JLabel("Payment method"));
		add(paymentMethods);
		add(new JLabel("Card No."));
		add(cardNo);
		add(new JLabel("Due"));
		add(due);
		add(new JLabel("Paid"));
		add(paid);
		add(new JLabel("Change"));
		add(change);
		add(new JLabel());
		add(process);
		
		// initialize frame properties
		setSize(500, 400);
		setTitle("Payment");
		setVisible(true);
		setLocation(150, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == process){ // Process button action
			String method = paymentMethods.getSelectedItem().toString();
			SaleDatabase sb = new SaleDatabase("sales");
			Sale sale = null;
			
			if(method.equals("CASH")){ // CASH payment
				double d = Double.parseDouble(due.getText());
				double p = Double.parseDouble(paid.getText());
				
				if(p < d){ // less amount paid, show error
					JOptionPane.showMessageDialog(null, "Please pay correct amount! Thank you.");
				}
				
				else{ // equal or more amount paid, calculate and display change if applicable
					double c = p - d;
					change.setText(c + "");
					
					if(p == d){ // no change
						JOptionPane.showMessageDialog(null, "Transaction successful. Thank you!");
						dispose();
					}
					
					else{ // change
						JOptionPane.showMessageDialog(null, "<html><h1>Please return change, amount: " + change.getText() + "</h1></html>");
						dispose();
					}
					
					// insert into sales table
					sale = new Sale("", d);
					sb.insert(sale);
				}
			}
			
			else if(method.equals("CARD")){ // CARD payment
				String cNumber = cardNo.getText();
				
				if(cNumber.length() == 16){
					JOptionPane.showMessageDialog(null, "Transaction successful. Thank you!");
					// insert into sales table
					sale = new Sale("", Double.parseDouble(due.getText()));
					sb.insert(sale);
					dispose();
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Invalid Card Number! Please check again.");
				}
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == 1){
			String selectedMethod = e.getItem().toString();
			
			if(selectedMethod.equals("CASH")){ // CASH selected
				cardNo.setEditable(false);
				paid.setEditable(true);
			}
			
			else if(selectedMethod.equals("CARD")){ // CARD selected
				paid.setEditable(false);
				cardNo.setEditable(true);
			}
		}
	}
}