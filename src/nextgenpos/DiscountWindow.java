// GUI for configuring discount parameters.

package nextgenpos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;

enum DiscountType{
	None,
	Percentage,
	Threshold
}

public class DiscountWindow extends JFrame implements ActionListener, ItemListener{
	private JComboBox<DiscountType> discountComboBox;
    private JSpinner percentageSpinner;
	private JSpinner thresholdSpinner;
	private JSpinner discountSpinner;
	private JButton updateButton;
	
	private final int SIZE = 300;
	private final int LOCATION = 100;
	private final String UPDATE = "Update";
	private final String RATE = "Rate";
	private final String THRESHOLD = "Threshold";
	private final String DISCOUNT = "Discount";
	private final String TITLE = "Discount Method";
	private final String BLANK = "";
	
	public DiscountWindow(){
		setLayout(new GridLayout(10, 2, 5, 5));
		discountComboBox = new JComboBox<DiscountType>(DiscountType.values());
		discountComboBox.addItemListener(this);
		percentageSpinner = new JSpinner();
		thresholdSpinner = new JSpinner();
		discountSpinner = new JSpinner();
		updateButton = new JButton(UPDATE);
		updateButton.addActionListener(this);
		add(new JLabel(BLANK));
		add(discountComboBox);
		add(new JLabel(RATE));
		add(percentageSpinner);
		add(new JLabel(THRESHOLD));
		add(thresholdSpinner);
		add(new JLabel(DISCOUNT));
		add(discountSpinner);
		add(new JLabel(BLANK));
		add(updateButton);
		setSize(SIZE, SIZE);
		setTitle(TITLE);
		setVisible(true);
		setLocation(LOCATION, LOCATION);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent actionEvent) {
		// "Update" is clicked.
		if(actionEvent.getSource() == updateButton){
			var discountType = (DiscountType) discountComboBox.getSelectedItem();
			Discount discount = null;
			
			// If percentage discount is selected.
			if(discountType == DiscountType.Percentage){
				double rate = Double.parseDouble(percentageSpinner.getValue().toString());
				discount = new PercentageDiscount(rate);
			}
			
			// If threshold discount is selected.
			else if(discountType == DiscountType.Threshold){
				double thresholdAmount = Double.parseDouble(thresholdSpinner.getValue().toString());
				double discountAmount = Double.parseDouble(discountSpinner.getValue().toString());
				discount = new ThresholdDiscount(thresholdAmount, discountAmount);
			}
			
			// Update discount method.
			setStrategy(discount);
			dispose();
		}
	}
	
	// Updates discount strategy.
	public void setStrategy(Discount discount){
		try {
			var fileWriter = new FileWriter("asset/data/discount.config", false);
			
			if(discount == null){
				fileWriter.write("none");
			}
			
			else if(discount instanceof PercentageDiscount){
				var temp = (PercentageDiscount) discount;
				fileWriter.write("percentage\n");
				fileWriter.write(temp.getRate() + "");
			}
			
			else if(discount instanceof ThresholdDiscount){
				var temp = (ThresholdDiscount) discount;
				fileWriter.write("threshold\n");
				fileWriter.write(temp.getThreshold() + "\n" + temp.getDiscount());
			}
			
			fileWriter.close();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	public static Discount getStrategy(){
		Discount discount = null;
		
		try {
			var scanner = new Scanner(new File("asset/data/discount.config"));
			String content = scanner.nextLine();
			
			if(content.equals("percentage")){
				var rate = Double.parseDouble(scanner.nextLine());
				discount = new PercentageDiscount(rate);
			}
			
			else if(content.equals("threshold")){
				var t = Double.parseDouble(scanner.nextLine());
				var dis = Double.parseDouble(scanner.nextLine());
				discount = new ThresholdDiscount(t, dis);
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		
		return discount;
	}

	@Override
	public void itemStateChanged(ItemEvent itemEvent) {
		// If discount strategy is changed.
		if(itemEvent.getStateChange() == 1){
			// Discount type.
			var discountType = (DiscountType) discountComboBox.getSelectedItem();
			// Enable/disable fields based on discount type.
			percentageSpinner.setEnabled((discountType == DiscountType.Percentage));
			thresholdSpinner.setEnabled((discountType == DiscountType.Threshold));
			discountSpinner.setEnabled((discountType == DiscountType.Threshold));
		}
	}
}