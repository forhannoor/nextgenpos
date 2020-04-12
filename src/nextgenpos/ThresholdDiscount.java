// Implements threshold discount.

package nextgenpos;

public class ThresholdDiscount implements Discount{
	private double threshold;
	private double discount;
	
	public ThresholdDiscount(double threshold, double discount) {
		this.threshold = threshold;
		this.discount = discount;
	}

	// Calculates price after discount (if any).
	@Override
	public double getTotal(double price) {
		if(price >= threshold) {
			price -= discount;
		}
		
		return price;
	}

	// Calculates amount of discount (if any).
	@Override
	public double getDiscount(double price) {
		return (price >= threshold) ? discount : 0;
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