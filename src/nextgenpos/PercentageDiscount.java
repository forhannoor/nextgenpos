package nextgenpos;

public class PercentageDiscount implements Discount{
    private double rate;
    
    public PercentageDiscount(double rate){
        this.rate = rate;
    }
    
    public double getNetTotal(Sale s){
        return s.getAmount() - getDiscount(s);
    }
    
    public double getNetTotal(Sale s, double vat){
    	Sale temp = s;
    	temp.setAmount(s.getAmount() - getDiscount(s));
    	double baseVat = s.getAmount() * vat / 100.00;
    	double discountedVat = temp.getAmount() * vat / 100.00;
        return s.getAmount() + baseVat - temp.getAmount() - discountedVat;
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