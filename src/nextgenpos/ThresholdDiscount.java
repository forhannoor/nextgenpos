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