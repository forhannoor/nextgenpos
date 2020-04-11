// GUI for configuring discount parameters.

package nextgenpos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;

public class DiscountWindow extends JFrame implements ActionListener{
    
	private JRadioButton noStrategy;
	private JRadioButton percentageStrategy;
	private JRadioButton thresholdStrategy;
	
	private JSpinner percentage;
	private JSpinner threshold;
	private JSpinner discount;
	
	private JButton set;
	
	public DiscountWindow(){
		
		setLayout(new GridLayout(10, 2, 5, 5));
		
		noStrategy = new JRadioButton("No discount");
		percentageStrategy = new JRadioButton("Percentage discount");
		thresholdStrategy = new JRadioButton("Threshold discount");
		ButtonGroup bg = new ButtonGroup();
		bg.add(noStrategy);
		bg.add(percentageStrategy);
		bg.add(thresholdStrategy);
		
		SpinnerModel percentageModel = new SpinnerNumberModel(0, 0, 100, 0.5);
		SpinnerModel thresholdModel = new SpinnerNumberModel(0, 0, 1000000, 0.5);
		SpinnerModel discountModel = new SpinnerNumberModel(0, 0, 1000000, 0.5);
		
		percentage = new JSpinner(percentageModel);
		threshold = new JSpinner(thresholdModel);
		discount = new JSpinner(discountModel);
		
		set = new JButton("Set");
		set.addActionListener(this);
		
		add(noStrategy);
		add(percentageStrategy);
		add(new JLabel("Rate"));
		add(percentage);
		add(thresholdStrategy);
		add(new JLabel("Threshold"));
		add(threshold);
		add(new JLabel("Discount"));
		add(discount);
		add(set);
		
		setSize(250, 400);
		setTitle("Discount Window");
		setVisible(true);
		setLocation(400, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == set){
			Discount d = null;
			
			if(percentageStrategy.isSelected()){
				double rate = Double.parseDouble(percentage.getValue().toString());
				d = new PercentageDiscount(rate);
			}
			
			else if(thresholdStrategy.isSelected()){
				double t = Double.parseDouble(threshold.getValue().toString());
				double dis = Double.parseDouble(discount.getValue().toString());
				d = new ThresholdDiscount(t, dis);
			}
			
			setStrategy(d);
			dispose();
		}
	}
	
	public void setStrategy(Discount d){
		try {
			FileWriter f = new FileWriter("asset/data/discount.config", false);
			
			if(d == null){
				f.write("none");
			}
			
			else if(d instanceof PercentageDiscount){
				PercentageDiscount temp = (PercentageDiscount) d;
				f.write("percentage\n");
				f.write(temp.getRate() + "");
			}
			
			else if(d instanceof ThresholdDiscount){
				ThresholdDiscount temp = (ThresholdDiscount) d;
				f.write("threshold\n");
				f.write(temp.getThreshold() + "\n" + temp.getDiscount());
			}
			
			f.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Discount getStrategy(){
		Discount d = null;
		
		try {
			Scanner s = new Scanner(new File("asset/data/discount.config"));
			String content = s.nextLine();
			
			if(content.equals("percentage")){
				double rate = Double.parseDouble(s.nextLine());
				d = new PercentageDiscount(rate);
			}
			
			else if(content.equals("threshold")){
				double t = Double.parseDouble(s.nextLine());
				double dis = Double.parseDouble(s.nextLine());
				d = new ThresholdDiscount(t, dis);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return d;
	}
}