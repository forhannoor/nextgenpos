package nextgenpos;

import java.awt.Font;
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
	
	private ProductDatabase productDatabase;
	private SaleConduct saleConduct;
	
	private int salesLineRowCount;
	
	public POSWindow(){
		saleConduct = new SaleConduct(); // object that contains product and saleslineitem list
		saleConduct.setVat(5);
		productDatabase = new ProductDatabase("products");
		salesLineRowCount = 0;
		
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
		total.setFont(new Font("SansSerif", Font.BOLD, 24));
		total.setHorizontalAlignment(JTextField.RIGHT);
		vat = new JTextField();
		vat.setEditable(false);
		vat.setFont(new Font("SansSerif", Font.BOLD, 24));
		vat.setHorizontalAlignment(JTextField.RIGHT);
		discount = new JTextField();
		discount.setEditable(false);
		discount.setFont(new Font("SansSerif", Font.BOLD, 24));
		discount.setHorizontalAlignment(JTextField.RIGHT);
		netTotal = new JTextField();
		netTotal.setEditable(false);
		netTotal.setFont(new Font("SansSerif", Font.BOLD, 24));
		netTotal.setHorizontalAlignment(JTextField.RIGHT);
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
		barcode.requestFocusInWindow(); // focus on barcode field once the window launches
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == discountItem){ // Discount menu item action
			new DiscountWindow();
		}
		
		else if(e.getSource() == start){ // START button action
			saleConduct.clearSalesLineItems(); // clear saleslineitems
			saleConduct.setStrategy(DiscountWindow.getStrategy()); // get discount strategy
			setSalesLineRowCount(0);
			total.setText("0.0");
			vat.setText("0.0");
			discount.setText("0.0");
			netTotal.setText("0.0");
		}
		
		else if(e.getSource() == add){ // ADD button action
			if(barcode.getText().length() > 0){ // barcode field is not empty
				Product temp = productDatabase.getWhere("barcode", barcode.getText());
				
				if(temp != null){ // barcode matches with product in database
					// add product to saleslineitem list
					SalesLineItem sli = new SalesLineItem(temp, Double.parseDouble(quantity.getValue().toString()));
					saleConduct.addSalesLineItem(sli);
					
					// display saleslineitem to salesline
					salesLine.setValueAt(salesLineRowCount + 1 + "", salesLineRowCount, 0); // #
					salesLine.setValueAt(sli.getProduct().getName(), salesLineRowCount, 1); // name
					salesLine.setValueAt(sli.getProduct().getVendorId(), salesLineRowCount, 2); // vendor
					salesLine.setValueAt(sli.getQuantity(), salesLineRowCount, 3); // quantity
					salesLine.setValueAt(sli.getSubTotal(), salesLineRowCount, 4); // subtotal
					salesLineRowCount += 1;
					
					// update total
					double t = Double.parseDouble(total.getText()); // previous total
					t += sli.getSubTotal(); // current total
					total.setText(t + "");
					
					// update discount
					double d = saleConduct.getStrategy().getDiscount(new Sale(t));
					discount.setText(d + "");
				}
				
				else{ // barcode doesn't match with product in database
					JOptionPane.showMessageDialog(null, "Invalid barcode! Such product does not exist on database! Please scan again!");
				}
				
				barcode.setText(""); // clear barcode field
				barcode.requestFocusInWindow(); // set focus on barcode field
				quantity.setValue(1.0); // reset quantity field
			}
		}
	}
	
	public void setSaleConduct(SaleConduct saleConduct){
		this.saleConduct = saleConduct;
	}
	
	public SaleConduct getSaleConduct(){
		return saleConduct;
	}
	
	public int getSalesLineRowCount() {
		return salesLineRowCount;
	}

	public void setSalesLineRowCount(int salesLineRowCount) {
		this.salesLineRowCount = salesLineRowCount;
	}

	public static void main(String[] args) {
		new POSWindow();
	}
}