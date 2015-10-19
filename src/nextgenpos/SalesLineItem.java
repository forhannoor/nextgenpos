package nextgenpos;

public class SalesLineItem {
    private Product p;
    private int quantity;
    private double subTotal;
	
    public SalesLineItem(Product p, int quantity) {
		this.p = p;
		this.quantity = quantity;
		this.subTotal = p.getPrice() * quantity;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}