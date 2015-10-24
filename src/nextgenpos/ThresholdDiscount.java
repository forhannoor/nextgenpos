package nextgenpos;

public class ThresholdDiscount implements Discount{

	private double threshold;
	private double discount;
	
	public ThresholdDiscount(double threshold, double discount) {
		this.threshold = threshold;
		this.discount = discount;
	}

	@Override
	public double getNetTotal(Sale s) {
		double d = s.getAmount();
		
		if(d >= threshold){
			d -= discount;
		}
		
		return d;
	}
	
	public double getNetTotal(Sale s, double vat) {
		double temp = getNetTotal(s);
		double d = 0;
		
		if(temp < s.getAmount()){ // discount, vat is applied on each component
			double baseVat = s.getAmount() + s.getAmount() * vat / 100.00;
			double discountedVat = temp + temp * vat / 100.00;
			d = s.getAmount() + baseVat - temp - discountedVat; 
		}
		
		else{ // no discount, vat is applied on base price
			d = s.getAmount() + s.getAmount() * vat / 100.00;
		}
		
		return d;
	}

	@Override
	public double getDiscount(Sale s) {
		return discount;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}