// GUI for main program.

package nextgenpos;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumn;

public class POSWindow extends JFrame implements ActionListener{
	private JMenuBar menubar;
	private JMenu discountMenu;
	private JMenuItem updateDiscountStrategyItem;
	private JMenuItem updateVatItem;
	private JTable salesLineTable;
	private JTextField barcodeField;
	private JTextField totalField;
	private JTextField vatField;
	private JTextField discountField;
	private JTextField netTotalField;
	private JSpinner quantitySpinner;
	private JToggleButton saleToggleButton;
	private JButton addButton;
	private JButton discardButton;
	private ProductDatabase productDatabase;
	private SaleConduct saleConduct;
	private Vat vat;
	private DiscountWindow discountWindow;
	private int salesLineRowCount;
	private double priceWithoutDiscount;
	private double priceWithDiscount;
	private double discountAmount;
	private double vatAmount;
	private double netAmount;
	private double total;
	
	private final int WIDTH = 1024;
	private final int HEIGHT = 768;
	private final int INI_LOCATION = 100;
	private final int NUM_COLUMNS = 5;
	private final int FONT_SIZE = 24;
	private final int VAT_RATE = 5;
	private final int ZERO = 0;
	private final int [] CELL_WIDTHS = {10, 360, 160, 50, 100};
	private final String FRAME_TITLE = "NextgenPOSv3.0";
	private final String START_SALE = "Start Sale";
	private final String END_SALE = "End Sale";
	private final String OPTION = "Options";
	private final String DISCOUNT = "Discount";
	private final String UPDATE_DISCOUNT_OPTION = "Update Discount";
	private final String UPDATE_VAT = "Update VAT";
	private final String ADD = "Add";
	private final String DISCARD = "Discard";
	private final String BARCODE = "Barcode";
	private final String QTY = "Qty";
	private final String BLANK = "";
	private final String TOTAL = "Total";
	private final String VAT = "VAT";
	private final String NET = "Net";
	private final String FONT_NAME = "SansSerif";
	private final String INVALID_BARCODE = "Invalid barcode! Please scan again!";
	private final String [] LABELS = new String [] {"#", "Product", "Vendor", "Qty", "Subtotal"};
	
	public POSWindow(){
		setLayout(new GridLayout(2, 1, 0, 5));
		saleConduct = new SaleConduct();
		productDatabase = new ProductDatabase();
		vat = new Vat(VAT_RATE);
		salesLineRowCount = 0;
		menubar = new JMenuBar();
		discountMenu = new JMenu(OPTION);
		updateDiscountStrategyItem = new JMenuItem(UPDATE_DISCOUNT_OPTION);
		discountWindow = new DiscountWindow();
		updateDiscountStrategyItem.addActionListener(e -> { discountWindow.showDialog(); });
		discountMenu.add(updateDiscountStrategyItem);
		updateVatItem = new JMenuItem(UPDATE_VAT);
		updateVatItem.addActionListener(e -> {
			// Get VAT rate from user.
			var vatRate = Double.parseDouble(JOptionPane.showInputDialog(VAT, vat.getRate()));
			// Update vat rate.
			vat.setRate(vatRate);
		});
		discountMenu.add(updateVatItem);
		menubar.add(discountMenu);
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
		salesLineTable = new JTable(200, 5);
		var cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		var columnModel = salesLineTable.getTableHeader().getColumnModel();
		
		// Set table parameters.
		for(int i = 0; i < NUM_COLUMNS; ++i) {
			var column = columnModel.getColumn(i);
			column.setHeaderValue(LABELS[i]);
			column.setPreferredWidth(CELL_WIDTHS[i]);
			column.setCellRenderer(cellRenderer);
		}
		
		var scrollPane = new JScrollPane(salesLineTable);
		salesLineTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		topPanel.add(scrollPane);
		saleToggleButton = new JToggleButton(START_SALE);
		saleToggleButton.addActionListener(this);
		bottomFirstColumn.add(saleToggleButton);
		barcodeField = new JTextField();
		var model = new SpinnerNumberModel(1.0, 0.5, 10000.00, 0.5);  
		quantitySpinner = new JSpinner(model);
		addButton = new JButton(ADD);
		addButton.addActionListener(this);
		discardButton = new JButton(DISCARD);
		discardButton.addActionListener(this);
		var tempLabel = new JLabel(BARCODE);
		bottomSecondColumn.add(tempLabel);
		bottomSecondColumn.add(barcodeField);
		tempLabel = new JLabel(QTY);
		bottomSecondColumn.add(tempLabel);
		bottomSecondColumn.add(quantitySpinner);
		tempLabel = new JLabel(BLANK);
		bottomSecondColumn.add(tempLabel);
		bottomSecondColumn.add(addButton);
		bottomSecondColumn.add(new JLabel());
		bottomSecondColumn.add(discardButton);
		totalField = new JTextField();
		totalField.setEditable(false);
		var font = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
		totalField.setFont(font);
		totalField.setHorizontalAlignment(JTextField.RIGHT);
		vatField = new JTextField();
		vatField.setEditable(false);
		vatField.setFont(font);
		vatField.setHorizontalAlignment(JTextField.RIGHT);
		discountField = new JTextField();
		discountField.setEditable(false);
		discountField.setFont(font);
		discountField.setHorizontalAlignment(JTextField.RIGHT);
		netTotalField = new JTextField();
		netTotalField.setEditable(false);
		netTotalField.setFont(font);
		netTotalField.setHorizontalAlignment(JTextField.RIGHT);
		tempLabel = new JLabel(TOTAL);
		bottomThirdColumn.add(tempLabel);
		bottomThirdColumn.add(totalField);
		tempLabel = new JLabel(VAT);
		bottomThirdColumn.add(tempLabel);
		bottomThirdColumn.add(vatField);
		tempLabel = new JLabel(DISCOUNT);
		bottomThirdColumn.add(tempLabel);
		bottomThirdColumn.add(discountField);
		tempLabel = new JLabel(NET);
		bottomThirdColumn.add(tempLabel);
		bottomThirdColumn.add(netTotalField);
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
		barcodeField.requestFocusInWindow();
	}
	
