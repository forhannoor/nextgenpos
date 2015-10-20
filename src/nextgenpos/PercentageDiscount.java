package nextgenpos;

public class PercentageDiscount implements Discount{
    private double rate;
    
    public PercentageDiscount(double rate){
        this.rate = rate;
    }
    
    public double getNetTotal(Sale s){
        return s.getAmount() - getDiscount(s);
    }
    
    public double getDiscount(Sale s){
    	return (rate * s.getAmount()) / 100.0;
    }

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}