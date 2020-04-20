// GUI for payment processing.

package nextgenpos;

//import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

enum PaymentMethod{
	None,
	Cash,
	Card
}

public class PaymentWindow extends JFrame implements ActionListener, ItemListener{
	private JComboBox<PaymentMethod> paymentMethodsComboBox;
	private JTextField cardNoField;
	private JTextField dueField;
	private JTextField paidField;
	private JTextField changeField;
	private JButton processButton;
	
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	private final int LOCATION = 100;
	private final int VALID_CARD_LENGTH = 16;
	private final String BLANK = "";
	private final String CARD_NO = "Card No.";
	private final String DUE = "Due";
	private final String PAID = "Paid";
	private final String CHANGE = "Change";
	private final String TITLE = "Payment Processing";
	private final String PROCESS = "Process";
	private final String INVALID_AMOUNT_MESSAGE = "Please pay correct amount!";
	private final String TRANSACTION_SUCCESS_MESSAGE = "Transaction successful!";
	private final String CHANGE_MESSAGE = "Change to return: ";
	private final String INVALID_CARD_MESSAGE = "Invalid Card Number!";
	
	public PaymentWindow(double dueAmount){
		setLayout(new GridLayout(6, 2));
		paymentMethodsComboBox = new JComboBox<PaymentMethod>(PaymentMethod.values());
		paymentMethodsComboBox.addItemListener(this);
		cardNoField = new JTextField();
		cardNoField.setEditable(false);
		dueField = new JTextField();
		dueAmount = Math.ceil(dueAmount);
		dueField.setText(dueAmount + "");
		dueField.setEditable(false);
		paidField = new JTextField();
		paidField.setEditable(false);
		changeField = new JTextField();
		changeField.setEditable(false);
		processButton = new JButton(PROCESS);
		processButton.addActionListener(this);
		add(new JLabel(BLANK));
		add(paymentMethodsComboBox);
		add(new JLabel(CARD_NO));
		add(cardNoField);
		add(new JLabel(DUE));
		add(dueField);
		add(new JLabel(PAID));
		add(paidField);
		add(new JLabel(CHANGE));
		add(changeField);
		add(new JLabel(BLANK));
		add(processButton);
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setVisible(true);
		setLocation(LOCATION, LOCATION);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// "Process" is clicked.
		if(actionEvent.getSource() == processButton){
			// Determine payment method.
			var paymentMethod = (PaymentMethod) paymentMethodsComboBox.getSelectedItem();
			var saleDatabase = new SaleDatabase();
			var due = Double.parseDouble(dueField.getText());
			
			// Cash payment.
			if(paymentMethod == PaymentMethod.Cash){
				var paid = Double.parseDouble(paidField.getText());
				
				// If paid amount is less than due.
				if(paid < due){
					JOptionPane.showMessageDialog(null, INVALID_AMOUNT_MESSAGE);
				}
				
				else{
					double change = paid - due;
					changeField.setText(change + BLANK);
					JOptionPane.showMessageDialog(null, CHANGE_MESSAGE + changeField.getText());
					// Insert sale record into database.
					var sale = new Sale(BLANK, due);
					saleDatabase.insert(sale);
					dispose();
				}
			}
			
			// Card payment.
			else if(paymentMethod == PaymentMethod.Card){
				var cardNo = cardNoField.getText();
				
				// If card no is valid.
				if(cardNo.length() == VALID_CARD_LENGTH){
					JOptionPane.showMessageDialog(null, TRANSACTION_SUCCESS_MESSAGE);
					// Insert sale record into database.
					var sale = new Sale(BLANK, due);
					saleDatabase.insert(sale);
					dispose();
				}
				
				// If card no is invalid.
				else{
					JOptionPane.showMessageDialog(null, INVALID_CARD_MESSAGE);
				}
			}
		}
	}

	// Invoked when payment method is selected.
	@Override
	public void itemStateChanged(ItemEvent itemEvent) {
		if(itemEvent.getStateChange() == 1){
			// Determine payment method.
			var paymentMethod = (PaymentMethod) itemEvent.getItem();
			System.out.println(paymentMethod);
			boolean isCashPayment = (paymentMethod == PaymentMethod.Cash) ? true : false;
			cardNoField.setEditable(!isCashPayment);
			paidField.setEditable(isCashPayment);
		}
	}
}