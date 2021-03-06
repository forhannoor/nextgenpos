// Class that implements percentage discount.

package nextgenpos;

public class PercentageDiscount implements Discount{
    private double rate;
    private final double PERCENT = 100.0;
    
    public PercentageDiscount(double rate){
        this.rate = rate / PERCENT;
    }
    
    // Calculates price after discount (if any). 
    @Override
    public double getTotal(double price){
        return (price - (price * rate));
    }

	public double getRate() {
		return rate * PERCENT;
	}

	public void setRate(double rate) {
		this.rate = rate / PERCENT;
	}
}