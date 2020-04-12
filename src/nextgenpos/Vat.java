// Handles VAT.

package nextgenpos;

public class Vat {
	private double rate;
	private final double PERCENT = 100.0;

	public Vat(double rate) {
		this.rate = rate / PERCENT;
	}

	public double getRate() {
		return rate * PERCENT;
	}

	public void setRate(double rate) {
		this.rate = rate / PERCENT;
	}
	
	public double calculateVat(double totalPrice){
		return totalPrice * rate;
	}
}