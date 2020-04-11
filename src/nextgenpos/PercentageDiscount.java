// Class that implements percentage discount.

package nextgenpos;

public class PercentageDiscount implements Discount{
    private double rate;
    
    public PercentageDiscount(double rate){
        this.rate = rate;
    }
    
    public double getTotal(double s){
        return s - getDiscount(s);
    }
    
    public double getDiscount(double s){
    	return (rate * s) / 100.0;
    }

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}