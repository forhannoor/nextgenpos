package nextgenpos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class POSWindow extends JFrame implements ActionListener{
	
	private JMenuBar menubar;
	private JMenu option;
	private JMenuItem discountItem;
	
	private JTable salesLine;
	
	private JTextField barcode;
	private JTextField total;
	private JTextField vat;
	private JTextField discount;
	private JTextField netTotal;
	
	private JSpinner quantity;
	
	private JButton start;
	private JButton end;
	private JButton add;
	private JButton discard;
	
	public POSWindow(){
		setLayout(new GridLayout(2, 1, 0, 5)); // frame layout
		
		menubar = new JMenuBar();
		option = new JMenu("Option");
		discountItem = new JMenuItem("Discount Strategy");
		discountItem.addActionListener(this);
		option.add(discountItem);
		menubar.add(option);
		setJMenuBar(menubar);
		
		JPanel top = new JPanel(); // contains sales line
		top.setLayout(new GridLayout(1, 1));
		JPanel bottom = new JPanel(); // contains 3 more panels
		bottom.setLayout(new GridLayout(1, 3, 20, 0));
		JPanel bottomFirstColumn = new JPanel(); // contains start and end buttons 
		bottomFirstColumn.setLayout(new GridLayout(2, 1));
		JPanel bottomSecondColumn = new JPanel(); // contains fields to add/discard product 
		bottomSecondColumn.setLayout(new GridLayout(4, 2));
		JPanel bottomThirdColumn = new JPanel(); // contains total, vat, discount and netTotal fields
		bottomThirdColumn.setLayout(new GridLayout(4, 2));
		
		// initialize top panel component
		salesLine = new JTable(200, 5);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		salesLine.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("#");
		salesLine.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(10);
		salesLine.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		salesLine.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Product");
		salesLine.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(360);
		salesLine.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		salesLine.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("Vendor");
		salesLine.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(160);
		salesLine.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		salesLine.getTableHeader().getColumnModel().getColumn(3).setHeaderValue("Quantity");
		salesLine.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(50);
		salesLine.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		salesLine.getTableHeader().getColumnModel().getColumn(4).setHeaderValue("Subtotal");
		salesLine.getTableHeader().getColumnModel().getColumn(4).setPreferredWidth(100);
		salesLine.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		JScrollPane pane = new JScrollPane(salesLine);
		salesLine.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		top.add(pane);
		
		// initialize first column of bottom panel
		start = new JButton("Start Sale");
		start.addActionListener(this);
		end = new JButton("End Sale");
		end.addActionListener(this);
		bottomFirstColumn.add(start);
		bottomFirstColumn.add(end);
		
		// initialize second column of bottom panel
		barcode = new JTextField();
		SpinnerNumberModel model = new SpinnerNumberModel(1.0, 0.5, 100.00, 1.0);  
		quantity = new JSpinner(model);
		add = new JButton("Add");
		add.addActionListener(this);
		discard = new JButton("Discard");
		discard.addActionListener(this);
		bottomSecondColumn.add(new JLabel("Barcode"));
		bottomSecondColumn.add(barcode);
		bottomSecondColumn.add(new JLabel("Quantity"));
		bottomSecondColumn.add(quantity);
		bottomSecondColumn.add(new JLabel());
		bottomSecondColumn.add(add);
		bottomSecondColumn.add(new JLabel());
		bottomSecondColumn.add(discard);
		
		// initialize third column of bottom panel
		total = new JTextField();
		total.setEditable(false);
		vat = new JTextField();
		vat.setEditable(false);
		discount = new JTextField();
		discount.setEditable(false);
		netTotal = new JTextField();
		netTotal.setEditable(false);
		bottomThirdColumn.add(new JLabel("Total"));
		bottomThirdColumn.add(total);
		bottomThirdColumn.add(new JLabel("VAT"));
		bottomThirdColumn.add(vat);
		bottomThirdColumn.add(new JLabel("Discount"));
		bottomThirdColumn.add(discount);
		bottomThirdColumn.add(new JLabel("Net Total"));
		bottomThirdColumn.add(netTotal);
		
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
		if(e.getSource() == discountItem){
			new DiscountWindow();
		}
	}
	
	public static void main(String[] args) {
		new POSWindow();
	}
}