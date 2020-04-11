// Handles VAT.

package nextgenpos;

public class Vat {

	private double rate;

	public Vat(double rate) {
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public double calculateVat(double amount){
		return amount * rate / 100.00;
	}
}