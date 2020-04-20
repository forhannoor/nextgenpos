// GUI for updating discount parameters.

package nextgenpos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;


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
	private Discount discountStrategy;
	
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
			discountStrategy = null;
			
			// If percentage discount is selected.
			if(discountType == DiscountType.Percentage){
				var rate = Double.parseDouble(percentageSpinner.getValue().toString());
				discountStrategy = new PercentageDiscount(rate);
			}
			
			// If threshold discount is selected.
			else if(discountType == DiscountType.Threshold){
				var thresholdAmount = Double.parseDouble(thresholdSpinner.getValue().toString());
				var discountAmount = Double.parseDouble(discountSpinner.getValue().toString());
				discountStrategy = new ThresholdDiscount(thresholdAmount, discountAmount);
			}
			
			dispose();
		}
	}
	
	public Discount getDiscountStrategy() {
		return discountStrategy;
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