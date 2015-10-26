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
		
		paymentMethods = new JComboBox(new String [] {"----", "CASH", "Card"});
		paymentMethods.addItemListener(this);
		
		cardNo = new JTextField();
		cardNo.setEditable(false);
		due = new JTextField();
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
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == 1){
			String selectedMethod = e.getItem().toString();
			
			if(selectedMethod.equals("")){ // CASH selected
				
			}
		}
	}
}