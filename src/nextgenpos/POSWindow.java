// Program that provides GUI for all operations.

package nextgenpos;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumn;

public class POSWindow extends JFrame implements ActionListener{
	private JMenuBar menubar;
	private JMenu discountOption;
	private JMenuItem updateDiscountStrategy;
	private JTable salesLine;
	private JTextField barcode;
	private JTextField total;
	private JTextField vat;
	private JTextField discount;
	private JTextField netTotal;
	private JSpinner quantity;
	private JToggleButton saleButton;
	private JButton add;
	private JButton discard;
	private ProductDatabase productDatabase;
	private SaleConduct saleConduct;
	private int salesLineRowCount;
	
	private final int WIDTH = 1024;
	private final int HEIGHT = 768;
	private final int INI_LOCATION = 100;
	private final int NUM_COLUMNS = 5;
	private final int [] CELL_WIDTHS = {10, 360, 160, 50, 100};
	private final String FRAME_TITLE = "NextgenPOSv3.0";
	private final String START_SALE = "Start Sale";
	private final String END_SALE = "End Sale";
	private final String DISCOUNT_OPTION = "Discount";
	private final String UPDATE_DISCOUNT_OPTION = "Update Discount";
	private final String ADD = "Add";
	private final String DISCARD = "Discard";
	private final String BARCODE = "Barcode";
	private final String QTY = "Qty";
	private final String BLANK = "";
	private final String TOTAL = "Total";
	private final String VAT = "VAT";
	private final String NET = "Net";
	private final String [] LABELS = new String [] {"#", "Product", "Vendor", "Qty", "Subtotal"};
	
	public POSWindow(){
		setLayout(new GridLayout(2, 1, 0, 5));
		saleConduct = new SaleConduct();
		productDatabase = new ProductDatabase();
		salesLineRowCount = 0;
		menubar = new JMenuBar();
		discountOption = new JMenu(DISCOUNT_OPTION);
		updateDiscountStrategy = new JMenuItem(UPDATE_DISCOUNT_OPTION);
		updateDiscountStrategy.addActionListener(this);
		discountOption.add(updateDiscountStrategy);
		menubar.add(discountOption);
		setJMenuBar(menubar);
		var topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 1));
		var bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 3, 20, 0));
		var bottomFirstColumn = new JPanel(); 
		bottomFirstColumn.setLayout(new GridLayout(2, 1));
		var bottomSecondColumn = new JPanel(); 
		bottomSecondColumn.setLayout(new GridLayout(4, 2));
		var bottomThirdColumn = new JPanel();
		bottomThirdColumn.setLayout(new GridLayout(4, 2));
		salesLine = new JTable(200, 5);
		var cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		var columnModel = salesLine.getTableHeader().getColumnModel();
		
		// Set table parameters.
		for(int i = 0; i < NUM_COLUMNS; ++i) {
			var column = columnModel.getColumn(i);
			column.setHeaderValue(LABELS[i]);
			column.setPreferredWidth(CELL_WIDTHS[i]);
			column.setCellRenderer(cellRenderer);
		}
		
		var scrollPane = new JScrollPane(salesLine);
		salesLine.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		topPanel.add(scrollPane);
		saleButton = new JToggleButton(START_SALE);
		saleButton.addActionListener(this);
		bottomFirstColumn.add(saleButton);
		barcode = new JTextField();
		var model = new SpinnerNumberModel(1.0, 0.5, 10000.00, 0.5);  
		quantity = new JSpinner(model);
		add = new JButton(ADD);
		add.addActionListener(this);
		discard = new JButton(DISCARD);
		discard.addActionListener(this);
		var tempLabel = new JLabel(BARCODE);
		bottomSecondColumn.add(tempLabel);
		bottomSecondColumn.add(barcode);
		tempLabel = new JLabel(QTY);
		bottomSecondColumn.add(tempLabel);
		bottomSecondColumn.add(quantity);
		tempLabel = new JLabel(BLANK);
		bottomSecondColumn.add(tempLabel);
		bottomSecondColumn.add(add);
		bottomSecondColumn.add(new JLabel());
		bottomSecondColumn.add(discard);
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
		tempLabel = new JLabel(TOTAL);
		bottomThirdColumn.add(tempLabel);
		bottomThirdColumn.add(total);
		tempLabel = new JLabel(VAT);
		bottomThirdColumn.add(tempLabel);
		bottomThirdColumn.add(vat);
		tempLabel = new JLabel(DISCOUNT_OPTION);
		bottomThirdColumn.add(tempLabel);
		bottomThirdColumn.add(discount);
		tempLabel = new JLabel(NET);
		bottomThirdColumn.add(tempLabel);
		bottomThirdColumn.add(netTotal);
		bottomPanel.add(bottomFirstColumn);
		bottomPanel.add(bottomSecondColumn);
		bottomPanel.add(bottomThirdColumn);
		add(topPanel);
		add(bottomPanel);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setTitle(FRAME_TITLE);
		setVisible(true);
		setLocation(INI_LOCATION, INI_LOCATION);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		barcode.requestFocusInWindow();
	}
	
	public void actionPerformed(ActionEvent actionEvent){
		// "Discount strategy" is clicked.
		if(actionEvent.getSource() == updateDiscountStrategy){
			new DiscountWindow();
		}
		
		// "Start Sale" / "End Sale" is clicked.
		else if(actionEvent.getSource() == saleButton){
			var isSelected = saleButton.isSelected();
			var saleButtonLabel = isSelected ? END_SALE : START_SALE;
			saleButton.setText(saleButtonLabel);
			
			// If "Start Sale".
			if(isSelected){
				// Clear sales line items.
				saleConduct.clearSalesLineItems();
				// Update discount strategy.
				saleConduct.setStrategy(DiscountWindow.getStrategy());
			}
			
			// If "End Sale".
			else{
				new Payment(Double.parseDouble(netTotal.getText()));
			}
			
			// Clear fields.
			resetFields();
		}
		
		else if(actionEvent.getSource() == add){ // ADD button action
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
					Discount dis = saleConduct.getStrategy();
					double d = t; // if no discount is applicable
					
					if(dis != null){ // if discount is applicable
						d = dis.getDiscount(t);
					}
					
					discount.setText(d + "");
					
					// update vat
					double basePrice = t;
					double discountedPrice = d;
					Vat v = new Vat(10);
					double baseVat = v.calculateVat(basePrice);
					double discountedVat = v.calculateVat(discountedPrice);
					double netVat = baseVat - discountedVat;
					vat.setText(netVat + "");
					
					// update net total
					double net = t + netVat - d;
					netTotal.setText(net + "");
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
	
	public void resetFields(){
		total.setText("0.0");
		vat.setText("0.0");
		discount.setText("0.0");
		netTotal.setText("0.0");
		
		// clear sales line
		for (int i = 0; i < getSalesLineRowCount(); i++){
		    for(int j = 0; j < 5; j++) {
		        salesLine.setValueAt("", i, j);
		    }
		}
		
		setSalesLineRowCount(0);
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
}