package nextgenpos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class POSWindow extends JFrame implements ActionListener{
	
	private JTable salesLine;
	
	private JTextField barcode;
	private JTextField quantity;
	private JTextField total;
	private JTextField vat;
	private JTextField discount;
	private JTextField netTotal;
	
	private JButton start;
	private JButton end;
	private JButton add;
	private JButton discard;
	
	public POSWindow(){
		setLayout(new GridLayout(2, 1)); // frame layout
		
		JPanel top = new JPanel(); // contains sales line
		top.setLayout(new GridLayout(1, 1));
		JPanel bottom = new JPanel(); // contains 3 more panels
		bottom.setLayout(new GridLayout(1, 3));
		JPanel bottomFirstColumn = new JPanel(); // contains start and end buttons 
		bottomFirstColumn.setLayout(new GridLayout(2, 1));
		JPanel bottomSecondColumn = new JPanel(); // contains fields to add/discard product 
		bottomSecondColumn.setLayout(new GridLayout(4, 2));
		JPanel bottomThirdColumn = new JPanel(); // contains total, vat, discount and netTotal fields
		bottomThirdColumn.setLayout(new GridLayout(3, 2));
		
		// initialize top panel component
		salesLine = new JTable();
		top.add(salesLine);
		
		// initialize first column of bottom panel
		start = new JButton("Start Sale");
		end = new JButton("End Sale");
		bottomFirstColumn.add(start);
		bottomFirstColumn.add(end);
		
		// initialize second column of bottom panel
		
		// initialize third column of bottom panel
		
		// initialize bottom panel components
		bottom.add(bottomFirstColumn);
		bottom.add(bottomSecondColumn);
		bottom.add(bottomThirdColumn);
		
		// add panels to frame
		add(top);
		add(bottom);
		
		// initialize frame properties
		setSize(1000, 600);
		setTitle("NextGenPOS");
		setVisible(true);
		setLocation(150, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
	public static void main(String[] args) {
		new POSWindow();
	}
}