	public void actionPerformed(ActionEvent actionEvent){
		// "Start Sale" / "End Sale" is clicked.
		if(actionEvent.getSource() == saleToggleButton){
			var isSelected = saleToggleButton.isSelected();
			
			// If starting sale.
			if(isSelected){
				// Update button label.
				saleToggleButton.setText(END_SALE);
				// Clear sales line items.
				saleConduct.clearSalesLineItems();
				// Update discount strategy.
				saleConduct.setStrategy(discountWindow.getDiscountStrategy());
			}
			
			// If ending sale.
			else{
				// Update button label.
				saleToggleButton.setText(START_SALE);
				new PaymentWindow(Double.parseDouble(netTotalField.getText()));
			}
			
			// Clear fields.
			resetFields();
		}
		
		// "Add" is clicked.
		else if(actionEvent.getSource() == addButton){
			// If barcode is not empty.
			if(barcodeField.getText().length() > 0){
				Product product = productDatabase.getWhere("barcode", barcodeField.getText());
				
				// If matching record is found.
				if(product != null){
					// Add product to saleslineitem list.
					var salesLineItem = new SalesLineItem(product, Double.parseDouble(quantitySpinner.getValue()
							.toString()));
					saleConduct.addSalesLineItem(salesLineItem);
					// Display saleslineitem.
					salesLineTable.setValueAt(salesLineRowCount + 1 + BLANK, salesLineRowCount, 0);
					salesLineTable.setValueAt(salesLineItem.getProduct().getName(), salesLineRowCount, 1);
					salesLineTable.setValueAt(salesLineItem.getProduct().getVendorId(), salesLineRowCount, 2);
					salesLineTable.setValueAt(salesLineItem.getQuantity(), salesLineRowCount, 3);
					salesLineTable.setValueAt(salesLineItem.getSubTotal(), salesLineRowCount, 4);
					++salesLineRowCount;
					// Total price without discount.
					total += salesLineItem.getSubTotal();
					priceWithoutDiscount = total;
					totalField.setText(priceWithoutDiscount + BLANK);
					// Get discount type.
					Discount discount = saleConduct.getStrategy();
					// Total price with discount.
					priceWithDiscount = priceWithoutDiscount;
					
					// If discount is set.
					if(discount != null){
						priceWithDiscount = discount.getTotal(priceWithoutDiscount);
					}
					
					// Discount amount.
					discountAmount = priceWithoutDiscount - priceWithDiscount;
					discountField.setText(discountAmount + BLANK);
					// Calculate VAT.
					vatAmount = vat.calculateVat(priceWithDiscount);
					vatField.setText(vatAmount + BLANK);
					netAmount = priceWithDiscount + vatAmount;
					netTotalField.setText(netAmount + BLANK);
				}
				
				// If no matching record is found.
				else{
					JOptionPane.showMessageDialog(null, INVALID_BARCODE);
				}
				
				// Reset barcode field.
				barcodeField.setText(BLANK);
				 // Set focus on barcode field.
				barcodeField.requestFocusInWindow();
				// Reset qty spinner.
				quantitySpinner.setValue(1.0);
			}
		}
	}
	
	// Clears textfields associated with sale.
	public void resetFields(){
		var clearText = ZERO + BLANK;
		total = 0;
		totalField.setText(clearText);
		vatField.setText(clearText);
		discountField.setText(clearText);
		netTotalField.setText(clearText);
		
		// Clear sales line.
		for (int i = 0; i < salesLineRowCount; ++i){
		    for(int j = 0; j < NUM_COLUMNS; ++j) {
		        salesLineTable.setValueAt(BLANK, i, j);
		    }
		}
		
		salesLineRowCount = ZERO;
	}
}