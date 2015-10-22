package nextgenpos;

public class SalesLineItem {
    private Product product;
    private double quantity;
    private double subTotal;
	
    public SalesLineItem(Product p, double quantity) {
		this.product = p;
		this.quantity = quantity;
		this.subTotal = p.getPrice() * quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product p) {
		this.product = p;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}